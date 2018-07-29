package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Authentication_Mobile {
    WebElementInformation webInfo = new WebElementInformation();
    Authentication_Common authComm = new Authentication_Common();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;



    // Gamebase Authentication Run
    public void authenticationGamebaseForMobile(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
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

    private void loginTestRegression(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
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
    private void runLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        gbInfo.setTestStartTime();
        webDrvFn.clickElementByXpath(webInfo.getAuth_LoginWith_Btn_Xpath());

        // Guest Login 은 이후 처리할 것이 없음
        if(gbInfo.getIdPTypeNo() > 1) {
            webDrvFn.readyToPopupHandle(gbInfo);
            webDrvFn.switchToSubWindow();

            switch(gbInfo.getIdPTypeNo()) {
                case 2: {
                    this.loginFacebook(webDrvFn, gbInfo);
                    break;
                }

                case 3: {
                    this.loginPayco(webDrvFn, gbInfo);
                    break;
                }

                case 4: {
                    this.loginNaver(webDrvFn, gbInfo);
                    break;
                }

                case 5: {
                    this.loginGoogle(webDrvFn, gbInfo);
                    break;
                }
            }
        }
        gbInfo.setTestEndTime();

        authComm.finishGamebaseAuthentication(webDrvFn, gbInfo, fi, authComm.idpTypeNoToName(gbInfo.getIdPTypeNo()) + " Login");
    }



    // Facebook Login
    private void loginFacebook(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Facebook Login View
        if(webDrvFn.findElementByName(webInfo.get_M_A_Facebook_LoginView_Login_Btn_Name(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_M_A_Facebook_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_M_A_Facebook_LoginView_ID_TextArea_Id(), gbInfo.getTestId(FACEBOOK));
            webDrvFn.clearTextById(webInfo.get_M_A_Facebook_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_M_A_Facebook_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementByName(webInfo.get_M_A_Facebook_LoginView_Login_Btn_Name());
        }

        // Facebook OAuth Permission View
        if(webDrvFn.findElementByXpath(webInfo.get_M_A_Facebook_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
            webDrvFn.clickElementByXpath(webInfo.get_M_A_Facebook_PermissionView_Agree_Btn_Xpath());
            webDrvFn.switchToMainWindow();
        }

        else {
            webDrvFn.switchToMainWindow();
        }
    }



    // Payco Login
    private void loginPayco(WebDriverFunction webDrvfn, GamebaseInformation gbInfo) throws InterruptedException {
        // Payco Login View
        if(webDrvfn.findElementById(webInfo.get_M_A_Payco_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvfn.clearTextById(webInfo.get_M_A_Payco_LoginView_ID_TextArea_Id());
            webDrvfn.sendTextById(webInfo.get_M_A_Payco_LoginView_ID_TextArea_Id(), gbInfo.getTestId(PAYCO));
            webDrvfn.clearTextById(webInfo.get_M_A_Payco_LoginView_PW_TextArea_Id());
            webDrvfn.sendTextById(webInfo.get_M_A_Payco_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(PAYCO));
            webDrvfn.clickElementById(webInfo.get_M_A_Payco_LoginView_Login_Btn_Id());
            webDrvfn.switchToMainWindow();
        }

        // Payco Simple Login View
        else if(webDrvfn.findElementById(webInfo.get_M_A_Payco_SimpleLoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvfn.clickElementById(webInfo.get_M_A_Payco_SimpleLoginView_Login_Btn_Id());
            webDrvfn.switchToMainWindow();
        }

        else {
            webDrvfn.switchToMainWindow();
        }
    }



    // Naver Login
    private void loginNaver(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        // Naver Login View
        if(webDrvFn.findElementById(webInfo.get_M_A_Naver_LoginView_Login_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_M_A_Naver_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_M_A_Naver_LoginView_ID_TextArea_Id(), gbInfo.getTestId(NAVER));
            webDrvFn.clearTextById(webInfo.get_M_A_Naver_LoginView_PW_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_M_A_Naver_LoginView_PW_TextArea_Id(), gbInfo.getTestPW(NAVER));
            webDrvFn.clickElementById(webInfo.get_M_A_Naver_LoginView_Login_Btn_Id());

            // Naver OAuth Permission View
            if(webDrvFn.findElementByXpath(webInfo.get_M_A_Naver_PermissionView_Agree_Btn_Xpath(), 500, 5000)) {
                webDrvFn.clickElementByXpath(webInfo.get_M_A_Naver_PermissionView_Agree_Btn_Xpath());
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
        // Google Login View _ ID
        if(webDrvFn.findElementById(webInfo.get_M_A_Google_LoginView_ID_Next_Btn_Id(), 500, 5000)) {
            webDrvFn.clearTextById(webInfo.get_M_A_Google_LoginView_ID_TextArea_Id());
            webDrvFn.sendTextById(webInfo.get_M_A_Google_LoginView_ID_TextArea_Id(), gbInfo.getTestId(GOOGLE));
            webDrvFn.clickElementById(webInfo.get_M_A_Google_LoginView_ID_Next_Btn_Id());

            // Google Login View _ PW
            if(webDrvFn.findElementById(webInfo.get_M_A_Google_LoginView_PW_Next_Btn_Id(), 500, 5000)) {
                webDrvFn.clearTextByName(webInfo.get_M_A_Google_LoginView_PW_TextArea_Name());
                webDrvFn.sendTextByName(webInfo.get_M_A_Google_LoginView_PW_TextArea_Name(), gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementById(webInfo.get_M_A_Google_LoginView_PW_Next_Btn_Id());
                webDrvFn.switchToMainWindow();
            }

            else {
                webDrvFn.switchToMainWindow();
            }
        }

        // Google Re-Login View
        else if(webDrvFn.findElementById(webInfo.get_M_A_Google_IDSelectView_OtherID_Btn_Id(), 500, 5000)) {
            webDrvFn.clickElementByXpath(webInfo.get_M_A_Google_IDSelectView_RecentId_Btn_Xpath());
            webDrvFn.switchToMainWindow();
        }

        else {
            webDrvFn.switchToMainWindow();
        }
    }
}