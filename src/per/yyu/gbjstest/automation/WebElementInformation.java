package per.yyu.gbjstest.automation;

public class WebElementInformation {
    // GB Main page Element
    private String main_Sandbox_Icon_Xpath = "/html/body/div[5]";
    private String main_Launching_StatusCode_Id = "launchingStatusSpan";
    private String main_Launching_StatusCode_Xpath = "//*[@id=\"launchingStatusSpan\"]";
    private String main_LogArea_Clear_Btn_Id = "clearTextArea";
    private String main_LogArea_TextArea_Id = "apiResult";

    // Application Setting Panel Element
    private String appSet_Panel_Btn_Xpath = "//*[@id=\"panel-head-1\"]/h4/a";
    private String appSet_AppID_TextArea_Id = "appId";
    private String appSet_DisplayLanguage_TextArea_Id = "displayLanguage";
    private String appSet_EnablePopup_Checkbox_Id = "enablePopUp";
    private String appSet_Initialize_Btn_Id = "initializeBtn";
    private String appSet_Zone_ALPHA_Xpath = "//*[@id=\"zoneType\"]/option[1]";
    private String appSet_Zone_BETA_Xpath = "//*[@id=\"zoneType\"]/option[2]";
    private String appSet_Zone_REAL_Xpath = "//*[@id=\"zoneType\"]/option[3]";
    private String appSet_ClientVersion_Testing_Xpath = "//*[@id=\"serviceType\"]/option[1]";
    private String appSet_ClientVersion_InspectInStore_Xpath = "//*[@id=\"serviceType\"]/option[2]";
    private String appSet_ClientVersion_InService_Xpath = "//*[@id=\"serviceType\"]/option[3]";
    private String appSet_ClientVersion_RecommendUpdate_Xpath = "//*[@id=\"serviceType\"]/option[4]";
    private String appSet_ClientVersion_RequireUpdate_Xpath = "//*[@id=\"serviceType\"]/option[5]";
    private String appSet_ClientVersion_OutOfService_Xpath = "//*[@id=\"serviceType\"]/option[6]";
    private String appSet_ClientVersion_Maintenance_Xpath = "//*[@id=\"serviceType\"]/option[7]";
    private String appSet_ClientVersion_Notice_Xpath = "//*[@id=\"serviceType\"]/option[8]";

    // APIs Panel Element
    private String apis_Panel_Btn_Xpath = "//*[@id=\"panel-head-2\"]/h4/a";
    private String apis_Panel_View_Xpath = "//*[@id=\"apipanel\"]/div/div/ul";
    private String apis_Core_Btn_Xpath = "//*[@id=\"apipanel\"]/div/div/ul/li[1]/a";
    private String apis_Auth_Btn_Xpath = "//*[@id=\"apipanel\"]/div/div/ul/li[2]/a";

    // Launching Message Element
    private String launching_Modal_UI_ClassName = "gamebase-ui-modal";
    private String launching_Modal_Title_ClassName = "gamebase-ui-modal-title";
    private String launching_Modal_Body_ClassName = "gamebase-ui-modal-message";
    private String launching_Modal_accept_Btn_CSSSelector = "div.gamebase-ui-modal-footer > button.gamebase-ui-modal-btn.positive";
    private String launching_Modal_dismiss_Btn_CSSSelector = "div.gamebase-ui-modal-footer > button.gamebase-ui-modal-btn.negative";

    // Core Menu Element
    private String core_IsInitialize_Btn_Id = "isInitializeBtn";

    // Auth Menu Element
    private String auth_UserID_TextArea_Id = "userIdSpan";
    private String auth_LoginWith_Btn_Xpath = "//*[@id=\"loginWithIDPBtn\"]";
    private String auth_Logout_Btn_Id = "logoutBtn";
    private String auth_Withdraw_Btn_Id = "withdrawBtn";
    private String auth_IdPOption_Guest_Xpath = "//*[@id=\"idp\"]/option[1]";
    private String auth_IdPOption_Facebook_Xpath = "//*[@id=\"idp\"]/option[2]";
    private String auth_IdPOption_Payco_Xpath = "//*[@id=\"idp\"]/option[3]";
    private String auth_IdPOption_Naver_Xpath = "//*[@id=\"idp\"]/option[4]";
    private String auth_IdPOption_Google_Xpath = "//*[@id=\"idp\"]/option[5]";

