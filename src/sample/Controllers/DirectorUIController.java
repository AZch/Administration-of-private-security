package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Tables.ActPay;
import sample.Tables.Graphics;
import sample.Tables.Path;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DirectorUIController {
    public Label fio;

    private ObservableList<Path> pathData = FXCollections.observableArrayList();
    public TableView<Path> TablePath;
    public TableColumn<Path, String> DateEndPath;
    public TableColumn<Path, String> DateCreatePath;
    public TableColumn<Path, String> SerPath;
    public TableColumn<Path, String> ListObjPath;

    public DatePicker DateEndPathEdit;
    public DatePicker DateCreatePathEdit;
    public TextArea ListObjPathEdit;
    public TextField SerPathEdit;

    private ObservableList<Graphics> graphData = FXCollections.observableArrayList();
    public TableView<Graphics> TableGraphics;
    public TableColumn<Graphics, String> SerGraph;
    public TableColumn<Graphics, String> DateCreateGraph;
    public TableColumn<Graphics, String> DateEndGraph;
    public TableColumn<Graphics, String> SheduleGraph;

    public DatePicker DateCreateGraphEdit;
    public DatePicker DateEndGraphEdit;
    public TextArea SheduleGraphEdit;
    public TextField SerGraphEdit;
    public ChoiceBox PatrolOff;
    public Label RangPatrolOff;

    private Long id = Long.valueOf("0");
    private Long pathId = Long.valueOf("0");
    private Long graphId = Long.valueOf("0");
    private Long patrolOfId = Long.valueOf("0");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);

    @FXML
    private void initialize() {
    }

    // задание начальных данных
    public void setStartData(Long id, String fio) {
        initialize();
        this.fio.setText(fio);
        this.id = id;

        initTablePath();
        refreshTablePath();

        initTableGraph();
        refreshTableGraph();
    }

    private void initTablePath() {
        DateEndPath.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnDateEnd));
        DateCreatePath.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnDateCreate));
        SerPath.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnSeries));
        ListObjPath.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnListObj));

        TablePath.setItems(pathData);

        TablePath.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                pathId = newSelection.getId();
                DateCreatePathEdit.setValue(LocalDate.parse(newSelection.getDateCreate(), formatter));
                DateEndPathEdit.setValue(LocalDate.parse(newSelection.getDateEnd(), formatter));
                ListObjPathEdit.setText(newSelection.getListObj());
                SerPathEdit.setText(newSelection.getSeries());
                refreshTableGraph();
            }
        });
    }

    private void initTableGraph() {
        SerGraph.setCellValueFactory(new PropertyValueFactory<Graphics, String>(Graphics.columnSeries));
        DateCreateGraph.setCellValueFactory(new PropertyValueFactory<Graphics, String>(Graphics.columnDateCreate));
        DateEndGraph.setCellValueFactory(new PropertyValueFactory<Graphics, String>(Graphics.columnDateEnd));
        SheduleGraph.setCellValueFactory(new PropertyValueFactory<Graphics, String>(Graphics.columnShedule));

        TableGraphics.setItems(graphData);

        TableGraphics.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                graphId = newSelection.getId();
                patrolOfId = newSelection.getIdPatrol();
                DateCreateGraphEdit.setValue(LocalDate.parse(newSelection.getDateCreate(), formatter));
                DateEndGraphEdit.setValue(LocalDate.parse(newSelection.getDateEnd(), formatter));
                SheduleGraphEdit.setText(newSelection.getShedule());
                SerGraphEdit.setText(newSelection.getSeries());
            }
        });
    }

    private void refreshTablePath() {
        pathData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPath + Select.where +
                    Select.getDataPathDirector + id);
            while (rs != null && rs.next()) {
                pathData.add(new Path(rs.getLong(Select.dataPathID), rs.getString(Select.dataPathDataCreate),
                        rs.getString(Select.dataPathDataEnd), rs.getString(Select.dataPathSeries), rs.getString(Select.dataPathListObj)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTableGraph() {
        graphData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataGraph + Select.where +
                    Select.getDataGraphPathId + pathId);
            while (rs != null && rs.next()) {
                graphData.add(new Graphics(rs.getLong(Select.dataGraohID), pathId,
                        rs.getLong(Select.dataGraohPatrolId), rs.getString(Select.dataGraohSeries),
                        rs.getString(Select.dataGraohDateCreate), rs.getString(Select.dataGraohDateEnd),
                        rs.getString(Select.dataGraohShedule)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPathAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Insert.insertPath + id + Insert.comma +
                    Insert.toDate + "'" + DateCreatePathEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + "'" + DateEndPathEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    "'" + SerPathEdit.getText() + "'" + Insert.comma + "'" + ListObjPathEdit.getText() + "'" + Insert.rbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void EditPathAction(ActionEvent actionEvent) {
    }

    public void delPathAction(ActionEvent actionEvent) {
    }

    public void addGraphAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Insert.insertGraphic + pathId + Insert.comma + patrolOfId + Insert.comma + "'" + SerGraphEdit.getText() + "'" + Insert.comma +
                    Insert.toDate + "'" + DateCreateGraphEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Insert.toDate + "'" + DateEndGraphEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    "'" + SheduleGraphEdit.getText() + "'" + Insert.rbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editGraphAction(ActionEvent actionEvent) {
    }

    public void delGraphAction(ActionEvent actionEvent) {
    }
}
