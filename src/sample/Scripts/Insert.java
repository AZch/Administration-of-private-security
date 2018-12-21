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

    public static String insertPath = "insert into path (director_iddirector, datecreate_path, dateend_path, series_path, " +
            "listobj_path) values (";
    public static String insertGraphic = "insert into graphic (path_idpath, patrolofficier_idpatroloff, series_graph, " +
            "datecreate_graph, dateend_graph, shedule_graph) values (";

    public static String insertDirector = "insert into director (fio_dir, series_dir, num_dir, login_dir, password_dir) " +
            "values (";
    public static String insertAdmin = "insert into admin (fio_admin, lgn_admin, psw_admin) " +
            "values (";
    public static String insertAccountant = "insert into accountant (fio_acc, series_acc, num_acc, login_acc, password_acc) " +
            "values (";
    public static String insertCustService = "insert into serviceofficier (fio_so, series_so, num_so, login_so, password_so) " +
            "values (";
    public static String insertDuty = "insert into operator (fio_oper, series_oper, num_oper, login_oper, password_oper) " +
            "values (";
    public static String insertPatrolOfficer = "insert into patrolofficier (fio_po, series_po, num_po, login_po, password_po, rank_po) " +
            "values (";
}
