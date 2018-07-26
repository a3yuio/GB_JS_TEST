package per.yyu.gbjstest.automation;

public class Authentication_PC {
    WebElementInformation webInfo = new WebElementInformation();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;



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
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.getApis_Auth_Btn_Xpath()) == false) {
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
        }

        // Faceboiok OAuth Permission View
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Xpath());
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

    private void loginNaver(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) {
        
    }
}