    // PC Facebook Element
    private String pc_Facebook_LoginView_Login_Btn_Id = "loginbutton";
    private String pc_Facebook_LoginView_ID_TextArea_Id = "email";
    private String pc_Facebook_LoginView_PW_TextArea_Id = "pass";
    private String pc_Facebook_Re_LoginView_PW_TextArea_Name = "pass";
    private String pc_Facebook_Re_LoginView_Next_Btn_Xpath = "//*[@id=\"u_0_1\"]/div[3]/label";
    private String pc_Facebook_PermissionView_Agree_Btn_Xpath = "//*[@id=\"u_0_4\"]/div[2]/div[1]/div[1]/button";
    private String pc_Facebook_PermissionView_Agree_Btn_Xpath_ForSafari = "//*[@id=\"platformDialogForm\"]/div[3]/div/table/tbody/tr/td[2]/button[2]";
    private String pc_Facebook_PermissionView_Agree_Btn_Name_ForSafari = "__CONFIRM__";

    // PC Payco Element
    private String pc_Payco_LoginView_Login_Btn_Id = "loginBtn";
    private String pc_Payco_LoginView_ID_TextArea_Id = "id";
    private String pc_Payco_LoginView_PW_TextArea_Id = "pw";
    private String pc_Payco_SecurityView_Birthday_TextArea_Id = "birthday";
    private String pc_Payco_SecurityView_Login_Btn_Id = "confirmBtn";
    private String pc_Payco_SimpleLoginView_Login_Btn_Id = "autoOauthLogin";

    // PC Naver Element
    private String pc_Naver_LoginView_Login_Btn_Xpath = "//*[@id=\"frmNIDLogin\"]/fieldset/input";
    private String pc_Naver_LoginView_ID_TextArea_Id = "id";
    private String pc_Naver_LoginView_PW_TextArea_Id = "pw";
    private String pc_Naver_PermissionView_Agree_Btn_Xpath = "//*[@id=\"content\"]/div[4]/div[2]/button";

    // PC Google Element
    private String pc_Google_LoginView_ID_TextArea_Id = "identifierId";
    private String pc_Google_LoginView_ID_Next_Btn_Xpath = "//*[@id=\"identifierNext\"]";
    private String pc_Google_LoginView_PW_TextArea_Name = "password";
    private String pc_Google_LoginView_PW_Next_Btn_Id = "passwordNext";
    private String pc_Google_LoginView_PW_Next_Btn_Xpath = "//*[@id=\"passwordNext\"]";
    private String pc_Google_IDSelectView_OtherID_Btn_Id = "identifierLink";
    private String pc_Google_IDSelectView_RecentID_Btn_Xpath = "//*[@id=\"view_container\"]/form/div[2]/div/div/div[1]/ul/li[1]/div";
    private String pc_Google_IE10_LoginView_ID_TextArea_Id = "Email";
    private String pc_Google_IE10_LoginView_ID_Next_Btn_Id = "next";
    private String pc_Google_IE10_LoginView_PW_TextArea_Id = "Passwd";
    private String pc_Google_IE10_LoginView_PW_Login_Btn_Id = "signIn";
    private String pc_Google_IE10_Permisiion_Agree_Btn_Id = "submit_approve_access";

    // Mobile Android Facebook Element
    private String m_a_Facebook_LoginView_ID_TextArea_Id = "m_login_email";
    private String m_a_Facebook_LoginView_PW_TextArea_Id = "m_login_password";
    private String m_a_Facebook_LoginView_Login_Btn_Name = "login";
    private String m_a_Facebook_PermissionView_Agree_Btn_Xpath = "//*[@id=\"u_0_9\"]";

    // Mobile Android Payco Element
    private String m_a_Payco_LoginView_ID_TextArea_Id = "id";
    private String m_a_Payco_LoginView_PW_TextArea_Id = "pw";
    private String m_a_Payco_LoginView_Login_Btn_Id = "loginBtn";
    private String m_a_Payco_SimpleLoginView_Login_Btn_Id = "autoOauthLogin";

    // Mobile Android Naver Element
    private String m_a_Naver_LoginView_ID_TextArea_Id = "id";
    private String m_a_Naver_LoginView_PW_TextArea_Id = "pw";
    private String m_a_Naver_LoginView_Login_Btn_Id = "login_submit";
    private String m_a_Naver_PermissionView_Agree_Btn_Xpath = "//*[@id=\"content\"]/div[4]/div[2]/button";

