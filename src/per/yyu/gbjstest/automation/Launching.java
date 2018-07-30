package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Launching {
    WebElementInformation webInfo = new WebElementInformation();

    final int en = 0;
    final int ko = 1;
    final int ja = 2;



    // Gamebase Initialize Run
    public void initializeGamebase(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException {
        if(gbInfo.getClientVersionIndex() == 352) {
            this.initTestRegression(webDrvFn, gbInfo, fi);
        }

        else {
            this.initTestAboutSpecificVersion(webDrvFn, gbInfo, fi);
        }
    }

    public void initTestAboutSpecificVersion(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        // Ready
        this.openAppSettingPanel(webDrvFn);

        // Set
        this.selectLaunchingZone(webDrvFn, gbInfo);
        this.selectClientVersion(webDrvFn, gbInfo);
        this.setDisplayLanguage(webDrvFn, gbInfo);

        // Run
        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webInfo.getAppSet_Initialize_Btn_Id());
        gbInfo.setTestEndTime();

        // Finish
        this.closeAppSettingPanel(webDrvFn);
        this.finishGamebaseInitialize(webDrvFn, gbInfo, fi);
    }

    public void initTestRegression(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        Authentication_PC authPC = new Authentication_PC();
        int clientVersionIndex = 1;
        int CLIENT_VERSION_QUANTITY = 7;

        // clientVersionIndex 를 1 ~ 7 까지 동작해야 하므로, 1을 더해준다.
        while(clientVersionIndex < CLIENT_VERSION_QUANTITY + 1) {
            gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webInfo.getMain_Launching_StatusCode_Id(), 500, 5000));

            this.openAppSettingPanel(webDrvFn);
            gbInfo.setClientVersionIndex(clientVersionIndex);
            this.selectClientVersion(webDrvFn, gbInfo);
            this.uncheckOption_GBEnablePopup(webDrvFn);

            gbInfo.setTestStartTime();
            webDrvFn.clickElementById(webInfo.getAppSet_Initialize_Btn_Id());
            gbInfo.setTestEndTime();

            this.finishGamebaseInitialize(webDrvFn, gbInfo, fi);

            authPC.authenticationGamebaseForPC(webDrvFn, gbInfo, fi);
            webDrvFn.refreshPage();

            gbInfo.setUserID("");
            clientVersionIndex++;
        }
    }


    // Menu Setting
    private void openAppSettingPanel(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectAppSettingPanelView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webInfo.getAppSet_Panel_Btn_Xpath());
        }

        else {
            System.out.println("[Launching][Open App Setting Panel] : Already opened");
        }
    }

    private void closeAppSettingPanel(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectAppSettingPanelView(webDrvFn) == false) {
            System.out.println("[Launching][Open App Setting Panel] : Already closed");
        }

        else {
            webDrvFn.clickElementByXpath(webInfo.getAppSet_Panel_Btn_Xpath());
        }
    }

    private void openApisPanel(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectApisPanelView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webInfo.getApis_Panel_Btn_Xpath() );
        }

        else {
            System.out.println("[Launching][Open Apis Panel] : Already opened");
        }
    }

    private void closeApisPanel(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectApisPanelView(webDrvFn) == false) {
            System.out.println("[Launching][Open Apis Panel] : Already closed");
        }

        else {
            webDrvFn.clickElementByXpath(webInfo.getApis_Panel_Btn_Xpath());
        }
    }

    private void openCoreMenu(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectCoreMenuView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webInfo.getApis_Core_Btn_Xpath());
        }

        else {
            System.out.println("[Launching][Open Core Menu] : Already opened");
        }
    }

    private void closeCoreMenu(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectCoreMenuView(webDrvFn) == false) {
            System.out.println("[Launching][Open Core Menu] : Already closed");
        }

        else {
            webDrvFn.clickElementByXpath(webInfo.getApis_Core_Btn_Xpath());
        }
    }

    /**
     * App Setting Panel 이 열려있으면 True 를 반환 <br/>
     * App Setting Panel 이 닫혀있으면 False 를 반환 <br/><br/>
     * !! false 로 판단하는 이유<br/>
     * true 로 찾을 경우, 보이지 않으면 Exception 이 발생하기 때문에<br/>
     * false 로 찾아야 Exception 이 발생하지 않고 정상 동작함.<br/>
     */
    private boolean detectAppSettingPanelView(WebDriverFunction webDrvFn) {
        if(webDrvFn.findElementById(webInfo.getAppSet_Initialize_Btn_Id()) == false) {
            return false;
        }

        else {
            return true;
        }
    }

    /**
     * Apis Panel 이 열려있으면 True 를 반환 <br/>
     * Apis Panel 이 닫혀있으면 False 를 반환 <br/><br/>
     * !! false 로 판단하는 이유<br/>
     * true 로 찾을 경우, 보이지 않으면 Exception 이 발생하기 때문에<br/>
     * false 로 찾아야 Exception 이 발생하지 않고 정상 동작함.<br/>
     */
    private boolean detectApisPanelView(WebDriverFunction webDrvFn) {
        if(webDrvFn.findElementByXpath(webInfo.getApis_Panel_View_Xpath()) == false) {
            return false;
        }

        else {
            return true;
        }
    }

    /**
     * Core Menu 가 열려있으면 True 를 반환 <br/>
     * Core Menu 가 닫혀있으면 False 를 반환 <br/><br/>
     * !! false 로 판단하는 이유<br/>
     * true 로 찾을 경우, 보이지 않으면 Exception 이 발생하기 때문에<br/>
     * false 로 찾아야 Exception 이 발생하지 않고 정상 동작함.<br/>
     */
    private boolean detectCoreMenuView(WebDriverFunction webDrvFn) {
        if(webDrvFn.findElementById(webInfo.getCore_IsInitialize_Btn_Id()) == false) {
            return false;
        }

        else {
            return true;
        }
    }

    private void uncheckOption_GBEnablePopup(WebDriverFunction webDrvFn) throws InterruptedException {
        if(webDrvFn.findCheckedBoxById(webInfo.getAppSet_EnablePopup_Checkbox_Id(), 500, 5000)) {
            webDrvFn.clickElementById(webInfo.getAppSet_EnablePopup_Checkbox_Id());
        }

        else {
            System.out.println("[Launching][Uncheck Enable Popup] : Already Enable Popup option is off");
        }
    }



    // Display Language Setting
    private void setDisplayLanguage(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(webDrvFn.findElementById(webInfo.getAppSet_DisplayLanguage_TextArea_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.getAppSet_DisplayLanguage_TextArea_Id());
            webDrvFn.sendTextById(webInfo.getAppSet_DisplayLanguage_TextArea_Id(), gbInfo.getLanguageCode());
        }
    }



    // Launching Zone Setting
    private void selectLaunchingZone(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        switch(gbInfo.getLaunchingZoneIndex()) {
            case 1: {
                System.out.println("[Launching][Select Launching Zone] : ALPHA");
                webDrvFn.clickElementByXpath(webInfo.getAppSet_Zone_ALPHA_Xpath());
                break;
            }

            case 2: {
                System.out.println("[Launching][Select Launching Zone] : BETA");
                webDrvFn.clickElementByXpath(webInfo.getAppSet_Zone_BETA_Xpath());
                webDrvFn.clearTextById(webInfo.getAppSet_AppID_TextArea_Id());
                webDrvFn.sendTextById(webInfo.getAppSet_AppID_TextArea_Id(), gbInfo.getAppId());
                break;
            }

            case 3: {
                System.out.println("[Launching][Select Launching Zone] : REAL");
                webDrvFn.clickElementByXpath(webInfo.getAppSet_Zone_REAL_Xpath());
                break;
            }
        }
    }



    // Client Version Setting
    private void selectClientVersion(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        switch(gbInfo.getClientVersionIndex()) {
            case 1: {
                System.out.println("[Launching][Select Client Version] : Testing");
                this.setClientVersionToTesting(webDrvFn);
                break;
            }

            case 2: {
                System.out.println("[Launching][Select Client Version] : Inspect In Store");
                this.setClientVersionToInspectInStore(webDrvFn);
                break;
            }

            case 3: {
                System.out.println("[Launching][Select Client Version] : In Service");
                this.setClientVersionToInService(webDrvFn);
                break;
            }

            case 4: {
                System.out.println("[Launching][Select Client Version] : Recommend Update");
                this.setClientVersionToRecommendUpdate(webDrvFn);
                break;
            }

            case 5: {
                System.out.println("[Launching][Select Client Version] : Require Update");
                this.setClientVersionToRequireUpdate(webDrvFn);
                break;
            }

            case 6: {
                System.out.println("[Launching][Select Client Version] : Out of Service");
                this.setClientVersionToOutOfService(webDrvFn);
                break;
            }

            case 7: {
                System.out.println("[Launching][Select Client Version] : Maintenance");
                this.setClientVersionToMaintenance(webDrvFn);
                break;
            }

            case 8: {
                System.out.println("[Launching][Select Client Version] : Notice");
                this.setClientVersionToNotice(webDrvFn);
                break;
            }
        }
    }

    private void setClientVersionToTesting(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_Testing_Xpath());
    }

    private void setClientVersionToInspectInStore(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_InspectInStore_Xpath());
    }

    private void setClientVersionToInService(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_InService_Xpath());
    }

    private void setClientVersionToRecommendUpdate(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_RecommendUpdate_Xpath());
    }

    private void setClientVersionToRequireUpdate(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_RequireUpdate_Xpath());
    }

    private void setClientVersionToOutOfService(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_OutOfService_Xpath());
    }

    private void setClientVersionToMaintenance(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_Maintenance_Xpath());
    }

    private void setClientVersionToNotice(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAppSet_ClientVersion_Notice_Xpath());
    }



    // Finish Gamebase Initialize
    private void finishGamebaseInitialize(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        this.closeApisPanel(webDrvFn);
        webDrvFn.clickElementById(webInfo.getMain_Log_Clear_Btn_Id());

        this.openApisPanel(webDrvFn);
        this.openCoreMenu(webDrvFn);
        webDrvFn.clickElementById(webInfo.getCore_IsInitialize_Btn_Id());

        this.updateLaunchingStatusCode(webDrvFn, gbInfo);
        this.writeInitializeResult(webDrvFn, gbInfo, fi);
    }

    /**
     * Main Page 의 Launching Status Code Element 를 <br/>
     * 우선 5초간 기다리면서 변경된 값을 저장하고 <br/>
     * 5초 이후 변경된 값이 없더라도 그 값을 저장한다. <br/>
     * 변경된 값이 없을 때 저장하는 이유는 <br/>
     * Test Result 에 이 값을 출력하여, 비정상 동작 시 추적용임 <br/>
     */
    private void updateLaunchingStatusCode(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(this.isInitialize(webDrvFn)) {
            System.out.println("[Launching][Update Status Code] : Success");
            gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webInfo.getMain_Launching_StatusCode_Id()));
        }

        else {
            System.out.println("[Launching][Update Status Code] : Status code is not change");
            gbInfo.setLaunchingStatusCode(webDrvFn.getTextById(webInfo.getMain_Launching_StatusCode_Id()));
        }
    }

    private void writeInitializeResult(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        if(this.isInitialize(webDrvFn)) {
            System.out.println("[Launching][Initialize] : Success");
            fi.writeTestResult(gbInfo, "Initialize", webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()), true);
        }

        else {
            System.out.println("[Launching][Initialize] : !!!!! Failure !!!!!");
            fi.writeTestResult(gbInfo, "Initialize", webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()), false);
        }
    }

    private boolean isInitialize(WebDriverFunction webDrvFn) throws InterruptedException {
        if(webDrvFn.findTextFromTextAreaById(webInfo.getMain_LogArea_TextArea_Id(), "true")) {
            return true;
        }

        else {
            return false;
        }
    }
}