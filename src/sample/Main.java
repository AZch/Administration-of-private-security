package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Locale;

public class Main extends Application {

    private static Statement stmt = null;
    private static Connection con = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
        primaryStage.setTitle("Вневедомственная охрана v1.0");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "vo", "1");
            stmt = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }

        launch(args);
    }

    public static Statement getStmt() {
        return stmt;
    }

    public static void closeWnd(Button btn) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Выход");
        alert.setTitle("Выход");
        alert.setContentText("Выход?");
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                ((Stage) (btn.getScene().getWindow())).close();
            }
        });
    }
}
