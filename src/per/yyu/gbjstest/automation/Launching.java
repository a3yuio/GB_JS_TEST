package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Launching
{
    WebElementInformation webInfo = new WebElementInformation();

    final int en = 0;
    final int ko = 1;
    final int ja = 2;

    public void gamebaseInitialize(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        // Set
        gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webDrvFn.driver, webInfo.main_Launching_StatusCode_ById, 500, 5000));
        this.openAppSettingPanel(webDrvFn);
        this.selectLaunchingZone(webDrvFn, gbInfo);
        this.selectClientVersion(webDrvFn, gbInfo);
        this.setDisplayLanguage(webDrvFn, gbInfo);

        // Run
        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webDrvFn.driver, webInfo.appSetting_Initialize_Btn_ById);

//        System.out.println(webDrvFn.getTextByClassName(webDrvFn.driver, webInfo.launching_Modal_Title_ByClassName, 500, 5000));
//        System.out.println("blank");
//        System.out.println(webDrvFn.getTextByClassName(webDrvFn.driver, webInfo.launching_Modal_Body_ByClassName, 500, 5000));
//
//        webDrvFn.testWebAPI(webDrvFn.driver, webInfo.launching_Modal_accept_Btn_ByCSSSelector);

        // Finish
        this.closeAppSettingPanel(webDrvFn);
        this.finishGamebaseInitialize(webDrvFn, gbInfo);
        fi.writeCSV_GBInitTestResult(gbInfo);
    }


    // Menu Setter
    private void openAppSettingPanel(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(this.detectAppSettingPanel(webDrvFn) == false)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Panel_Btn_ByXpath);
        }
    }

    private void closeAppSettingPanel(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(this.detectAppSettingPanel(webDrvFn) == true)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Panel_Btn_ByXpath);
        }
    }

    private boolean detectAppSettingPanel(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.appSetting_Initialize_Btn_ById) == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    private void setDisplayLanguage(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.appSetting_DisplayLanguage_TextArea_ById, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.appSetting_DisplayLanguage_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.appSetting_DisplayLanguage_TextArea_ById, gbInfo.getLanguageCode());
        }
    }


    // Launching Zone Setter
    private void selectLaunchingZone(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        switch(gbInfo.getLaunchingZoneIndex())
        {
            case 1:
            {
                System.out.println("[Launching][Zone Selector] : ALPHA");
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Zone_ALPHA_ByXpath);
                break;
            }

            case 2:
            {
                System.out.println("[Launching][Zone Selector] : BETA");
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Zone_BETA_ByXpath);
                webDrvFn.clearTextById(webDrvFn.driver, webInfo.appSetting_AppID_TextArea_ById);
                webDrvFn.sendTextById(webDrvFn.driver, webInfo.appSetting_AppID_TextArea_ById, gbInfo.getAppID());
                break;
            }

            case 3:
            {
                System.out.println("[Launching][Zone Selector] : REAL");
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Zone_REAL_ByXpath);
                break;
            }
        }
    }


    // Update Launching Status and Code
    private void finishGamebaseInitialize(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        this.updateLaunchingStatusCode(webDrvFn, gbInfo);
        this.updateLaunchingStatus(gbInfo);
    }

    private void updateLaunchingStatusCode(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.detectTextChangeById(webDrvFn.driver, webInfo.main_Launching_StatusCode_ById, gbInfo.getLaunchingStatusCode(), 500, 5000) == true)
        {
            gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webDrvFn.driver, webInfo.main_Launching_StatusCode_ById, 500, 5000));
        }

        else
        {
            System.out.println("[Launching][Update Status Code] : Fail !!!!!");
        }
    }

    private void updateLaunchingStatus(GamebaseInformation gbInfo)
    {
        if(gbInfo.getLaunchingStatusCode().equals("-"))
        {
            gbInfo.setLaunchingStatus(false);
        }

        else
        {
            gbInfo.setLaunchingStatus(true);
        }
    }


    // Client Version Setter
    private void selectClientVersion(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        switch(gbInfo.getClientVersionIndex())
        {
            case 1:
            {
                System.out.println("[Launching][Version Selector] : Testing");
                this.setClientVersionToTesting(webDrvFn);
                break;
            }

            case 2:
            {
                System.out.println("[Launching][Version Selector] : Inspect In Store");
                this.setClientVersionToInspectInStore(webDrvFn);
                break;
            }

            case 3:
            {
                System.out.println("[Launching][Version Selector] : In Service");
                this.setClientVersionToInService(webDrvFn);
                break;
            }

            case 4:
            {
                System.out.println("[Launching][Version Selector] : Recommend Update");
                this.setClientVersionToRecommendUpdate(webDrvFn);
                break;
            }

            case 5:
            {
                System.out.println("[Launching][Version Selector] : Require Update");
                this.setClientVersionToRequireUpdate(webDrvFn);
                break;
            }

            case 6:
            {
                System.out.println("[Launching][Version Selector] : Out of Service");
                this.setClientVersionToOutOfService(webDrvFn);
                break;
            }

            case 7:
            {
                System.out.println("[Launching][Version Selector] : Maintenance");
                this.setClientVersionToMaintenance(webDrvFn);
                break;
            }

            case 8:
            {
                System.out.println("[Launching][Version Selector] : Notice");
                this.setClientVersionToNotice(webDrvFn);
                break;
            }
        }
    }

    private void setClientVersionToTesting(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_Testing_ByXpath);
    }

    private void setClientVersionToInspectInStore(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_InspectInStore_ByXpath);
    }

    private void setClientVersionToInService(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_InService_ByXpath);
    }

    private void setClientVersionToRecommendUpdate(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_RecommendUpdate_ByXpath);
    }

    private void setClientVersionToRequireUpdate(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_RequireUpdate_ByXpath);
    }

    private void setClientVersionToOutOfService(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_OutOfService_ByXpath);
    }

    private void setClientVersionToMaintenance(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_Maintenance_ByXpath);
    }

    private void setClientVersionToNotice(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_ClientVersion_Notice_ByXpath);
    }



    // For Regression Test
    private void uncheckEnablePopupOption(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findCheckedBoxById(webDrvFn.driver, webInfo.appSetting_EnablePopup_Checkbox_ById, 500, 5000) == true)
        {
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.appSetting_EnablePopup_Checkbox_ById);
        }
    }

    public void eachOfLaunchingStatusInitRegressionTest(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        Authentication_PC authPC = new Authentication_PC();
        int clientVersionIndex = 1;

        while(clientVersionIndex < 8)
        {
            gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webDrvFn.driver, webInfo.main_Launching_StatusCode_ById, 500, 5000));
            this.openAppSettingPanel(webDrvFn);
            gbInfo.setClientVersionIndex(clientVersionIndex);
            this.selectClientVersion(webDrvFn, gbInfo);
            this.uncheckEnablePopupOption(webDrvFn);

            gbInfo.setTestStartTime();
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.appSetting_Initialize_Btn_ById);

            this.finishGamebaseInitialize(webDrvFn, gbInfo);
            fi.writeCSV_GBInitTestResult(gbInfo);

            authPC.gamebaseAuthentication_PC(webDrvFn, gbInfo, fi);
            webDrvFn.refreshPage();

            gbInfo.setLaunchingStatus(false);
            gbInfo.setLoginStatus(false);
            gbInfo.setUserID("");
            clientVersionIndex++;
        }
    }
}