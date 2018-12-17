package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Select;
import sample.Tables.Graphic;
import sample.Tables.ObjetsPath;
import sample.Tables.Path;
import sample.Tables.Shedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class PatrolOfficerUIController {

    //Поля таблицы графика патрулирования
    private ObservableList<Graphic> GraphicData = FXCollections.observableArrayList();
    public TableView<Graphic> GraphicTable;
    public TableColumn<Graphic, String> SeriesGraphicC;
    public TableColumn<Graphic, String> DateCreateC;
    public TableColumn<Graphic, String> DateEndC;

    //Поля таблицы расписания
    private ObservableList<Shedule> SheduleData = FXCollections.observableArrayList();
    public TableView<Shedule> SheduleTable;
    public TableColumn<Shedule, String> SheduleC;

    // Поля таблицы маршрута патрулирования
    private ObservableList<Path> PathData = FXCollections.observableArrayList();
    public TableView<Path> PathTable;
    public TableColumn<Path, String> SeriesPathC;
    public TableColumn<Path, String> DataCreateC;
    public TableColumn<Path, String> DataEndC;

    //Поля таблицы объектов патруля
    private ObservableList<ObjetsPath> ObjectsPathData = FXCollections.observableArrayList();
    public TableView<ObjetsPath> ObjectsPathTable;
    public TableColumn<ObjetsPath, String> StreetC;
    public TableColumn<ObjetsPath, String> HomeC;

    @FXML
    private Label FIO;
    @FXML
    private Label Rank;
    @FXML
    public Label SerGun;

    //Данные по Патрульному
    private String fioPO = "";
    private String rankPO = "";
    private String serGUN = "";
    private Long idPO = Long.valueOf("0");

    private Long idSelectGraphic= Long.valueOf("0");

    private void initialize() {
        FIO.setText("ФИО: " + fioPO);
        Rank.setText("Звание: " + rankPO);
        SerGun.setText("Табельный номер оружия: " + serGUN);

        refreshTableGraphic();
        initGraphicTable();
    }

    // задание начальных данных
    void setStartData(Long id, String fio, String rank, String sergun) {
        this.idPO = id;
        this.fioPO = fio;
        this.rankPO = rank;
        this.serGUN = sergun;
        initialize();
    }

    // инициализации таблицы с договорами
    private void refreshTableGraphic() {
        GraphicData.clear();

        try {
            ResultSet rs = null;

            rs = Main.getStmt().executeQuery(Select.getDataGraphic + Select.where + Select.getDataGraphicIDPO + idPO + "");

            while (rs != null && rs.next()) {
                GraphicData.add(new Graphic(
                        rs.getLong(Select.dataGraphicID),
                        rs.getLong(Select.dataGraphicIDP),
                        rs.getLong(Select.dataGraphicIDPO),
                        rs.getString(Select.dataGraphicSER),
                        rs.getString(Select.dataGraphicDateCreate),
                        rs.getString(Select.dataGraphicDateEnd),
                        rs.getString(Select.dataGraphicSHED))
                );
            }

            initGraphicTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // инициализации таблицы
    private void initGraphicTable() {
        SeriesGraphicC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnSeries));
        DateCreateC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnDataCreate));
        DateEndC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnDataEnd));
        GraphicTable.setItems(GraphicData);

        GraphicTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                initSheduleTable(newSelection);

                idSelectGraphic = newSelection.getIdPath();
                refreshPathTable();
                initPathTable();
            }
        });
    }

    // обновить строки таблицы расписания
    private void initSheduleTable(Graphic cur) {
        SheduleData.clear();
        String[] obj = cur.getSheduleG().split(" \\| ");
        for (String s : obj) {
            SheduleData.add(new Shedule(s.replace('>', 'и')));
        }
        SheduleC.setCellValueFactory(new PropertyValueFactory<Shedule, String>(Shedule.columnStrS));
        SheduleTable.setItems(SheduleData);
    }


    // обновить строки таблицы маршрутов
    private void refreshPathTable() {
        PathData.clear();

        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPath + Select.where + Select.getDataPathID + idSelectGraphic + "");

            while (rs != null && rs.next()) {
                PathData.add(new Path(
                        rs.getLong(Select.dataPathID),
                        rs.getLong(Select.dataPathIDD),
                        rs.getString(Select.dataPathDateCreate),
                        rs.getString(Select.dataPathDateEnd),
                        rs.getString(Select.dataPathSER),
                        rs.getString(Select.dataPathOBJ))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // инициализация таблицы маршрутов
    private void initPathTable() {
        SeriesPathC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnSeries));
        DataCreateC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnDataCreate));
        DataEndC.setCellValueFactory(new PropertyValueFactory<Path, String>(Path.columnDataEnd));
        PathTable.setItems(PathData);

        PathTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                initObjectsPathTable(newSelection);
            }
        });
    }

    // обновить строки таблицы объектов патруля
    private void initObjectsPathTable(Path cur) {
        ObjectsPathData.clear();
        String[] obj = cur.getObjects().split(": ");
        for (String s : obj) {
            String[] obj_setings = s.split("-");
            ObjectsPathData.add(new ObjetsPath(obj_setings[0] + " " + obj_setings[1], obj_setings[2]));
        }
        StreetC.setCellValueFactory(new PropertyValueFactory<ObjetsPath, String>(ObjetsPath.columnStreetOP));
        HomeC.setCellValueFactory(new PropertyValueFactory<ObjetsPath, String>(ObjetsPath.columnHomeOP));

        ObjectsPathTable.setItems(ObjectsPathData);
    }

}
