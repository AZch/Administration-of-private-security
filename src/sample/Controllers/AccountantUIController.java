package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Scripts.Update;
import sample.Tables.ActPay;
import sample.Tables.LOA;

import java.awt.*;
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

        // инициализация выборки договора
        allSerDogovor.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataDog);
            while (rs != null && rs.next()) {
                allSerDogovor.add(rs.getString(Select.dataDogSeries));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                }
                refreshDog();
            }
        });

        // инициализации таблицы актов
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

        // инициализация таблицы строк актов
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
            rs = Main.getStmt().executeQuery(Select.getDataActPaying + Select.where +
                    Select.getDataActPayingIdAcc + idAcc + addSqlQuestion);
            int index = 1;
            while (rs != null && rs.next()) {
                actPaysData.add(new ActPay(index++, rs.getString(Select.dataActPayingDateCreate),
                        rs.getString(Select.dataActPayingDatePay), rs.getLong(Select.dataActPayingDateId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        } catch (SQLException e) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delActPayAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Delete.deleteActPay +
                    Select.where + Update.whereIdActPay + idSelectActPay);
        } catch (SQLException e) {
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
            String resStr = Insert.insertLineOfAct + idSelectActPay + Insert.comma + idDog + Insert.comma +
                    Insert.toDate + dateFactEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + dateSupposeEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    strToInt(SumPayEdit.getText()) + Insert.comma + selectEditTypePay + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertLineOfAct + idSelectActPay + Insert.comma + idDog + Insert.comma +
                    Insert.toDate + "'" + dateFactEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + "'" + dateSupposeEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    strToInt(SumPayEdit.getText()) + Insert.comma + "'" + selectEditTypePay + "'" + Insert.rbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int strToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            return -1;
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delLoaAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            if (strToInt(SumPayEdit.getText()) == -1) {
                return;
            }
            Main.getStmt().executeQuery(Delete.deleteLOA +
                    Select.where + Update.whereIdLOA + idLOA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // активация поиска по бланкам оплаты или строкам бланка
    public void searchActPayAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchDateStartCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDateCreate + " >= '" + dateStartCreateSuppose.getValue().format(formatter) + "'";
        if (isSearchDateEndCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDateCreate + " <= '" + dateEndCreateSuppose.getValue().format(formatter) + "'";
        if (isSearchDateStartPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDatePay + " >= '" + dateStartPayFact.getValue().format(formatter) + "'";
        if (isSearchDateEndPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataActPayingDatePay + " <= '" + dateEndPayFact.getValue().format(formatter) + "'";
        initTableActPay(addSqlQuestion);
    }

    public void searchLOAAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchDateStartCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateSuppose + " >= '" + dateStartCreateSuppose.getValue().format(formatter) + "'";
        if (isSearchDateEndCreateSuppose.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateSuppose + " <= '" + dateEndCreateSuppose.getValue().format(formatter) + "'";
        if (isSearchDateStartPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateFact + " >= '" + dateStartPayFact.getValue().format(formatter) + "'";
        if (isSearchDateEndPayFact.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataLOADateFact + " <= '" + dateEndPayFact.getValue().format(formatter) + "'";
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
}
