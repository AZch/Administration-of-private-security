package sample.Tables;

public class ObjectOfProtect {
    public static String columnAddressObject = "AddressObject";
    public static String columnTypeObject = "TypeObject";
    public static String columnSistemonObject = "SistemonObject";

    private Long id;
    private String AddressObject;
    private String TypeObject;
    private String SistemonObject;

    public ObjectOfProtect (Long id, String AddressObject, String TypeObject, String SistemonObject) {
        this.id = id;
        this.AddressObject = AddressObject;
        this.TypeObject = TypeObject;
        this.SistemonObject = SistemonObject;
    }

    // Set and get methods
    // set
    public void setId(Long id) {
        this.id = id;
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

    // get
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
