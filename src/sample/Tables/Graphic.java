package sample.Tables;

public class Graphic {

    public static String columnSeries = "Series";
    public static String columnDataCreate = "DateCreate";
    public static String columnDataEnd = "DateEnd";

    private Long idG;
    private Long idPath;
    private Long idPO;
    private String Series;
    private String DateCreate;
    private String DateEnd;
    private String Shedule;

    public Graphic(Long idG, Long idPath,Long idPO, String Series, String DateCreate, String DateEnd, String Shedule){
        this.idG = idG;
        this.idPath = idPath;
        this.idPO = idPO;
        this.Series = Series;
        this.DateCreate = DateCreate;
        this.DateEnd = DateEnd;
        this.Shedule = Shedule;
    }

    public Long getIdG() {
        return idG;
    }

    public void setIdG(Long idG) {
        this.idG = idG;
    }

    public Long getIdPath() {
        return idPath;
    }

    public void setIdPath(Long idPath) {
        this.idPath = idPath;
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

    public void setSeries(String Series) {
        Series = Series;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String DateCreate) {
        DateCreate = DateCreate;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = DateEnd;
    }

    public String getShedule() {
        return Shedule;
    }

    public void setShedule(String Shedule) {
        Shedule = Shedule;
    }

}
