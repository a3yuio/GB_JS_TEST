package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Launching
{

    WebElementInformation webinfo = new WebElementInformation();

    public void gamebaseInitialize(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws IOException, InterruptedException
    {
        System.out.println("[YYU][GB Initialize] : Start");
        gbinfo.setTestCaseStart();
        gbinfo.setLaunchingStatusCode(webapi.getTextById(webapi.driver, webinfo.launching_LaunchingStatusCodeId));

        this.initPanelOpener(webapi);

        this.launchingZoneSelector(webapi, gbinfo);
        this.clientVersionSelector(webapi, gbinfo);
        webapi.clickElementById(webapi.driver, webinfo.launching_InitializeButtonId);

        this.updateLaunchingStatusCode(webapi, gbinfo);
        this.updateLaunchingStatus(gbinfo);

        fi.initResultWriter(gbinfo);
    }

    private void launchingZoneSelector(WebDriverAPI webapi, GamebaseInformation gbinfo)
    {
        switch(gbinfo.getLaunchingZoneIndex())
        {
            case 1:
            {
                webapi.clickElementByXPath(webapi.driver, webinfo.launching_Zone_ALPHA);
                break;
            }

            case 2:
            {
                webapi.clickElementByXPath(webapi.driver, webinfo.launching_Zone_BETA);
                webapi.textClearById(webapi.driver, webinfo.launching_AppIDTextId);
                webapi.sendTextById(webapi.driver, webinfo.launching_AppIDTextId, gbinfo.getAppId());
                break;
            }

            case 3:
            {
                webapi.clickElementByXPath(webapi.driver, webinfo.launching_Zone_REAL);
                break;
            }
        }
    }

    private void clientVersionSelector(WebDriverAPI webapi, GamebaseInformation gbinfo) throws IOException, InterruptedException
    {
        switch(gbinfo.getClientVersionIndex())
        {
            case 1:
            {
                System.out.println("[YYU][Client Version Selector] : Testing");
                this.setClientVersionToTesting(webapi);
                break;
            }

            case 2:
            {
                System.out.println("[YYU][Client Version Selector] : Inspection In Store");
                this.setClientVersionToInspectionInStore(webapi);
                break;
            }

            case 3:
            {
                System.out.println("[YYU][Client Version Selector] : In Service");
                this.setClientVersionToInService(webapi);
                break;
            }

            case 4:
            {
                System.out.println("[YYU][Client Version Selector] : Recommend Update");
                this.setClientVersionToRecommendUpdate(webapi);
                break;
            }

            case 5:
            {
                System.out.println("[YYU][Client Version Selector] : Must Update");
                this.setClientVersionToMustUpdate(webapi);
                break;
            }

            case 6:
            {
                System.out.println("[YYU][Client Version Selector] : Out of Service");
                this.setClientVersionToOutOfService(webapi);
                break;
            }

            case 7:
            {
                System.out.println("[YYU][Client Version Selector] : Maintenance");
                this.setClientVersionToMaintenance(webapi);
                break;
            }

            case 8:
            {
                System.out.println("[YYU][Client Version Selector] : Notice");
                this.setClientVersionToNotice(webapi);
                break;
            }
        }
    }

    private void enablePopupUnchecker(WebDriverAPI webapi) throws InterruptedException
    {
        if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.checkbox_EnablePopupId, 500, 2500) == true)
        {
            if(webapi.findCheckBoxById(webapi.driver, webinfo.checkbox_EnablePopupId) == true)
            {
                webapi.clickElementById(webapi.driver, webinfo.checkbox_EnablePopupId);
            }
        }
    }

    private void initPanelOpener(WebDriverAPI webapi) throws InterruptedException
    {
        if(this.isOpenApplilcationPanel(webapi) == false)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.panel_ApplicationSettingButtonXPath);
        }
    }

    private void initPanelCloser(WebDriverAPI webapi) throws InterruptedException
    {
        if(this.isOpenApplilcationPanel(webapi) == true)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.panel_ApplicationSettingButtonXPath);
        }
    }

    private boolean isOpenApplilcationPanel(WebDriverAPI webapi) throws InterruptedException
    {
        if(webapi.findElementById(webapi.driver, webinfo.launching_InitializeButtonId) == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    private void setClientVersionToTesting(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_Testing);
    }

    private void setClientVersionToInspectionInStore(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_InspectionInStore);
    }

    private void setClientVersionToInService(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_InService);
    }

    private void setClientVersionToRecommendUpdate(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_RecommendUpdate);
    }

    private void setClientVersionToMustUpdate(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_MustUpdate);
    }

    private void setClientVersionToOutOfService(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_OutOfService);
    }

    private void setClientVersionToMaintenance(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_Maintenance);
    }

    private void setClientVersionToNotice(WebDriverAPI webapi)
    {
        webapi.clickElementByXPath(webapi.driver, webinfo.launching_ClientVersion_Notice);
    }

    private void updateLaunchingStatusCode(WebDriverAPI webapi, GamebaseInformation gbinfo) throws InterruptedException
    {
        if(webapi.detectorOfTextChangeByIdWithPolling(webapi.driver, webinfo.launching_LaunchingStatusCodeId, gbinfo.getLaunchingStatusCode(), 500, 2500) == true)
        {
            gbinfo.setLaunchingStatusCode(webapi.getTextById(webapi.driver, webinfo.launching_LaunchingStatusCodeId));
        }

        else
        {
            gbinfo.setLaunchingStatusCode("");
            System.out.println("[YYU][Update Launching Status Code] : Initialize Fail !!!!!");
        }
    }

    private void updateLaunchingStatus(GamebaseInformation gbinfo)
    {
        if(gbinfo.getLaunchingStatusCode().equals(""))
        {
            gbinfo.setLaunchingStatus(false);
        }

        else
        {
            gbinfo.setLaunchingStatus(true);
        }
    }

    public void launchingRegressionTest(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws IOException, InterruptedException
    {
        Authentication auth = new Authentication();

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToTesting(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToInspectionInStore(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);;
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToInService(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToRecommendUpdate(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToMustUpdate(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToOutOfService(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);

        this.enablePopupUnchecker(webapi);
        Thread.sleep(500);
        this.setClientVersionToMaintenance(webapi);
        Thread.sleep(500);
        this.gamebaseInitialize(webapi, gbinfo, fi);
        Thread.sleep(500);
        auth.idPLoginRun(webapi, gbinfo, fi);
        Thread.sleep(500);
        webapi.refreshPage();
        gbinfo.setLoginStatus(false);
    }
}