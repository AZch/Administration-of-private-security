package sample.Tables;

public class Path {

    public static String columnSeries = "series";
    public static String columnDataCreate = "dateCreate";
    public static String columnDataEnd = "dataEnd";
    public static String columnObjects = "objects";

    private Long id;
    private Long idDirector;
    private String dateCreate;
    private String dateEnd;
    private String series;
    private String objects;

    public Path(Long id, Long idDirector, String dateCreate, String dateEnd, String series, String objects){
        this.id = id;
        this.idDirector = idDirector;
        this.dateCreate = dateCreate;
        this.dateEnd = dateEnd;
        this.series = series;
        this.objects = objects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Long idDirector) {
        this.idDirector = idDirector;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getObjects() {
        return objects;
    }

    public void setObjects(String objects) {
        this.objects = objects;
    }

}
