package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.Scripts.Select;
import sample.Tables.ActPay;
import sample.Tables.LOA;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountantUIController {
    public Label NumDog;
    public Label PeriodPay;
    public Label DateStart;
    public Label DateEnd;
    public Label FIO;
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


    @FXML
    private void initialize() {
        initTableActPay();
        FIO.setText(fio);

        NumAct.setCellValueFactory(new PropertyValueFactory<ActPay, Integer>(ActPay.columnNum));
        DateCreate.setCellValueFactory(new PropertyValueFactory<ActPay, String>(ActPay.columnDateCreate));
        DatePayAct.setCellValueFactory(new PropertyValueFactory<ActPay, String>(ActPay.columnDateSuppose));

        ActPayTable.setItems(actPaysData);

        ActPayTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idSelectActPay = newSelection.getId();
                refreshSubTable();
            }
        });

        DateSuppose.setCellValueFactory(new PropertyValueFactory<LOA, String>(LOA.columnDateSuppose));
        DateFactPay.setCellValueFactory(new PropertyValueFactory<LOA, String>(LOA.columnDateFact));
        SumPay.setCellValueFactory(new PropertyValueFactory<LOA, Integer>(LOA.columnFactPay));
        TypePay.setCellValueFactory(new PropertyValueFactory<LOA, String>(LOA.columnTypePay));

        TableLOA.setItems(loaData);

        TableLOA.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idDog = newSelection.getIdDogovor();
                refreshDog();
            }
        });
    }

    private void initTableActPay() {
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataActPaying + Select.where +
                    Select.getDataActPayingIdAcc + idAcc);
            int index = 1;
            while (rs != null && rs.next()) {
                actPaysData.add(new ActPay(index++, rs.getString(Select.dataActPayingDateCreate),
                        rs.getString(Select.dataActPayingDatePay), rs.getLong(Select.dataActPayingDateId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStartData(Long id, String fio) {
        this.idAcc = id;
        this.fio = fio;

        initialize();
    }

    private void refreshSubTable() {
        loaData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataLOA + Select.where +
                    Select.getDataLOAidAct + idSelectActPay);
            while (rs != null && rs.next()) {
                loaData.add(new LOA(rs.getLong(Select.getDataLOAID), rs.getLong(Select.getDataLOAidDog),
                        rs.getString(Select.getDataLOADateFact), rs.getString(Select.getDataLOADateSuppose),
                        rs.getInt(Select.getDataLOAPayment),
                        rs.getInt(Select.getDataLOAFacPay), rs.getString(Select.getDataLOAPayType)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

}
