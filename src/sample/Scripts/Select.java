package sample.Scripts;

import sample.Constants;

public class Select {
    public static String comma = ", ";
    public static final String where = " where ";
    public static final String and = " and ";

    //Select для Бухгалтера
    public static final String dataAccountantID = "IDACCOUNTANT";
    public static final String dataAccountantFIO = "fio_acc";
    public static final String dataAccountantNUM = "num_acc";
    public static final String dataAccountantSer = "series_acc";
    public static final String dataAccountantLgn = "login_acc";
    public static final String dataAccountantPsw = "password_acc";
    public static final String getDataAccountant = "select IDACCOUNTANT, fio_acc, series_acc, num_acc, login_acc, password_acc from ACCOUNTANT";
    public static final String getDataAccountantLgn = "login_acc = ";
    public static final String getDataAccountantPsw = "password_acc = ";

    //Select для Директора
    public static final String dataDirID = "iddirector";
    public static final String dataDirFIO = "fio_dir";
    public static final String dataDirNUM = "num_dir";
    public static final String dataDirSer = "series_dir";
    public static final String dataDirLgn = "login_dir";
    public static final String dataDirPsw = "password_dir";
    public static final String getDataDir = "select iddirector, fio_dir, series_dir, num_dir, login_dir, password_dir from director";
    public static final String getDataDirLgn = "login_dir = ";
    public static final String getDataDirPsw = "password_dir = ";

    //Select для Патрульного
    public static final String dataPatrolOfficerID = "idpatroloff";
    public static final String dataPatrolOfficerFIO = "fio_po";
    public static final String dataPatrolOfficerSER = "series_po";
    public static final String dataPatrolOfficerNUM = "num_po";
    public static final String dataPatrolOfficerRANK = "rank_po";
    public static final String dataPatrolOfficerSERG = "seriesgun_po";
    public static final String dataPatrolOfficerLgn = "login_po";
    public static final String dataPatrolOfficerPsw = "password_po";
    public static final String getDataPatrolOfficer = "select idpatroloff, fio_po, series_po, num_po, rank_po, login_po, password_po, seriesgun_po from patrolofficier";
    public static final String getDataPatrolOfficerLgn = "login_po = ";
    public static final String getDataPatrolOfficerPsw = "password_po = ";

    //Select для администратора
    public static final String dataAdminLgn = "lgn_admin";
    public static final String dataAdminPsw = "psw_admin";
    public static final String dataAdminFio = "fio_admin";
    public static final String dataAdminId = "idadmin";
    public static final String getDataAdmin = "select idadmin, fio_admin, lgn_admin, psw_admin from admin";
    public static final String getDataAdminLgn = "lgn_admin = ";
    public static final String getDataAdminPsw = "psw_admin = ";
    public static final String getDataAdminId = "idadmin = ";

    //Select для дежурного
    public static final String dataOperLgn = "login_oper";
    public static final String dataOperPsw = "password_oper";
    public static final String dataOperFio = "fio_oper";
    public static final String dataOperId = "idoperator";
    public static final String getDataDuty = "select idoperator, fio_oper, series_oper, num_oper, login_oper, " +
            "password_oper from operator";
    public static final String getDataOperLgn = "login_oper = ";
    public static final String getDataOperPsw = "password_oper = ";
    public static final String getDataOperId = "idoperator = ";

    //Select для сотрудника по работе с клиентами
    public static final String dataServOffLgn = "login_so";
    public static final String dataServOffPsw = "password_so";
    public static final String dataServOffFio = "fio_so";
    public static final String dataServOffId = "idserviceoff";
    public static final String getDataServOff = "select idserviceoff, fio_so, series_so, num_so, login_so, password_so " +
            "from serviceofficier";
    public static final String getDataServOffLgn = "login_so = ";
    public static final String getDataServOffPsw = "password_so = ";
    public static final String getDataServOffId = "idserviceoff = ";

