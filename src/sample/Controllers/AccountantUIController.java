package sample.Controllers;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
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
import sample.Tables.ActPay;
import sample.Tables.LOA;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AccountantUIController {
    public Label NumDog;
    public Label PeriodPay;
    public Label DateStart;
    public Label DateEnd;
    public Label FIO;
    public DatePicker dateCreateEdit;
    public DatePicker datePayEdit;
    public DatePicker dateStartCreateSuppose;
    public DatePicker dateEndCreateSuppose;
    public DatePicker dateEndPayFact;
    public DatePicker dateStartPayFact;
    public DatePicker dateSupposeEdit;
    public DatePicker dateFactEdit;
    public SplitMenuButton TypePayEdit;
    public TextField SumPayEdit;
    public SplitMenuButton TypePaySearch;
    public TextField SumPaySearch;
    public RadioButton More;
    public RadioButton Less;
    public CheckBox Equal;
    public MenuItem searchTypePayCash;
    public MenuItem searchTypePayTrans;
    public CheckBox isSearchDateStartCreateSuppose;
    public CheckBox isSearchDateEndCreateSuppose;
    public CheckBox isSearchDateStartPayFact;
    public CheckBox isSearchDateEndPayFact;
    public CheckBox isSearchTypePay;
    public Button ExitBtn;
    public Label msg;
    public TextField nameOth;
    private String selectSearchTypePay;
    public MenuItem editTypePayCash;
    public MenuItem editTypePayTrans;
    private String selectEditTypePay;
    private ObservableList<String> allSerDogovor = FXCollections.observableArrayList();
    public ChoiceBox<String> allDogovor;
    // поля таблицы акта оплаты
    private ObservableList<ActPay> actPaysData = FXCollections.observableArrayList();
    public TableView<ActPay> ActPayTable;
    public TableColumn<ActPay, Integer> NumAct;
    public TableColumn<ActPay, String> DateCreate;
    public TableColumn<ActPay, String> DatePayAct;
    // поля таблицы строки акта олпаты
    private ObservableList<LOA> loaData = FXCollections.observableArrayList();
    public TableView<LOA> TableLOA;
    public TableColumn<LOA, String> DateSuppose;
    public TableColumn<LOA, String> DateFactPay;
    public TableColumn<LOA, Integer> SumPay;
    public TableColumn<LOA, String> TypePay;

    private Long idAcc = Long.valueOf("0");
    private String fio = "";
    private Long idLOA = Long.valueOf("0");
    private Long idDog = Long.valueOf("0");
    private Long idSelectActPay = Long.valueOf("0");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);


    @FXML
    private void initialize() {
        initTableActPay("");
        FIO.setText(fio);

        initDog();

        initMainTable();

        initSubTable();
    }

    // инициализации таблицы актов
    private void initMainTable() {
        NumAct.setCellValueFactory(new PropertyValueFactory<ActPay, Integer>(ActPay.columnNum));
        DateCreate.setCellValueFactory(new PropertyValueFactory<ActPay, String>(ActPay.columnDateCreate));
        DatePayAct.setCellValueFactory(new PropertyValueFactory<ActPay, String>(ActPay.columnDateSuppose));

        ActPayTable.setItems(actPaysData);

        ActPayTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idSelectActPay = newSelection.getId();
                refreshSubTable("");
                dateCreateEdit.setValue(LocalDate.parse(newSelection.getDateCreate(), formatter));
                datePayEdit.setValue(LocalDate.parse(newSelection.getDateSuppose(), formatter));
            }
        });
    }

    // инициализация выборки договора
    private void initDog() {
        allSerDogovor.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataDog);
            while (rs != null && rs.next()) {
                allSerDogovor.add(rs.getString(Select.dataDogSeries));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            msg.setText("При формировании списка договоров произошла ошибка");
        }

        allDogovor.setItems(allSerDogovor);
        allDogovor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getDataDog + Select.where +
                            Select.getDataDogSerDog + "'" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        idDog = rs.getLong(Select.dataDogId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    msg.setText("При извлечении договора произошла ошибка");
                }
                msg.setText("Договор успешно извлечен");
                refreshDog();
            }
        });
    }

    // инициализация таблицы строк актов
    private void initSubTable() {
        DateSuppose.setCellValueFactory(new PropertyValueFactory<LOA, String>(LOA.columnDateSuppose));
        DateFactPay.setCellValueFactory(new PropertyValueFactory<LOA, String>(LOA.columnDateFact));
        SumPay.setCellValueFactory(new PropertyValueFactory<LOA, Integer>(LOA.columnPayment));

        TypePay.setCellValueFactory(new PropertyValueFactory<LOA, String>(LOA.columnTypePay));

        TableLOA.setItems(loaData);

        TableLOA.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idLOA = newSelection.getId();
                idDog = newSelection.getIdDogovor();
                refreshDog();
                dateSupposeEdit.setValue(LocalDate.parse(newSelection.getDateSuppose(), formatter));
                dateFactEdit.setValue(LocalDate.parse(newSelection.getDateFact(), formatter));
                SumPayEdit.setText(String.valueOf(newSelection.getPayment()));
                TypePayEdit.setText(newSelection.getPayType());
                selectEditTypePay = newSelection.getPayType();
            }
        });
    }

    // инициализации таблицы с договорами
    private void initTableActPay(String addSqlQuestion) {
        actPaysData.clear();
        try {
            ResultSet rs = null;
            String str = Select.getDataActPaying + Select.where +
                    Select.getDataActPayingIdAcc + idAcc + addSqlQuestion;
            rs = Main.getStmt().executeQuery(Select.getDataActPaying + Select.where +
                    Select.getDataActPayingIdAcc + idAcc + addSqlQuestion);
            int index = 1;
            while (rs != null && rs.next()) {
                actPaysData.add(new ActPay(index++, rs.getString(Select.dataActPayingDateCreate),
                        rs.getString(Select.dataActPayingDatePay), rs.getLong(Select.dataActPayingDateId)));
            }
        } catch (SQLException e) {
            msg.setText("При формировании таблицы актов произошла ошибка");
            e.printStackTrace();
        }
        msg.setText("Табилца актов успешно сформирована");
    }

    private int strToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            msg.setText("Не число");
            return -1;
        }
    }

    // задание начальных данных
    public void setStartData(Long id, String fio) {
        this.idAcc = id;
        this.fio = fio;

        initialize();
    }

    // обновить строки бланков
    private void refreshSubTable(String addSqlQuestion) {
        loaData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataLOA + Select.where +
                    Select.getDataLOAidAct + idSelectActPay + addSqlQuestion);
            while (rs != null && rs.next()) {
                loaData.add(new LOA(rs.getLong(Select.dataLOAID), rs.getLong(Select.dataLOAidDog),
                        rs.getString(Select.dataLOADateFact), rs.getString(Select.dataLOADateSuppose),
                        rs.getInt(Select.dataLOAPayment),
                        rs.getInt(Select.dataLOAFacPay), rs.getString(Select.dataLOAPayType)));
            }
        } catch (SQLException e) {
            msg.setText("При формировании строк акта произошла ошибка");
            e.printStackTrace();
        }
    }

    // обновить договор
    private void refreshDog() {
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataDog + Select.where +
                    Select.getDataDogIdDog + idDog);
            if (rs != null && rs.next()) {
                NumDog.setText(rs.getString(Select.dataDogSeries));
                PeriodPay.setText(rs.getString(Select.dataDogPeriod));
                DateStart.setText(rs.getString(Select.dataDogDateStart));
                DateEnd.setText(rs.getString(Select.dataDogDateEnd));
            }
        } catch (SQLException e) {
            msg.setText("При заполнения договора произошла ошибка");
            e.printStackTrace();
        }
    }

    // работа с бланками
    public void addActPayAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            String resStr = Insert.insertActPay + idAcc + Insert.comma +
                    Insert.toDate + dateCreateEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + datePayEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertActPay + idAcc + Insert.comma +
                    Insert.toDate + "'" + dateCreateEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + "'" + datePayEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.rbc);
            initTableActPay("");
            msg.setText("запись добавлена");
        } catch (SQLException e) {
            msg.setText("При добавлении записи произошла ошибка");
            e.printStackTrace();
        }
    }

    public void editActPayAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Update.updateActPay + Update.set + Update.setDateCreate +
                    Insert.toDate + "'" + dateCreateEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setDatePay +
                    Insert.toDate + "'" + datePayEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Select.where + Update.whereIdActPay + idSelectActPay);
            initTableActPay("");
            msg.setText("Редактирование записи прошло успешно");
        } catch (SQLException e) {
            msg.setText("При редактировании записи произошла ошибка");
            e.printStackTrace();
        }
    }

    public void delActPayAction(ActionEvent actionEvent) {
        try {
//            if (loaData.size() > 0) {
//                msg.setText("Невозможно удалить запись");
//                return;
//            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Delete.deleteActPay +
                    Select.where + Update.whereIdActPay + idSelectActPay);
            initTableActPay("");
            msg.setText("Запись удалена");
        } catch (SQLException e) {
            msg.setText("Невозможно удалить запись");
            e.printStackTrace();
        }
    }

    // работа с строками бланка
    public void addLoaAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            if (strToInt(SumPayEdit.getText()) == -1) {
                return;
            }
            int startSize = loaData.size();
            //Main.getStmt().execute("set transaction insert only name 'check'");
            String resStr = Insert.insertLineOfAct + idSelectActPay + Insert.comma + idDog + Insert.comma +
                    Insert.toDate + dateFactEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + dateSupposeEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    strToInt(SumPayEdit.getText()) + Insert.comma + selectEditTypePay + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertLineOfAct + idSelectActPay + Insert.comma + idDog + Insert.comma +
                    Insert.toDate + "'" + dateFactEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + "'" + dateSupposeEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    strToInt(SumPayEdit.getText()) + Insert.comma + "'" + selectEditTypePay + "'" + Insert.rbc);
            refreshSubTable("");
            msg.setText("Строка акта добавлена");
            //if (loaData.size() == startSize)
           //     Main.getStmt().execute("rollback");
           // else
           //     Main.getStmt().execute("commit");
        } catch (SQLException e) {
            msg.setText("Не получилось добавить строку акта");
            e.printStackTrace();
        }
    }

    public void editLoaAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);

            if (strToInt(SumPayEdit.getText()) == -1) {
                return;
            }
            Main.getStmt().executeQuery(Update.updateLOA + Update.setLoaDogovorId + idDog + Insert.comma +
                    Update.setLoaDataFact +
                    Insert.toDate + "'" + dateFactEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setLoaDataSuppose +
                    Insert.toDate + "'" + dateSupposeEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setLoaPayment +
                    strToInt(SumPayEdit.getText()) + Insert.comma + Update.setLoaPayType + "'" + selectEditTypePay + "'" +
                    Select.where + Update.whereIdLOA + idLOA);
            refreshSubTable("");
            msg.setText("Строка акта отредактирована");
        } catch (SQLException e) {
            msg.setText("Не получилось редактировать строку акта");
            e.printStackTrace();
        }
    }

    public void delLoaAction(ActionEvent actionEvent) {
        try {
            if (strToInt(SumPayEdit.getText()) == -1) {
                return;
            }
            Main.getStmt().executeQuery(Delete.deleteLOA +
                    Select.where + Update.whereIdLOA + idLOA);
            refreshSubTable("");
            msg.setText("Строка акта удалена");
        } catch (SQLException e) {
            msg.setText("Не получилось удалить строку акта");
            e.printStackTrace();
        }
    }

    // активация поиска по бланкам оплаты или строкам бланка
    public void searchActPayAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchDateStartCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDateCreate + " >= to_date('" + dateStartCreateSuppose.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchDateEndCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDateCreate + " <= to_date('" + dateEndCreateSuppose.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchDateStartPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDatePay + " >= to_date('" + dateStartPayFact.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchDateEndPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDatePay + " <= to_date('" + dateEndPayFact.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        initTableActPay(addSqlQuestion);
    }

    public void searchLOAAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchDateStartCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateSuppose + " >= to_date('" + dateStartCreateSuppose.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchDateEndCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateSuppose + " <= to_date('" + dateEndCreateSuppose.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchDateStartPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateFact + " >= to_date('" + dateStartPayFact.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchDateEndPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateFact + " <= to_date('" + dateEndPayFact.getValue().format(formatter) + "', '" + Constants.dateFormat + "')";
        if (isSearchTypePay.isSelected())
            addSqlQuestion += Select.and + Select.getDataLOAPayType + "'" + selectSearchTypePay + "'";
        if (!SumPaySearch.getText().equals("") && (More.isSelected() || Less.isSelected())) {
            String quest = ">";
            if (More.isSelected()) {
                if (Equal.isSelected())
                    quest = ">=";
            } else {
                quest = "<";
                if (Equal.isSelected())
                    quest = "<=";

            }
            addSqlQuestion += Select.and + Select.dataLOAPayment + quest + strToInt(SumPaySearch.getText());
        }
        refreshSubTable(addSqlQuestion);
    }

    // изменение текста в выборке типа оплаты, по нему будет сверка и выбор типа
    public void ChangeSearchTypePayCash(ActionEvent actionEvent) {
        TypePaySearch.setText(Constants.payTypeCash);
        selectSearchTypePay = Constants.payTypeCash;
    }

    public void ChangeSearchTypePayTrans(ActionEvent actionEvent) {
        TypePaySearch.setText(Constants.payTypeTransfer);
        selectSearchTypePay = Constants.payTypeTransfer;
    }

    public void ChangeEditTypePayCash(ActionEvent actionEvent) {
        TypePayEdit.setText(Constants.payTypeCash);
        selectEditTypePay = Constants.payTypeCash;
    }

    public void ChangeEditTypePayTrans(ActionEvent actionEvent) {
        TypePayEdit.setText(Constants.payTypeTransfer);
        selectEditTypePay = Constants.payTypeTransfer;
    }

    public void ExitBtnAction(ActionEvent actionEvent) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход");
            alert.setTitle("Выход");
            alert.setContentText("Выход?");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    ((Stage) (ExitBtn.getScene().getWindow())).close();
                }
            });
    }

    public void ClearLineAction(ActionEvent actionEvent) {
        refreshSubTable("");
    }

    public void clearActAction(ActionEvent actionEvent) {
        initTableActPay("");
    }

    public void createOthetAction(ActionEvent actionEvent) {
        try {
            if (nameOth.getText().equals("")) {
                msg.setText("Некорректное имя отчета");
                return;
            }
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(nameOth.getText() + ".pdf"));
            document.open();
            ResultSet rs = Main.getStmt().executeQuery("select client.fio_client fio, client.address_client address, " +
                    "client.doc_client doc, loa.fine_loa fine, dog.series_dog ser " +
                    "from client, lineofact loa, dogovor dog, actofpaying aop, accountant acca " +
                    "where loa.dogovor_iddogovor = dog.iddogovor " +
                    "  and dog.client_idclient = client.idclient" +
                    "  and loa.fine_loa > 0" +
                    "  and aop.idactpay = loa.actofpaying_idactpay" +
                    "  and aop.accountant_idaccountant = acca.idaccountant" +
                    "  and acca.idaccountant = " + idAcc);
            int count = 1;
            BaseFont times = BaseFont.createFont("c:/windows/fonts/times.ttf","cp1251",BaseFont.EMBEDDED);
            document.add(new Paragraph("Othet: "));
            while (rs != null && rs.next()) {
                document.add(new Paragraph("№ " + String.valueOf(count) + " | " +
                        "ФИО: " + rs.getString("fio") + " | " +
                        "Адрес: " + rs.getString("address") + " | " +
                        "Документ: " + rs.getString("doc") + " | " +
                        "Долг: " + rs.getString("fine") + " | " +
                        "Серия договора: " + rs.getString("ser"), new Font(times, 14)));
                count++;
            }
            document.close();
            msg.setText("Отчет сформирован");
        } catch(Exception e) {
            msg.setText("Не удалось сформировать отчет");
            e.printStackTrace();
        }
    }
}
