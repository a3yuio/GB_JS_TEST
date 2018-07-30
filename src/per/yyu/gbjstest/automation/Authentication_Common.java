package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Authentication_Common {
    WebElementInformation webInfo = new WebElementInformation();



    // Menu Setting
    public void openLoginMenu(WebDriverFunction webDrvFn) throws InterruptedException {
        if(this.detectApisPanelView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webInfo.getApis_Panel_Btn_Xpath());
        }

        Thread.sleep(500);

        if(this.detectAuthMenuView(webDrvFn) == false) {
            webDrvFn.clickElementByXpath(webInfo.getApis_Auth_Btn_Xpath());
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
     * Auth Menu 가 열려있으면 True 를 반환 <br/>
     * Auth Menu 가 닫혀있으면 False 를 반환 <br/><br/>
     * !! false 로 판단하는 이유<br/>
     * true 로 찾을 경우, 보이지 않으면 Exception 이 발생하기 때문에<br/>
     * false 로 찾아야 Exception 이 발생하지 않고 정상 동작함.<br/>
     */
    private boolean detectAuthMenuView(WebDriverFunction webDrvFn) {
        if(webDrvFn.findElementByXpath(webInfo.getAuth_LoginWith_Btn_Xpath()) == false) {
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
    public void selectIdP(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        switch(gbInfo.getIdPTypeNo()) {
            case 1: {
                System.out.println("[Authentication][Select IdP] : Guest");
                this.setIdPToGuest(webDrvFn);
                break;
            }

            case 2: {
                System.out.println("[Authentication][Select IdP] : Facebook");
                this.setIdPToFacebook(webDrvFn);
                break;
            }

            case 3: {
                System.out.println("[Authentication][Select IdP] : Payco");
                this.setIdPToPayco(webDrvFn);
                break;
            }

            case 4: {
                System.out.println("[Authentication][Select IdP] : Naver");
                this.setIdPToNaver(webDrvFn);
                break;
            }

            case 5: {
                System.out.println("[Authentication][Select IdP] : Google");
                this.setIdPToGoogle(webDrvFn);
                break;
            }
        }
    }

    private void setIdPToGuest(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAuth_IdPOption_Guest_Xpath());
    }

    private void setIdPToFacebook(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAuth_IdPOption_Facebook_Xpath());
    }

    private void setIdPToPayco(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAuth_IdPOption_Payco_Xpath());
    }

    private void setIdPToNaver(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAuth_IdPOption_Naver_Xpath());
    }

    private void setIdPToGoogle(WebDriverFunction webDrvFn) throws InterruptedException {
        webDrvFn.clickElementByXpath(webInfo.getAuth_IdPOption_Google_Xpath());
    }

    public String idpTypeNoToName(int idpTypeNo) {
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



    // Logout
    public void logout(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        this.openLoginMenu(webDrvFn);

        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webInfo.getAuth_Logout_Btn_Id());
        gbInfo.setTestEndTime();

        this.finishGamebaseAuthentication(webDrvFn, gbInfo, fi, "Logout");
    }

    public void withdraw(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException {
        this.openLoginMenu(webDrvFn);

        gbInfo.setTestStartTime();
        webDrvFn.clickElementById(webInfo.getAuth_Withdraw_Btn_Id());
        gbInfo.setTestEndTime();

        this.finishGamebaseAuthentication(webDrvFn, gbInfo, fi, "Withdraw");
    }



    // Finish Gamebase Authentication

    /**
     * updateUserID 에서 gbInfo 의 UserID 를 바꾸기 때문에 <br/>
     * Test Result 작성을 먼저 해야함. <br/>
     */
    public void finishGamebaseAuthentication(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi, String testCaseName) throws InterruptedException, IOException {
        this.writeGamebaseAuthenticationResult(webDrvFn, gbInfo, fi, testCaseName);
        this.updateUserID(webDrvFn, gbInfo);
    }

    private void updateUserID(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(this.isChangeUserID(webDrvFn, gbInfo)) {
            System.out.println("[Authentication][Update User ID] : Success");
            gbInfo.setUserID(webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()));
        }

        else {
            System.out.println("[Authentication][Update User ID] : UserID is not change");
            gbInfo.setUserID(webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()));
        }
    }

    private void writeGamebaseAuthenticationResult(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi, String testCaseName) throws InterruptedException, IOException {
        if(this.isChangeUserID(webDrvFn, gbInfo)) {
            System.out.println("[Authentication][" + testCaseName + "] : Success");
            fi.writeTestResult(gbInfo, testCaseName, webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()), true);
        }

        else {
            System.out.println("[Authentication][" + testCaseName + "] : !!!!! Failure !!!!!");
            fi.writeTestResult(gbInfo, testCaseName, webDrvFn.getTextById(webInfo.getAuth_UserID_TextArea_Id()), false);
        }
    }

    /**
     * Login / Logout 은 정상 동작하면 UserID 가 변경되기 때문에 <br/>
     * UserID 가 변경되면 기능 정상 동작 <br/>
     * UserID 가 변경되지 않으면 동작 실패로 판단함 <br/>
     */
    private boolean isChangeUserID(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException {
        if(webDrvFn.detectTextChangeById(webInfo.getAuth_UserID_TextArea_Id(), gbInfo.getUserID(),500, 5000)) {
            return true;
        }

        else {
            return false;
        }
    }
}
