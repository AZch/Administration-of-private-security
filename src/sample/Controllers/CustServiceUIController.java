package sample.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Long idClient = Long.valueOf("0");
    private Long idDog = Long.valueOf("0");
    private Long idObj = Long.valueOf("0");

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
    }

    // задание начальных данных
    public void setStartData(Long id, String fio)
    {
        this.idCustService = id;
        this.fio = fio;

        initialize();
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
                    //idClient = newSelection.getId();
                    FIOClientEdit.setText(newSelection.getFIOClient());
                    serialEdit.setText(String.valueOf(newSelection.getSerial()));
                    numberEdit.setText(String.valueOf(newSelection.getNumber()));
                    AddressLivingEdit.setText(newSelection.getAddressClient());
                    editSobst.setText(newSelection.getDocClient());
                    selecteditSobst = newSelection.getDocClient();
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
                    //idObj = newSelection.getId();
                    AddressObjectEdit.setText(newSelection.getAddressObject());
                    editTypeObject.setText(newSelection.getTypeObject());
                    selecteditTypeObject = newSelection.getTypeObject();
                    SistemObjectEdit.setText(newSelection.getSistemonObject());
                }
            });
        }

        private void initTableDogovor()
        {
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
                    idClient = newSelection.getIdClient();
                    idObj = newSelection.getIdObject();
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
                DogovorsData.add(new Dogovor(rs.getLong(Select.dataDogId), idClient, idObj, idCustService, rs.getString(Select.dataDogSeries), rs.getString(Select.dataDogPeriod),
                        rs.getString(Select.dataDogDateStart), rs.getString(Select.dataDogDateEnd),rs.getInt(Select.dataDogPeople)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClientAction(ActionEvent actionEvent) {
        try {
            String resStr = Insert.insertClient + Insert.comma + FIOClientEdit + Insert.comma + strToInt(serialEdit.getText()) +
                    Insert.comma + strToInt(numberEdit.getText()) + Insert.comma + selecteditSobst + Insert.comma + AddressLivingEdit + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertClient + Insert.comma +
                    "'" + FIOClientEdit + "'" + Insert.comma + strToInt(serialEdit.getText()) +
                    Insert.comma + strToInt(numberEdit.getText()) + Insert.comma + "'" + selecteditSobst + "'" + Insert.comma + "'" + AddressLivingEdit + "'" + Insert.rbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editClientAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Update.updateClient + Update.set + Update.setClientFIO + "'" + FIOClientEdit + "'" +
                    Insert.comma + Update.setClientSeries + strToInt(serialEdit.getText()) + Insert.comma + Update.setClientNumber +
                    strToInt(numberEdit.getText()) + Insert.comma + Update.setClientDoc  + "'" + selecteditSobst + "'" + Insert.comma +
                    Update.setClientAddress + "'" + AddressLivingEdit + "'" +
                    Select.where + Update.whereIdClient + Select.dataDogClient + Select.and + Update.setDogIdCustService + idCustService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delClientAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Delete.deleteClient +
                    Select.where + Update.whereIdClient + Select.dataDogClient + Select.and + Update.setDogIdCustService + idCustService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addDogovorAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            String resStr = Insert.insertDogovor + idClient + Insert.comma + idCustService + Insert.comma + idObj + Insert.comma + strToInt(NumDogEdit.getText()) + Insert.comma + selectpayPeriod +
                    Insert.comma + Insert.toDate + DataCreateEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + Insert.toDate + DataEndEdit.getValue().format(formatter) + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + strToInt(LivesEdit.getText()) + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertDogovor + idClient + Insert.comma + idCustService + Insert.comma + idObj + Insert.comma + strToInt(NumDogEdit.getText()) + Insert.comma + "'" + selectpayPeriod + "'" +
                    Insert.comma + Insert.toDate + "'" + DataCreateEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + Insert.toDate + "'" + DataEndEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + strToInt(LivesEdit.getText()) + Insert.rbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editDogovorAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Update.updateDogovor + Update.set + Update.setDogIdClient + idClient + Insert.comma + Update.setDogSeries + strToInt(NumDogEdit.getText()) + Insert.comma +
                    Update.setDogIdObj + idObj + Insert.comma  + Insert.comma + Update.setDogPayPeriod + "'" + selectpayPeriod + "'" + Insert.comma + Update.setDogDataStart +
                    Insert.toDate + "'" + DataCreateEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc + Insert.comma +
                    Update.setDogDataEnd +
                    Insert.toDate + "'" + DataEndEdit.getValue().format(formatter) + "'" + Insert.comma + Insert.formatDate + Insert.rbc +
                    Insert.comma + Update.setDogPeople + strToInt(LivesEdit.getText())  +
                    Select.where + Update.setDogIdCustService + idCustService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delDogovorAction(ActionEvent actionEvent) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.dateFormat);
            Main.getStmt().executeQuery(Delete.deleteDogovor +
                    Select.where + Update.setDogIdCustService + idCustService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addObjectAction(ActionEvent actionEvent) {
        try {
            String resStr = Insert.insertObject + idObj + Insert.comma + AddressObjectEdit + Insert.comma + selecteditTypeObject + Insert.comma + SistemObjectEdit + Insert.rbc;
            Main.getStmt().executeQuery(Insert.insertObject + idObj + Insert.comma + "'" + AddressObjectEdit + "'" + Insert.comma + "'" + selecteditTypeObject + "'" +
                                             Insert.comma + "'" + SistemObjectEdit + "'" + Insert.rbc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editObjectAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Update.updateObject + Update.set + Update.setObjAddress + "'" + AddressObjectEdit + "'" +
                    Insert.comma + Update.setObjType + "'" + selecteditTypeObject + "'" + Insert.comma + Update.setObjSistem +
                    "'" + SistemObjectEdit + "'" +
                    Select.where + Update.whereIdObject + Select.dataDogObject+ Select.and + Update.setDogIdCustService + idCustService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delObjectAction(ActionEvent actionEvent) {
        try {
            Main.getStmt().executeQuery(Delete.deleteObject +
                    Select.where + Update.whereIdObject + Select.dataDogObject+ Select.and + Update.setDogIdCustService + idCustService);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchClientAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchFIO.isSelected())
            addSqlQuestion += Select.and + Select.getDataClientFIO + "'" + FIOClientSearch + "'";
        if (isSearchDoc.isSelected())
            addSqlQuestion += Select.and + Select.getDataClientDoc + "'" + selectsearchSobst + "'";
        if (isSearchAddress.isSelected())
            addSqlQuestion += Select.and + Select.getDataClientAddress + "'" + AddressSearch + "'";
        refreshClientTable(addSqlQuestion);
    }

    public void searchDogovorAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchPeopleLives.isSelected())
            addSqlQuestion += Select.and + Select.getDataDogPeople + "'" + LivesSearch + "'";
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

        refreshDogovorTable(addSqlQuestion);
    }

    public void searchObjectAction(ActionEvent actionEvent) {
        String addSqlQuestion = "";
        if (isSearchAddress.isSelected())
            addSqlQuestion += Select.and + Select.getDataObjAddress + "'" + AddressSearch + "'";
        if (isSearchTypeObj.isSelected())
            addSqlQuestion += Select.and + Select.getDataObjType + "'" + selectsearchType + "'";
        if (isSearchSistem.isSelected())
            addSqlQuestion += Select.and + Select.getDataObjSistem + "'" + PSistemSearch + "'";
        refreshObjectTable(addSqlQuestion);
    }

    private int strToInt(String num) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {
            return -1;
        }
    }
}
