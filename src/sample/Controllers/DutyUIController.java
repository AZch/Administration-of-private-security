package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DutyUIController {

    public Label FIO;
    public Label RankPO;

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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);


    @FXML
    private void initialize() {
        FIO.setText("ФИО: " + _fio);
        refreshTableObjectsOP();
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

        TableObjects.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            Notes.clear();
            ObjectsSecurityData.clear();
            delFields();
            if (newSelection != null) {
                idSelectObjectOP = newSelection.getId();
                refreshTableRequest();
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
    private void refreshTableRequest() {
        RequestData.clear();

        try {
            ResultSet rs = Main.getStmt().executeQuery(Select.getDataRequest + Select.where + Select.getDataRequestO + _id +
                    Select.and +  Select.getDdataRequestidOoP + idSelectObjectOP + "");

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
        TableRequests.setItems(RequestData);

        TableRequests.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Notes.clear();
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
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPatrolOfficerALL);
            while (rs != null && rs.next()) {
                PatrolOfficerData.add(rs.getString(Select.dataPatrolOfficerFIO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PatrolOffChoice.setItems(PatrolOfficerData);

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


    public void addReqAction(ActionEvent actionEvent) {
        try {
            String resStr = Insert.insertRequest + idSelectObjectOP + Insert.comma + _id + Insert.comma +
                    idSelectObjectPO + Insert.comma + "'" + SerReqT.getText() + "'" + Insert.comma +
                    "'" + setTypeReq.getText() + "'" + Insert.comma + "'" + setFineReqT.getText() + "'" + Insert.comma +
                    Insert.toDate + setdateCreateReqDTP.getValue().format(formatter) + Insert.comma +  Insert.formatDate + Insert.rbc + Insert.comma +
                    "'" + setNotesReqTF + "'" + Insert.rbc;

            Main.getStmt().executeQuery(Insert.insertRequest + idSelectObjectOP + Insert.comma + _id + Insert.comma +
                    idSelectObjectPO + Insert.comma + "'" + SerReqT.getText() + "'" + Insert.comma +
                    "'" + setTypeReq.getText() + "'" + Insert.comma + "'" + setFineReqT.getText() + "'" + Insert.comma +
                    Insert.toDate + setdateCreateReqDTP.getValue().format(formatter) + Insert.comma +  Insert.formatDate + Insert.rbc + Insert.comma +
                    "'" + setNotesReqTF + "'" + Insert.rbc);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editReqAction(ActionEvent actionEvent) {
    }

    public void delReqAction(ActionEvent actionEvent) {
    }
}
