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

            //Открытие формы Патрульного
            rs = Main.getStmt().executeQuery(Select.getDataPatrolOfficer + Select.where +
                    Select.getDataPatrolOfficerLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataPatrolOfficerPsw + "\'" + pswUser.getText() + "\'");

            if (rs != null && rs.next()) {
                openPatrolOfficerForm(rs.getLong(Select.dataPatrolOfficerID), rs.getString(Select.dataPatrolOfficerFIO),
                        rs.getLong(Select.dataPatrolOfficerSER), rs.getLong(Select.dataPatrolOfficerNUM),
                        rs.getString(Select.dataPatrolOfficerRANK), rs.getString(Select.dataPatrolOfficerSERG));
            }

        } catch (SQLException e) {
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

    private void openPatrolOfficerForm(Long id, String fio, Long ser, Long num, String rank, String serg){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/PatrolOfficerUI.fxml"));
            AnchorPane load = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("П А Т Р У Л Ь Н Ы Й");
            Scene scene = new Scene(load);
            stage.setScene(scene);

            PatrolOfficerUIController patrolOfficerUIController = loader.getController();
            patrolOfficerUIController.setStartData(fio, rank);

            stage.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
