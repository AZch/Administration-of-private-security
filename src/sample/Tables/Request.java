package sample.Tables;

public class Request {

    public static String columnSeries = "Series";
    public static String columnType = "Type";
    public static String columnDataCreate = "DateCreate";
    public static String columnFine = "Fine";

    private Long id;
    private Long idOoP;
    private Long idO;
    private Long idPO;
    private String Series;
    private String Type;
    private Long Fine;
    private String DateCreate;
    private String Notes;

    public Request(Long id, Long idOoP, Long idO, Long idPO, String Series, String Type, Long Fine, String DateCreate, String Notes){
        this.id = id;
        this.idOoP = idOoP;
        this.idO = idO;
        this.idPO = idPO;
        this.Series = Series;
        this.Type = Type;
        this.Fine = Fine;
        this.DateCreate = DateCreate;
        this.Notes = Notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdOoP() {
        return idOoP;
    }

    public void setIdOoP(Long idOoP) {
        this.idOoP = idOoP;
    }

    public Long getIdO() {
        return idO;
    }

    public void setIdO(Long idO) {
        this.idO = idO;
    }

    public Long getIdPO() {
        return idPO;
    }

    public void setIdPO(Long idPO) {
        this.idPO = idPO;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Long getFine() {
        return Fine;
    }

    public void setFine(Long fine) {
        Fine = fine;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

}
