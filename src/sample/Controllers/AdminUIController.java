package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Scripts.Update;
import sample.Tables.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminUIController {
    public TextField rankEdit;
    public TextField rankSearch;
    public CheckBox isRankSearch;
    public Label msg;
    public Button btnExit;
    private ObservableList<String> workNameList = FXCollections.observableArrayList();
    public ChoiceBox<String> WhatStaffBox;
    public Label NicknameAdmin;

    private ObservableList<Staff> dataStaff = FXCollections.observableArrayList();
    public TableView<Staff> tableStaff;
    public TableColumn<Staff, String> fioStaff;
    public TableColumn<Staff, String> lgnStaff;
    public TableColumn<Staff, String> pswStaff;
    public TableColumn<Staff, String> Rank;

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

    private Long idSelect = Long.valueOf("0");

    public void setStartData(Long id, String fio) {
        NicknameAdmin.setText(fio);
        WhatStaffBox.setItems(workNameList);
        //workNameList.add(Constants.staffAdmin);
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
        Rank.setCellValueFactory(new PropertyValueFactory<Staff, String>(Staff.columnRank));


        tableStaff.setItems(dataStaff);

        tableStaff.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fioEdit.setText(newSelection.getFio());
                lgnEdit.setText(newSelection.getLgn());
                pswEdit.setText(newSelection.getPsw());
                rankEdit.setText(newSelection.getRank());
                idSelect = newSelection.getId();
            }
        });

        refreshTable("");
    }

    private void refreshTable(String addSqlSearch) {
        if (addSqlSearch.length() > 4)
            addSqlSearch = Select.where + addSqlSearch.substring(4, addSqlSearch.length()); // обрезка первого and
        switch (WorkName.getText()) {
            case Constants.staffAdmin:
                fillTable(Select.getDataAdmin + addSqlSearch, Select.dataAdminId, Select.dataAdminFio,
                        Select.dataAdminLgn, Select.dataAdminPsw, "");
                Rank.setVisible(false);
                break;
            case Constants.staffAccountant:
                fillTable(Select.getDataAccountant + addSqlSearch, Select.dataAccountantID, Select.dataAccountantFIO,
                        Select.dataAccountantLgn, Select.dataAccountantPsw, "");
                Rank.setVisible(false);
                break;
            case Constants.staffCustSerrv:
                fillTable(Select.getDataServOff + addSqlSearch, Select.dataServOffId, Select.dataServOffFio,
                        Select.dataServOffLgn, Select.dataServOffPsw, "");
                Rank.setVisible(false);
                break;
            case Constants.staffDirector:
                fillTable(Select.getDataDir + addSqlSearch, Select.dataDirID, Select.dataDirFIO,
                        Select.dataDirLgn, Select.dataDirPsw, "");
                Rank.setVisible(false);
                break;
            case Constants.staffDuty:
                fillTable(Select.getDataDuty + addSqlSearch, Select.dataOperId, Select.dataOperFio,
                        Select.dataOperLgn, Select.dataOperPsw, "");
                Rank.setVisible(false);
                break;
            case Constants.staffPatrolOff:
                fillTable(Select.getDataPatrolOfficer + addSqlSearch, Select.dataPatrolOfficerID, Select.dataPatrolOfficerFIO,
                        Select.dataPatrolOfficerLgn, Select.dataPatrolOfficerPsw, Select.dataPatrolOfficerRANK);
                Rank.setVisible(true);
                break;
        }
    }

    private void fillTable(String sql, String id, String fio, String lgn, String psw, String rank) {
        dataStaff.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(sql);
            while (rs != null && rs.next()) {
                if (rank.equals(""))
                    dataStaff.add(new Staff(rs.getLong(id), rs.getString(fio), rs.getString(lgn), rs.getString(psw), ""));
                else
                    dataStaff.add(new Staff(rs.getLong(id), rs.getString(fio), rs.getString(lgn), rs.getString(psw), rs.getString(rank)));
            }
        } catch (SQLException e) {
            msg.setText("Не удалось заоплнить таблицу");
            e.printStackTrace();
        }
    }

    private void executeStript(String sql) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтвердите опреацию");
        alert.setTitle("Подтвердите опреацию");
        alert.setContentText("Подтвердите опреацию?");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                try {
                    Main.getStmt().execute(sql);
                } catch (SQLException e) {
                    msg.setText("Не удалось выполнить операцию");
                    e.printStackTrace();
                }
            }
        });

    }

    public void addStaffAction(ActionEvent actionEvent) {
        switch (WorkName.getText()) {
            case Constants.staffAdmin:
                //executeStript(Insert.insertAdmin + "'" + fioEdit.getText() + "', '" + lgnEdit.getText() + "', '" + pswEdit.getText() + "')");
                msg.setText("Нельзя выполнить опреацию");
                break;
            case Constants.staffAccountant:
                executeStript(Insert.insertAccountant + "'" + fioEdit.getText() + "'," + Constants.startSer + ", " +
                        Constants.startNum + ", '" + lgnEdit.getText() + "', '" + pswEdit.getText() + "')");
                break;
            case Constants.staffCustSerrv:
                executeStript(Insert.insertCustService + "'" + fioEdit.getText() + "'," + Constants.startSer + ", " +
                        Constants.startNum + ", '" + lgnEdit.getText() + "', '" + pswEdit.getText() + "')");
                break;
            case Constants.staffDirector:
                executeStript(Insert.insertDirector + "'" + fioEdit.getText() + "'," + Constants.startSer + ", " +
                        Constants.startNum + ", '" + lgnEdit.getText() + "', '" + pswEdit.getText() + "')");
                break;
            case Constants.staffDuty:
                executeStript(Insert.insertDuty + "'" + fioEdit.getText() + "'," + Constants.startSer + ", " +
                        Constants.startNum + ", '" + lgnEdit.getText() + "', '" + pswEdit.getText() + "')");
                break;
            case Constants.staffPatrolOff:
                executeStript(Insert.insertPatrolOfficer + "'" + fioEdit.getText() + "'," + Constants.startSer + ", " +
                        Constants.startNum + ", '" + lgnEdit.getText() + "', '" + pswEdit.getText() + "', " +
                        "'" + rankEdit.getText() + "')");
                break;
        }
        refreshTable("");
    }

    public void changeStaffAction(ActionEvent actionEvent) {
        switch (WorkName.getText()) {
            case Constants.staffAdmin:
                /*executeStript(Update.updateAdmin + Update.setFioAdmin + "'" + fioEdit.getText() + "', " +
                        Update.setLgnAdmin + "'" + lgnEdit.getText() + "', " + Update.setPswAdmin + "'" + pswEdit.getText() + "'" +
                        Select.where + Update.whereAdmin + idSelect);*/
                msg.setText("Нельзя изменить данные админа");
                break;
            case Constants.staffAccountant:
                executeStript(Update.updateAccountant + Update.setFioAccountant + "'" + fioEdit.getText() + "', " +
                        Update.setLgnAccountant + "'" + lgnEdit.getText() + "', " + Update.setPswAccountant + "'" + pswEdit.getText() + "'" +
                        Select.where + Update.whereAccountantId + idSelect);
                break;
            case Constants.staffCustSerrv:
                executeStript(Update.updateServOff + Update.setFioServOff + "'" + fioEdit.getText() + "', " +
                        Update.setLgnServOff + "'" + lgnEdit.getText() + "', " + Update.setPswServOff + "'" + pswEdit.getText() + "'" +
                        Select.where + Update.whereServOffId + idSelect);
                break;
            case Constants.staffDirector:
                executeStript(Update.updateDirector + Update.setFioDirector + "'" + fioEdit.getText() + "', " +
                        Update.setLgnDirector + "'" + lgnEdit.getText() + "', " + Update.setPswDirector + "'" + pswEdit.getText() + "'" +
                        Select.where + Update.whereDirectorId + idSelect);
                break;
            case Constants.staffDuty:
                executeStript(Update.updateOperator + Update.setFioOperator + "'" + fioEdit.getText() + "', " +
                        Update.setLgnOperator + "'" + lgnEdit.getText() + "', " + Update.setPswOperator + "'" + pswEdit.getText() + "'" +
                        Select.where + Update.whereOperatorId + idSelect);
                break;
            case Constants.staffPatrolOff:
                executeStript(Update.updatePatrOff + Update.setFioPatrOff + "'" + fioEdit.getText() + "', " +
                        Update.setLgnPatrOff + "'" + lgnEdit.getText() + "', " + Update.setPswPatrOff + "'" + pswEdit.getText() + "', " +
                        Update.setRankPatrOff + "'" + rankEdit.getText() + "' " +
                        Select.where + Update.wherePatrOffId + idSelect);
                break;
        }
        refreshTable("");
    }

    public void delStaffAction(ActionEvent actionEvent) {
        switch (WorkName.getText()) {
            case Constants.staffAdmin:
                //executeStript(Delete.deleteAdmin + Select.where + Update.whereAdmin + idSelect);
                msg.setText("Нельзя изменять администраторов");
                break;
            case Constants.staffAccountant:
                executeStript(Delete.deleteAccountant + Select.where + Update.whereAccountantId + idSelect);
                break;
            case Constants.staffCustSerrv:
                executeStript(Delete.deleteCustService + Select.where + Update.whereServOffId + idSelect);
                break;
            case Constants.staffDirector:
                executeStript(Delete.deleteDirector + Select.where + Update.whereDirectorId + idSelect);
                break;
            case Constants.staffDuty:
                executeStript(Delete.deleteDuty + Select.where + Update.whereOperatorId + idSelect);
                break;
            case Constants.staffPatrolOff:
                executeStript(Delete.deletePatrolOff + Select.where + Update.wherePatrOffId + idSelect);
                break;
        }
        refreshTable("");
    }

    public void searchAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";

        switch (WorkName.getText()) {
            case Constants.staffAdmin:
                if (isFioSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setFioAdmin + "'" + fioSearch.getText() + "'";
                if (isLgnSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setLgnAdmin + "'" + lgnSearch.getText() + "'";
                if (isPswSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setPswAdmin + "'" + pswSearch.getText() + "'";
                break;
            case Constants.staffAccountant:
                if (isFioSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setFioAccountant + "'" + fioSearch.getText() + "'";
                if (isLgnSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setLgnAccountant + "'" + lgnSearch.getText() + "'";
                if (isPswSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setPswAccountant + "'" + pswSearch.getText() + "'";
                break;
            case Constants.staffCustSerrv:
                if (isFioSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setFioServOff + "'" + fioSearch.getText() + "'";
                if (isLgnSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setLgnServOff + "'" + lgnSearch.getText() + "'";
                if (isPswSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setPswServOff + "'" + pswSearch.getText() + "'";
                break;
            case Constants.staffDirector:
                if (isFioSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setFioDirector + "'" + fioSearch.getText() + "'";
                if (isLgnSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setLgnDirector + "'" + lgnSearch.getText() + "'";
                if (isPswSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setPswDirector + "'" + pswSearch.getText() + "'";
                break;
            case Constants.staffDuty:
                if (isFioSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setFioOperator + "'" + fioSearch.getText() + "'";
                if (isLgnSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setLgnOperator + "'" + lgnSearch.getText() + "'";
                if (isPswSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setPswOperator + "'" + pswSearch.getText() + "'";
                break;
            case Constants.staffPatrolOff:
                if (isFioSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setFioPatrOff + "'" + fioSearch.getText() + "'";
                if (isLgnSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setLgnPatrOff + "'" + lgnSearch.getText() + "'";
                if (isPswSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setPswPatrOff + "'" + pswSearch.getText() + "'";
                if (isRankSearch.isSelected())
                    addSqlQuestion += Select.and + Update.setRankPatrOff + "'" + rankSearch.getText() + "'";
                break;
        }
        refreshTable(addSqlQuestion);
    }

    public void exitAction(ActionEvent actionEvent) {
        Main.closeWnd(btnExit);
    }

    public void clearSearch(ActionEvent actionEvent) {
        refreshTable("");
    }
}
