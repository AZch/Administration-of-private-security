package sample.Tables;

public class Path {

    public static String columnSeries = "Series";
    public static String columnDataCreate = "DateCreate";
    public static String columnDataEnd = "DateEnd";

    private Long id;
    private Long idD;
    private String DateCreate;
    private String DateEnd;
    private String Series;
    private String Objects;

    public Path(Long id, Long idD, String DateCreate, String DateEnd, String Series, String Objects){
        this.id = id;
        this.idD = idD;
        this.DateCreate = DateCreate;
        this.DateEnd = DateEnd;
        this.Series = Series;
        this.Objects = Objects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdD() {
        return idD;
    }

    public void setIdD(Long idD) {
        this.idD = idD;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public void setDateCreate(String dateCreate) {
        DateCreate = dateCreate;
    }

    public String getDateEnd() {
        return DateEnd;
    }

    public void setDateEnd(String dateEnd) {
        DateEnd = dateEnd;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getObjects() {
        return Objects;
    }

    public void setObjects(String objects) {
        Objects = objects;
    }

}
