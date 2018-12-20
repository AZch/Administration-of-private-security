package sample.Tables;

public class ObjectsSecurity {

    public static String columnObject = "Object";

    private String Object;

    public ObjectsSecurity(String Object) {
        this.Object = Object;
    }

    public String getObject() {
        return Object;
    }

    public void setObject(String object) {
        Object = object;
    }
}
