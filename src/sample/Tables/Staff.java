package sample.Tables;

public class Staff {
    public static final String columnRank = "rank";
    public static final String columnFio = "fio";
    public static final String columnLgn = "lgn";
    public static final String columnPsw = "psw";


    private Long id;
    private String fio;
    private String lgn;
    private String psw;
    private String rank;

    public Staff(Long id, String fio, String lgn, String psw, String rank) {
        this.id = id;
        this.fio = fio;
        this.lgn = lgn;
        this.psw = psw;
        this.rank = rank;
    }

    // Set and Get methods
    // Set
    public void setId(Long id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setLgn(String lgn) {
        this.lgn = lgn;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    // Get
    public Long getId() {
        return id;
    }

    public String getFio() {
        return fio;
    }

    public String getLgn() {
        return lgn;
    }

    public String getPsw() {
        return psw;
    }

    public String getRank() {
        return rank;
    }
}
