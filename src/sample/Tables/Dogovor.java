package sample.Tables;

public class Dogovor {

    public static String columnNumDog = "NumDog";
    public static String columnPayPeriod = "PayPeriod";
    public static String columnDataCreate = "DataCreate";
    public static String columnDataEnd = "DataEnd";
    public static String columnLives = "Lives";

    private Long id;
    private Long idclient;
    private Long idserviceoff;
    private Long idobject;
    private String NumDog;
    private String PayPeriod;
    private String DataCreate;
    private String DataEnd;
    private int Lives;

    public Dogovor(Long id, Long idclient, Long idobject, Long idserviceoff, String NumDog, String PayPeriod, String DataCreate, String DataEnd, int Lives) {
        this.id = id;
        this.idclient = idclient;
        this.idobject = idobject;
        this.idserviceoff = idserviceoff;
        this.NumDog = NumDog;
        this.PayPeriod = PayPeriod;
        this.DataCreate = DataCreate;
        this.DataEnd = DataEnd;
        this.Lives = Lives;
    }

    // Set and get methods
    // set
    public void setId(Long id) {
        this.id = id;
    }
    public void setIdClient(Long idclient) {
        this.id = idclient;
    }
    public void setIdObject(Long idobject) {
        this.id = idobject;
    }
    public void setIdServiceOff(Long idserviceoff) {
        this.id = idserviceoff;
    }

    public void setNumDog(String NumDog) {
        this.NumDog = NumDog;
    }

    public void setPayPeriod(String PayPeriod) {
        this.PayPeriod = PayPeriod;
    }

    public void setDataCreate(String DataCreate) {
        this.DataCreate = DataCreate;
    }

    public void setDataEnd(String DataEnd) {
        this.DataEnd = DataEnd;
    }

    public void setLives(int Lives) {
        this.Lives = Lives;
    }

    // get
    public Long getId() {
        return id;
    }
    public Long getIdClient() {
        return idclient;
    }
    public Long getIdObject() {
        return idobject;
    }
    public Long getIdServiceOff() {
        return idserviceoff;
    }

    public String getNumDog() {
        return NumDog;
    }

    public String getPayPeriod() {
        return PayPeriod;
    }

    public String getDataCreate() {
        return DataCreate;
    }

    public String getDataEnd() {
        return DataEnd;
    }

    public int getLives() {
        return Lives;
    }

}
