package sample.Tables;

public class Graphic {

    public static String columnSeries = "series";
    public static String columnDataCreate = "dateCreate";
    public static String columnDataEnd = "dataEnd";
    public static String columnShedule = "SheduleG";

    private Long idG;
    private Long idPath;
    private Long idPO;
    private String SerGr;
    private String DateCreateG;
    private String DateEndG;
    private String SheduleG;

    public Graphic(Long idG, Long idPath,Long idPO, String SerGr, String DateCreateG, String DateEndG, String SheduleG){
        this.idG = idG;
        this.idPath = idPath;
        this.idPO = idPO;
        this.SerGr = SerGr;
        this.DateCreateG = DateCreateG;
        this.DateEndG = DateEndG;
        this.SheduleG = SheduleG;
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

    public String getSerGr() {
        return SerGr;
    }

    public void setSerGr(String serGr) {
        SerGr = serGr;
    }

    public String getDateCreateG() {
        return DateCreateG;
    }

    public void setDateCreateG(String dateCreateG) {
        DateCreateG = dateCreateG;
    }

    public String getDateEndG() {
        return DateEndG;
    }

    public void setDateEndG(String dateEndG) {
        DateEndG = dateEndG;
    }

    public String getSheduleG() {
        return SheduleG;
    }

    public void setSheduleG(String sheduleG) {
        SheduleG = sheduleG;
    }

}
