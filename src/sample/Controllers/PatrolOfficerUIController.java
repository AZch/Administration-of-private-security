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
    public TableColumn<Graphic, String> SeriesGraphicC;
    public TableColumn<Graphic, String> DateCreateC;
    public TableColumn<Graphic, String> DateEndC;
    public TableColumn<Graphic, String> SheduleC;

    // Поля таблицы маршрута патрулирования
    private ObservableList<Path> PathData = FXCollections.observableArrayList();
    public TableView<Path> PathTable;
    public TableColumn<Path, String> SeriesPathC;
    public TableColumn<Path, String> DataCreateC;
    public TableColumn<Path, String> DataEndC;
    public TableColumn<Path, String> ObjPathC;

    private DatePicker dataCreateGraphic;
    private DatePicker dataEndGraphic;

    @FXML
    private Label FIO;
    @FXML
    private Label Rank;

    //Данные по Патрульному
    private String fioPO = "";
    private String rankPO = "";
    private String idPO = "";

    private Long idSelectGraphic= Long.valueOf("0");

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);

    private void initialize() {
        FIO.setText("ФИО: " + fioPO);
        Rank.setText("Звание: " + rankPO);

        initTableGraphic("");
        initMainTable();
    }

    // задание начальных данных
    void setStartData(String id, String fio, String rank) {
        this.idPO = id;
        this.fioPO = fio;
        this.rankPO = rank;
        initialize();
    }

    // инициализации таблицы с договорами
    private void initTableGraphic(String addSqlQuestion) {
        GraphicData.clear();

        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataGraphic + Select.where +
                    Select.getDataGraphicIDPO + idPO + addSqlQuestion);

            int index = 1;
            while (rs != null && rs.next()) {
                GraphicData.add(new Graphic((long) index++, rs.getLong(Select.dataGraphicIDP), rs.getLong(Select.dataGraphicIDPO),
                        rs.getString(Select.dataGraphicSER), rs.getString(Select.dataGraphicDateCreate), rs.getString(Select.dataGraphicDateEnd),
                        rs.getString(Select.dataGraphicSHED)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // инициализации таблицы
    private void initMainTable() {
        SeriesGraphicC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnSeriesG));
        DateCreateC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnDataCreate));
        DateEndC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnDataEnd));
        SheduleC.setCellValueFactory(new PropertyValueFactory<Graphic, String>(Graphic.columnShedule));

        GraphicTable.setItems(GraphicData);

        GraphicTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                idSelectGraphic = newSelection.getIdPath();
                refreshPathTable("");
                dataCreateGraphic.setValue(LocalDate.parse(newSelection.getDateCreateG(), formatter));
                dataEndGraphic.setValue(LocalDate.parse(newSelection.getDateEndG(), formatter));
            }
        });
    }

    // обновить строки табоицы маршрутов
    private void refreshPathTable(String addSqlQuestion) {
        PathData.clear();

        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataPath + Select.where +
                    Select.getDataPathID + idSelectGraphic + "");

            while (rs != null && rs.next()) {
                PathData.add(new Path(rs.getLong(Select.dataPathID), rs.getLong(Select.dataPathIDD), rs.getString(Select.dataPathDateCreate),
                        rs.getString(Select.dataPathDateEnd), rs.getString(Select.dataPathSER), rs.getString(Select.dataPathOBJ)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
