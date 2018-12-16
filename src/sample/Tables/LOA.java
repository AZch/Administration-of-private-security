package sample.Tables;

public class LOA {
    public static String columnDateSuppose = "dateSuppose";
    public static String columnDateFact = "dateFact";
    public static String columnPayment = "payment";
    public static String columnTypePay = "payType";

    private Long id;
    private Long idDogovor;
    private String dateFact;
    private String dateSuppose;
    private int payment;
    private int factPay;
    private String payType;

    public LOA (Long id, Long idDogovor, String dateFact, String dateSuppose, int payment, int factPay, String payType) {
        this.id = id;
        this.idDogovor = idDogovor;
        this.dateFact = dateFact;
        this.dateSuppose = dateSuppose;
        this.payment = payment;
        this.factPay = factPay;
        this.payType = payType;
    }

    // Set and get methods
    // set
    public void setId(Long id) {
        this.id = id;
    }

    public void setDateFact(String dateFact) {
        this.dateFact = dateFact;
    }

    public void setIdDogovor(Long idDogovor) {
        this.idDogovor = idDogovor;
    }

    public void setDateSuppose(String dateSuppose) {
        this.dateSuppose = dateSuppose;
    }

    public void setFactPay(int factPay) {
        this.factPay = factPay;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    // get
    public String getDateSuppose() {
        return dateSuppose;
    }

    public int getFactPay() {
        return factPay;
    }

    public int getPayment() {
        return payment;
    }

    public Long getId() {
        return id;
    }

    public Long getIdDogovor() {
        return idDogovor;
    }

    public String getDateFact() {
        return dateFact;
    }

    public String getPayType() {
        return payType;
    }
}
