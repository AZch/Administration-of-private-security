package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import sample.Main;
import sample.Scripts.Select;
import sample.Tables.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatrolOfficerUIController {

    //Шрифт
    public Font x1;
    public Font x2;

    //Поля таблицы графика патрулирования
    private ObservableList<Graphic> GraphicData = FXCollections.observableArrayList();
    public TableView<Graphic> GraphicTable;
    public TableColumn<Graphic, String> SeriesGC;
    public TableColumn<Graphic, String> DataCreateGC;
    public TableColumn<Graphic, String> DataEndGC;

    //Поля таблицы расписания
    private ObservableList<Shedule> SheduleData = FXCollections.observableArrayList();
    public TableView<Shedule> SheduleTable;
    public TableColumn<Shedule, String> SheduleC;

    // Поля таблицы маршрута патрулирования
    private ObservableList<Path> PathData = FXCollections.observableArrayList();
    public TableView<Path> PathTable;
    public TableColumn<Path, String> SeriesPC;
    public TableColumn<Path, String> DataCreatePC;
    public TableColumn<Path, String> DataEndPC;

    //Поля таблицы объектов патруля
    private ObservableList<ObjetsPath> ObjectsPathData = FXCollections.observableArrayList();
    public TableView<ObjetsPath> ObjectsPathTable;
    public TableColumn<ObjetsPath, String> StreetC;
    public TableColumn<ObjetsPath, String> HomeC;

    //Поля таблицы Заявки на обследование
    private ObservableList<Request> RequestData = FXCollections.observableArrayList();
    public TableView<Request> RequestTable;
    public TableColumn<Request, String> SeriesRC;
    public TableColumn<Request, String> TypeC;
    public TableColumn<Request, String> DataCreateRC;
    public TextArea NoteRequest;

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
        refreshTableRequest();
    }

    // задание начальных данных
    void setStartData(Long id, String fio, String rank, String sergun) {
        this.idPO = id;
        this.fioPO = fio;
        this.rankPO = rank;
        this.serGUN = sergun;
        initialize();
    }

    // инициализации таблицы с Графика
    private void refreshTableGraphic() {
        GraphicData.clear();

        try {

            ResultSet rs = Main.getStmt().executeQuery(Select.getDataGraphic + Select.where + Select.getDataGraphicIDPO + idPO + "");

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

    // инициализации таблицы Графика
    private void initGraphicTable() {
        SeriesGC.setCellValueFactory(new PropertyValueFactory<>(Graphic.columnSeries));
        DataCreateGC.setCellValueFactory(new PropertyValueFactory<>(Graphic.columnDataCreate));
        DataEndGC.setCellValueFactory(new PropertyValueFactory<>(Graphic.columnDataEnd));
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

    // инициализации таблицы с Заявки
    private void refreshTableRequest() {
        RequestData.clear();

        try {
            ResultSet rs = Main.getStmt().executeQuery(Select.getDataRequest + Select.where + Select.getDataRequestPO + idPO + "");

            while (rs != null && rs.next()) {
                RequestData.add(new Request(
                        rs.getLong(Select.dataRequestID),
                        rs.getLong(Select.dataRequestidOoP),
                        rs.getLong(Select.dataRequestidO),
                        rs.getLong(Select.dataRequestidPO),
                        rs.getString(Select.dataRequestSER),
                        rs.getString(Select.dataRequestTYPE),
                        rs.getLong(Select.dataRequestFINE),
                        rs.getString(Select.dataRequestDataCreate),
                        rs.getString(Select.dataRequestNotes)
                ));
            }

            initRequestTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // инициализации таблицы Заявки
    private void initRequestTable() {
        SeriesRC.setCellValueFactory(new PropertyValueFactory<>(Request.columnSeries));
        TypeC.setCellValueFactory(new PropertyValueFactory<>(Request.columnType));
        DataCreateRC.setCellValueFactory(new PropertyValueFactory<>(Request.columnDataCreate));
        RequestTable.setItems(RequestData);

        RequestTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                NoteRequest.clear();
                NoteRequest.appendText(newSelection.getNotes());
            }
        });
    }

    // обновить строки таблицы расписания
    private void initSheduleTable(Graphic cur) {
        SheduleData.clear();
        String[] obj = cur.getShedule().split(" \\| ");
        for (String s : obj) {
            SheduleData.add(new Shedule(s.replace('>', 'и')));
        }
        SheduleC.setCellValueFactory(new PropertyValueFactory<>(Shedule.columnStr));
        SheduleTable.setItems(SheduleData);
    }


    // обновить строки таблицы маршрутов
    private void refreshPathTable() {
        PathData.clear();

        try {
            ResultSet rs = Main.getStmt().executeQuery(Select.getDataPath + Select.where + Select.getDataPathID + idSelectGraphic + "");

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
        SeriesPC.setCellValueFactory(new PropertyValueFactory<>(Path.columnSeries));
        DataCreatePC.setCellValueFactory(new PropertyValueFactory<>(Path.columnDataCreate));
        DataEndPC.setCellValueFactory(new PropertyValueFactory<>(Path.columnDataEnd));
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
        StreetC.setCellValueFactory(new PropertyValueFactory<>(ObjetsPath.columnStreetOP));
        HomeC.setCellValueFactory(new PropertyValueFactory<>(ObjetsPath.columnHomeOP));

        ObjectsPathTable.setItems(ObjectsPathData);
    }

}
