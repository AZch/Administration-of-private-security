package sample.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Constants;
import sample.Main;
import sample.Scripts.Delete;
import sample.Scripts.Insert;
import sample.Scripts.Select;
import sample.Scripts.Update;
import sample.Tables.Client;
import sample.Tables.Dogovor;
import sample.Tables.ObjectOfProtect;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CustServiceUIController {
    public Label FIOLabel;
    public DatePicker DataCreateEdit;
    public DatePicker DataEndEdit;
    public TextField LivesEdit;
    public TextField FIOClientEdit;
    public TextField serialEdit;
    public TextField numberEdit;

    public TextField AddressLivingEdit;
    public SplitMenuButton editSobst;
    public MenuItem editSobstSvidetel;
    public MenuItem editSobstVipiska;
    public TextField NumDogEdit;
    public CheckBox isSearchPayPeriod;
    public CheckBox isSearchAddress;
    public CheckBox isSearchFIO;
    public CheckBox isSearchPeopleLives;
    public CheckBox isSearchSistem;
    public CheckBox isSearchDoc;
    public CheckBox isSearchTypeObj;
    public CheckBox isSearchDataEndStart;
    public CheckBox isSearchDataCreateStart;
    public DatePicker DataCreateSearchFin;
    public DatePicker DataEndSearchFin;
    public CheckBox isSearchDataCreateFin;
    public CheckBox isSearchDataEndFin;
    public DatePicker DataCreateSearchStart;
    public DatePicker DataEndSearchStart;
    public Button CancelSearch;
    public Label Messedge;
    private String selecteditSobst;

    public TextField AddressObjectEdit;
    public SplitMenuButton editTypeObject;
    public MenuItem editTypeKvartira;
    public MenuItem editTypeGarage;
    private String selecteditTypeObject;
    public TextField SistemObjectEdit;

    public TextField AddressSearch;
    public TextField FIOClientSearch;
    public TextField LivesSearch;
    public TextField PSistemSearch;
    public SplitMenuButton searchSobst;
    public MenuItem searchSobstSvidetel;
    public MenuItem searchSobstVipiska;
    private String selectsearchSobst;
    public SplitMenuButton searchType;
    public MenuItem searchTypeKvartira;
    public MenuItem searchTypeGarage;
    private String selectsearchType;
    public SplitMenuButton payPeriod;
    public MenuItem payPeriodMonth;
    public MenuItem payPeriodDecade;
    public MenuItem payPeriodYear;
    private String selectpayPeriod;
    public SplitMenuButton payPeriodSearch;
    public MenuItem searchpayPeriodMonth;
    public MenuItem searchpayPeriodDecade;
    public MenuItem searchpayPeriodYear;
    private String selectpayPeriodSearch;

    private ObservableList<String> AllFIOOper = FXCollections.observableArrayList();
    public ChoiceBox<String> AllOperator;

    private ObservableList<String> AllSerPath = FXCollections.observableArrayList();
    public ChoiceBox<String> AllPath;

    private ObservableList<String> AllDogClient = FXCollections.observableArrayList();
    public ChoiceBox<String> DogClientEdit;

    private ObservableList<String> AllDogAddress = FXCollections.observableArrayList();
    public ChoiceBox<String> DogAddressEdit;

    // поля таблицы клиент
    private ObservableList<Client> ClientsData = FXCollections.observableArrayList();
    public TableView<Client> ClientTable;
    public TableColumn<Client, String> FIOClient;
    public TableColumn<Client, Integer> Serial;
    public TableColumn<Client, Integer> Number;
    public TableColumn<Client, String> AddressClient;
    public TableColumn<Client, String> DocClient;

    // поля таблицы договор
    private ObservableList<Dogovor> DogovorsData = FXCollections.observableArrayList();
    public TableView<Dogovor> DogovorTable;
    public TableColumn<Dogovor, String> NumDog;
    public TableColumn<Dogovor, String> PayPeriod;
    public TableColumn<Dogovor, String> DataCreate;
    public TableColumn<Dogovor, String> DataEnd;
    public TableColumn<Dogovor, Integer> Lives;


    // поля таблицы объект охраны
    private ObservableList<ObjectOfProtect> ObjProtectsData = FXCollections.observableArrayList();
    public TableView<ObjectOfProtect> ObjProtectTable;
    public TableColumn<ObjectOfProtect, String> AddressObject;
    public TableColumn<ObjectOfProtect, String> TypeObject;
    public TableColumn<ObjectOfProtect, String> SistemonObject;

    private Long idCustService = Long.valueOf("0");
    private String fio = "";
    private Long idCurClient = Long.valueOf("0");
    private Long idDog = Long.valueOf("0");
    private Long idCurObj = Long.valueOf("0");
    private Long idCurOper = Long.valueOf("0");
    private Long idCurPath = Long.valueOf("0");



    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);


    @FXML
    private void initialize() {
        //initTableActPay("");
        FIOLabel.setText(fio);

        initTableDogovor();
        refreshDogovorTable("");

        initTableClient();
        refreshClientTable("");

        initTableObject();
        refreshObjectTable("");

        initDogClient();
        initDogAddress();

        initOperator();
        initPath();
    }

    // задание начальных данных
    public void setStartData(Long id, String fio)
    {
        this.idCustService = id;
        this.fio = fio;

        initialize();
    }

    private void initOperator()
    {
        // инициализация выборки договора
        AllFIOOper.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getOperator);
            while (rs != null && rs.next()) {
                AllFIOOper.add(rs.getString(Select.DataOperatorFIO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AllOperator.setItems(AllFIOOper);
        AllOperator.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getOperator + Select.where +
                            Select.getDataOperatorFIO + "'" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        idCurOper = rs.getLong(Select.dataOperatorID);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //refreshDog();
            }
        });
    }

    private void initPath()
    {
        // инициализация выборки договора
        AllSerPath.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getPath);
            while (rs != null && rs.next()) {
                AllSerPath.add(rs.getString(Select.dataPathSER));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        AllPath.setItems(AllSerPath);
        AllPath.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getPath + Select.where +
                            Select.getDataSerPath + "'" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        idCurPath = rs.getLong(Select.dataPathID);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //refreshDog();
            }
        });
    }

    private void initDogClient()
    {
        // инициализация выборки договора
        AllDogClient.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getClient);
            while (rs != null && rs.next()) {
                AllDogClient.add(rs.getString(Select.dataClientFIO));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DogClientEdit.setItems(AllDogClient);
        DogClientEdit.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getClientId + Select.where +
                            Select.getDataClientFIO + "'" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        idCurClient = rs.getLong(Select.dataClientId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //refreshDog();
            }
        });
    }

    private void initDogAddress()
    {
        // инициализация выборки договора
        AllDogAddress.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getObj);
            while (rs != null && rs.next()) {
                AllDogAddress.add(rs.getString(Select.dataObjAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DogAddressEdit.setItems(AllDogAddress);
        DogAddressEdit.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    ResultSet rs = null;
                    rs = Main.getStmt().executeQuery(Select.getObjId + Select.where +
                            Select.getDataObjAddress + "'" + newSelection + "'");
                    if (rs != null && rs.next()) {
                        idCurObj = rs.getLong(Select.dataObjId);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //refreshDog();
            }
        });
    }

    private void initTableClient()
        {
            // инициализации таблицы клиентов
            FIOClient.setCellValueFactory(new PropertyValueFactory<Client, String>(Client.columnFIO));
            Serial.setCellValueFactory(new PropertyValueFactory<Client, Integer>(Client.columnSerial));
            Number.setCellValueFactory(new PropertyValueFactory<Client, Integer>(Client.columnNumber));
            AddressClient.setCellValueFactory(new PropertyValueFactory<Client, String>(Client.columnAddress));
            DocClient.setCellValueFactory(new PropertyValueFactory<Client, String>(Client.columnDocClient));


            ClientTable.setItems(ClientsData);

            ClientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    idCurClient = newSelection.getId();
                    FIOClientEdit.setText(newSelection.getFIOClient());
                    serialEdit.setText(String.valueOf(newSelection.getSerial()));
                    numberEdit.setText(String.valueOf(newSelection.getNumber()));
                    AddressLivingEdit.setText(newSelection.getAddressClient());
                    editSobst.setText(newSelection.getDocClient());
                    selecteditSobst = newSelection.getDocClient();
                    refreshObjectTable("");
                    DogClientEdit.setValue(newSelection.getFIOClient());
                    //DogAddressEdit = newSelection.getAddressClient();
                }
            });
        }

        private void initTableObject()
        {
            // инициализации таблицы объектов охраны
            AddressObject.setCellValueFactory(new PropertyValueFactory<ObjectOfProtect, String>(ObjectOfProtect.columnAddressObject));
            TypeObject.setCellValueFactory(new PropertyValueFactory<ObjectOfProtect, String>(ObjectOfProtect.columnTypeObject));
            SistemonObject.setCellValueFactory(new PropertyValueFactory<ObjectOfProtect, String>(ObjectOfProtect.columnSistemonObject));


            ObjProtectTable.setItems(ObjProtectsData);

            ObjProtectTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    idCurObj = newSelection.getId();
                    AddressObjectEdit.setText(newSelection.getAddressObject());
                    editTypeObject.setText(newSelection.getTypeObject());
                    selecteditTypeObject = newSelection.getTypeObject();
                    SistemObjectEdit.setText(newSelection.getSistemonObject());
                    DogAddressEdit.setValue(newSelection.getAddressObject());
                }
            });
        }

        private void initTableDogovor()
        {
            //String fio = "null";
            // инициализации таблицы договоров
            NumDog.setCellValueFactory(new PropertyValueFactory<Dogovor, String>(Dogovor.columnNumDog));
            PayPeriod.setCellValueFactory(new PropertyValueFactory<Dogovor, String>(Dogovor.columnPayPeriod));
            DataCreate.setCellValueFactory(new PropertyValueFactory<Dogovor, String>(Dogovor.columnDataCreate));
            DataEnd.setCellValueFactory(new PropertyValueFactory<Dogovor, String>(Dogovor.columnDataEnd));
            Lives.setCellValueFactory(new PropertyValueFactory<Dogovor, Integer>(Dogovor.columnLives));

            DogovorTable.setItems(DogovorsData);


            DogovorTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    idDog = newSelection.getId();
                    idCurClient = newSelection.getIdClient();
                    try {
                        ResultSet rs = Main.getStmt().executeQuery(Select.getClient + Select.where + Select.getDataClientId + idCurClient);
                        String fiocl = "null";
                        if(rs != null && rs.next())
                            fiocl = rs.getString(Select.dataClientFIO);
                        rs = Main.getStmt().executeQuery(Select.getObj + Select.where + Select.getDataObjId + idCurObj);
                        String addrcl = "null";
                        if(rs != null && rs.next()) addrcl = rs.getString(Select.dataObjAddress);

                        DogClientEdit.setValue(fiocl);
                        DogAddressEdit.setValue(addrcl);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //DogClientEdit.setText(String.valueOf(idClient));
                    //DogAddressEdit.setText(String.valueOf(idObj));
                    idCurObj = newSelection.getIdObject();
                    NumDogEdit.setText(String.valueOf(newSelection.getNumDog()));
                    payPeriod.setText(newSelection.getPayPeriod());
                    selectpayPeriod = newSelection.getPayPeriod();
                    DataCreateEdit.setValue(LocalDate.parse(newSelection.getDataCreate(), formatter));
                    DataEndEdit.setValue(LocalDate.parse(newSelection.getDataEnd(), formatter));
                    LivesEdit.setText(String.valueOf(newSelection.getLives()));

                }
            });
        }


    // обновить объект
    private void refreshObjectTable(String addSqlQuestion) {
        ObjProtectsData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataObj + Select.getDataFromDog + Select.where +
                    Select.getDataDogIdCustService + idCustService + Select.and +
                    Select.getDataDogIdClient + idCurClient + Select.and +
                    Select.getDataDogIdObj + Select.dataObjId + addSqlQuestion);
            int index = 1;
            while (rs != null && rs.next()) {
                ObjProtectsData.add(new ObjectOfProtect(rs.getLong(Select.dataObjId), rs.getString(Select.dataObjAddress), rs.getString(Select.dataObjType),
                        rs.getString(Select.dataObjListSys)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // обновить клиента
    private void refreshClientTable(String addSqlQuestion) {
        ClientsData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getDataClient + Select.getDataFromDog + Select.where +
                    Select.getDataDogIdCustService + idCustService + Select.and +
                    Select.getDataDogIdClient + Select.dataClientId + addSqlQuestion);
            int index = 1;
            while (rs != null && rs.next()) {
                ClientsData.add(new Client(rs.getLong(Select.dataClientId), rs.getString(Select.dataClientFIO), rs.getInt(Select.dataClientSeries),
                        rs.getInt(Select.dataClientNum), rs.getString(Select.dataClientDoc), rs.getString(Select.dataClientAddress)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // обновить договор
    private void refreshDogovorTable(String addSqlQuestion) {
        DogovorsData.clear();
        try {
            ResultSet rs = null;
            rs = Main.getStmt().executeQuery(Select.getFullDataDog + Select.where +
                    Select.getDataDogIdCustService + idCustService + addSqlQuestion);
            int index = 1;
            while (rs != null && rs.next()) {
                DogovorsData.add(new Dogovor(rs.getLong(Select.dataDogId), rs.getLong(Select.dataDogClient), rs.getLong(Select.dataDogObject), idCustService, rs.getString(Select.dataDogSeries), rs.getString(Select.dataDogPeriod),
                        rs.getString(Select.dataDogDateStart), rs.getString(Select.dataDogDateEnd),rs.getInt(Select.dataDogPeople)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClientAction(ActionEvent actionEvent) {
        try {
            String resStr = Insert.insertClient + FIOClientEdit.getText() + Insert.comma + strToInt(serialEdit.getText()) +
                    Insert.comma + strToInt(numberEdit.getText()) + Insert.comma + selecteditSobst + Insert.comma + AddressLivingEdit.getText() + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertClient + "'" + FIOClientEdit.getText() + "'" + Insert.comma + strToInt(serialEdit.getText()) +
                    Insert.comma + strToInt(numberEdit.getText()) + Insert.comma + "'" + selecteditSobst + "'" + Insert.comma + "'" + AddressLivingEdit.getText() + "'" + Insert.rbc);
            try {
                ResultSet rs = Main.getStmt().executeQuery(Select.getClientId + Select.where + Select.getDataClientFIO+ "'" + FIOClientEdit.getText() + "'" + Select.and + Select.getDataClientSer + strToInt(serialEdit.getText()) +
                        Select.and + Select.getDataClientNum + strToInt(numberEdit.getText()) + Select.and + Select.getDataClientDoc + "'" + selecteditSobst + "'" + Select.and + Select.getDataClientAddress + "'" + AddressLivingEdit.getText() + "'");
                Long idcl = Long.valueOf("0");
                if(rs != null && rs.next())
                    idcl = rs.getLong(Select.dataClientId);
                ClientsData.add(new Client(idcl, FIOClientEdit.getText(), strToInt(serialEdit.getText()), strToInt(numberEdit.getText()), selecteditSobst, AddressLivingEdit.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Messedge.setText("Клиент добавлен");
            AddressObjectEdit.setText(AddressLivingEdit.getText());
            DogClientEdit.setValue(FIOClientEdit.getText());
            DogAddressEdit.setValue(AddressLivingEdit.getText());
            initDogClient();
            //refreshClientTable("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editClientAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Update.updateClient + Update.setClientFIO + "'" + FIOClientEdit.getText() + "'" +
                    Insert.comma + Update.setClientSeries + strToInt(serialEdit.getText()) + Insert.comma + Update.setClientNumber +
                    strToInt(numberEdit.getText()) + Insert.comma + Update.setClientDoc  + "'" + selecteditSobst + "'" + Insert.comma +
                    Update.setClientAddress + "'" + AddressLivingEdit.getText() + "'" +
                    Select.where + Update.whereIdClient + idCurClient);
            Messedge.setText("Информация о клиенте изменена");
            refreshClientTable("");
            initDogClient();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delClientAction(ActionEvent actionEvent) {
        try {
            Messedge.setText(String.valueOf(idCurClient));

            Long idob = Long.valueOf("0");
            try {
                ResultSet rs = Main.getStmt().executeQuery(Select.getDogObj + Select.where + Select.getDataDogIdCustService + idCustService + Select.and +
                        Select.getDataDogIdClient + idCurClient);
                if(rs != null && rs.next())
                    idob = rs.getLong(Select.dataObjId);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ResultSet rs = Main.getStmt().executeQuery(Select.getDog + Select.where + Select.getDataDogIdClient + idCurClient + Select.and +
                    Select.getDataDogIdObj + idob );
            if(rs == null)
            {
                Main.getStmt().executeQuery(Delete.deleteClient +
                    Select.where + Update.whereIdClient + idCurClient);
                Messedge.setText("Клиент удален");
                refreshClientTable("");
                initDogClient();
            }
            else
                Messedge.setText("Невозможно удалить клиента с действительным договором");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDogovorAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            String resStr = Insert.insertDogovor + idCurClient + Insert.comma + idCustService + Insert.comma + idCurObj + Insert.comma + NumDogEdit.getText() + Insert.comma + selectpayPeriod +
                    Insert.comma + Insert.toDate + DataCreateEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + Insert.toDate + DataEndEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + strToInt(LivesEdit.getText()) + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertDogovor + idCurClient + Insert.comma + idCustService + Insert.comma + idCurObj + Insert.comma + "'" + NumDogEdit.getText() + "'" + Insert.comma + "'" + selectpayPeriod + "'" +
                    Insert.comma + Insert.toDate + "'" + DataCreateEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + Insert.toDate + "'" + DataEndEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + strToInt(LivesEdit.getText()) + Insert.rbc);

            try {
                ResultSet rs = Main.getStmt().executeQuery(Select.getDog + Select.where + Select.getDataDogIdClient + idCurClient + Select.and +
                        Select.getDataDogIdObj + idCurObj + Select.and + Select.getDataDogSerDog + "'" + NumDogEdit.getText() + "'" + Select.and + Select.getDataDogPeriod + "'" + selectpayPeriod + "'" + Select.and +
                        Select.getDataDogPeople + strToInt(LivesEdit.getText()));
                Long idd = Long.valueOf("0");
                if(rs != null && rs.next())
                    idd = rs.getLong(Select.dataDogId);
                DogovorsData.add(new Dogovor(idd, idCurClient, idCustService, idCurObj, NumDogEdit.getText(), selectpayPeriod, DataCreateEdit.getValue().format(formatter), DataEndEdit.getValue().format(formatter), strToInt(LivesEdit.getText())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Messedge.setText("Договор добавлен");
            //refreshDogovorTable("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editDogovorAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Update.updateDogovor + Update.setDogIdClient + idCurClient + Insert.comma + Update.setDogIdCustService + idCustService + Insert.comma + Update.setDogIdObj + idCurObj + Insert.comma +
                    Update.setDogSeries + "'" + NumDogEdit.getText() + "'" + Insert.comma +
                    Update.setDogPayPeriod + "'" + selectpayPeriod + "'" + Insert.comma + Update.setDogDataStart +
                    Insert.toDate + "'" + DataCreateEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setDogDataEnd + Insert.toDate + "'" + DataEndEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + Update.setDogPeople + strToInt(LivesEdit.getText())  +
                    Select.where + Update.setDogIdCustService + idCustService + Select.and + Update.whereIdDogovor + idDog);
            Messedge.setText("Договор изменен");
            refreshDogovorTable("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delDogovorAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Delete.deleteDogovor +
                    Select.where + Update.setDogIdCustService + idCustService);
            Messedge.setText("Договор удален");
            refreshDogovorTable("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addObjectAction(ActionEvent actionEvent) {
        try {
            String resStr = Insert.insertObject + idCurOper + Insert.comma + idCurPath + Insert.comma + AddressObjectEdit.getText() + Insert.comma + selecteditTypeObject + Insert.comma + SistemObjectEdit.getText() + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertObject + idCurOper + Insert.comma + idCurPath + Insert.comma + "'" + AddressObjectEdit.getText() + "'" + Insert.comma + "'" + selecteditTypeObject + "'" +
                                             Insert.comma + "'" + SistemObjectEdit.getText() + "'" + Insert.rbc);

            try {
                ResultSet rs = Main.getStmt().executeQuery(Select.getObjId + Select.where + Select.getDataObjAddress + "'" + AddressObjectEdit.getText() + "'" + Select.and + Select.getDataObjType + "'" + selecteditTypeObject + "'" +
                        Select.and + Select.getDataObjSistem + "'" + SistemObjectEdit.getText() + "'");
                Long idob = Long.valueOf("0");
                if(rs != null && rs.next())
                    idob = rs.getLong(Select.dataObjId);
                ObjProtectsData.add(new ObjectOfProtect(idob, AddressObjectEdit.getText(),selecteditTypeObject, SistemObjectEdit.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Messedge.setText("Объект добавлен");
            initDogAddress();
            //refreshObjectTable("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editObjectAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Update.updateObject + Update.setObjAddress + "'" + AddressObjectEdit.getText() + "'" +
                    Insert.comma + Update.setObjType + "'" + selecteditTypeObject + "'" + Insert.comma + Update.setObjSistem +
                    "'" + SistemObjectEdit.getText() + "'" +
                    Select.where + Update.whereIdObject + idCurObj);
            Messedge.setText("Объект изменен");
            refreshObjectTable("");
            initDogAddress();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delObjectAction(ActionEvent actionEvent) {
        try {

            Messedge.setText(String.valueOf(idCurObj));


            ResultSet rs = Main.getStmt().executeQuery(Select.getDog + Select.where + Select.getDataDogIdObj + idCurObj );
            if(rs == null)
            {
                Main.getStmt().executeQuery(Delete.deleteObject +
                        Select.where + Update.whereIdObject + idCurObj);
                Messedge.setText("Объект удален");
                refreshObjectTable("");
                initDogAddress();
            }
            else
                Messedge.setText("Невозможно удалить объект с действительным договором");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchClientAction(ActionEvent actionEvent) {
        try {
            String addSqlQuestion = "";
            if (isSearchFIO.isSelected())
                addSqlQuestion += Select.and + Select.getDataClientFIO + "'" + FIOClientSearch.getText() + "'";
            if (isSearchDoc.isSelected())
                addSqlQuestion += Select.and + Select.getDataClientDoc + "'" + selectsearchSobst + "'";
            if (isSearchAddress.isSelected())
                addSqlQuestion += Select.and + Select.getDataClientAddress + "'" + AddressSearch.getText() + "'";
            Messedge.setText("Поиск клиента успешно завершен");
            refreshClientTable(addSqlQuestion);
        } catch (Exception e)
        {
            Messedge.setText("Ошибка при поиске клиента");
        }
    }

    public void searchDogovorAction(ActionEvent actionEvent) {
        try {
            String addSqlQuestion = "";
            if (isSearchPeopleLives.isSelected())
                addSqlQuestion += Select.and + Select.getDataDogPeople + "'" + LivesSearch.getText() + "'";
            if (isSearchPayPeriod.isSelected())
                addSqlQuestion += Select.and + Select.getDataDogPeriod + "'" + selectpayPeriodSearch + "'";

            if (isSearchDataCreateStart.isSelected())
                addSqlQuestion += Select.and + Select.notEqDataDogDataCreate + " >= '" + DataCreateSearchStart.getValue().format(formatter) + "'";
            if (isSearchDataCreateFin.isSelected())
                addSqlQuestion += Select.and + Select.notEqDataDogDataCreate + " <= '" + DataCreateSearchFin.getValue().format(formatter) + "'";
            if (isSearchDataEndStart.isSelected())
                addSqlQuestion += Select.and + Select.notEqDataDogDataEnd + " >= '" + DataEndSearchStart.getValue().format(formatter) + "'";
            if (isSearchDataEndFin.isSelected())
                addSqlQuestion += Select.and + Select.notEqDataDogDataEnd + " <= '" + DataEndSearchFin.getValue().format(formatter) + "'";
            Messedge.setText("Поиск договора успешно завершен");
            refreshDogovorTable(addSqlQuestion);
        } catch (Exception e)
        {
            Messedge.setText("Ошибка при поиске договора");
        }
    }

    public void searchObjectAction(ActionEvent actionEvent) {
        try {
            String addSqlQuestion = "";
            if (isSearchAddress.isSelected())
                addSqlQuestion += Select.and + Select.getDataObjAddress + "'" + AddressSearch.getText() + "'";
            if (isSearchTypeObj.isSelected())
                addSqlQuestion += Select.and + Select.getDataObjType + "'" + selectsearchType + "'";
            if (isSearchSistem.isSelected())
                addSqlQuestion += Select.and + Select.getDataObjSistem + "'" + PSistemSearch.getText() + "'";
            refreshObjectTable(addSqlQuestion);
            Messedge.setText("Поиск по объекту успешно завершен");
        } catch(Exception e)
        { Messedge.setText("Ошибка при поиске объекта");}
    }

    private int strToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            return -1;
        }
    }

    // изменение текста в выборке типа оплаты, по нему будет сверка и выбор типа
    public void ChangepayPeriodMonth(ActionEvent actionEvent) {
        payPeriod.setText(Constants.periodPayMonth);
        selectpayPeriod = Constants.periodPayMonth;
    }

    public void ChangepayPeriodDecade(ActionEvent actionEvent) {
        payPeriod.setText(Constants.periodPayDecade);
        selectpayPeriod = Constants.periodPayDecade;
    }

    public void ChangepayPeriodYear(ActionEvent actionEvent) {
        payPeriod.setText(Constants.periodPayYear);
        selectpayPeriod = Constants.periodPayYear;
    }
    public void ChangesearchpayPeriodMonth(ActionEvent actionEvent) {
        payPeriodSearch.setText(Constants.periodPayMonth);
        selectpayPeriodSearch = Constants.periodPayMonth;
    }

    public void ChangesearchpayPeriodDecade(ActionEvent actionEvent) {
        payPeriodSearch.setText(Constants.periodPayDecade);
        selectpayPeriodSearch = Constants.periodPayDecade;
    }

    public void ChangesearchpayPeriodYear(ActionEvent actionEvent) {
        payPeriodSearch.setText(Constants.periodPayYear);
        selectpayPeriodSearch = Constants.periodPayYear;
    }


    public void ChangeeditTypeKvartira(ActionEvent actionEvent) {
        editTypeObject.setText(Constants.TypeKvartira);
        selecteditTypeObject = Constants.TypeKvartira;
    }

    public void ChangeeditTypeGarage(ActionEvent actionEvent) {
        editTypeObject.setText(Constants.TypeGarage);
        selecteditTypeObject = Constants.TypeGarage;
    }
    public void ChangesearchTypeKvartira(ActionEvent actionEvent) {
        searchType.setText(Constants.TypeKvartira);
        selectpayPeriodSearch = Constants.TypeKvartira;
    }

    public void ChangesearchTypeGarage(ActionEvent actionEvent) {
        searchType.setText(Constants.TypeGarage);
        selectsearchType = Constants.TypeGarage;
    }



    public void ChangeeditSobstSvidetel(ActionEvent actionEvent) {
        editSobst.setText(Constants.SobstSvidetel);
        selecteditSobst = Constants.SobstSvidetel;
    }

    public void ChangeeditSobstVipiska(ActionEvent actionEvent) {
        editSobst.setText(Constants.SobstVipiska);
        selecteditSobst = Constants.SobstVipiska;
    }
    public void ChangesearchSobstSvidetel(ActionEvent actionEvent) {
        searchSobst.setText(Constants.SobstSvidetel);
        selectsearchSobst = Constants.SobstSvidetel;
    }

    public void ChangesearchSobstVipiska(ActionEvent actionEvent) {
        searchSobst.setText(Constants.SobstVipiska);
        selectsearchSobst = Constants.SobstVipiska;
    }

    public void CancelSearchAction(ActionEvent actionEvent) {
        refreshDogovorTable("");
        refreshClientTable("");
        refreshObjectTable("");
    }
}