    // Mobile Android Google Element
    private String m_a_Google_LoginView_ID_TextArea_Id = "identifierId";
    private String m_a_Google_LoginView_ID_Next_Btn_Id = "identifierNext";
    private String m_a_Google_LoginView_PW_TextArea_Name = "password";
    private String m_a_Google_LoginView_PW_Next_Btn_Id = "passwordNext";
    private String m_a_Google_IDSelectView_OtherID_Btn_Id = "identifierLink";
    private String m_a_Google_IDSelectView_RecentId_Btn_Xpath = "//*[@id=\"view_container\"]/form/div[2]/div/div/div[1]/ul/li[1]/div";



    // GB Main page Element
    public String getMain_Sandbox_Icon_Xpath() {
        return main_Sandbox_Icon_Xpath;
    }

    public String getMain_Launching_StatusCode_Id() {
        return main_Launching_StatusCode_Id;
    }

    public String getMain_Launching_StatusCode_Xpath() {
        return main_Launching_StatusCode_Xpath;
    }

    public String getMain_LogArea_Clear_Btn_Id() {
        return main_LogArea_Clear_Btn_Id;
    }

    public String getMain_LogArea_TextArea_Id() {
        return main_LogArea_TextArea_Id;
    }


    // Application Setting Panel Element
    public String getAppSet_Panel_Btn_Xpath() {
        return appSet_Panel_Btn_Xpath;
    }

    public String getAppSet_AppID_TextArea_Id() {
        return appSet_AppID_TextArea_Id;
    }

    public String getAppSet_DisplayLanguage_TextArea_Id() {
        return appSet_DisplayLanguage_TextArea_Id;
    }

    public String getAppSet_EnablePopup_Checkbox_Id() {
        return appSet_EnablePopup_Checkbox_Id;
    }

    public String getAppSet_Initialize_Btn_Id() {
        return appSet_Initialize_Btn_Id;
    }

    public String getAppSet_Zone_ALPHA_Xpath() {
        return appSet_Zone_ALPHA_Xpath;
    }

    public String getAppSet_Zone_BETA_Xpath() {
        return appSet_Zone_BETA_Xpath;
    }

    public String getAppSet_Zone_REAL_Xpath() {
        return appSet_Zone_REAL_Xpath;
    }

    public String getAppSet_ClientVersion_Testing_Xpath() {
        return appSet_ClientVersion_Testing_Xpath;
    }

    public String getAppSet_ClientVersion_InspectInStore_Xpath() {
        return appSet_ClientVersion_InspectInStore_Xpath;
    }

    public String getAppSet_ClientVersion_InService_Xpath() {
        return appSet_ClientVersion_InService_Xpath;
    }

    public String getAppSet_ClientVersion_RecommendUpdate_Xpath() {
        return appSet_ClientVersion_RecommendUpdate_Xpath;
    }

    public String getAppSet_ClientVersion_RequireUpdate_Xpath() {
        return appSet_ClientVersion_RequireUpdate_Xpath;
    }

    public String getAppSet_ClientVersion_OutOfService_Xpath() {
        return appSet_ClientVersion_OutOfService_Xpath;
    }

    public String getAppSet_ClientVersion_Maintenance_Xpath() {
        return appSet_ClientVersion_Maintenance_Xpath;
    }

    public String getAppSet_ClientVersion_Notice_Xpath() {
        return appSet_ClientVersion_Notice_Xpath;
    }



    // APIs Panel Element
    public String getApis_Panel_Btn_Xpath() {
        return apis_Panel_Btn_Xpath;
    }

    public String getApis_Panel_View_Xpath() {
        return apis_Panel_View_Xpath;
    }

    public String getApis_Core_Btn_Xpath() {
        return apis_Core_Btn_Xpath;
    }

    public String getApis_Auth_Btn_Xpath() {
        return apis_Auth_Btn_Xpath;
    }



    // Launching Message Element
    public String getLaunching_Modal_UI_ClassName() {
        return launching_Modal_UI_ClassName;
    }

    public String getLaunching_Modal_Title_ClassName() {
        return launching_Modal_Title_ClassName;
    }

    public String getLaunching_Modal_Body_ClassName() {
        return launching_Modal_Body_ClassName;
    }

    public String getLaunching_Modal_accept_Btn_CSSSelector() {
        return launching_Modal_accept_Btn_CSSSelector;
    }

    public String getLaunching_Modal_dismiss_Btn_CSSSelector() {
        return launching_Modal_dismiss_Btn_CSSSelector;
    }

    // Core Menu Element
    public String getCore_IsInitialize_Btn_Id() {
        return core_IsInitialize_Btn_Id;
    }

