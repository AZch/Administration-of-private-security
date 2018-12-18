package sample.Tables;

public class Client {
    public static String columnFIO = "FIOClient";
    public static String columnSerial = "Serial";
    public static String columnNumber = "Number";
    public static String columnDocClient = "DocClient";
    public static String columnAddress = "AddressClient";

    private Long id;
    private String FIOClient;
    private int Serial;
    private int Number;
    private String DocClient;
    private String AddressClient;

    public Client (Long id, String FIOClient, int Serial, int Number, String DocClient, String AddressClient) {
        this.id = id;
        this.FIOClient = FIOClient;
        this.Serial = Serial;
        this.Number = Number;
        this.AddressClient = AddressClient;
        this.DocClient = DocClient;
    }

    // Set and get methods
    // set
    public void setId(Long id) {
        this.id = id;
    }

    public void setFIOClient(String FIOClient) {
        this.FIOClient = FIOClient;
    }

    public void setSerial(int dateFact) {
        this.Serial = Serial;
    }

    public void setNumber(int idDogovor) {
        this.Number = Number;
    }

    public void setAddressClient(String AddressClient) {
        this.AddressClient = AddressClient;
    }

    public void setDocClient(String DocClient) {
        this.DocClient = DocClient;
    }

    // get
    public Long getId() {
        return id;
    }

    public String getFIOClient() {
        return FIOClient;
    }

    public int getSerial() {
        return Serial;
    }

    public int getNumber() {
        return Number;
    }

    public String getAddressClient() {
        return AddressClient;
    }

    public String getDocClient() {
        return DocClient;
    }
}
