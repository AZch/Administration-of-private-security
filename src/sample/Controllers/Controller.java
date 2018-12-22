package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import oracle.jdbc.driver.Const;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Select;
import sample.Scripts.Update;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    public TextField lgnUser;
    public PasswordField pswUser;
    public Button exitBtn;
    public Label msg;

    private Long id;
    private String fio;
    private String lgn;
    private String psw;
    private Long num;
    private Long ser;
    private String rank;
    private String serGun;

    public void openDirectorAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/DirectorUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Б О С С");
            Scene scene = new Scene(load);
            stage.setScene(scene);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDataAcc() {
        try {
            ResultSet rs = null;

            //Открытие формы Бухгалтера
            rs = Main.getStmt().executeQuery(Select.getDataAccountant + Select.where +
                    Select.getDataAccountantLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataAccountantPsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {
                id = rs.getLong(Select.dataAccountantID);
                fio = rs.getString(Select.dataAccountantFIO);
                lgn = lgnUser.getText();
                psw = pswUser.getText();
                num = rs.getLong(Select.dataAccountantNUM);
                ser = rs.getLong(Select.dataAccountantSer);
                return Constants.staffAccountant;
            }

            // открытие директора
            rs = Main.getStmt().executeQuery(Select.getDataDir + Select.where +
                    Select.getDataDirLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataDirPsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {

                id = rs.getLong(Select.dataDirID);
                fio = rs.getString(Select.dataDirFIO);
                lgn = lgnUser.getText();
                psw = pswUser.getText();
                num = rs.getLong(Select.dataDirNUM);
                ser = rs.getLong(Select.dataDirSer);
                return Constants.staffDirector;
            }

            //Открытие формы Патрульного
            rs = Main.getStmt().executeQuery(Select.getDataPatrolOfficer + Select.where +
                    Select.getDataPatrolOfficerLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataPatrolOfficerPsw + "\'" + pswUser.getText() + "\'");

            if (rs != null && rs.next()) {
                id = rs.getLong(Select.dataPatrolOfficerID);
                fio = rs.getString(Select.dataPatrolOfficerFIO);
                lgn = lgnUser.getText();
                psw = pswUser.getText();
                rank = rs.getString(Select.dataPatrolOfficerRANK);
                serGun = rs.getString(Select.dataPatrolOfficerSERG);
                return Constants.staffPatrolOff;
            }

            // открытие формы админа
            rs = Main.getStmt().executeQuery(Select.getDataAdmin + Select.where +
                    Select.getDataAdminLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataAdminPsw + "\'" + pswUser.getText() + "\'");

            if (rs != null && rs.next()) {
                id = rs.getLong(Select.dataAdminId);
                fio = rs.getString(Select.dataAdminFio);
                lgn = lgnUser.getText();
                psw = pswUser.getText();
                return Constants.staffAdmin;
            }
            // открытие формы Менеджера по работе с клиентами
            rs = Main.getStmt().executeQuery(Select.getDataCustService + Select.where +
                    Select.getDataCustServiceLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataCustServicePsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {
                id = rs.getLong(Select.dataCustServiceID);
                fio = rs.getString(Select.dataCustServiceFIO);
                lgn = lgnUser.getText();
                psw = pswUser.getText();
                num = rs.getLong(Select.dataCustServiceNUM);
                ser = rs.getLong(Select.dataCustServiceSer);
                return Constants.staffCustSerrv;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void loginAction(ActionEvent actionEvent) {
        switch (getDataAcc()) {
            case Constants.staffAdmin:
                openAdminForm(id, fio);
                break;
            case Constants.staffAccountant:
                openAccountatnForm(id, fio, num, ser);
                break;
            case Constants.staffCustSerrv:
                openCustServiceForm(id, fio, num, ser);
                break;
            case Constants.staffDirector:
                openDirForm(id, fio, num, ser);
                break;
            case Constants.staffDuty:

                break;
            case Constants.staffPatrolOff:
                openPatrolOfficerForm(id, fio, rank, serGun);
                break;
            default:
                msg.setText("Не верный логин или пароль");
        }
    }

    private void openAdminForm(Long id, String fio) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/AdminUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("А Д М И Н");
            Scene scene = new Scene(load);
            stage.setScene(scene);

            AdminUIController adminUIController = loader.getController();
            adminUIController.setStartData(id, fio);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openAccountatnForm(Long id, String fio, Long num, Long ser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/AccountantUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Б У Х Г А Л Т Е Р");
            Scene scene = new Scene(load);
            stage.setScene(scene);

            AccountantUIController accountantUIController = loader.getController();
            accountantUIController.setStartData(id, fio);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openPatrolOfficerForm(Long id, String fio, String rank, String sergun) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/PatrolOfficerUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("П А Т Р У Л Ь Н Ы Й");
            Scene scene = new Scene(load);
            stage.setScene(scene);

            PatrolOfficerUIController patrolOfficerUIController = loader.getController();
            patrolOfficerUIController.setStartData(id, fio, rank, sergun);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openCustServiceForm(Long id, String fio, Long num, Long ser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/CustServiceUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("МЕНЕДЖЕР ПО РАБОТЕ С КЛИЕНТАМИ");
            Scene scene = new Scene(load);
            stage.setScene(scene);

            CustServiceUIController custServiceUIController = loader.getController();
            custServiceUIController.setStartData(id, fio);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void openDirForm(Long id, String fio, Long num, Long ser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/DirectorUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Б О С С");
            Scene scene = new Scene(load);
            stage.setScene(scene);

            DirectorUIController directorUIController = loader.getController();
            directorUIController.setStartData(id, fio);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setTitle("Выход");
        alert.setContentText("Выход?");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                ((Stage) (exitBtn.getScene().getWindow())).close();
            }
        });
    }


    public void AccountAction(ActionEvent actionEvent) {
        String whatAcc = getDataAcc();
        if (!whatAcc.equals("")) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/AccountUI.fxml"));
                AnchorPane load = loader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle(whatAcc);
                Scene scene = new Scene(load);
                stage.setScene(scene);

                AccountUIController accountUIController = loader.getController();
                accountUIController.setStartData(id, fio, lgn, psw, num, ser, rank, serGun, whatAcc);

                stage.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            msg.setText("Не верный логин или пароль");
    }
}
        

