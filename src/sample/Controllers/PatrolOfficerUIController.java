package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Select;
import sample.Tables.Graphic;
import sample.Tables.Path;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatrolOfficerUIController {

    //Поля таблицы графика дежурства
    private ObservableList<Graphic> GraphicData = FXCollections.observableArrayList();
    public TableView<Graphic> GraphicTable;
    public TableColumn<Graphic, String> SeriesGraphic;
    public TableColumn<Graphic, String>DateCreate;
    public TableColumn<Graphic, String>DateEnd;
    public TableColumn<Graphic, String> Shedule;

    // Поля таблицы маршрута патрулирования
    private ObservableList<Path> PathData = FXCollections.observableArrayList();
    public TableView<Path> PathTable;
    public TableColumn<Path, String> SeriesPathC;
    public TableColumn<Path, String> DataCreateC;
    public TableColumn<Path, String> DataEndC;
    public TableColumn<Path, String> ObjPathC;

    private DatePicker dataCreatePath;
    private DatePicker dataEndPath;

    @FXML
    private Label FIO;
    @FXML
    private Label Rank;

    //Данные по Патрульному
    private String fioPO = "";
    private String rankPO = "";

    private Long idSelectPath = Long.valueOf("1");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);

    private void initialize() {
        FIO.setText("ФИО: " + fioPO);
        Rank.setText("Звание: " + rankPO);

        initPathTable();
    }

    // задание начальных данных
    void setStartData(String fio, String rank) {
        this.fioPO = fio;
        this.rankPO = rank;
        initialize();
    }

    // инициализации таблицы Маршрутов
    private void initPathTable() {
        SeriesPathC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnSeries));
        DataCreateC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnDataCreate));
        DataEndC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnDataEnd));
        ObjPathC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnObjects));

        PathTable.setItems(PathData);

        PathTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idSelectPath = newSelection.getId();
                refreshPathTable();
                dataCreatePath.setValue(LocalDate.parse(newSelection.getDateCreate(), formatter));
                dataEndPath.setValue(LocalDate.parse(newSelection.getDateEnd(), formatter));
            }
        });
    }

    // обновить строки табоицы маршрутов
    private void refreshPathTable() {
        PathData.clear();

        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPath + Select.where +
                    Select.dataPathID + idSelectPath + "");

            while (rs != null && rs.next()) {
                PathData.add(new Path(rs.getLong(Select.dataPathID), rs.getLong(Select.dataPathIDD), rs.getString(Select.dataPathDateCreate),
                        rs.getString(Select.dataPathDateEnd), rs.getString(Select.dataPathSER), rs.getString(Select.dataPathOBJ)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