    // Auth Menu Element
    public String getAuth_UserID_TextArea_Id() {
        return auth_UserID_TextArea_Id;
    }

    public String getAuth_LoginWith_Btn_Xpath() {
        return auth_LoginWith_Btn_Xpath;
    }

    public String getAuth_Logout_Btn_Id() {
        return auth_Logout_Btn_Id;
    }

    public String getAuth_Withdraw_Btn_Id() {
        return auth_Withdraw_Btn_Id;
    }

    public String getAuth_IdPOption_Guest_Xpath() {
        return auth_IdPOption_Guest_Xpath;
    }

    public String getAuth_IdPOption_Facebook_Xpath() {
        return auth_IdPOption_Facebook_Xpath;
    }

    public String getAuth_IdPOption_Payco_Xpath() {
        return auth_IdPOption_Payco_Xpath;
    }

    public String getAuth_IdPOption_Naver_Xpath() {
        return auth_IdPOption_Naver_Xpath;
    }

    public String getAuth_IdPOption_Google_Xpath() {
        return auth_IdPOption_Google_Xpath;
    }



    // PC Facebook Element
    public String get_PC_Facebook_LoginView_Login_Btn_Id() {
        return pc_Facebook_LoginView_Login_Btn_Id;
    }

    public String get_PC_Facebook_LoginView_ID_TextArea_Id() {
        return pc_Facebook_LoginView_ID_TextArea_Id;
    }

    public String get_PC_Facebook_LoginView_PW_TextArea_Id() {
        return pc_Facebook_LoginView_PW_TextArea_Id;
    }

    public String get_PC_Facebook_Re_LoginView_PW_TextArea_Name() {
        return pc_Facebook_Re_LoginView_PW_TextArea_Name;
    }

    public String getPc_Facebook_Re_LoginView_Next_Btn_Xpath() {
        return pc_Facebook_Re_LoginView_Next_Btn_Xpath;
    }

    public String get_PC_Facebook_PermissionView_Agree_Btn_Xpath() {
        return pc_Facebook_PermissionView_Agree_Btn_Xpath;
    }

    public String get_PC_Facebook_PermissionView_Agree_Btn_Xpath_ForSafari() {
        return pc_Facebook_PermissionView_Agree_Btn_Xpath_ForSafari;
    }

    public String get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari() {
        return pc_Facebook_PermissionView_Agree_Btn_Name_ForSafari;
    }



    // PC Payco Element
    public String get_PC_Payco_LoginView_Login_Btn_Id() {
        return pc_Payco_LoginView_Login_Btn_Id;
    }

    public String get_PC_Payco_LoginView_ID_TextArea_Id() {
        return pc_Payco_LoginView_ID_TextArea_Id;
    }

    public String get_PC_Payco_LoginView_PW_TextArea_Id() {
        return pc_Payco_LoginView_PW_TextArea_Id;
    }

    public String get_PC_Payco_SecurityView_Birthday_TextArea_Id() {
        return pc_Payco_SecurityView_Birthday_TextArea_Id;
    }

    public String get_PC_Payco_SecurityView_Login_Btn_Id() {
        return pc_Payco_SecurityView_Login_Btn_Id;
    }

    public String get_PC_Payco_SimpleLoginView_Login_Btn_Id() {
        return pc_Payco_SimpleLoginView_Login_Btn_Id;
    }



    // PC Naver Element
    public String get_PC_Naver_LoginView_Login_Btn_Xpath() {
        return pc_Naver_LoginView_Login_Btn_Xpath;
    }

    public String get_PC_Naver_LoginView_ID_TextArea_Id() {
        return pc_Naver_LoginView_ID_TextArea_Id;
    }

    public String get_PC_Naver_LoginView_PW_TextArea_Id() {
        return pc_Naver_LoginView_PW_TextArea_Id;
    }

    public String get_PC_Naver_PermissionView_Agree_Btn_Xpath() {
        return pc_Naver_PermissionView_Agree_Btn_Xpath;
    }



    // PC Google Element
    public String get_PC_Google_LoginView_ID_TextArea_Id() {
        return pc_Google_LoginView_ID_TextArea_Id;
    }

    public String get_PC_Google_LoginView_ID_Next_Btn_Xpath() {
        return pc_Google_LoginView_ID_Next_Btn_Xpath;
    }

    public String get_PC_Google_LoginView_PW_TextArea_Name() {
        return pc_Google_LoginView_PW_TextArea_Name;
    }

