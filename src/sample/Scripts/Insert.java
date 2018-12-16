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
}
