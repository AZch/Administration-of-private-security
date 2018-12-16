package sample.Tables;

public class Path {
    public static String columnDateCreate = "dateCreate";
    public static String columnDateEnd = "dateEnd";
    public static String columnSeries = "series";
    public static String columnListObj = "listObj";

    private Long id;
    private String dateCreate;
    private String dateEnd;
    private String series;
    private String listObj;

    public Path(Long id, String dateCreate, String dateEnd, String series, String listObj) {
        this.id = id;
        this.dateCreate = dateCreate;
        this.dateEnd = dateEnd;
        this.series = series;
        this.listObj = listObj;
    }

    // Set and get methods
    //set
    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setListObj(String listObj) {
        this.listObj = listObj;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    //get
    public Long getId() {
        return id;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getListObj() {
        return listObj;
    }

    public String getSeries() {
        return series;
    }
}
