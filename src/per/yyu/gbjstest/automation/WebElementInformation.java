package per.yyu.gbjstest.automation;

public class WebElementInformation
{
    // Main Element
    public String main_Sandbox_Icon_ByXpath = "/html/body/div[5]";
    public String main_Launching_StatusCode_ById = "launchingStatusSpan";
    public String main_Launching_StatusCode_ByXpath = "//*[@id=\"launchingStatusSpan\"]";


    // Application Setting Panel Element
    public String appSetting_Panel_Btn_ByXpath = "//*[@id=\"panel-head-1\"]/h4/a";
    public String appSetting_AppID_TextArea_ById = "appId";
    public String appSetting_EnablePopup_Checkbox_ById = "enablePopUp";
    public String appSetting_Initialize_Btn_ById = "initializeBtn";


    // Launching Option Element
    public String launching_Zone_ALPHA_ByXpath = "//*[@id=\"zoneType\"]/option[1]";
    public String launching_Zone_BETA_ByXpath = "//*[@id=\"zoneType\"]/option[2]";
    public String launching_Zone_REAL_ByXpath = "//*[@id=\"zoneType\"]/option[3]";
    public String launching_ClientVersion_Testing_ByXpath = "//*[@id=\"serviceType\"]/option[1]";
    public String launching_ClientVersion_InspectInStore_ByXpath = "//*[@id=\"serviceType\"]/option[2]";
    public String launching_ClientVersion_InService_ByXpath = "//*[@id=\"serviceType\"]/option[3]";
    public String launching_ClientVersion_RecommendUpdate_ByXpath = "//*[@id=\"serviceType\"]/option[4]";
    public String launching_ClientVersion_RequireUpdate_ByXpath = "//*[@id=\"serviceType\"]/option[5]";
    public String launching_ClientVersion_OutOfService_ByXpath = "//*[@id=\"serviceType\"]/option[6]";
    public String launching_ClientVersion_Maintenance_ByXpath = "//*[@id=\"serviceType\"]/option[7]";
    public String launching_ClientVersion_Notice_ByXpath = "//*[@id=\"serviceType\"]/option[8]";


    // APIs Panel Element
    public String apis_Panel_Btn_ByXpath = "//*[@id=\"panel-head-2\"]/h4/a";
    public String apis_Panel_View_ByXpath = "//*[@id=\"apipanel\"]/div/div/ul";
    public String apis_Core_Btn_ByXpath = "//*[@id=\"apipanel\"]/div/div/ul/li[1]/a";
    public String apis_Auth_Btn_ByXpath = "//*[@id=\"apipanel\"]/div/div/ul/li[2]/a";



    // Auth Menu Element
    public String auth_UserID_TextArea_ById = "userIdSpan";
    public String auth_LoginWith_Btn_ByXpath = "//*[@id=\"loginWithIDPBtn\"]";
    public String auth_Logout_Btn_ById = "logoutBtn";
    public String auth_Withdraw_Btn_ById = "withdrawBtn";
    public String auth_IdPOption_Guest_ByXpath = "//*[@id=\"idp\"]/option[1]";
    public String auth_IdPOption_Facebook_ByXpath = "//*[@id=\"idp\"]/option[2]";
    public String auth_IdPOption_Payco_ByXpath = "//*[@id=\"idp\"]/option[3]";
    public String auth_IdPOption_Naver_ByXpath = "//*[@id=\"idp\"]/option[4]";
    public String auth_IdPOption_Google_ByXpath = "//*[@id=\"idp\"]/option[5]";


    // PC Facebook Element
    public String pc_Facebook_LoginView_Login_Btn_ById = "loginbutton";
    public String pc_Facebook_LoginView_ID_TextArea_ById = "email";
    public String pc_Facebook_LoginView_PW_TextArea_ById = "pass";
    public String pc_Facebook_PermissionView_Agree_Btn_ByXpath = "//*[@id=\"u_0_4\"]/div[2]/div[1]/div[1]/button";
    public String pc_Facebook_PermissionView_Agree_Btn_ByXpath_ForSafari = "//*[@id=\"platformDialogForm\"]/div[3]/div/table/tbody/tr/td[2]/button[2]";
    public String pc_Facebook_PermissionView_Agree_Btn_ByName_ForSafari = "__CONFIRM__";