    //Select для Акта об оплате
    public static final String dataActPayingDateCreate = "datacreate_aop";
    public static final String dataActPayingDatePay = "datapay_aop";
    public static final String dataActPayingDateId = "idactpay";
    public static final String getDataActPayingIdAcc = "accountant_idaccountant = ";
    public static final String notEqDataActPayingDatePay = "to_date(datapay_aop, '" + Constants.dateFormat + "')";
    public static final String notEqDataActPayingDateCreate = "to_date(datacreate_aop, '" + Constants.dateFormat + "')";
    public static final String getDataActPaying = "select to_char(datacreate_aop, '" + Constants.dateFormat + "') datacreate_aop, " +
            "to_char(datapay_aop, '" + Constants.dateFormat + "') datapay_aop, idactpay from ACTOFPAYING";

    //Select для Строки акта
    public static final String dataLOAID = "idiineact";
    public static final String dataLOAidDog = "dogovor_iddogovor";
    public static final String dataLOADateFact = "datafact_loa";
    public static final String dataLOADateSuppose = "datasuppose_loa";
    public static final String dataLOAPayment = "payment_loa";
    public static final String dataLOAFacPay = "factpay_loa";
    public static final String dataLOAPayType = "paytype_loa";
    public static final String getDataLOAidAct = "actofpaying_idactpay = ";

    public static final String notEqDataLOADateFact = "to_date(datafact_loa, '" + Constants.dateFormat + "')";
    public static final String notEqDataLOADateSuppose = "to_date(datasuppose_loa, '" + Constants.dateFormat + "')";

    public static final String getDataLOAPayType = "paytype_loa = ";
    public static final String getDataLOAPayment = "payment_loa = ";
    public static final String getDataLOA = "select idiineact, dogovor_iddogovor, to_char(datafact_loa, '" + Constants.dateFormat + "') datafact_loa, " +
            "to_char(datasuppose_loa, '" + Constants.dateFormat + "') datasuppose_loa, payment_loa, factpay_loa, paytype_loa from lineofact";

    //Select для Договора
    public static final String dataDogPeriod = "payperiod_dog";
    public static final String dataDogDateStart = "datastart_dog";
    public static final String dataDogDateEnd = "dataend_dog";
    public static final String dataDogSeries = "series_dog";
    public static final String dataDogId = "iddogovor";
    public static final String getDataDogIdDog = "iddogovor = ";
    public static final String getDataDogSerDog = "series_dog = ";
    public static final String DataDogPeoples = "peoplecount_dog";
    public static final String getDataDogPeoples = "select peoplecount_dog from dogovor where objectofprotect_idobject = ";
    public static final String getDataDog = "select iddogovor, payperiod_dog, to_char(datastart_dog, '" + Constants.dateFormat + "') datastart_dog, " +
            "to_char(dataend_dog, '" + Constants.dateFormat + "') dataend_dog, series_dog from dogovor";

    //Select для Патрульного
    public static final String getDataPatrolOfficerFIO = "fio_po = ";
    public static final String getDataPatrolOfficerALL = "select idpatroloff, fio_po, series_po, num_po, login_po, password_po, rank_po, seriesgun_po from patrolofficier";
    public static final String dataDogPeople = "peoplecount_dog";
    public static final String dataDogCustService = "serviceofficier_idserviceoff";
    public static final String dataDogClient = "client_idclient";
    public static final String dataDogObject = "objectofprotect_idobject";
    public static final String getDog = "select iddogovor from dogovor";
    public static final String getDogObj = "select objectofprotect_idobject from dogovor";

    public static final String getFullDataDog = "select iddogovor, client_idclient, objectofprotect_idobject, serviceofficier_idserviceoff, payperiod_dog, to_char(datastart_dog, '" + Constants.dateFormat + "') datastart_dog, " +
            "to_char(dataend_dog, '" + Constants.dateFormat + "') dataend_dog, series_dog, peoplecount_dog from dogovor";
    public static final String notEqDataDogDataCreate = "to_char(datastart_dog, '" + Constants.dateFormat + "')";
    public static final String notEqDataDogDataEnd = "to_char(dataend_dog, '" + Constants.dateFormat + "')";
    public static final String getDataDogPeople = "peoplecount_dog = ";
    public static final String getDataDogPeriod = "payperiod_dog = ";
    public static final String getDataDogIdCustService = "serviceofficier_idserviceoff = ";
    public static String getDataDogIdClient = "client_idclient = ";
    public static String getDataDogIdObj = "objectofprotect_idobject = ";
  

