package sample.Tables;

public class PatrolOfficer {
    private Long Id;
    private String Fio;
    private String Series;
    private String Num;
    private String Login;
    private String Passw;
    private String Rank;
    private String SerG;


    public PatrolOfficer(Long Id, String Fio, String Series, String Num, String Login, String Passw, String Rank, String SerG) {
        this.Id = Id;
        this.Fio =Fio;
        this.Series = Series;
        this.Num = Num;
        this.Login = Login;
        this.Passw = Passw;
        this.Rank = Rank;
        this.SerG = SerG;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFio() {
        return Fio;
    }

    public void setFio(String fio) {
        Fio = fio;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassw() {
        return Passw;
    }

    public void setPassw(String passw) {
        Passw = passw;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        Rank = rank;
    }

    public String getSerG() {
        return SerG;
    }

    public void setSerG(String serG) {
        SerG = serG;
    }

}
