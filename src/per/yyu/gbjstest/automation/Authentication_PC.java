package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Authentication_PC {
    WebElementInformation webInfo = new WebElementInformation();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;



    // Gamebase Authentication Run
    public void authenticationGamebase(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException {
        this.openLoginMenu(webDrvFn);

        if(gbInfo.getIdPTypeNo() == 352) {
            this.loginTestRegression(webDrvFn, gbInfo, fi);
        }

        else {
            this.loginTestAboutSpecificIdP(webDrvFn, gbInfo, fi);
        }
    }

    private void loginTestAboutSpecificIdP(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.getAuth_UserID_TextArea_Id()));
        this.selectIdP(webDrvFn, gbInfo);
        this.runLogin(webDrvFn, gbInfo, fi);
    }

    private void loginTestRegression(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        int idpTypeNo = 1;
        int SUPPORT_IDP_QUANTITY = 5;

        // idpTypeNo 를 1 ~ 5 까지 동작해야 하므로, 1을 더해준다.
        while(idpTypeNo < SUPPORT_IDP_QUANTITY + 1) {
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.getAuth_UserID_TextArea_Id()));
            gbInfo.setIdPTypeNo(idpTypeNo);

            this.selectIdP(webDrvFn, gbInfo);

            this.runLogin(webDrvFn, gbInfo, fi);
            this.logout(webDrvFn, gbInfo, fi);

            this.runLogin(webDrvFn, gbInfo, fi);
            this.withdraw(webDrvFn, gbInfo, fi);

            this.runLogin(webDrvFn, gbInfo, fi);
            this.withdraw(webDrvFn, gbInfo, fi);

            idpTypeNo++;
        }
    }



    // Menu Setting
    private void openLoginMenu(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectApisPanelView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getApis_Panel_Btn_Xpath());
        }

        Thread.sleep(500);

        if(this.detectAuthMenuView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getApis_Auth_Btn_Xpath());
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
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.getApis_Panel_View_Xpath()) == false) {
            return false;
        }

        else {
            return true;
        }
    }

    /**
     * Auth Menu 가 열려있으면 True 를 반환 <br/>
     * Auth Menu 가 닫혀있으면 False 를 반환 <br/><br/>
     * !! false 로 판단하는 이유<br/>
     * true 로 찾을 경우, 보이지 않으면 Exception 이 발생하기 때문에<br/>
     * false 로 찾아야 Exception 이 발생하지 않고 정상 동작함.<br/>
     */
    private boolean detectAuthMenuView(WebDriverFunction webDrvFn) {
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.getAuth_LoginWith_Btn_Xpath()) == false) {
            return false;
        }

        else {
            return true;
        }
    }



    // IdP Setting

    /**
     * 사용자가 선택한 IdP 에 따라 <br/>
     * Auth Menu 의 IdP 를 선택함. <br/>
     */
    private void selectIdP(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        switch(gbInfo.getIdPTypeNo()) {
            case 1: {
                System.out.println("[Auth PC][Select IdP] : Guest");
                this.setIdPToGuest(webDrvFn);
                break;
            }

            case 2: {
                System.out.println("[Auth PC][Select IdP] : Facebook");
                this.setIdPToFacebook(webDrvFn);
                break;
            }

            case 3: {
                System.out.println("[Auth PC][Select IdP] : Payco");
                this.setIdPToPayco(webDrvFn);
                break;
            }

            case 4: {
                System.out.println("[Auth PC][Select IdP] : Naver");
                this.setIdPToNaver(webDrvFn);
                break;
            }

            case 5: {
                System.out.println("[Auth PC][Select IdP] : Google");
                this.setIdPToGoogle(webDrvFn);
                break;
            }
        }
    }

    private void setIdPToGuest(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getAuth_IdPOption_Guest_Xpath());
    }

    private void setIdPToFacebook(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getAuth_IdPOption_Facebook_Xpath());
    }

    private void setIdPToPayco(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getAuth_IdPOption_Payco_Xpath());
    }

    private void setIdPToNaver(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getAuth_IdPOption_Naver_Xpath());
    }

    private void setIdPToGoogle(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getAuth_IdPOption_Google_Xpath());
    }

    private String idpTypeNoToName(int idpTypeNo) {
        switch(idpTypeNo) {
            case 1: {
                return "Guest";
            }

            case 2: {
                return "Facebook";
            }

            case 3: {
                return "Payco";
            }

            case 4: {
                return "Naver";
            }

            case 5: {
                return "Google";
            }
        }

        return "Invalid";
    }



    // Login Run
    private void runLogin(WebDriverFunction webDrvfn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        gbInfo.setTestStartTime();
        webDrvfn.clickElementByXpath(webDrvfn.driver, webInfo.getAuth_LoginWith_Btn_Xpath());
        // Guest Login 은 이후 처리할 것이 없음

        if(gbInfo.getIdPTypeNo() > 1) {
            webDrvfn.readyToPopupHandle(gbInfo);
            webDrvfn.switchToSubWindow();

            switch(gbInfo.getIdPTypeNo()) {
                case 2: {
                    this.loginFacebook(webDrvfn, gbInfo);
                    break;
                }

                case 3: {
                    this.loginPayco(webDrvfn, gbInfo);
                    break;
                }

                case 4: {
                    this.loginNaver(webDrvfn, gbInfo);
                    break;
                }

                case 5: {
                    this.loginGoogle(webDrvfn, gbInfo);
                    break;
                }
            }
        }
        gbInfo.setTestEndTime();

        this.finishGamebaseAuthentication(webDrvfn, gbInfo, fi, this.idpTypeNoToName(gbInfo.getIdPTypeNo()) + " Login");
    }



    // Facebook Login
    private void loginFacebook(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(gbInfo.getBrowserTypeNo() != 4) {
            this.loginFacebookForOtherBrowser(webDrvFn, gbInfo);
        }

        else {
            this.loginFacebookForSafari(webDrvFn, gbInfo);
        }
    }

    /**
     * Safari Browser 를 위한 Facebook Login Method <br/>
     * Safari Browser 에서는 Facebook Login View 의 UI 가 다르므로 <br/>
     * 이 Method 를 사용해야 함.
     */
    private void loginFacebookForSafari(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Facebook Login View
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id(), gbInfo.getTestId(FACEBOOK));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_Login_Btn_Id());
        }

        // Facebook OAuth Permission View
        if(webDrvFn.findElementByName(webDrvFn.driver, webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari(), 500, 5000)) {
            webDrvFn.clickElementByName(webDrvFn.driver, webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari());
            webDrvFn.switchToMainWindow();
        }

        // Browser Cache Login
        else {
            webDrvFn.switchToMainWindow();
        }
    }

    /**
     * Safari 를 제외한 다른 Browser 들은 UI 가 모두 동일하여 <br/>
     * 같은 Element 를 사용할 수 있음. <br/>
     */
    private void loginFacebookForOtherBrowser(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Facebook Login View
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id(), gbInfo.getTestId(FACEBOOK));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Facebook_LoginView_Login_Btn_Id());

            // Faceboiok OAuth Permission View
            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Xpath());
                webDrvFn.switchToMainWindow();
            }

            else {
                webDrvFn.switchToMainWindow();
            }
        }

        else if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.getPc_Facebook_Re_LoginView_Next_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clearTextByName(webDrvFn.driver, webInfo.get_PC_Facebook_Re_LoginView_PW_TextArea_Name());
            webDrvFn.sendTextByName(webDrvFn.driver, webInfo.get_PC_Facebook_Re_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.getPc_Facebook_Re_LoginView_Next_Btn_Xpath());
            webDrvFn.switchToMainWindow();
        }

        // Browser Cache Login
        else {
            webDrvFn.switchToMainWindow();
        }
    }



    // Payco Login
    private void loginPayco(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Payco Login View
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Payco_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Payco_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Payco_LoginView_ID_TextArea_Id(), gbInfo.getTestId(PAYCO));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Payco_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Payco_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(PAYCO));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Payco_LoginView_Login_Btn_Id());

            // 이후 새로운 브라우저 차단 페이지로 이동된다면
            // Payco 아이디의 보안설정에서 해당 옵션을 Off 한다.
            // 해당 로직은 Payco 의 스펙이지, SDK 의 스펙이 아니기 때문에
            // 필요없는 테스트 로직을 추가하지 않는다.

            webDrvFn.switchToMainWindow();
        }

        // Payco Simple Login View
        else if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Payco_SimpleLoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Payco_SimpleLoginView_Login_Btn_Id());
            webDrvFn.switchToMainWindow();
        }

        // Browser Cache Login
        else {
            webDrvFn.switchToMainWindow();
        }
    }



    // Naver Login
    private void loginNaver(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Naver Login View
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Naver_LoginView_Login_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Naver_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver,webInfo.get_PC_Naver_LoginView_ID_TextArea_Id(), gbInfo.getTestId(NAVER));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Naver_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Naver_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(NAVER));
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Naver_LoginView_Login_Btn_Xpath());

            // Naver OAuth Permission View
            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Naver_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Naver_PermissionView_Agree_Btn_Xpath());
                webDrvFn.switchToMainWindow();
            }

            else {
                webDrvFn.switchToMainWindow();
            }
        }

        else {
            webDrvFn.switchToMainWindow();
        }
    }



    // Google Login
    private void loginGoogle(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(gbInfo.getIEVersion().equals("10")) {
            this.loginGoogleForIE10(webDrvFn, gbInfo);
        }

        else {
            this.loginGoogleForOtherBrowser(webDrvFn, gbInfo);
        }
    }

    private void loginGoogleForIE10(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Google Login_ID View
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_ID_Next_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_ID_TextArea_Id(), gbInfo.getTestId(GOOGLE));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_ID_Next_Btn_Id());

            // Google Login_PW View
            if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_PW_Login_Btn_Id())) {
                webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_PW_TextArea_Id());
                webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Google_IE10_LoginView_PW_Login_Btn_Id());

                // Google OAuth Permission View
                if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Google_IE10_Permisiion_Agree_Btn_Id())) {
                    webDrvFn.clickElementById(webDrvFn.driver, webInfo.get_PC_Google_IE10_Permisiion_Agree_Btn_Id());
                    webDrvFn.switchToMainWindow();
                }

                else {
                    webDrvFn.switchToMainWindow();
                }
            }

            else {
                webDrvFn.switchToMainWindow();
            }
        }

        else {
            webDrvFn.switchToMainWindow();
        }
    }



    private void loginGoogleForOtherBrowser(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Google Login_ID View
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_LoginView_ID_Next_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.get_PC_Google_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.get_PC_Google_LoginView_ID_TextArea_Id(), gbInfo.getTestId(GOOGLE));
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_LoginView_ID_Next_Btn_Xpath());

            // Google Login_PW View
            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clearTextByName(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_TextArea_Name());
                webDrvFn.sendTextByName(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath());

                // 이후 문제가 생기면 sleep 2000 추가

                webDrvFn.switchToMainWindow();
            }

            else {
                webDrvFn.switchToMainWindow();
            }
        }

        // Google Simple Login View
        else if(webDrvFn.findElementById(webDrvFn.driver, webInfo.get_PC_Google_IDSelectView_OtherID_Btn_Id(), 500, 5000)) {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_IDSelectView_RecentID_Btn_Xpath());

            // Google Login_PW View
            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clearTextByName(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_TextArea_Name());
                webDrvFn.sendTextByName(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath());
            }

            else {
                webDrvFn.switchToMainWindow();
            }
        }

        else {
            webDrvFn.switchToMainWindow();
        }
    }



    // Logout
    private void logout(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        this.openLoginMenu(webDrvFn);

        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webDrvFn.driver, webInfo.getAuth_Logout_Btn_Id());
        gbInfo.setTestEndTime();

        this.finishGamebaseAuthentication(webDrvFn, gbInfo, fi, "Logout");
    }

    private void withdraw(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        this.openLoginMenu(webDrvFn);

        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webDrvFn.driver, webInfo.getAuth_Withdraw_Btn_Id());
        gbInfo.setTestEndTime();

        this.finishGamebaseAuthentication(webDrvFn, gbInfo, fi, "Withdraw");
    }



    // Finish Gamebase Authentication
    private void finishGamebaseAuthentication(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi, String testCaseName) throws InterruptedException, IOException {
        this.writeGamebaseAuthenticationResult(webDrvFn, gbInfo, fi, testCaseName);
        this.updateUserID(webDrvFn, gbInfo);
    }

    private void updateUserID(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(this.isChangeUserID(webDrvFn, gbInfo)) {
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.getAuth_UserID_TextArea_Id()));
        }

        else {
            System.out.println("[Auth PC][Update User ID] : UserID is not change");
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.getAuth_UserID_TextArea_Id()));
        }
    }

    private void writeGamebaseAuthenticationResult(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi, String testCaseName) throws InterruptedException, IOException {
        if(this.isChangeUserID(webDrvFn, gbInfo)) {
            System.out.println("[Auth PC][" + testCaseName + "] : Success");
            fi.writeTestResult(gbInfo, testCaseName, true);
        }

        else {
            System.out.println("[Auth PC][" + testCaseName + "] : !!!!! Failure !!!!!");
            fi.writeTestResult(gbInfo, testCaseName, false);
        }
    }

    /**
     * Login / Logout 은 정상 동작하면 UserID 가 변경되기 때문에 <br/>
     * UserID 가 변경되면 기능 정상 동작 <br/>
     * UserID 가 변경되지 않으면 동작 실패로 판단함 <br/>
     */
    private boolean isChangeUserID(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(webDrvFn.detectTextChangeById(webDrvFn.driver, webInfo.getAuth_UserID_TextArea_Id(), gbInfo.getUserID(),500, 5000)) {
            return true;
        }

        else {
            return false;
        }
    }
}