    public String get_PC_Google_LoginView_PW_Next_Btn_Id() {
        return pc_Google_LoginView_PW_Next_Btn_Id;
    }

    public String get_PC_Google_LoginView_PW_Next_Btn_Xpath() {
        return pc_Google_LoginView_PW_Next_Btn_Xpath;
    }

    public String get_PC_Google_IDSelectView_OtherID_Btn_Id() {
        return pc_Google_IDSelectView_OtherID_Btn_Id;
    }

    public String get_PC_Google_IDSelectView_RecentID_Btn_Xpath() {
        return pc_Google_IDSelectView_RecentID_Btn_Xpath;
    }

    public String get_PC_Google_IE10_LoginView_ID_TextArea_Id() {
        return pc_Google_IE10_LoginView_ID_TextArea_Id;
    }

    public String get_PC_Google_IE10_LoginView_ID_Next_Btn_Id() {
        return pc_Google_IE10_LoginView_ID_Next_Btn_Id;
    }

    public String get_PC_Google_IE10_LoginView_PW_TextArea_Id() {
        return pc_Google_IE10_LoginView_PW_TextArea_Id;
    }

    public String get_PC_Google_IE10_LoginView_PW_Login_Btn_Id() {
        return pc_Google_IE10_LoginView_PW_Login_Btn_Id;
    }

    public String get_PC_Google_IE10_Permisiion_Agree_Btn_Id() {
        return pc_Google_IE10_Permisiion_Agree_Btn_Id;
    }



    // Mobile Android Facebook Element
    public String get_M_A_Facebook_LoginView_ID_TextArea_Id() {
        return m_a_Facebook_LoginView_ID_TextArea_Id;
    }

    public String get_M_A_Facebook_LoginView_PW_TextArea_Id() {
        return m_a_Facebook_LoginView_PW_TextArea_Id;
    }

    public String get_M_A_Facebook_LoginView_Login_Btn_Name() {
        return m_a_Facebook_LoginView_Login_Btn_Name;
    }

    public String get_M_A_Facebook_PermissionView_Agree_Btn_Xpath() {
        return m_a_Facebook_PermissionView_Agree_Btn_Xpath;
    }



    // Mobile Android Payco Element
    public String get_M_A_Payco_LoginView_ID_TextArea_Id() {
        return m_a_Payco_LoginView_ID_TextArea_Id;
    }

    public String get_M_A_Payco_LoginView_PW_TextArea_Id() {
        return m_a_Payco_LoginView_PW_TextArea_Id;
    }

    public String get_M_A_Payco_LoginView_Login_Btn_Id() {
        return m_a_Payco_LoginView_Login_Btn_Id;
    }

    public String get_M_A_Payco_SimpleLoginView_Login_Btn_Id() {
        return m_a_Payco_SimpleLoginView_Login_Btn_Id;
    }



    // Mobile Android Naver Element
    public String get_M_A_Naver_LoginView_ID_TextArea_Id() {
        return m_a_Naver_LoginView_ID_TextArea_Id;
    }

    public String get_M_A_Naver_LoginView_PW_TextArea_Id() {
        return m_a_Naver_LoginView_PW_TextArea_Id;
    }

    public String get_M_A_Naver_LoginView_Login_Btn_Id() {
        return m_a_Naver_LoginView_Login_Btn_Id;
    }

    public String get_M_A_Naver_PermissionView_Agree_Btn_Xpath() {
        return m_a_Naver_PermissionView_Agree_Btn_Xpath;
    }



    // Mobile Android Google Element
    public String get_M_A_Google_LoginView_ID_TextArea_Id() {
        return m_a_Google_LoginView_ID_TextArea_Id;
    }

    public String get_M_A_Google_LoginView_ID_Next_Btn_Id() {
        return m_a_Google_LoginView_ID_Next_Btn_Id;
    }

    public String get_M_A_Google_LoginView_PW_TextArea_Name() {
        return m_a_Google_LoginView_PW_TextArea_Name;
    }

    public String get_M_A_Google_LoginView_PW_Next_Btn_Id() {
        return m_a_Google_LoginView_PW_Next_Btn_Id;
    }

    public String get_M_A_Google_IDSelectView_OtherID_Btn_Id() {
        return m_a_Google_IDSelectView_OtherID_Btn_Id;
    }

    public String get_M_A_Google_IDSelectView_RecentId_Btn_Xpath() {
        return m_a_Google_IDSelectView_RecentId_Btn_Xpath;
    }
}