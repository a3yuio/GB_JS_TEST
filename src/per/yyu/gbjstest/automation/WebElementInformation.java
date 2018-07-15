package per.yyu.gbjstest.automation;

public class WebElementInformation
{
    // Main Element
    public String main_Sandbox_Icon_ByXPath = "/html/body/div[5]";
    public String main_Launching_StatusCode_ById = "launchingStatusSpan";


    // Application Setting Panel Element
    public String appSetting_Panel_Btn_ByXPath = "//*[@id=\"panel-head-1\"]/h4/a";
    public String appSetting_AppID_TextArea_ById = "appId";
    public String appSetting_EnablePopup_Checkbox_ById = "enablePopUp";
    public String appSetting_Initialize_Btn_ById = "initializeBtn";


    // Launching Option Element
    public String launching_Zone_ALPHA_ByXPath = "//*[@id=\"zoneType\"]/option[1]";
    public String launching_Zone_BETA_ByXPath = "//*[@id=\"zoneType\"]/option[2]";
    public String launching_Zone_REAL_ByXPath = "//*[@id=\"zoneType\"]/option[3]";
    public String launching_ClientVersion_Testing_ByXPath = "//*[@id=\"serviceType\"]/option[1]";
    public String launching_ClientVersion_InspectInStore_ByXPath = "//*[@id=\"serviceType\"]/option[2]";
    public String launching_ClientVersion_InService_ByXPath = "//*[@id=\"serviceType\"]/option[3]";
    public String launching_ClientVersion_RecommendUpdate_ByXPath = "//*[@id=\"serviceType\"]/option[4]";
    public String launching_ClientVersion_RequireUpdate_ByXPath = "//*[@id=\"serviceType\"]/option[5]";
    public String launching_ClientVersion_OutOfService_ByXPath = "//*[@id=\"serviceType\"]/option[6]";
    public String launching_ClientVersion_Maintenance_ByXPath = "//*[@id=\"serviceType\"]/option[7]";
    public String launching_ClientVersion_Notice_ByXPath = "//*[@id=\"serviceType\"]/option[8]";


    // APIs Panel Element
    public String apis_Panel_Btn_ByXPath = "//*[@id=\"panel-head-2\"]/h4/a";
    public String apis_Panel_View_ByXPath = "//*[@id=\"apipanel\"]/div";
    public String apis_Core_Btn_ByXPath = "//*[@id=\"apipanel\"]/div/div/ul/li[1]/a";
    public String apis_Auth_Btn_ByXPath = "//*[@id=\"apipanel\"]/div/div/ul/li[2]/a";


    // Auth Menu Element
    public String auth_UserID_TextArea_ById = "userIdSpan";
    public String auth_LoginWith_Btn_ByXPath = "//*[@id=\"loginWithIDPBtn\"]";
    public String auth_Logout_Btn_ById = "logoutBtn";
    public String auth_Withdraw_Btn_ById = "withdrawBtn";
    public String auth_IdPOption_Guest_ByXPath = "//*[@id=\"idp\"]/option[1]";
    public String auth_IdPOption_Facebook_ByXPath = "//*[@id=\"idp\"]/option[2]";
    public String auth_IdPOption_Payco_ByXPath = "//*[@id=\"idp\"]/option[3]";
    public String auth_IdPOption_Naver_ByXPath = "//*[@id=\"idp\"]/option[4]";
    public String auth_IdPOption_Google_ByXPath = "//*[@id=\"idp\"]/option[5]";


    // PC Facebook Element
    public String pc_Facebook_LoginView_Login_Btn_ById = "loginbutton";
    public String pc_Facebook_LoginView_ID_TextArea_ById = "email";
    public String pc_Facebook_LoginView_PW_TextArea_ById = "pass";
    public String pc_Facebook_PermissionView_Agree_Btn_ByXPath = "//*[@id=\"u_0_4\"]/div[2]/div[1]/div[1]/button";
    public String pc_Facebook_PermissionView_Agree_Btn_ByXPath_ForSafari = "//*[@id=\"platformDialogForm\"]/div[3]/div/table/tbody/tr/td[2]/button[2]";
    public String pc_Facebook_PermissionView_Agree_Btn_ByName_ForSafari = "__CONFIRM__";


    // PC Payco Element
    public String pc_Payco_LoginView_Login_Btn_ById = "loginBtn";
    public String pc_Payco_LoginView_ID_TextArea_ById = "id";
    public String pc_Payco_LoginView_PW_TextArea_ById = "pw";
    public String pc_Payco_SecurityView_Birthday_TextArea_ById = "birthday";
    public String pc_Payco_SecurityView_Login_Btn_ById = "confirmBtn";
    public String pc_Payco_SimpleLoginView_Login_Btn_ById = "autoOauthLogin";


    // PC Naver Element
    public String pc_Naver_LoginView_Login_Btn_ByXPath = "//*[@id=\"frmNIDLogin\"]/fieldset/input";
    public String pc_Naver_LoginView_ID_TextArea_ById = "id";
    public String pc_Naver_LoginView_PW_TextArea_ById = "pw";
    public String pc_Naver_PermissionView_Agree_Btn_ByXPath = "//*[@id=\"content\"]/div[4]/div[2]/Btn";


    // PC Google Element
    public String pc_Google_LoginView_ID_TextArea_ById = "identifierId";
    public String pc_Google_LoginView_ID_Next_Btn_ByXPath = "//*[@id=\"identifierNext\"]/content";
    public String pc_Google_LoginView_PW_TextArea_ByName = "password";
    public String pc_Google_LoginView_PW_Next_BtnXPath = "//*[@id=\"passwordNext\"]/content";
    public String pc_Google_IDSelectView_OtherID_Btn_ById = "identifierLink";
    public String pc_Google_IDSelectView_RecentID_Btn_ByXPath = "//*[@id=\"view_container\"]/form/div[2]/div/div/div[1]/ul/li[1]/div";


    // Mobile Facebook Element
    public String m_Facebook_LoginView_ID_TextArea_ById = "m_login_email";
    public String m_Facebook_LoginView_PW_TextArea_ById = "m_login_password";
    public String m_Facebook_LoginView_Login_Btn_ByName = "login";
    public String m_Facebook_PermissionView_Agree_Btn_ByXPath = "//*[@id=\"u_0_9\"]";


    // Mobile Payco Element
    public String m_Payco_LoginView_ID_TextArea_ById = "id";
    public String m_Payco_LoginView_PW_TextArea_ById = "pw";
    public String m_Payco_LoginView_Login_Btn_ById = "loginBtn";
    public String m_Payco_SimpleLoginView_Login_Btn_ById = "autoOauthLogin";
}