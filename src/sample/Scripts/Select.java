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
    public static final String dataDogPeople = "peoplecount_dog";
    public static final String getDataDog = "select iddogovor, payperiod_dog, to_char(datastart_dog, '" + Constants.dateFormat + "') datastart_dog, " +
            "to_char(dataend_dog, '" + Constants.dateFormat + "') dataend_dog, series_dog from dogovor";
    public static final String getFullDataDog = "select iddogovor, payperiod_dog, to_char(datastart_dog, '" + Constants.dateFormat + "') datastart_dog, " +
            "to_char(dataend_dog, '" + Constants.dateFormat + "') dataend_dog, series_dog, peoplecount_dog from dogovor";
    public static final String getDataDogIdDog = "iddogovor = ";
    public static final String getDataDogSerDog = "series_dog = ";
    public static final String notEqDataDogDataCreate = "to_char(datastart_dog, '" + Constants.dateFormat + "')";
    public static final String notEqDataDogDataEnd = "to_char(dataend_dog, '" + Constants.dateFormat + "')";
    public static final String getDataDogPeople = "peoplecount_dog = ";
    public static final String getDataDogPeriod = "payperiod_dog = ";

    public static final String dataClientId = "idclient";
    public static final String dataClientFIO = "fio_client";
    public static final String dataClientSeries = "series_client";
    public static final String dataClientNum = "num_client";
    public static final String dataClientDoc = "doc_client";
    public static final String dataClientAddress = "address_client";
    public static final String getDataClient = "select idclient, fio_client, series_client, num_client, doc_client, address_client from client";
    public static final String getDataClientId = "idclient = ";
    public static final String getDataClientFIO = "fio_client = ";
    public static final String getDataClientDoc = "doc_client = ";
    public static final String getDataClientAddress = "address_client = ";

    public static final String dataObjId = "idobject";
    //public static final String dataObjIdOper = "operator_idoperator";
    //public static final String dataObjIdPath = "path_idpath";
    public static final String dataObjAddress = "address_obj";
    public static final String dataObjType = "type_obj";
    public static final String dataObjListSys = "listsecsys_obj";
    public static final String getDataObj = "select idobject, address_obj, type_obj, listsecsys_obj from client";
    public static final String getDataObjId = "idobject = ";
    public static final String getDataObjAddress = "address_obj = ";
    public static final String getDataObjType = "type_obj = ";
    public static final String getDataObjSistem = "listsecsys_obj = ";


    public static final String dataCustServiceID = "IDSERVICEOFF";
    public static final String dataCustServiceFIO = "fio_so";
    public static final String dataCustServiceNUM = "num_so";
    public static final String dataCustServiceSer = "series_so";
    public static final String getDataCustService = "select IDSERVICEOFF, fio_so, series_so, num_so from serviceofficier";
    public static final String getDataCustServiceLgn = "login_so = ";
    public static final String getDataCustServicePsw = "password_so = ";

}
