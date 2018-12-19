package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Scripts.Update;
import sample.Tables.ActPay;
import sample.Tables.Graphics;
import sample.Tables.Path;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DirectorUIController {
    public Label fio;
    public DatePicker dateStartCreateSearch;
    public DatePicker dateStartEndSearch;
    public DatePicker dateEndCreateSearch;
    public DatePicker dateEndEndSearch;
    public TextField SerSearch;
    public TextArea ShedListSearch;
    public ChoiceBox PatrolOffSearch;
    public CheckBox isPatrolOffSearch;
    public CheckBox isSerSearch;
    public CheckBox isShedListSearch;
    public CheckBox isDateCreateSearchEnd;
    public CheckBox isDateEndSearchEnd;
    public CheckBox isDateCreateSearchStart;
    public CheckBox isDateEndSearchStart;

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
    private ObservableList<String> patrolData = FXCollections.observableArrayList();
    public ChoiceBox<String> PatrolOff;
    public Label RangPatrolOff;

    private Long id = Long.valueOf("0");
    private Long pathId = Long.valueOf("0");
    private Long graphId = Long.valueOf("0");
    private Long patrolOfId = Long.valueOf("0");
    private Long patrolOfIdSearch = Long.valueOf("0");

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
        refreshTablePath("");

        initTableGraph();
        refreshTableGraph("");

        initPatrolOf();
    }

    private void refreshPatrOff() {
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getPatrolOff + Select.where + Select.getPatrolOffId + patrolOfId);
            if (rs != null && rs.next()) {
                RangPatrolOff.setText(rs.getString(Select.patrolOfRang) + " " + rs.getString(Select.patrolOfName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initPatrolOf() {
        patrolData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getPatrolOff);
            while (rs != null && rs.next()) {
                patrolData.add(rs.getString(Select.patrolOfName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PatrolOff.setItems(patrolData);
        PatrolOffSearch.setItems(patrolData);
        PatrolOff.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getPatrolOff + Select.where +
                            Select.patrolOfName + "='" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        patrolOfId = rs.getLong(Select.patrolOfId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                refreshPatrOff();
            }
        });

        PatrolOffSearch.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getPatrolOff + Select.where +
                            Select.patrolOfName + "='" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        patrolOfIdSearch = rs.getLong(Select.patrolOfId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
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
                refreshTableGraph("");
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
                refreshPatrOff();
            }
        });
    }

    private void refreshTablePath(String addSqlQuest) {
        pathData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPath + Select.where +
                    Select.getDataPathDirector + id + addSqlQuest);
            while (rs != null && rs.next()) {
                pathData.add(new Path(rs.getLong(Select.dataPathID), rs.getString(Select.dataPathDataCreate),
                        rs.getString(Select.dataPathDataEnd), rs.getString(Select.dataPathSeries), rs.getString(Select.dataPathListObj)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void refreshTableGraph(String addSqlQuest) {
        graphData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataGraph + Select.where +
                    Select.getDataGraphPathId + pathId + addSqlQuest);
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
        refreshTablePath("");
    }

    public void EditPathAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Update.updatePath + Update.set + Update.setPathDateCreate +
                    Insert.toDate + "'" + DateCreatePathEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setPathDateEnd +
                    Insert.toDate + "'" + DateEndPathEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setPathIdDir + id + Insert.comma + Update.setPathSeries + "'" + SerPathEdit.getText() + "'" + Insert.comma +
                    Update.setPathListObj + "'" + ListObjPathEdit.getText() + "'" +
                    Select.where + Update.wherePathId + pathId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTablePath("");
    }

    public void delPathAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Delete.deletePath +
                    Select.where + Update.wherePathId + pathId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTablePath("");
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
        refreshTableGraph("");
    }

    public void editGraphAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Update.updateGraph + Update.set + Update.setGraphDateCreate +
                    Insert.toDate + "'" + DateCreateGraphEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setGraphDateEnd +
                    Insert.toDate + "'" + DateEndGraphEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setGraphIdPath + pathId + Insert.comma + Update.setGraphPatrOff + patrolOfId + Insert.comma +
                    Update.setShedule + "'" + SheduleGraphEdit.getText() + "'" + Insert.comma +
                    Update.setSerGraph + "'" + SerGraphEdit.getText() + "'" +
                    Select.where + Update.whereGrapId + graphId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTableGraph("");
    }

    public void delGraphAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Delete.deleteGraphic +
                    Select.where + Update.whereGrapId + graphId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTableGraph("");
    }

    public void searchPathAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isDateCreateSearchStart.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataPathDateCretate + " >= '" + dateStartCreateSearch.getValue().format(formatter) + "'";
        if (isDateCreateSearchEnd.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataPathDateCretate + " <= '" + dateEndCreateSearch.getValue().format(formatter) + "'";
        if (isDateEndSearchStart.isSelected())
            addSqlQuestion += Select.and + Select.notEqPathDataEnd + " >= '" + dateStartEndSearch.getValue().format(formatter) + "'";
        if (isDateEndSearchEnd.isSelected())
            addSqlQuestion += Select.and + Select.notEqPathDataEnd + " <= '" + dateEndEndSearch.getValue().format(formatter) + "'";
        if (isSerSearch.isSelected())
            addSqlQuestion += Select.and + Select.dataPathSeries + "='" + SerSearch.getText() + "'";
        if (isShedListSearch.isSelected())
            addSqlQuestion += Select.and + Select.dataPathListObj + "='" + ShedListSearch.getText() + "'";

        refreshTablePath(addSqlQuestion);
    }

    public void searchGraphAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isDateCreateSearchStart.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataGraphCretate + " >= '" + dateStartCreateSearch.getValue().format(formatter) + "'";
        if (isDateCreateSearchEnd.isSelected())
            addSqlQuestion += Select.and + Select.notEqDataGraphCretate + " <= '" + dateEndCreateSearch.getValue().format(formatter) + "'";
        if (isDateEndSearchStart.isSelected())
            addSqlQuestion += Select.and + Select.notEqGraphDataEnd + " >= '" + dateStartEndSearch.getValue().format(formatter) + "'";
        if (isDateEndSearchEnd.isSelected())
            addSqlQuestion += Select.and + Select.notEqGraphDataEnd + " <= '" + dateEndEndSearch.getValue().format(formatter) + "'";
        if (isSerSearch.isSelected())
            addSqlQuestion += Select.and + Select.dataGraohSeries + "='" + SerSearch.getText() + "'";
        if (isShedListSearch.isSelected())
            addSqlQuestion += Select.and + Select.dataGraohShedule + "='" + ShedListSearch.getText() + "'";
        if (isPatrolOffSearch.isSelected())
            addSqlQuestion += Select.and + Select.dataGraohPatrolId + "='" + patrolOfIdSearch + "'";

        refreshTableGraph(addSqlQuestion);
    }
}