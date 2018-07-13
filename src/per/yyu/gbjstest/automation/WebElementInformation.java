package per.yyu.gbjstest.automation;

public class WebElementInformation
{
    // Main Element
    public String panel_ApplicationSettingButtonXPath = "//*[@id=\"panel-head-1\"]/h4/a";
    public String panel_APIsButtonXPath = "//*[@id=\"panel-head-2\"]/h4/a";
    public String view_APIsPanelView = "//*[@id=\"apipanel\"]/div";
    public String view_SandboxIconXPath = "/html/body/div[5]";
    public String checkbox_EnablePopupId = "enablePopUp";

    // Launching Element
    public String launching_AppIDTextId = "appId";
    public String launching_LaunchingStatusCodeId = "launchingStatusSpan";
    public String launching_InitializeButtonId = "initializeBtn";
    public String launching_Zone_ALPHA = "//*[@id=\"zoneType\"]/option[1]";
    public String launching_Zone_BETA = "//*[@id=\"zoneType\"]/option[2]";
    public String launching_Zone_REAL = "//*[@id=\"zoneType\"]/option[3]";
    public String launching_ClientVersion_Testing = "//*[@id=\"serviceType\"]/option[1]";
    public String launching_ClientVersion_InspectionInStore = "//*[@id=\"serviceType\"]/option[2]";
    public String launching_ClientVersion_InService = "//*[@id=\"serviceType\"]/option[3]";
    public String launching_ClientVersion_RecommendUpdate = "//*[@id=\"serviceType\"]/option[4]";
    public String launching_ClientVersion_MustUpdate = "//*[@id=\"serviceType\"]/option[5]";
    public String launching_ClientVersion_OutOfService = "//*[@id=\"serviceType\"]/option[6]";
    public String launching_ClientVersion_Maintenance = "//*[@id=\"serviceType\"]/option[7]";
    public String launching_ClientVersion_Notice = "//*[@id=\"serviceType\"]/option[8]";

    // Auth Element
    public String auth_AuthMenuButtonXpath = "//*[@id=\"apipanel\"]/div/div/ul/li[2]/a";
    public String auth_UserIdTextId = "userIdSpan";
    public String auth_LoginWithButtonXPath = "//*[@id=\"loginWithIDPBtn\"]";
    public String auth_LogoutButtonId = "logoutBtn";
    public String auth_WithdrawButtonId = "withdrawBtn";
    public String auth_IdPOption_GuestXPath = "//*[@id=\"idp\"]/option[1]";
    public String auth_IdPOption_FacebookXPath = "//*[@id=\"idp\"]/option[2]";
    public String auth_IdPOption_PaycoXPath = "//*[@id=\"idp\"]/option[3]";
    public String auth_IdPOption_NaverXPath = "//*[@id=\"idp\"]/option[4]";
    public String auth_IdPOption_GoogleXPath = "//*[@id=\"idp\"]/option[5]";

    // Facebook PC Web Element
    public String facebook_LoginView_IdTextAreaId = "email";
    public String facebook_LoginView_PWTextAreaId = "pass";
    public String facebook_LoginView_LoginButtonId = "loginbutton";
    public String facebook_Permission_AgreeButtonXPath = "//*[@id=\"u_0_4\"]/div[2]/div[1]/div[1]/button";
    public String facebook_Permission_AgreeButtonXPathForSafari = "//*[@id=\"platformDialogForm\"]/div[3]/div/table/tbody/tr/td[2]/button[2]";
    public String facebook_Permission_AgreeButtonNameForSafari = "__CONFIRM__";

    // Facebook Mobile Web Element
    public String facebook_mLoginView_IdTextAreaId = "m_login_email";
    public String facebook_mLoginView_PWTextAreaId = "m_login_password";
    public String facebook_mLoginView_LoginButtonName = "login";

    // Payco Element
    public String payco_LoginView_IdTextAreaId = "id";
    public String payco_LoginView_PWTextAreaId = "pw";
    public String payco_LoginView_LoginButtonId = "loginBtn";
    public String payco_SecurityView_BirthdayTextAreaId = "birthday";
    public String payco_SecurityView_LoginButtonId = "confirmBtn";
    public String payco_SimpleLoginView_ConfirmButtonId = "autoOauthLogin";

    // Naver Element
    public String naver_LoginView_LoginButtonXPath = "//*[@id=\"frmNIDLogin\"]/fieldset/input";
    public String naver_LoginView_IdTextAreaId = "id";
    public String naver_LoginView_PWTextAreaId = "pw";
    public String naver_Permission_AgreeButtonXPath = "//*[@id=\"content\"]/div[4]/div[2]/button";

    // Google Element
    public String google_LoginView_IdTextAreaId = "identifierId";
    public String google_LoginView_PWTextAreaName = "password";
    public String google_LoginView_IdNextButtonXPath = "//*[@id=\"identifierNext\"]/content";
    public String google_LoginView_PWNextButtonXPath = "//*[@id=\"passwordNext\"]/content";
    public String google_IDSelectView_OtherIdButtonId = "identifierLink";
    public String google_IDSelectView_RecentIdButtonXPath = "//*[@id=\"view_container\"]/form/div[2]/div/div/div[1]/ul/li[1]/div";
}