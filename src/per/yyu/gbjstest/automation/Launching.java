package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Launching
{
    WebElementInformation webInfo = new WebElementInformation();

    public void gamebaseInitialize(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        // Set
        gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webDrvFn.driver, webInfo.main_Launching_StatusCode_ById, 500, 5000));
        this.appSettingPanelOpener(webDrvFn);
        this.launchingZoneSelector(webDrvFn, gbInfo);
        this.clientVersionSelector(webDrvFn, gbInfo);

        // Run
        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webDrvFn.driver, webInfo.appSetting_Initialize_Btn_ById);

        // Finish
        this.appSettingPanelCloser(webDrvFn);
        this.finishGamebaseInitialize(webDrvFn, gbInfo);
        fi.gbInitTestResultWriter(gbInfo);
    }


    // Menu Setter
    private void appSettingPanelOpener(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(this.appSettingPanelDetector(webDrvFn) == false)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Panel_Btn_ByXPath);
        }
    }

    private void appSettingPanelCloser(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(this.appSettingPanelDetector(webDrvFn) == true)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.appSetting_Panel_Btn_ByXPath);
        }
    }

    private boolean appSettingPanelDetector(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.appSetting_Initialize_Btn_ById, 500, 5000) == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }


    // Launching Zone Setter
    private void launchingZoneSelector(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        switch(gbInfo.getLaunchingZoneIndex())
        {
            case 1:
            {
                System.out.println("[Launching][Zone Selector] : ALPHA");
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_Zone_ALPHA_ByXPath);
                break;
            }

            case 2:
            {
                System.out.println("[Launching][Zone Selector] : BETA");
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_Zone_BETA_ByXPath);
                webDrvFn.clearTextById(webDrvFn.driver, webInfo.appSetting_AppID_TextArea_ById);
                webDrvFn.sendTextById(webDrvFn.driver, webInfo.appSetting_AppID_TextArea_ById, gbInfo.getAppID());
                break;
            }

            case 3:
            {
                System.out.println("[Launching][Zone Selector] : REAL");
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_Zone_REAL_ByXPath);
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
        if(webDrvFn.textChangeDetectorById(webDrvFn.driver, webInfo.main_Launching_StatusCode_ById, gbInfo.getLaunchingStatusCode(), 500, 5000) == true)
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
    private void clientVersionSelector(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
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
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_Testing_ByXPath);
    }

    private void setClientVersionToInspectInStore(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_InspectInStore_ByXPath);
    }

    private void setClientVersionToInService(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_InService_ByXPath);
    }

    private void setClientVersionToRecommendUpdate(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_RecommendUpdate_ByXPath);
    }

    private void setClientVersionToRequireUpdate(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_RequireUpdate_ByXPath);
    }

    private void setClientVersionToOutOfService(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_OutOfService_ByXPath);
    }

    private void setClientVersionToMaintenance(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_Maintenance_ByXPath);
    }

    private void setClientVersionToNotice(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.launching_ClientVersion_Notice_ByXPath);
    }



    // For Regression Test
    private void enablePopupOptionUnchecker(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findCheckedBoxById(webDrvFn.driver, webInfo.appSetting_EnablePopup_Checkbox_ById, 500, 5000) == true)
        {
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.appSetting_EnablePopup_Checkbox_ById);
        }
    }

    public void eachOfLaunchingStatusInitRegressionTest(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi)
    {

    }
}