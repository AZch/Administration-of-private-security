package sample.Scripts;

import sample.Constants;

public class Insert {
    public static String comma = ", ";
    public static String rbc = ")";
    public static String toDate =  "to_date(";
    public static String formatDate = "'" + Constants.dateFormat + "'";

    public static String insertActPay = "insert into actofpaying (accountant_idaccountant, datacreate_aop, datapay_aop) " +
            "values(";
    public static String insertLineOfAct = "insert into lineofact (actofpaying_idactpay, dogovor_iddogovor, datafact_loa, " +
            "datasuppose_loa, payment_loa, paytype_loa) values (";
    public static String insertClient = "insert into client (fio_client, series_client, num_client, doc_client, address_client) " +
            "values(";
    public static String insertObject = "insert into objectofprotect (operator_idoperator, path_idpath, address_obj, type_obj, listsecsys_obj) " +
            "values(";
    public static String insertDogovor = "insert into dogovor (client_idclient, serviceofficier_idserviceoff, objectofprotect_idobject, series_dog, payperiod_dog, datastart_dog, dataend_dog, peoplecount_dog) " +
            "values(";

    public static String insertPath = "insert into path (director_iddirector, datecreate_path, dateend_path, series_path, " +
            "listobj_path) values (";
    public static String insertGraphic = "insert into graphic (path_idpath, patrolofficier_idpatroloff, series_graph, " +
            "datecreate_graph, dateend_graph, shedule_graph) values (";
}
