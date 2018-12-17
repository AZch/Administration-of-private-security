package sample.Tables;

public class ObjetsPath {
    public static String columnStreetOP = "StreetOP";
    public static String columnHomeOP = "HomeOP";


    private String StreetOP;
    private String HomeOP;

    public ObjetsPath(String StreetOP, String HomeOP) {
        this.StreetOP = StreetOP;
        this.HomeOP = HomeOP;
    }


    public String getStreetOP() {
        return StreetOP;
    }

    public void setStreetOP(String streetOP) {
        StreetOP = streetOP;
    }

    public String getHomeOP() {
        return HomeOP;
    }

    public void setHomeOP(String homeOP) {
        HomeOP = homeOP;
    }

}