    public static final String dataClientId = "idclient";
    public static final String dataClientFIO = "fio_client";
    public static final String dataClientSeries = "series_client";
    public static final String dataClientNum = "num_client";
    public static final String dataClientDoc = "doc_client";
    public static final String dataClientAddress = "address_client";
    public static final String getDataClient = "select idclient, fio_client, series_client, num_client, doc_client, address_client from client";
    public static final String getClient = "select fio_client from client";
    public static final String getClientId = "select idclient from client";
    public static final String getDataClientId = "idclient = ";
    public static final String getDataClientFIO = "fio_client = ";
    public static final String getDataClientNum = "num_client = ";
    public static final String getDataClientSer = "series_client = ";
    public static final String getDataClientDoc = "doc_client = ";
    public static final String getDataClientAddress = "address_client = ";

    public static final String dataObjId = "idobject";
    public static final String dataObjAddress = "address_obj";
    public static final String dataObjType = "type_obj";
    public static final String dataObjListSys = "listsecsys_obj";
    public static final String getDataObj = "select idobject, address_obj, type_obj, listsecsys_obj from objectofprotect";
    public static final String getDataObjId = "idobject = ";
    public static final String getDataObjAddress = "address_obj = ";
    public static final String getDataObjType = "type_obj = ";
    public static final String getDataObjSistem = "listsecsys_obj = ";
    public static final String getObj = "select address_obj from objectofprotect";
    public static final String getObjId = "select idobject from objectofprotect";
    public static final String getDataFromDog = ", dogovor";

    public static final String dataCustServiceID = "IDSERVICEOFF";
    public static final String dataCustServiceFIO = "fio_so";
    public static final String dataCustServiceNUM = "num_so";
    public static final String dataCustServiceSer = "series_so";
    public static final String getDataCustService = "select IDSERVICEOFF, fio_so, series_so, num_so from serviceofficier";
    public static final String getDataCustServiceLgn = "login_so = ";
    public static final String getDataCustServicePsw = "password_so = ";

    public static final String getPath = "select idpath, series_path from path";
    public static final String getOperator = "select idoperator, fio_oper from operator";
    public static final String getDataOperatorFIO = "fio_oper = ";
    public static final String getDataSerPath = "series_path = ";
    public static final String DataOperatorFIO = "fio_oper";


    //Select для Графика патрулирования
    public static final String dataGraphicID = "idgraphic";
    public static final String dataGraphicIDP = "path_idpath";
    public static final String dataGraphicIDPO = "patrolOfficier_idpatroloff";
    public static final String dataGraphicSER = "Series_Graph";
    public static final String dataGraphicDateCreate = "DateCreate_Graph";
    public static final String dataGraphicDateEnd = "DateEnd_Graph";
    public static final String dataGraphicSHED = "Shedule_Graph";
    public static final String getDataGraphicIDPO = "patrolOfficier_idpatroloff = ";
    public static final String getDataGraphic = "select idgraphic, path_idpath, patrolOfficier_idpatroloff, Series_Graph, " +
            "to_char(DateCreate_Graph, '" + Constants.dateFormat + "') DateCreate_Graph, "  +
            "to_char(DateEnd_Graph, '" + Constants.dateFormat + "') DateEnd_Graph, Shedule_Graph from graphic";

    //Select для Маршрута патрулирования
    public static final String dataPathID = "idpath";
    public static final String dataPathIDD = "director_iddirector";
    public static final String dataPathDateCreate = "datecreate_path";
    public static final String dataPathDateEnd = "dateend_path";
    public static final String dataPathSER = "series_path";
    public static final String dataPathOBJ = "listobj_path";
    public static final String getDataPathID = "idpath = ";
    public static final String getDataPath = "select idpath, director_iddirector, to_char(datecreate_path, '" + Constants.dateFormat + "') datecreate_path, "  +
            "to_char(dateend_path, '" + Constants.dateFormat + "') dateend_path, series_path, listobj_path from path";

