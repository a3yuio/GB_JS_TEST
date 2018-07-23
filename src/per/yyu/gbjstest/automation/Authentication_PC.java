package per.yyu.gbjstest.automation;

import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Authentication_PC
{
    WebElementInformation webInfo = new WebElementInformation();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;

    public void gamebaseAuthentication_PC(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        if(gbInfo.getIDPIndex() == 352)
        {
            this.regressionLoginTest(webDrvFn, gbInfo, fi);
        }

        else
        {
            this.specificIdPLoginTest(webDrvFn, gbInfo, fi);
        }
    }

    private void specificIdPLoginTest(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        if(gbInfo.getLaunchingStatus() == false)
        {
            System.out.println("[Auth PC][GB Authentication] : Not Initialize");
        }

        else
        {
            // Set
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.auth_UserID_TextArea_ById, 500, 5000));
            this.loginMenuOpener(webDrvFn);
            this.idPSelector(webDrvFn, gbInfo);

            // Run
            this.loginRunner(webDrvFn, gbInfo, fi);
        }
    }


    // Menu Setter
    private void loginMenuOpener(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(this.apisPanelViewDetector(webDrvFn) == false)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.apis_Panel_Btn_ByXpath);
        }

        Thread.sleep(500);

        if(this.authMenuDetector(webDrvFn) == false)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.apis_Auth_Btn_ByXpath);
        }
    }

    private boolean apisPanelViewDetector(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.apis_Panel_View_ByXpath) == false)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    private boolean authMenuDetector(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.auth_LoginWith_Btn_ByXpath) == false)
        {
            return false;
        }

        else
        {
            return true;
        }
    }


    // IdP Setter
    private void idPSelector(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        switch(gbInfo.getIDPIndex())
        {
            case 1:
            {
                System.out.println("[Auth PC][IdP Selector] : Guest");
                this.setIdPToGuest(webDrvFn);
                break;
            }

            case 2:
            {
                System.out.println("[Auth PC][IdP Selector] : Facebook");
                this.setIdPToFacebook(webDrvFn);
                break;
            }

            case 3:
            {
                System.out.println("[Auth PC][IdP Selector] : Payco");
                this.setIdPToPayco(webDrvFn);
                break;
            }

            case 4:
            {
                System.out.println("[Auth PC][IdP Selector] : Naver");
                this.setIdPToNaver(webDrvFn);
                break;
            }

            case 5:
            {
                System.out.println("[Auth PC][IdP Selector] : Google");
                this.setIdPToGoogle(webDrvFn);
                break;
            }
        }
    }

    private void setIdPToGuest(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Guest_ByXpath);
    }

    private void setIdPToFacebook(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Facebook_ByXpath);
    }

    private void setIdPToPayco(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Payco_ByXpath);
    }

    private void setIdPToNaver(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Naver_ByXpath);
    }

    private void setIdPToGoogle(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Google_ByXpath);
    }

    private String idPIndexToName(int idPIndex)
    {
        switch(idPIndex)
        {
            case 1:
            {
                return "Guest";
            }

            case 2:
            {
                return "Facebook";
            }

            case 3:
            {
                return "Payco";
            }

            case 4:
            {
                return "Naver";
            }

            case 5:
            {
                return "Google";
            }
        }

        return "";
    }


    // Popup Setter
    private void switchToSubWindow(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        webDrvFn.readyToPopupWindowHandler(gbInfo);
        webDrvFn.switchToSubWindow();
    }


    // Login Action
    private void loginRunner(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {
        gbInfo.setTestStartTime();

        if(gbInfo.getLoginStatus() == true)
        {
            System.out.println("[Auth PC][Login Runner] : This api must called by Not Logged-In status");
            this.finishGamebaseAuthentication(webDrvFn, gbInfo);
            gbInfo.setTestEndTime();
            fi.csvTestResultWriter(gbInfo, this.idPIndexToName(gbInfo.getIDPIndex()) + " Login", "Failure");
        }

        else
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_LoginWith_Btn_ByXpath);

            if(gbInfo.getIDPIndex() > 1)
            {
                this.switchToSubWindow(webDrvFn, gbInfo);

                switch(gbInfo.getIDPIndex())
                {
                    case 2:
                    {
                        this.facebookLogin(webDrvFn, gbInfo);
                        break;
                    }

                    case 3:
                    {
                        this.paycoLogin(webDrvFn, gbInfo);
                        break;
                    }

                    case 4:
                    {
                        this.naverLogin(webDrvFn, gbInfo);
                        break;
                    }

                    case 5:
                    {
                        this.googleLogin(webDrvFn, gbInfo);
                        break;
                    }
                }
            }
        }

        this.finishGamebaseAuthentication(webDrvFn, gbInfo);
        fi.gbLoginTestResultWriter(gbInfo, this.idPIndexToName(gbInfo.getIDPIndex()) + " Login");
    }

    private void facebookLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(gbInfo.getBrowserIndex() != 4)
        {
            this.fbLoginOtherBrowser(webDrvFn, gbInfo);
        }

        else
        {
            this.fbLoginForSafari(webDrvFn, gbInfo);
        }
    }

    private void fbLoginForSafari(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_Login_Btn_ById, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_ID_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_ID_TextArea_ById, gbInfo.getTestID(FACEBOOK));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_PW_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_PW_TextArea_ById, gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_Login_Btn_ById);

            if(webDrvFn.findElementByName(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByName_ForSafari, 500, 5000) == true)
            {
                webDrvFn.clickElementByName(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByName_ForSafari);
                webDrvFn.switchToMainWindow();
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else if(webDrvFn.findElementByName(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByName_ForSafari, 500, 5000) == true)
        {
            webDrvFn.clickElementByName(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByName_ForSafari);
            webDrvFn.switchToMainWindow();
        }

        else
        {
            webDrvFn.switchToMainWindow();
        }
    }

    private void fbLoginOtherBrowser(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_Login_Btn_ById, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_ID_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_ID_TextArea_ById, gbInfo.getTestID(FACEBOOK));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_PW_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_PW_TextArea_ById, gbInfo.getTestPW(FACEBOOK));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Facebook_LoginView_Login_Btn_ById);

            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByXpath, 500, 5000) == true)
            {
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByXpath);
                webDrvFn.switchToMainWindow();
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByXpath, 500, 5000) == true)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Facebook_PermissionView_Agree_Btn_ByXpath);
            webDrvFn.switchToMainWindow();
        }

        else
        {
            webDrvFn.switchToMainWindow();
        }
    }

    private void paycoLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Payco_LoginView_Login_Btn_ById, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Payco_LoginView_ID_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Payco_LoginView_ID_TextArea_ById, gbInfo.getTestID(PAYCO));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Payco_LoginView_PW_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Payco_LoginView_PW_TextArea_ById, gbInfo.getTestPW(PAYCO));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Payco_LoginView_Login_Btn_ById);

            if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Payco_SecurityView_Birthday_TextArea_ById, 500, 5000) == true)
            {
                webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Payco_SecurityView_Birthday_TextArea_ById);
                webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Payco_SecurityView_Birthday_TextArea_ById, "19890228");
                webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Payco_SecurityView_Login_Btn_ById);
                webDrvFn.switchToMainWindow();
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Payco_SimpleLoginView_Login_Btn_ById, 500, 5000) == true)
        {
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Payco_SimpleLoginView_Login_Btn_ById);
            webDrvFn.switchToMainWindow();
        }

        else
        {
            webDrvFn.switchToMainWindow();
        }
    }

    private void naverLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Naver_LoginView_Login_Btn_ByXpath, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Naver_LoginView_ID_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Naver_LoginView_ID_TextArea_ById, gbInfo.getTestID(NAVER));
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Naver_LoginView_PW_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Naver_LoginView_PW_TextArea_ById, gbInfo.getTestPW(NAVER));
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Naver_LoginView_Login_Btn_ByXpath);

            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Naver_PermissionView_Agree_Btn_ByXpath, 500, 5000) == true)
            {
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Naver_PermissionView_Agree_Btn_ByXpath);
                webDrvFn.switchToMainWindow();
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else
        {
            webDrvFn.switchToMainWindow();
        }
    }

    private void googleLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(gbInfo.getIEVersion().equals("10"))
        {
            this.googleLoginForIE10(webDrvFn, gbInfo);
        }

        else
        {
            this.googleLoginOtherBrowser(webDrvFn, gbInfo);
        }
    }

    private void googleLoginOtherBrowser(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Google_LoginView_ID_Next_Btn_ByXpath, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Google_LoginView_ID_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Google_LoginView_ID_TextArea_ById, gbInfo.getTestID(GOOGLE));
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Google_LoginView_ID_Next_Btn_ByXpath);

            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_Next_Btn_ByXpath, 500, 5000) == true)
            {
                webDrvFn.clearTextByName(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_TextArea_ByName);
                webDrvFn.sendTextByName(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_TextArea_ByName, gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_Next_Btn_ByXpath);
                // if have an error, add sleep 2000
                webDrvFn.switchToMainWindow();
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Google_IDSelectView_OtherID_Btn_ById, 500, 5000) == true)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Google_IDSelectView_RecentID_Btn_ByXpath);

            if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_Next_Btn_ByXpath, 500, 5000) == true)
            {
                webDrvFn.clearTextByName(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_TextArea_ByName);
                webDrvFn.sendTextByName(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_TextArea_ByName, gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.pc_Google_LoginView_PW_Next_Btn_ByXpath);
                webDrvFn.switchToMainWindow();
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else
        {
            webDrvFn.switchToMainWindow();
        }
    }

    private void googleLoginForIE10 (WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_ID_Next_Btn_ById, 500, 5000) == true)
        {
            webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_ID_TextArea_ById);
            webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_ID_TextArea_ById, gbInfo.getTestID(GOOGLE));
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_ID_Next_Btn_ById);

            if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_PW_Login_Btn_ById, 500, 5000) == true)
            {
                webDrvFn.clearTextById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_PW_TextArea_ById);
                webDrvFn.sendTextById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_PW_TextArea_ById, gbInfo.getTestPW(GOOGLE));
                webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Google_OLD_LoginView_PW_Login_Btn_ById);

                if(webDrvFn.findElementById(webDrvFn.driver, webInfo.pc_Google_OLD_Permisiion_Agree_Btn_ById, 500, 5000) == true)
                {
                    webDrvFn.clickElementById(webDrvFn.driver, webInfo.pc_Google_OLD_Permisiion_Agree_Btn_ById);
                    webDrvFn.switchToMainWindow();
                }

                else
                {
                    webDrvFn.switchToMainWindow();
                }
            }

            else
            {
                webDrvFn.switchToMainWindow();
            }
        }

        else
        {
            webDrvFn.switchToMainWindow();
        }
    }


    // Logout
    private void logout(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {
        gbInfo.setTestStartTime();

        if(gbInfo.getLoginStatus() == false)
        {
            System.out.println("[Auth PC][Log out] : This api must called by Logged-In status");
            gbInfo.setTestEndTime();
            fi.gbLogoutTestResultWriter(gbInfo, "Logout");
        }

        else
        {
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.auth_Logout_Btn_ById);
            this.finishGamebaseAuthentication(webDrvFn, gbInfo);
            fi.gbLogoutTestResultWriter(gbInfo, "Logout");
        }
    }

    private void withdraw(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {
        gbInfo.setTestStartTime();

        if(gbInfo.getLoginStatus() == false)
        {
            System.out.println("[Auth PC][Withdraw] : This api must called by Logged-In status");
            gbInfo.setTestEndTime();
            fi.gbLogoutTestResultWriter(gbInfo, "Withdraw");
        }

        else
        {
            webDrvFn.clickElementById(webDrvFn.driver, webInfo.auth_Withdraw_Btn_ById);
            this.finishGamebaseAuthentication(webDrvFn, gbInfo);
            fi.gbLogoutTestResultWriter(gbInfo, "Withdraw");
        }
    }


    // Update UserID and Login Status
    private void finishGamebaseAuthentication(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(gbInfo.getBrowserIndex() == 4)
        {
            webDrvFn.resetBrowserPopupWindowCollectorForSafari();
        }

        this.updateGBUserID(webDrvFn, gbInfo);
        this.updateLoggedInStatus(gbInfo);
    }

    private void updateGBUserID(WebDriverFunction webDrvFn, GamebaseInformation gbInfo) throws InterruptedException
    {
        if(webDrvFn.textChangeDetectorById(webDrvFn.driver, webInfo.auth_UserID_TextArea_ById, gbInfo.getUserID(), 500, 5000) == true)
        {
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.auth_UserID_TextArea_ById, 500, 5000));
        }

        else
        {
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.auth_UserID_TextArea_ById, 500, 5000));
        }
    }

    private void updateLoggedInStatus(GamebaseInformation gbInfo)
    {
        if(gbInfo.getUserID().contains("@") == true)
        {
            gbInfo.setLoginStatus(true);
        }

        else
        {
            gbInfo.setLoginStatus(false);
        }
    }



    // For Regression Test
    private void regressionLoginTest(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws InterruptedException, IOException
    {
        int idpIndex = 1;

        while(idpIndex < 6)
        {
            gbInfo.setUserID(webDrvFn.getTextById(webDrvFn.driver, webInfo.auth_UserID_TextArea_ById, 500, 5000));
            this.loginMenuOpener(webDrvFn);

            gbInfo.setIDPIndex(idpIndex);

            this.idPSelector(webDrvFn, gbInfo);

            this.loginRunner(webDrvFn, gbInfo, fi);
            this.logout(webDrvFn, gbInfo, fi);

            this.loginRunner(webDrvFn, gbInfo, fi);
            this.withdraw(webDrvFn, gbInfo, fi);

            this.loginRunner(webDrvFn, gbInfo, fi);
            this.withdraw(webDrvFn, gbInfo, fi);

            idpIndex++;
        }
    }
}