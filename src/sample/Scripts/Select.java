package sample.Scripts;

import sample.Constants;

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

    public static final String dataDirID = "iddirector";
    public static final String dataDirFIO = "fio_dir";
    public static final String dataDirNUM = "num_dir";
    public static final String dataDirSer = "series_dir";
    public static final String getDataDir = "select iddirector, fio_dir, series_dir, num_dir from director";
    public static final String getDataDirLgn = "login_dir = ";
    public static final String getDataDirPsw = "password_dir = ";

    public static final String dataActPayingDateCreate = "datacreate_aop";
    public static final String dataActPayingDatePay = "datapay_aop";
    public static final String dataActPayingDateId = "idactpay";
    public static final String getDataActPaying = "select to_char(datacreate_aop, '" + Constants.dateFormat + "') datacreate_aop, " +
            "to_char(datapay_aop, '" + Constants.dateFormat + "') datapay_aop, idactpay from ACTOFPAYING";
    public static final String getDataActPayingIdAcc = "accountant_idaccountant = ";
    public static final String notEqDataActPayingDatePay = "to_char(datapay_aop, '" + Constants.dateFormat + "')";
    public static final String notEqDataActPayingDateCreate = "to_char(datacreate_aop, '" + Constants.dateFormat + "')";

    public static final String dataLOAID = "idiineact";
    public static final String dataLOAidDog = "dogovor_iddogovor";
    public static final String dataLOADateFact = "datafact_loa";
    public static final String dataLOADateSuppose = "datasuppose_loa";
    public static final String dataLOAPayment = "payment_loa";
    public static final String dataLOAFacPay = "factpay_loa";
    public static final String dataLOAPayType = "paytype_loa";
    public static final String getDataLOA = "select idiineact, dogovor_iddogovor, to_char(datafact_loa, '" + Constants.dateFormat + "') datafact_loa, " +
            "to_char(datasuppose_loa, '" + Constants.dateFormat + "') datasuppose_loa, payment_loa, factpay_loa, paytype_loa from lineofact";
    public static final String getDataLOAidAct = "actofpaying_idactpay = ";
    public static final String notEqDataLOADateFact = "to_char(datafact_loa, '" + Constants.dateFormat + "')";
    public static final String notEqDataLOADateSuppose = "to_char(datasuppose_loa, '" + Constants.dateFormat + "')";
    public static final String getDataLOAPayType = "paytype_loa = ";
    public static final String getDataLOAPayment = "payment_loa = ";

    public static final String dataDogPeriod = "payperiod_dog";
    public static final String dataDogDateStart = "datastart_dog";
    public static final String dataDogDateEnd = "dataend_dog";
    public static final String dataDogSeries = "series_dog";
    public static final String dataDogId = "iddogovor";
    public static final String getDataDog = "select iddogovor, payperiod_dog, to_char(datastart_dog, '" + Constants.dateFormat + "') datastart_dog, " +
            "to_char(dataend_dog, '" + Constants.dateFormat + "') dataend_dog, series_dog from dogovor";
    public static final String getDataDogIdDog = "iddogovor = ";
    public static final String getDataDogSerDog = "series_dog = ";

    public static final String dataPathID = "idpath";
    public static final String dataPathDataCreate = "datecreate_path";
    public static final String dataPathDataEnd = "DATEEND_PATH";
    public static final String dataPathSeries = "series_path";
    public static final String dataPathListObj = "listobj_path";
    public static final String getDataPath = "select idpath, to_char(datecreate_path, '" + Constants.dateFormat + "') datecreate_path, " +
            "to_char(DATEEND_PATH, '" + Constants.dateFormat + "') DATEEND_PATH, series_path, listobj_path from path";
    public static final String getDataPathDirector = "DIRECTOR_IDDIRECTOR = ";
    public static final String notEqDataPathDateCretate = "to_char(datecreate_path, '" + Constants.dateFormat + "')";
    public static final String notEqPathDataEnd = "to_char(DATEEND_PATH, '" + Constants.dateFormat + "')";

    public static final String dataGraohID = "idgraphic";
    public static final String dataGraohPatrolId = "PATROLOFFICIER_IDPATROLOFF";
    public static final String dataGraohSeries = "SERIES_GRAPH";
    public static final String dataGraohDateCreate = "DATECREATE_GRAPH";
    public static final String dataGraohDateEnd = "DATEEND_GRAPH";
    public static final String dataGraohShedule = "SHEDULE_GRAPH";
    public static final String getDataGraph = "select idgraphic, PATROLOFFICIER_IDPATROLOFF, SERIES_GRAPH, " +
            "to_char(DATECREATE_GRAPH, '" + Constants.dateFormat + "') DATECREATE_GRAPH, " +
            "to_char(DATEEND_GRAPH, '" + Constants.dateFormat + "') DATEEND_GRAPH, " +
            "SHEDULE_GRAPH from graphic";
    public static final String getDataGraphPathId = "PATH_IDPATH = ";
    public static final String notEqDataGraphCretate = "to_char(DATECREATE_GRAPH, '" + Constants.dateFormat + "')";
    public static final String notEqGraphDataEnd = "to_char(DATEEND_GRAPH, '" + Constants.dateFormat + "')";

    public static final String patrolOfName = "fio_po";
    public static final String patrolOfRang = "rank_po";
    public static final String patrolOfId = "idpatroloff";
    public static final String getPatrolOff = "select idpatroloff, fio_po, rank_po from patrolofficier";
    public static final String getPatrolOffId = "idpatroloff = ";
}
