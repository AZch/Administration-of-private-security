package sample.Scripts;

public class Update {
    // add configuration
    public static String set = " set ";
  
    //update act pay
    public static String updateActPay = "update actofpaying ";
    public static String setDateCreate = "datacreate_aop = ";
    public static String setDatePay = "datapay_aop = ";
    public static String whereIdActPay = "idactpay = ";
    // update loa
    public static String updateLOA = "update lineofact set ";
    public static String setLoaDogovorId = "dogovor_iddogovor = ";
    public static String setLoaDataFact = "datafact_loa = ";
    public static String setLoaDataSuppose = "datasuppose_loa = ";
    public static String setLoaPayment = "payment_loa = ";
    public static String setLoaPayType = "paytype_loa = ";
    public static String whereIdLOA = "idiineact = ";
    // update client 
    public static String updateClient = "update client set ";
    public static String setClientFIO =  "fio_client = ";
    public static String setClientSeries =  "series_client = ";
    public static String setClientNumber =  "num_client = ";
    public static String setClientDoc =  "doc_client = ";
    public static String setClientAddress =  "address_client = ";
    public static String whereIdClient = "idclient = ";
    // update dogovor
    public static String updateDogovor = "update dogovor set ";
    public static String setDogSeries =  "series_dog = ";
    public static String setDogPayPeriod =  "payperiod_dog = ";
    public static String setDogDataStart =  "datastart_dog = ";
    public static String setDogDataEnd =  "dataend_dog = ";
    public static String setDogPeople =  "peoplecount_dog = ";
    public static String whereIdDogovor = "iddogovor = ";
    public static String setDogIdClient = "client_idclient = ";
    public static String setDogIdObj = "objectofprotect_idobject = ";
    public static String setDogIdCustService = "serviceofficier_idserviceoff = ";
    //update object
    public static String updateObject = "update objectofprotect set ";
    public static String setObjAddress =  "address_obj = ";
    public static String setObjType =  "type_obj = ";
    public static String setObjSistem =  "listsecsys_obj = ";
    public static String whereIdObject = "idobject = ";
    // update path
    public static String updatePath = "update path ";
    public static String setPathIdDir = "director_iddirector = ";
    public static String setPathDateCreate = "datecreate_path = ";
    public static String setPathDateEnd = "dateend_path = ";
    public static String setPathSeries = "series_path = ";
    public static String setPathListObj = "listobj_path = ";
    public static String wherePathId = "idpath = ";
    // update graph
    public static String updateGraph = "update graphic ";
    public static String setGraphIdPath = "path_idpath = ";
    public static String setGraphPatrOff = "patrolofficier_idpatroloff = ";
    public static String setGraphDateCreate = "datecreate_graph = ";
    public static String setGraphDateEnd = "dateend_graph = ";
    public static String setShedule = "shedule_graph = ";
    public static String setSerGraph = "series_graph = ";
    public static String whereGrapId = "idgraphic = ";
    // update request
    public static String updateRequest = "update request ";
    public static String setRequestIDOoP = "objectofprotect_idobject = ";
    public static String setRequestidO = "operator_idoperator = ";
    public static String setRequestIDPO = "patrolOfficier_idpatroloff = ";
    public static String setRequestSER = "series_req = ";
    public static String setRequestTYPE = "type_req = ";
    public static String setRequestFINE = "fine_req = ";
    public static String setRequestDataCreate = "datecreate_req = ";
    public static String setRequestNOTES = "notes_req = ";
    public static String whereRequestId = "idrequest = ";
    // update director
    public static String updateDirector = "update director set ";
    public static String setFioDirector = "fio_dir = ";
    public static String setSeriesDirector = "series_dir = ";
    public static String setNumDirector = "num_dir = ";
    public static String setLgnDirector = "login_dir = ";
    public static String setPswDirector = "password_dir = ";
    public static String whereDirectorId = "iddirector = ";
    // update admin
    public static String updateAdmin = "update admin set ";
    public static String setFioAdmin = "fio_admin = ";
    public static String setLgnAdmin = "lgn_admin = ";
    public static String setPswAdmin = "psw_admin = ";
    public static String whereAdmin = "idadmin = ";
    // update cust service
    public static String updateServOff = "update serviceofficier set ";
    public static String setFioServOff = "fio_so = ";
    public static String setSeriesServOff = "series_so = ";
    public static String setNumServOff = "num_so = ";
    public static String setLgnServOff = "login_so = ";
    public static String setPswServOff = "password_so = ";
    public static String whereServOffId = "idserviceoff = ";
    // update accountant
    public static String updateAccountant = "update accountant set ";
    public static String setFioAccountant = "fio_acc = ";
    public static String setSeriesAccountant = "series_acc = ";
    public static String setNumAccountant = "num_acc = ";
    public static String setLgnAccountant = "login_acc = ";
    public static String setPswAccountant = "password_acc = ";
    public static String whereAccountantId = "idaccountant = ";
    // update duty
    public static String updateOperator = "update operator set ";
    public static String setFioOperator = "fio_oper = ";
    public static String setSeriesOperator = "series_oper = ";
    public static String setNumOperator = "num_oper = ";
    public static String setLgnOperator = "login_oper = ";
    public static String setPswOperator = "password_oper = ";
    public static String whereOperatorId = "idoperator = ";
    // update patrol officer
    public static String updatePatrOff = "update patrolofficier set ";
    public static String setFioPatrOff = "fio_po = ";
    public static String setSeriesPatrOff = "series_po = ";
    public static String setNumPatrOff = "num_po = ";
    public static String setLgnPatrOff = "login_po = ";
    public static String setPswPatrOff = "password_po = ";
    public static String setRankPatrOff = "rank_po = ";
    public static String wherePatrOffId = "idpatroloff = ";
    public static String setPatrOffSerGun = "seriesgun_po = ";
}
