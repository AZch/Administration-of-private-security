package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DutyUIController {
    public Label fio;
    public TableView TableObject;
    public TableColumn address;
    public TableColumn typeObj;
    public TableColumn CountPeople;
    public TableColumn listOfSecurity;
    public TableView TableRequests;
    public TableColumn SeriesReq;
    public TableColumn TypeReq;
    public TableColumn FineReq;
    public TableColumn DateCreate;
    public TableColumn Notes;
    public TextField SerReqT;
    public ChoiceBox TypeReqChoice;
    public TextField FineReqT;
    public DatePicker dateCreateReqDTP;
    public TextArea NotesReqTF;
    public ChoiceBox PatrolOffChoice;
    public Label fioPatrolOfficer;
    public Label RangPatrolOfficer;

    @FXML
    private void initialize() {
    }

    // задание начальных данных
    public void setStartData(Long id, String fio) {
        initialize();
    }

    public void addReqAction(ActionEvent actionEvent) {
    }

    public void editReqAction(ActionEvent actionEvent) {
    }

    public void delReqAction(ActionEvent actionEvent) {
    }
}
