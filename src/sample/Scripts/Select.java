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
    public static final String dataActPayingDateId = "idactpay";
    public static final String getDataActPaying = "select to_char(datacreate_aop, 'dd.mm.yyyy') datacreate_aop, " +
            "to_char(datapay_aop, 'dd.mm.yyyy') datapay_aop, idactpay from ACTOFPAYING";
    public static final String getDataActPayingIdAcc = "accountant_idaccountant = ";

    public static final String getDataLOAID = "idiineact";
    public static final String getDataLOAidDog = "dogovor_iddogovor";
    public static final String getDataLOADateFact = "datafact_loa";
    public static final String getDataLOADateSuppose = "datasuppose_loa";
    public static final String getDataLOAPayment = "payment_loa";
    public static final String getDataLOAFacPay = "factpay_loa";
    public static final String getDataLOAPayType = "paytype_loa";
    public static final String getDataLOA = "select idiineact, dogovor_iddogovor, to_char(datafact_loa, 'dd.mm.yyyy') datafact_loa, " +
            "to_char(datasuppose_loa, 'dd.mm.yyyy') datasuppose_loa, payment_loa, factpay_loa, paytype_loa from lineofact";
    public static final String getDataLOAidAct = "actofpaying_idactpay = ";

    public static final String dataDogPeriod = "payperiod_dog";
    public static final String dataDogDateStart = "datastart_dog";
    public static final String dataDogDateEnd = "dataend_dog";
    public static final String dataDogSeries = "series_dog";
    public static final String getDataDog = "select payperiod_dog, to_char(datastart_dog, 'dd.mm.yyyy') datastart_dog, " +
            "to_char(dataend_dog, 'dd.mm.yyyy') dataend_dog, series_dog from dogovor";
    public static final String getDataDogIdDog = "iddogovor = ";

}
