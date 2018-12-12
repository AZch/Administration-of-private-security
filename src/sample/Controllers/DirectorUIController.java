package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DirectorUIController {
    public TextField firstNumTF;
    public TextField secNumTF;
    public Label resLabel;

    public void calcAction(ActionEvent actionEvent) {
        try {
            resLabel.setText(String.valueOf(Double.parseDouble(firstNumTF.getText()) + Double.parseDouble(secNumTF.getText())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
