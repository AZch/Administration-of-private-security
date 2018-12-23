package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Scripts.Update;

import java.sql.SQLException;

public class AccountUIController {
    public Label msg;
    public Button extBtn;
    public TextField fioAcc;
    public TextField lgn;
    public TextField psw;
    public TextField ser;
    public TextField num;
    public TextField rank;
    public TextField fioAdd;
    public TextField lgnAdd;
    public TextField serAdd;
    public TextField pswAdd;
    public TextField numAdd;
    public Button btnAdd;
    public TextField gun;
    public Label gunLabel;
    public Label rankLabel;
    public Label addAdminLabel;
    public Label fioLable;
    public Label lgnLabel;
    public Label pswLabel;

    private Long id;
    private String fio;
    private String lgnStr;
    private String pswStr;
    private Long numStr;
    private Long serStr;
    private String rankStr;
    private String whatAcc;
    private String gunStr;


    public void setStartData(Long id, String fio, String lgn, String psw, Long num, Long ser, String rank, String gun, String whatAcc) {
        this.id = id;
        this.fio = fio;
        this.lgnStr = lgn;
        this.pswStr = psw;
        this.numStr = num;
        this.serStr = ser;
        this.rankStr = rank;
        this.whatAcc = whatAcc;
        this.gunStr = gun;

        fioAcc.setText(fio);
        this.lgn.setText(lgn);
        this.psw.setText(psw);
        //
        this.ser = new TextField();
        this.num = new TextField();
        this.serAdd = new TextField();
        this.numAdd = new TextField();
        //
        this.ser.setText(String.valueOf(ser));
        this.num.setText(String.valueOf(num));
        this.rank.setText(rank);
        this.gun.setText(gun);


        if (!whatAcc.equals(Constants.staffAdmin)) {
            fioAdd.setVisible(false);
            lgnAdd.setVisible(false);
            serAdd.setVisible(false);
            pswAdd.setVisible(false);
            numAdd.setVisible(false);
            btnAdd.setVisible(false);
            addAdminLabel.setVisible(false);
            fioLable.setVisible(false);
            lgnLabel.setVisible(false);
            pswLabel.setVisible(false);
        }
        if (!whatAcc.equals(Constants.staffPatrolOff)) {
            this.rank.setVisible(false);
            this.gun.setVisible(false);
            gunLabel.setVisible(false);
            rankLabel.setVisible(false);
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        Main.closeWnd(extBtn);
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
                    msg.setText("Операция выполнена");
                } catch (SQLException e) {
                    msg.setText("Не удалось выполнить операцию");
                    e.printStackTrace();
                }
            }
        });

    }

    public void changeAction(ActionEvent actionEvent) {
        switch (whatAcc) {
            case Constants.staffAdmin:
                executeStript(Update.updateAdmin + Update.setFioAdmin + "'" + fioAcc.getText() + "', " +
                        Update.setLgnAdmin + "'" + lgn.getText() + "', " + Update.setPswAdmin + "'" + psw.getText() + "'" +
                        Select.where + Update.whereAdmin + id);
                break;
            case Constants.staffAccountant:
                executeStript(Update.updateAccountant + Update.setFioAccountant + "'" + fioAcc.getText() + "', " +
                        Update.setLgnAccountant + "'" + lgn.getText() + "', " + Update.setPswAccountant + "'" + psw.getText() + "'" +
                        Select.where + Update.whereAccountantId + id);
                break;
            case Constants.staffCustSerrv:
                executeStript(Update.updateServOff + Update.setFioServOff + "'" + fioAcc.getText() + "', " +
                        Update.setLgnServOff + "'" + lgn.getText() + "', " + Update.setPswServOff + "'" + psw.getText() + "'" +
                        Select.where + Update.whereServOffId + id);
                break;
            case Constants.staffDirector:
                executeStript(Update.updateDirector + Update.setFioDirector + "'" + fioAcc.getText() + "', " +
                        Update.setLgnDirector + "'" + lgn.getText() + "', " + Update.setPswDirector + "'" + psw.getText() + "'" +
                        Select.where + Update.whereDirectorId + id);
                break;
            case Constants.staffDuty:
                executeStript(Update.updateOperator + Update.setFioOperator + "'" + fioAcc.getText() + "', " +
                        Update.setLgnOperator + "'" + lgn.getText() + "', " + Update.setPswOperator + "'" + psw.getText() + "'" +
                        Select.where + Update.whereOperatorId + id);
                break;
            case Constants.staffPatrolOff:
                executeStript(Update.updatePatrOff + Update.setFioPatrOff + "'" + fioAcc.getText() + "', " +
                        Update.setLgnPatrOff + "'" + lgn.getText() + "', " + Update.setPswPatrOff + "'" + psw.getText() + "', " +
                        Update.setRankPatrOff + "'" + rank.getText() + "', " + Update.setPatrOffSerGun + "'" +  gun.getText() + "'" +
                        Select.where + Update.wherePatrOffId + id);
                break;
        }
    }

    public void addAdminAction(ActionEvent actionEvent) {
        executeStript(Insert.insertAdmin + "'" + fioAdd.getText() + "', '" + lgnAdd.getText() + "', '" + pswAdd.getText() + "')");
    }

    public void delAction(ActionEvent actionEvent) {
        switch (whatAcc) {
            case Constants.staffAdmin:
                executeStript(Delete.deleteAdmin + Select.where + Update.whereAdmin + id);
                break;
            case Constants.staffAccountant:
                executeStript(Delete.deleteAccountant + Select.where + Update.whereAccountantId + id);
                break;
            case Constants.staffCustSerrv:
                executeStript(Delete.deleteCustService + Select.where + Update.whereServOffId + id);
                break;
            case Constants.staffDirector:
                executeStript(Delete.deleteDirector + Select.where + Update.whereDirectorId + id);
                break;
            case Constants.staffDuty:
                executeStript(Delete.deleteDuty + Select.where + Update.whereOperatorId + id);
                break;
            case Constants.staffPatrolOff:
                executeStript(Delete.deletePatrolOff + Select.where + Update.wherePatrOffId + id);
                break;
        }
    }
}
