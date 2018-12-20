package sample.Tables;

public class ObjectOfProtect {

    public static String columnType = "Type";
    public static String columnAdress = "Adress";

    private Long id;
    private Long idO;
    private Long idP;
    private String Adress;
    private String Type;
    private String Securites;

    public ObjectOfProtect(Long id, Long idO, Long idP, String Adress, String Type, String Securites){
        this.id = id;
        this.idO = idO;
        this.idP = idP;
        this.Adress = Adress;
        this.Type = Type;
        this.Securites = Securites;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdO() {
        return idO;
    }

    public void setIdO(Long idO) {
        this.idO = idO;
    }

    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
        this.idP = idP;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getSecurites() {
        return Securites;
    }

    public void setSecurites(String securites) {
        Securites = securites;
    }




}
