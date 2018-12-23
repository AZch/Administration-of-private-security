package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Scripts.Update;
import sample.Tables.*;
import sample.MessagesForm.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DutyUIController {

    public Label FIO;
    public Label RankPO;
    public Label msg;
    public Button ExitBtn;

    //Поиск
    public DatePicker dateStartCreateSuppose;
    public DatePicker dateEndCreateSuppose;
    public CheckBox isSearchDateStartRequest;
    public CheckBox isSearchDateEndRequest;
    public Button FindBtn;

    //Поля таблицы Объектов охраны
    private ObservableList<ObjectsSecurity> ObjectsSecurityData = FXCollections.observableArrayList();
    public TableView<ObjectsSecurity> OSecurityTable;
    public TableColumn<ObjectsSecurity, String> OSecurityC;

    //Поля таблицы объектов охраны
    private ObservableList<ObjectOfProtect> ObjectOfProtectData = FXCollections.observableArrayList();
    public TableView<ObjectOfProtect> TableObjects;
    public TableColumn<ObjectOfProtect, String> addressC;
    public TableColumn<ObjectOfProtect, String>  typeObjC;

    //Поля таблицы заявок
    private ObservableList<Request> RequestData = FXCollections.observableArrayList();
    public TableView<Request>  TableRequests;
    public TableColumn<Request, String> SeriesReqC;
    public TableColumn<Request, String> TypeReqC;
    public TableColumn<Request, String> DateCreateC;
    public TableColumn<Request, String> FineC;

    private ObservableList<String> PatrolOfficerData = FXCollections.observableArrayList();
    public ChoiceBox<String> PatrolOffChoice;
    public TextField SerReqT;
    public TextField setTypeReq;
    public TextField setFineReqT;
    public DatePicker setdateCreateReqDTP;
    public TextArea setNotesReqTF;
    public Label fioPatrolOfficer;
    public Label RangPatrolOfficer;
    public TextArea Notes;

    //Данные по дежурному
    private Long _id = Long.valueOf("0");
    private String _fio = "";

    private Long idPO = Long.valueOf("0");
    private Long idSelectObjectOP = Long.valueOf("0");
    private Long idSelectObjectPO = Long.valueOf("0");
    private Long idSelectRequest = Long.valueOf("0");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);


    @FXML
    private void initialize() {
        FIO.setText("ФИО: " + _fio);
        msg.setText(msgForm.Sign);
        refreshTableObjectsOP();
        initPO();
    }

    // задание начальных данных
    public void setStartData(Long id, String fio) {
        this._id = id;
        this._fio = fio;
        initialize();
    }

    // инициализации таблицы Объектов охраны
    private void refreshTableObjectsOP() {
        ObjectOfProtectData.clear();

        try {

            ResultSet rs = Main.getStmt().executeQuery(Select.getDataObjecOfP + Select.where + Select.getDataObjecOfPIDO + _id + "");

            while (rs != null && rs.next()) {
                ObjectOfProtectData.add(new ObjectOfProtect(
                        rs.getLong(Select.dataObjecOfPID),
                        rs.getLong(Select.dataObjecOfPIDO),
                        rs.getLong(Select.dataObjecOfPIDP),
                        rs.getString(Select.dataObjecOfPADRESS),
                        rs.getString(Select.dataObjecOfPTYPE),
                        rs.getString(Select.dataObjecOfPOBJS)
                ));
            }

            initObjectsOPTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initObjectsOPTable(){
        addressC.setCellValueFactory(new PropertyValueFactory<>(ObjectOfProtect.columnAdress));
        typeObjC.setCellValueFactory(new PropertyValueFactory<>(ObjectOfProtect.columnType));
        TableObjects.setItems(ObjectOfProtectData);
        initPO();

        TableObjects.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Notes.clear();
            ObjectsSecurityData.clear();
            delFields();
            if (newSelection != null) {
                idSelectObjectOP = newSelection.getId();
                refreshTableRequest("");
                initOSecurityTable(newSelection);
            }

        });
    }

    // обновить строки объектов охраны
    private void initOSecurityTable(ObjectOfProtect cur) {
        ObjectsSecurityData.clear();
        String[] obj = cur.getSecurites().split("\\| ");
        for (String s : obj) {
            ObjectsSecurityData.add(new ObjectsSecurity(s));
        }
        OSecurityC.setCellValueFactory(new PropertyValueFactory<>(ObjectsSecurity.columnObject));
        OSecurityTable.setItems(ObjectsSecurityData);
    }

    // инициализации таблицы с Заявки
    private void refreshTableRequest(String sql) {
        RequestData.clear();

        try {
            ResultSet rs = Main.getStmt().executeQuery(Select.getDataRequest + Select.where + Select.getDataRequestO + _id +
                    Select.and +  Select.getDdataRequestidOoP + idSelectObjectOP + sql);

            while (rs != null && rs.next()) {
                RequestData.add(new Request(
                        rs.getLong(Select.dataRequestID),
                        rs.getLong(Select.dataRequestidOoP),
                        rs.getLong(Select.dataRequestidO),
                        rs.getLong(Select.dataRequestidPO),
                        rs.getString(Select.dataRequestSER),
                        rs.getString(Select.dataRequestTYPE),
                        rs.getLong(Select.dataRequestFINE),
                        rs.getString(Select.dataRequestDataCreate),
                        rs.getString(Select.dataRequestNotes)
                ));
            }

            initRequestTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // инициализации таблицы Заявки
    private void initRequestTable() {
        SeriesReqC.setCellValueFactory(new PropertyValueFactory<>(Request.columnSeries));
        TypeReqC.setCellValueFactory(new PropertyValueFactory<>(Request.columnType));
        DateCreateC.setCellValueFactory(new PropertyValueFactory<>(Request.columnDataCreate));
        FineC.setCellValueFactory(new PropertyValueFactory<>(Request.columnFine));
        TableRequests.setItems(RequestData);
        initPO();

        TableRequests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Notes.clear();
                idPO = newSelection.getIdPO();
                idSelectRequest = newSelection.getId();
                Notes.appendText(newSelection.getNotes());
                initFields(newSelection);
            }
        });
    }

    //автозаполененение полей
    private void initFields(Request cur){
        delFields();
        SerReqT.setText(cur.getSeries());
        setTypeReq.setText(cur.getType());
        setFineReqT.setText(String.valueOf(cur.getFine()));
        setdateCreateReqDTP.setValue((LocalDate.parse(cur.getDateCreate(), formatter)));
        setNotesReqTF.setText(cur.getNotes());
        initPO();
    }

    //автоудаление полей
    private void delFields(){
        SerReqT.setText("");
        setTypeReq.setText("");
        setFineReqT.setText("");
        setdateCreateReqDTP.setValue(LocalDate.now());
        setNotesReqTF.setText("");
        PatrolOfficerData.clear();
        PatrolOffChoice.setItems(PatrolOfficerData);
    }

    // инициализация выборки Патрульного
    private void initPO() {
        PatrolOfficerData.clear();
        String fio_po = "";

        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPatrolOfficerALL);
            while (rs != null && rs.next()) {
                if(rs.getLong(Select.dataPatrolOfficerID) == idPO){
                    fio_po = rs.getString(Select.dataPatrolOfficerFIO);
                    PatrolOfficerData.add(rs.getString(Select.dataPatrolOfficerFIO));
                }
                else{
                    PatrolOfficerData.add(rs.getString(Select.dataPatrolOfficerFIO));
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PatrolOffChoice.setItems(PatrolOfficerData);
        PatrolOffChoice.setValue(fio_po);

        PatrolOffChoice.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getDataPatrolOfficerALL + Select.where +
                            Select.getDataPatrolOfficerFIO + "'" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        RankPO.setText(rs.getString(Select.dataPatrolOfficerRANK));
                        idSelectObjectPO = rs.getLong(Select.dataPatrolOfficerID);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Добавление записи
    public void addReqAction(ActionEvent actionEvent) {
        if (BtnActionForm("Подтверждение действия.", "Вы точно хотите добавить запись?")) {
            try {
                Main.getStmt().executeQuery(Insert.insertRequest +
                        idSelectObjectOP + Insert.comma +
                        _id + Insert.comma +
                        idSelectObjectPO + Insert.comma +
                        "'" + SerReqT.getText() + "'" + Insert.comma +
                        "'" + setTypeReq.getText() + "'" + Insert.comma +
                        setFineReqT.getText() + Insert.comma +
                        Insert.toDate + "'" + setdateCreateReqDTP.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                        "'" + setNotesReqTF.getText() + "'" + Insert.rbc);

                refreshTableRequest("");

                msg.setText(msgForm.GoodAdd);
            } catch (SQLException e) {
                msg.setText(msgForm.BadAdd);
                e.printStackTrace();
            }
        }
    }

    //Редактирование записи
    public void editReqAction(ActionEvent actionEvent) {
        if (BtnActionForm("Подтверждение действия.", "Вы точно хотите сохранить изменения?")) {
            try {
                Main.getStmt().executeQuery(Update.updateRequest + Update.set +
                        Update.setRequestIDOoP + idSelectObjectOP + Insert.comma +
                        Update.setRequestIDPO + idSelectObjectPO + Insert.comma +
                        Update.setRequestSER + "'" + SerReqT.getText() + "'" + Insert.comma +
                        Update.setRequestTYPE + "'" + setTypeReq.getText() + "'" + Insert.comma +
                        Update.setRequestFINE + "'" + setFineReqT.getText() + "'" + Insert.comma +
                        Update.setRequestDataCreate +
                        Insert.toDate + "'" + setdateCreateReqDTP.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                        Update.setRequestNOTES + "'" + setNotesReqTF.getText() + "'" +
                        Select.where + Update.whereRequestId + idSelectRequest);

                refreshTableRequest("");

                msg.setText(msgForm.GoodEdit);
            } catch (SQLException e) {
                msg.setText(msgForm.BadEdit);
                e.printStackTrace();
            }
        }
    }

    //Удаление записи
    public void delReqAction(ActionEvent actionEvent) {
        if (BtnActionForm("Подтверждение действия.", "Вы точно хотите удалить запись?")) {
            try {
                Main.getStmt().executeQuery(Delete.deleteRequest +
                        Select.where + Update.whereRequestId + idSelectRequest);

                refreshTableRequest("");

                msg.setText(msgForm.GoddDelete);
            } catch (SQLException e) {
                msg.setText(msgForm.BadDelete);
                e.printStackTrace();
            }
        }
    }

    //Открытие формы потверждения
    public boolean BtnActionForm(String Title, String Text){
        final boolean[] fl = {false};
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(Title);
        alert.setHeaderText("");
        alert.setContentText(Text);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                fl[0] = true;
            }
        });
        return fl[0];
    }

    //Кнопка выход
    public void ExitBtnAction(ActionEvent actionEvent) {
        if (BtnActionForm("Выход", "Вы точно хотите выйти?")) {
            ((Stage) (ExitBtn.getScene().getWindow())).close();
        }
    }

    //Активация поля поиска
    public void on_off_st(ActionEvent actionEvent) {

        if(isSearchDateStartRequest.isSelected()){
            dateStartCreateSuppose.setDisable(false);
        }
        else{
            dateStartCreateSuppose.setDisable(true);
        }
        activeBtn();
    }

    //Активация поля поиска
    public void on_off_en(ActionEvent actionEvent) {
        boolean fl = false;
        if(isSearchDateEndRequest.isSelected()){
            dateEndCreateSuppose.setDisable(false);
        }
        else{
            dateEndCreateSuppose.setDisable(true);
        }
        activeBtn();
    }

    //Активация кнопки поиска
    public void activeBtn(){
        if(isSearchDateEndRequest.isSelected() || isSearchDateStartRequest.isSelected()){
            FindBtn.setDisable(false);
        }
        else{
            FindBtn.setDisable(true);
        }
    }

    //Поиск по таблице Заявки на осбледование
    public void clickFindBtn(ActionEvent actionEvent) {
        if (BtnActionForm("Подтверждение действия.", "Вы точно хотите выполнить поиск?")) {
            String addSqlQuestion = "";
            boolean sql_f = false;

            if (!dateStartCreateSuppose.isDisable()) {
                if (dateStartCreateSuppose.getValue() == null) {
                    sql_f = false;
                } else {
                    addSqlQuestion += Select.and + Select.notEqDataRequestDateCreate + " >= to_date('" + dateStartCreateSuppose.getValue().format(formatter) +
                            "', '" + Constants.dateFormat + "')";
                    sql_f = true;
                }
            }

            if (!dateEndCreateSuppose.isDisable()) {
                if (dateEndCreateSuppose.getValue() == null) {
                    sql_f = false;
                } else {
                    addSqlQuestion += Select.and + Select.notEqDataRequestDateCreate + " <= to_date('" + dateEndCreateSuppose.getValue().format(formatter) +
                            "', '" + Constants.dateFormat + "')";
                    sql_f = true;
                }
            }

            if (sql_f) {
                refreshTableRequest(addSqlQuestion);
                msg.setText(msgForm.GoodFind);
            } else {
                msg.setText(msgForm.BadFind);
            }

        }
    }
}
