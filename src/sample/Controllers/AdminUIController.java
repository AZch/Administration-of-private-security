package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import oracle.jdbc.driver.Const;
import sample.Constants;
import sample.Main;
import sample.Scripts.Select;
import sample.Tables.ActPay;
import sample.Tables.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdminUIController {
    private ObservableList<String> workNameList = FXCollections.observableArrayList();
    public ChoiceBox<String> WhatStaffBox;
    public Label NicknameAdmin;

    private ObservableList<Staff> dataStaff = FXCollections.observableArrayList();
    public TableView<Staff> tableStaff;
    public TableColumn<Staff, String> fioStaff;
    public TableColumn<Staff, String> lgnStaff;
    public TableColumn<Staff, String> pswStaff;

    public Label WorkName;
    public TextField fioEdit;
    public TextField lgnEdit;
    public TextField pswEdit;
    public TextField lgnSearch;
    public TextField fioSearch;
    public TextField pswSearch;
    public CheckBox isFioSearch;
    public CheckBox isLgnSearch;
    public CheckBox isPswSearch;

    public void setStartData(Long id, String fio) {
        NicknameAdmin.setText(fio);
        WhatStaffBox.setItems(workNameList);
        workNameList.add(Constants.staffAdmin);
        workNameList.add(Constants.staffAccountant);
        workNameList.add(Constants.staffCustSerrv);
        workNameList.add(Constants.staffDirector);
        workNameList.add(Constants.staffDuty);
        workNameList.add(Constants.staffPatrolOff);

        WhatStaffBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null)
                WorkName.setText(newSelection);
            refreshTable("");

        });

        fioStaff.setCellValueFactory(new PropertyValueFactory<Staff, String>(Staff.columnFio));
        pswStaff.setCellValueFactory(new PropertyValueFactory<Staff, String>(Staff.columnPsw));
        lgnStaff.setCellValueFactory(new PropertyValueFactory<Staff, String>(Staff.columnLgn));

        tableStaff.setItems(dataStaff);

        tableStaff.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fioEdit.setText(newSelection.getFio());
                lgnEdit.setText(newSelection.getLgn());
                pswEdit.setText(newSelection.getPsw());
            }
        });

        refreshTable("");
    }

    private void refreshTable(String addSqlSearch) {
        switch (WorkName.getText()) {
            case Constants.staffAdmin:
                fillTable(Select.getDataAdmin, Select.dataAdminId, Select.dataAdminFio,
                        Select.dataAdminLgn, Select.dataAdminPsw);
                break;
            case Constants.staffAccountant:
                fillTable(Select.getDataAccountant, Select.dataAccountantID, Select.dataAccountantFIO,
                        Select.dataAccountantLgn, Select.dataAccountantPsw);
                break;
            case Constants.staffCustSerrv:
                fillTable(Select.getDataServOff, Select.dataServOffId, Select.dataServOffFio,
                        Select.dataServOffLgn, Select.dataServOffPsw);
                break;
            case Constants.staffDirector:
                fillTable(Select.getDataDir, Select.dataDirID, Select.dataDirFIO,
                        Select.dataDirLgn, Select.dataDirPsw);
                break;
            case Constants.staffDuty:
                fillTable(Select.getDataDuty, Select.dataOperId, Select.dataOperFio,
                        Select.dataOperLgn, Select.dataOperPsw);
                break;
            case Constants.staffPatrolOff:
                fillTable(Select.getDataPatrolOfficer, Select.dataPatrolOfficerID, Select.dataPatrolOfficerFIO,
                        Select.dataPatrolOfficerLgn, Select.dataPatrolOfficerPsw);
                break;
        }
    }

    private void fillTable(String sql, String id, String fio, String lgn, String psw) {
        dataStaff.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(sql);
            while (rs != null && rs.next()) {
                dataStaff.add(new Staff(rs.getLong(id), rs.getString(fio), rs.getString(lgn), rs.getString(psw)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addStaffAction(ActionEvent actionEvent) {
    }

    public void changeStaffAction(ActionEvent actionEvent) {
    }

    public void delStaffAction(ActionEvent actionEvent) {
    }

    public void searchAction(ActionEvent actionEvent) {
    }
}