    //Select для Заявки на обследование
    public static final String dataRequestID = "idrequest";
    public static final String dataRequestidOoP = "objectofprotect_idobject";
    public static final String dataRequestidO = "operator_idoperator";
    public static final String dataRequestidPO = "patrolOfficier_idpatroloff";
    public static final String dataRequestSER = "series_req";
    public static final String dataRequestTYPE = "type_req";
    public static final String dataRequestFINE = "fine_req";
    public static final String dataRequestDataCreate = "datecreate_req";
    public static final String dataRequestNotes = "notes_req";
    public static final String getDataRequestPO= "patrolOfficier_idpatroloff = ";
    public static final String getDataRequestO= "operator_idoperator = ";
    public static final String getDdataRequestidOoP = "objectofprotect_idobject = ";
    public static final String getDataRequest = "select idrequest, objectofprotect_idobject, operator_idoperator, patrolOfficier_idpatroloff, series_req, type_req, fine_req," +
            " to_char(datecreate_req, '" + Constants.dateFormat + "') datecreate_req, notes_req from request";
    public static final String notEqDataRequestDateCreate = "to_date(datecreate_req, '" + Constants.dateFormat + "')";


    public static final String dataPathDataCreate = "datecreate_path";
    public static final String dataPathDataEnd = "DATEEND_PATH";
    public static final String dataPathSeries = "series_path";
    public static final String dataPathListObj = "listobj_path";
    //public static final String getDataPath = "select idpath, to_char(datecreate_path, '" + Constants.dateFormat + "') datecreate_path, " +
    //        "to_char(DATEEND_PATH, '" + Constants.dateFormat + "') DATEEND_PATH, series_path, listobj_path from path";
    public static final String getDataPathDirector = "DIRECTOR_IDDIRECTOR = ";
    public static final String notEqDataPathDateCretate = "to_date(datecreate_path, '" + Constants.dateFormat + "')";
    public static final String notEqPathDataEnd = "to_date(DATEEND_PATH, '" + Constants.dateFormat + "')";


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
    public static final String notEqDataGraphCretate = "to_date(DATECREATE_GRAPH, '" + Constants.dateFormat + "')";
    public static final String notEqGraphDataEnd = "to_date(DATEEND_GRAPH, '" + Constants.dateFormat + "')";

    public static final String patrolOfName = "fio_po";
    public static final String patrolOfRang = "rank_po";
    public static final String patrolOfId = "idpatroloff";
    public static final String getPatrolOff = "select idpatroloff, fio_po, rank_po from patrolofficier";
    public static final String getPatrolOffId = "idpatroloff = ";

    //Select для дежурного
    public static final String dataOperatorID = "idoperator";
    public static final String dataOperatorFIO  = "fio_oper";
    public static final String dataOperatorSER  = "series_oper";
    public static final String dataOperatorNUM  = "num_oper";
    public static final String getOperatorLgn = "login_oper = ";
    public static final String getOperatorPsw = "password_oper = ";
    public static final String getDataOperator = "select idoperator, fio_oper, series_oper, num_oper from operator";

    //Select для Объекта охраны
    public static final String dataObjecOfPID = "idobject";
    public static final String dataObjecOfPIDO  = "Operator_idOperator";
    public static final String dataObjecOfPIDP  = "Path_idPath";
    public static final String dataObjecOfPADRESS  = "Address_Obj";
    public static final String dataObjecOfPTYPE = "Type_Obj";
    public static final String dataObjecOfPOBJS = "ListSecSys_Obj";
    public static final String getDataObjecOfPIDO  = "Operator_idOperator = ";
    public static final String getDataObjecOfP = "select idobject, Operator_idOperator, Path_idPath, Address_Obj, Type_Obj, ListSecSys_Obj from objectofprotect";

}