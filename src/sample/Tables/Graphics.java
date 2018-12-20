package sample.Tables;

public class Graphics {
    public static String columnSeries = "series";
    public static String columnDateCreate = "dateCreate";
    public static String columnDateEnd = "dateEnd";
    public static String columnShedule = "shedule";

    private Long id;
    private Long idPath;
    private Long idPatrol;
    private String series;
    private String dateCreate;
    private String dateEnd;
    private String shedule;

    public Graphics(Long id, Long idPath, Long idPatrol, String series, String dateCreate, String dateEnd, String shedule) {
        this.id = id;
        this.idPath = idPath;
        this.idPatrol = idPatrol;
        this.series = series;
        this.dateCreate = dateCreate;
        this.dateEnd = dateEnd;
        this.shedule = shedule;
    }

    //set and get methods
    //set
    public void setSeries(String series) {
        this.series = series;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdPath(Long idPath) {
        this.idPath = idPath;
    }

    public void setIdPatrol(Long idPatrol) {
        this.idPatrol = idPatrol;
    }

    public void setShedule(String shedule) {
        this.shedule = shedule;
    }

    //get
    public Long getId() {
        return id;
    }

    public String getSeries() {
        return series;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public Long getIdPath() {
        return idPath;
    }

    public Long getIdPatrol() {
        return idPatrol;
    }

    public String getShedule() {
        return shedule;
    }
}
