package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.Scripts.Select;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    public TextField lgnUser;
    public PasswordField pswUser;

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

    public void loginAction(ActionEvent actionEvent) {
        try {
            ResultSet rs = null;

            //Открытие формы Бухгалтера
            rs = Main.getStmt().executeQuery(Select.getDataAccountant + Select.where +
                    Select.getDataAccountantLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataAccountantPsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {
                openAccountatnForm(rs.getLong(Select.dataAccountantID), rs.getString(Select.dataAccountantFIO),
                        rs.getLong(Select.dataAccountantNUM), rs.getLong(Select.dataAccountantSer));
                return;
            }

            // открытие директора
            rs = Main.getStmt().executeQuery(Select.getDataDir + Select.where +
                    Select.getDataDirLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataDirPsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {
                openDirForm(rs.getLong(Select.dataDirID), rs.getString(Select.dataDirFIO),
                        rs.getLong(Select.dataDirNUM), rs.getLong(Select.dataDirSer));
                return;
            }

            //Открытие формы Патрульного
            rs = Main.getStmt().executeQuery(Select.getDataPatrolOfficer + Select.where +
                    Select.getDataPatrolOfficerLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataPatrolOfficerPsw + "\'" + pswUser.getText() + "\'");

            if (rs != null && rs.next()) {
                openPatrolOfficerForm(rs.getLong(Select.dataPatrolOfficerID), rs.getString(Select.dataPatrolOfficerFIO),
                        rs.getString(Select.dataPatrolOfficerRANK), rs.getString(Select.dataPatrolOfficerSERG));
                return;
            }

            // открытие формы админа
            rs = Main.getStmt().executeQuery(Select.getDataAdmin + Select.where +
                    Select.getDataAdminLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataAdminPsw + "\'" + pswUser.getText() + "\'");

            if (rs != null && rs.next()) {
                openAdminForm(rs.getLong(Select.dataAdminId), rs.getString(Select.dataAdminFio));
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
          // открытие формы Менеджера по работе с клиентами
            rs = Main.getStmt().executeQuery(Select.getDataCustService + Select.where +
                    Select.getDataCustServiceLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataCustServicePsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {
                openCustServiceForm(rs.getLong(Select.dataCustServiceID), rs.getString(Select.dataCustServiceFIO),
                        rs.getLong(Select.dataCustServiceNUM), rs.getLong(Select.dataCustServiceSer));
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
  
  private void openPatrolOfficerForm(Long id, String fio, String rank, String sergun){
        try{
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
        }catch (Exception e){
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
          }catch (Exception e) {
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
        

