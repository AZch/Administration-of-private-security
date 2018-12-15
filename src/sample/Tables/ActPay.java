package sample.Tables;

public class ActPay {
    public static String columnNum = "numAct";
    public static String columnDateCreate = "dateCreate";
    public static String columnDateSuppose = "dateSuppose";

    private int numAct;
    private String dateCreate;
    private String dateSuppose;
    private Long id;

    public ActPay(int numAct, String dateCreate, String dateSuppose, Long id) {
        this.numAct = numAct;
        this.dateCreate = dateCreate;
        this.dateSuppose = dateSuppose;
        this.id = id;
    }

    public ActPay() {
        numAct = 0;
        dateCreate = "";
        dateSuppose = "";
    }

    // Сеттеры и геттеры
    // set
    public void setNumAct(int numAct) {
        this.numAct = numAct;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateSuppose(String dateSuppose) {
        this.dateSuppose = dateSuppose;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // get
    public int getNumAct() {
        return numAct;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getDateSuppose() {
        return dateSuppose;
    }

    public Long getId() {
        return id;
    }
}
