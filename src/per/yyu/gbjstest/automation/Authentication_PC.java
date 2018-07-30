package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Authentication_PC {
    WebElementInformation webInfo = new WebElementInformation();
    Authentication_Common authComm = new Authentication_Common();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;



    // Gamebase Authentication Run
    public void authenticationGamebaseForPC(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException {
        authComm.openLoginMenu(webDrvFn);

        if(gbInfo.getIdPTypeNo() == 352) {
            this.loginTestRegression(webDrvFn, gbInfo, fi);
        }

        else {
            this.loginTestAboutSpecificIdP(webDrvFn, gbInfo, fi);
        }
    }

    private void loginTestAboutSpecificIdP(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        gbInfo.setUserID(webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()));
        authComm.selectIdP(webDrvFn, gbInfo);
        this.runLogin(webDrvFn, gbInfo, fi);
    }

    private void loginTestRegression(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        int idpTypeNo = 1;
        int SUPPORT_IDP_QUANTITY = 5;

        // idpTypeNo 를 1 ~ 5 까지 동작해야 하므로, 1을 더해준다.
        while(idpTypeNo < SUPPORT_IDP_QUANTITY + 1) {
            gbInfo.setUserID(webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()));
            gbInfo.setIdPTypeNo(idpTypeNo);

            authComm.selectIdP(webDrvFn, gbInfo);

            this.runLogin(webDrvFn, gbInfo, fi);
            authComm.logout(webDrvFn, gbInfo, fi);

            this.runLogin(webDrvFn, gbInfo, fi);
            authComm.withdraw(webDrvFn, gbInfo, fi);

            this.runLogin(webDrvFn, gbInfo, fi);
            authComm.withdraw(webDrvFn, gbInfo, fi);

            idpTypeNo++;
        }
    }



    // Login Run
    private void runLogin(WebDriverFunction webDrvfn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        gbInfo.setTestStartTime();
        webDrvfn.clickElementByXpath(webInfo.getAuth_LoginWith_Btn_Xpath());

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
        webDrvfn.switchToMainWindow();
        gbInfo.setTestEndTime();

        authComm.finishGamebaseAuthentication(webDrvfn, gbInfo, fi, authComm.idpTypeNoToName(gbInfo.getIdPTypeNo()) + " Login");
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
        if(webDrvFn.findElementById(webInfo.get_PC_Facebook_LoginView_Login_Btn_Id(), 500, 5000)) {
            System.out.println(webDrvFn.driver.getCurrentUrl());
            webDrvFn.clearTextById(webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id(), gbInfo.getTestId(FACEBOOK));
            webDrvFn.clearTextById(webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementById(webInfo.get_PC_Facebook_LoginView_Login_Btn_Id());

            // Facebook OAuth Permission View
            if(webDrvFn.findElementByName(webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari(), 500, 5000)) {
                System.out.println(webDrvFn.driver.getCurrentUrl());
                webDrvFn.clickElementByName(webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari());
            }

            else {
                System.out.println("[Auth PC][Login Facebook For Safari] : Popup is not exist");
            }
        }

        // Facebook Re-Login View
        else if(webDrvFn.findElementByXpath(webInfo.get_PC_Facebook_Re_LoginView_Next_Btn_Xpath(), 500, 5000)) {
            System.out.println(webDrvFn.driver.getCurrentUrl());
            webDrvFn.clearTextByName(webInfo.get_PC_Facebook_Re_LoginView_PW_TextArea_Name());
            webDrvFn.sendTextByName(webInfo.get_PC_Facebook_Re_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementByXpath(webInfo.get_PC_Facebook_Re_LoginView_Next_Btn_Xpath());

            // Facebook OAuth Permission View
            if(webDrvFn.findElementByName(webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari(), 500, 5000)) {
                System.out.println(webDrvFn.driver.getCurrentUrl());
                webDrvFn.clickElementByName(webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Name_ForSafari());
            }

            else {
                System.out.println("[Auth PC][Login Facebook For Safari] : Popup is not exist");
            }
        }

        else {
            System.out.println("[Auth PC][Login Facebook For Safari] : Popup is not exist");
        }
    }

    /**
     * Safari 를 제외한 다른 Browser 들은 UI 가 모두 동일하여 <br/>
     * 같은 Element 를 사용할 수 있음. <br/>
     */
    private void loginFacebookForOtherBrowser(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Facebook Login View
        if(webDrvFn.findElementById(webInfo.get_PC_Facebook_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Facebook_LoginView_ID_TextArea_Id(), gbInfo.getTestId(FACEBOOK));
            webDrvFn.clearTextById(webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Facebook_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementById(webInfo.get_PC_Facebook_LoginView_Login_Btn_Id());

            // Faceboiok OAuth Permission View
            if(webDrvFn.findElementByXpath(webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clickElementByXpath(webInfo.get_PC_Facebook_PermissionView_Agree_Btn_Xpath());
            }

            else {
                System.out.println("[Auth PC][Login Facebook] : Popup is not exist");
            }
        }

        // Facebook Re-Login View
        else if(webDrvFn.findElementByXpath(webInfo.get_PC_Facebook_Re_LoginView_Next_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clearTextByName(webInfo.get_PC_Facebook_Re_LoginView_PW_TextArea_Name());
            webDrvFn.sendTextByName(webInfo.get_PC_Facebook_Re_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementByXpath(webInfo.get_PC_Facebook_Re_LoginView_Next_Btn_Xpath());
        }

        else {
            System.out.println("[Auth PC][Login Facebook] : Popup is not exist");
        }
    }



    // Payco Login
    private void loginPayco(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Payco Login View
        if(webDrvFn.findElementById(webInfo.get_PC_Payco_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_PC_Payco_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Payco_LoginView_ID_TextArea_Id(), gbInfo.getTestId(PAYCO));
            webDrvFn.clearTextById(webInfo.get_PC_Payco_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Payco_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(PAYCO));
            webDrvFn.clickElementById(webInfo.get_PC_Payco_LoginView_Login_Btn_Id());

            // 이후 새로운 브라우저 차단 페이지로 이동된다면
            // Payco 아이디의 보안설정에서 해당 옵션을 Off 한다.
            // 해당 로직은 Payco 의 스펙이지, SDK 의 스펙이 아니기 때문에
            // 필요없는 테스트 로직을 추가하지 않는다.
        }

        // Payco Simple Login View
        else if(webDrvFn.findElementById(webInfo.get_PC_Payco_SimpleLoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clickElementById(webInfo.get_PC_Payco_SimpleLoginView_Login_Btn_Id());
        }

        else {
            System.out.println("[Auth PC][Login Payco] : Popup is not exist");
        }
    }



    // Naver Login
    private void loginNaver(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Naver Login View
        if(webDrvFn.findElementByXpath(webInfo.get_PC_Naver_LoginView_Login_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_PC_Naver_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Naver_LoginView_ID_TextArea_Id(), gbInfo.getTestId(NAVER));
            webDrvFn.clearTextById(webInfo.get_PC_Naver_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Naver_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(NAVER));
            webDrvFn.clickElementByXpath(webInfo.get_PC_Naver_LoginView_Login_Btn_Xpath());

            // Naver OAuth Permission View
            if(webDrvFn.findElementByXpath(webInfo.get_PC_Naver_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clickElementByXpath(webInfo.get_PC_Naver_PermissionView_Agree_Btn_Xpath());
            }

            else {
                System.out.println("[Auth PC][Login Naver] : Popup is not exist");
            }
        }

        else {
            System.out.println("[Auth PC][Login Naver] : Popup is not exist");
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
        if(webDrvFn.findElementById(webInfo.get_PC_Google_IE10_LoginView_ID_Next_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_PC_Google_IE10_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Google_IE10_LoginView_ID_TextArea_Id(), gbInfo.getTestId(GOOGLE));
            webDrvFn.clickElementById(webInfo.get_PC_Google_IE10_LoginView_ID_Next_Btn_Id());

            // Google Login_PW View
            if(webDrvFn.findElementById(webInfo.get_PC_Google_IE10_LoginView_PW_Login_Btn_Id())) {
                webDrvFn.clearTextById(webInfo.get_PC_Google_IE10_LoginView_PW_TextArea_Id());
                webDrvFn.sendTextById(webInfo.get_PC_Google_IE10_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementById(webInfo.get_PC_Google_IE10_LoginView_PW_Login_Btn_Id());

                // Google OAuth Permission View
                if(webDrvFn.findElementById(webInfo.get_PC_Google_IE10_Permisiion_Agree_Btn_Id())) {
                    webDrvFn.clickElementById(webInfo.get_PC_Google_IE10_Permisiion_Agree_Btn_Id());
                }

                else {
                    System.out.println("[Auth PC][Login Google For IE10] : Popup is not exist");
                }
            }

            else {
                System.out.println("[Auth PC][Login Google For IE10] : Popup is not exist");
            }
        }

        else {
            System.out.println("[Auth PC][Login Google For IE10] : Popup is not exist");
        }
    }



    private void loginGoogleForOtherBrowser(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Google Login_ID View
        if(webDrvFn.findElementByXpath(webInfo.get_PC_Google_LoginView_ID_Next_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_PC_Google_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_PC_Google_LoginView_ID_TextArea_Id(), gbInfo.getTestId(GOOGLE));
            webDrvFn.clickElementByXpath(webInfo.get_PC_Google_LoginView_ID_Next_Btn_Xpath());

            // Google Login_PW View
            if(webDrvFn.findElementByXpath(webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clearTextByName(webInfo.get_PC_Google_LoginView_PW_TextArea_Name());
                webDrvFn.sendTextByName(webInfo.get_PC_Google_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementByXpath(webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath());

                // 이후 문제가 생기면 sleep 2000 추가
            }

            else {
                System.out.println("[Auth PC][Login Google] : Popup is not exist");
            }
        }

        // Google Simple Login View
        else if(webDrvFn.findElementById(webInfo.get_PC_Google_IDSelectView_OtherID_Btn_Id(), 500, 5000)) {
            webDrvFn.clickElementByXpath(webInfo.get_PC_Google_IDSelectView_RecentID_Btn_Xpath());

            // Google Login_PW View
            if(webDrvFn.findElementByXpath(webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clearTextByName(webInfo.get_PC_Google_LoginView_PW_TextArea_Name());
                webDrvFn.sendTextByName(webInfo.get_PC_Google_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementByXpath(webInfo.get_PC_Google_LoginView_PW_Next_Btn_Xpath());
            }

            else {
                System.out.println("[Auth PC][Login Google] : Popup is not exist");
            }
        }

        else {
            System.out.println("[Auth PC][Login Google] : Popup is not exist");
        }
    }
}