    // PC Payco Element
    public String pc_Payco_LoginView_Login_Btn_ById = "loginBtn";
    public String pc_Payco_LoginView_ID_TextArea_ById = "id";
    public String pc_Payco_LoginView_PW_TextArea_ById = "pw";
    public String pc_Payco_SecurityView_Birthday_TextArea_ById = "birthday";
    public String pc_Payco_SecurityView_Login_Btn_ById = "confirmBtn";
    public String pc_Payco_SimpleLoginView_Login_Btn_ById = "autoOauthLogin";


    // PC Naver Element
    public String pc_Naver_LoginView_Login_Btn_ByXpath = "//*[@id=\"frmNIDLogin\"]/fieldset/input";
    public String pc_Naver_LoginView_ID_TextArea_ById = "id";
    public String pc_Naver_LoginView_PW_TextArea_ById = "pw";
    public String pc_Naver_PermissionView_Agree_Btn_ByXpath = "//*[@id=\"content\"]/div[4]/div[2]/Btn";


    // PC Google Element
    public String pc_Google_LoginView_ID_TextArea_ById = "identifierId";
    public String pc_Google_LoginView_ID_Next_Btn_ByXpath = "//*[@id=\"identifierNext\"]";
    public String pc_Google_LoginView_PW_TextArea_ByName = "password";
    public String pc_Google_LoginView_PW_Next_Btn_ById = "passwordNext";
    public String pc_Google_LoginView_PW_Next_Btn_ByXpath = "//*[@id=\"passwordNext\"]";
    public String pc_Google_IDSelectView_OtherID_Btn_ById = "identifierLink";
    public String pc_Google_IDSelectView_RecentID_Btn_ByXpath = "//*[@id=\"view_container\"]/form/div[2]/div/div/div[1]/ul/li[1]/div";
    public String pc_Google_OLD_LoginView_ID_TextArea_ById = "Email";
    public String pc_Google_OLD_LoginView_ID_Next_Btn_ById = "next";
    public String pc_Google_OLD_LoginView_PW_TextArea_ById = "Passwd";
    public String pc_Google_OLD_LoginView_PW_Login_Btn_ById = "signIn";
    public String pc_Google_OLD_Permisiion_Agree_Btn_ById = "submit_approve_access";


    // Mobile Android Facebook Element
    public String m_a_Facebook_LoginView_ID_TextArea_ById = "m_login_email";
    public String m_a_Facebook_LoginView_PW_TextArea_ById = "m_login_password";
    public String m_a_Facebook_LoginView_Login_Btn_ByName = "login";
    public String m_a_Facebook_PermissionView_Agree_Btn_ByXpath = "//*[@id=\"u_0_9\"]";


    // Mobile Android Payco Element
    public String m_a_Payco_LoginView_ID_TextArea_ById = "id";
    public String m_a_Payco_LoginView_PW_TextArea_ById = "pw";
    public String m_a_Payco_LoginView_Login_Btn_ById = "loginBtn";
    public String m_a_Payco_SimpleLoginView_Login_Btn_ById = "autoOauthLogin";


    // Mobile Android Naver Element
    public String m_a_Naver_LoginView_ID_TextArea_ById = "id";
    public String m_a_Naver_LoginView_PW_TextArea_ById = "pw";
    public String m_a_Naver_LoginView_Login_Btn_ById = "login_submit";
    public String m_a_Naver_PermissionView_Agree_Btn_ByXpath = "//*[@id=\"content\"]/div[4]/div[2]/button";


    // Mobile Android Google Element
    public String m_a_Google_LoginView_ID_TextArea_ById = "identifierId";
    public String m_a_Google_LoginView_ID_Next_Btn_ById = "identifierNext";
    public String m_a_Google_LoginView_PW_TextArea_ByName = "password";
    public String m_a_Google_LoginView_PW_Next_Btn_ById = "passwordNext";
    public String m_a_Google_IDSelectView_OtherID_Btn_ById = "identifierLink";
    public String m_a_Google_IDSelectView_RecentId_Btn_ByXpath = "//*[@id=\"view_container\"]/form/div[2]/div/div/div[1]/ul/li[1]/div";
}