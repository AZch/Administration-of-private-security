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

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountantUIController {
    public Label NumDog;
    public Label PeriodPay;
    public Label DateStart;
    public Label SumPeriod;
    public Label DateEnd;
    public Label FIO;
    // поля таблицы акта оплаты
    private ObservableList<ActPay> actPaysData = FXCollections.observableArrayList();
    public TableView<ActPay> ActPayTable;
    public TableColumn<ActPay, Integer> NumAct;
    public TableColumn<ActPay, String> DateCreate;
    public TableColumn<ActPay, String> DatePayAct;
    // поля таблицы строки акта олпаты
    public TableColumn DateSuppose;
    public TableColumn DateFactPay;
    public TableColumn SumPay;
    public TableColumn TypePay;

    private Long idAcc = Long.valueOf(0);
    private String fio = "";


    @FXML
    private void initialize() {
        initTableActPay();
        FIO.setText(fio);

        NumAct.setCellValueFactory(new PropertyValueFactory<ActPay, Integer>(ActPay.columnNum));
        DateCreate.setCellValueFactory(new PropertyValueFactory<ActPay, String>(ActPay.columnDateCreate));
        DatePayAct.setCellValueFactory(new PropertyValueFactory<ActPay, String>(ActPay.columnDateSuppose));

        ActPayTable.setItems(actPaysData);
    }

    private void initTableActPay() {
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataActPaying + Select.where +
                    Select.getDataActPayingIdAcc + idAcc);
            int index = 1;
            while (rs != null && rs.next()) {
                actPaysData.add(new ActPay(index++, rs.getString(Select.dataActPayingDateCreate), rs.getString(Select.dataActPayingDatePay)));
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

}
