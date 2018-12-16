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
            ResultSet rs = null, rs2 = null;
            rs = Main.getStmt().executeQuery(Select.getDataAccountant + Select.where +
                    Select.getDataAccountantLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataAccountantPsw + "\'" + pswUser.getText() + "\'");
            if (rs != null && rs.next()) {
                openAccountatnForm(rs.getLong(Select.dataAccountantID), rs.getString(Select.dataAccountantFIO),
                        rs.getLong(Select.dataAccountantNUM), rs.getLong(Select.dataAccountantSer));
            }
            rs2 = Main.getStmt().executeQuery(Select.getDataCustService + Select.where +
                    Select.getDataCustServiceLgn + "\'" + lgnUser.getText() + "\'" + Select.and +
                    Select.getDataCustServicePsw + "\'" + pswUser.getText() + "\'");
            if (rs2 != null && rs2.next()) {
                openCustServiceForm(rs.getLong(Select.dataCustServiceID), rs.getString(Select.dataCustServiceFIO),
                        rs.getLong(Select.dataCustServiceNUM), rs.getLong(Select.dataCustServiceSer));
            }
        }catch (SQLException e) {
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
}
