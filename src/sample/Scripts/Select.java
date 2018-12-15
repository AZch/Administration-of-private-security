package sample.Scripts;

public class Select {
    public static final String where = " where ";
    public static final String and = " and ";

    public static final String dataAccountantID = "IDACCOUNTANT";
    public static final String dataAccountantFIO = "fio_acc";
    public static final String dataAccountantNUM = "num_acc";
    public static final String dataAccountantSer = "series_acc";
    public static final String getDataAccountant = "select IDACCOUNTANT, fio_acc, series_acc, num_acc from ACCOUNTANT";
    public static final String getDataAccountantLgn = "login_acc = ";
    public static final String getDataAccountantPsw = "password_acc = ";

    public static final String dataActPayingDateCreate = "datacreate_aop";
    public static final String dataActPayingDatePay = "datapay_aop";
    public static final String getDataActPaying = "select datacreate_aop, datapay_aop from ACTOFPAYING";
    public static final String getDataActPayingIdAcc = "accountant_idaccountant = ";

}
