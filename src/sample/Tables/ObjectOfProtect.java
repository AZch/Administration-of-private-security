package sample.Tables;

public class ObjectOfProtect {

    public static String columnType = "Type";
    public static String columnAdress = "Adress";
  
    public static String columnAddressObject = "AddressObject";
    public static String columnTypeObject = "TypeObject";
    public static String columnSistemonObject = "SistemonObject";

    private Long id;
    private Long idO;
    private Long idP;
    private String Adress;
    private String Type;
    private String Securites;

    private String AddressObject;
    private String TypeObject;
    private String SistemonObject;

    public ObjectOfProtect(Long id, Long idO, Long idP, String Adress, String Type, String Securites){
        this.id = id;
        this.idO = idO;
        this.idP = idP;
        this.Adress = Adress;
        this.Type = Type;
        this.Securites = Securites;
    }
    
    public ObjectOfProtect (Long id, String AddressObject, String TypeObject, String SistemonObject) {
        this.id = id;
        this.AddressObject = AddressObject;
        this.TypeObject = TypeObject;
        this.SistemonObject = SistemonObject;
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

    public void setAddressObject(String AddressObject) {
        this.AddressObject = AddressObject;
    }

    public void setTypeObject(String TypeObject) {
        this.TypeObject = TypeObject;
    }

    public void setSistemonObject(String SistemonObject) {
        this.SistemonObject = SistemonObject;
    }

    public Long getId() {
        return id;
    }

    public String getAddressObject() {
        return AddressObject;
    }

    public String getTypeObject() {
        return TypeObject;
    }

    public String getSistemonObject() {
        return SistemonObject;
    }
}
