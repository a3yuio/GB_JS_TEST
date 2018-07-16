package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Authentication_Mobile
{
    WebElementInformation webInfo = new WebElementInformation();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;

    // Menu Setter
    private void loginMenuOpener(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(this.apisPanelViewDetector(webDrvFn) == false)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.apis_Panel_Btn_ByXPath);
        }

        Thread.sleep(500);

        if(this.authMenuDetector(webDrvFn) == false)
        {
            webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.apis_Auth_Btn_ByXPath);
        }
    }

    private boolean apisPanelViewDetector(WebDriverFunction webDrvFn) throws InterruptedException
    {
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.apis_Panel_View_ByXPath) == false)
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
        if(webDrvFn.findElementByXpath(webDrvFn.driver, webInfo.auth_LoginWith_Btn_ByXPath) == false)
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
                System.out.println("[Auth Mobile][IdP Selector] : Guest");
                this.setIdPToGuest(webDrvFn);
                break;
            }

            case 2:
            {
                System.out.println("[Auth Mobile][IdP Selector] : Facebook");
                this.setIdPToFacebook(webDrvFn);
                break;
            }

            case 3:
            {
                System.out.println("[Auth Mobile][IdP Selector] : Payco");
                this.setIdPToPayco(webDrvFn);
                break;
            }

            case 4:
            {
                System.out.println("[Auth Mobile][IdP Selector] : Naver");
                this.setIdPToNaver(webDrvFn);
                break;
            }

            case 5:
            {
                System.out.println("[Auth Mobile][IdP Selector] : Google");
                this.setIdPToGoogle(webDrvFn);
                break;
            }
        }
    }

    private void setIdPToGuest(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Guest_ByXPath);
    }

    private void setIdPToFacebook(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Facebook_ByXPath);
    }

    private void setIdPToPayco(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Payco_ByXPath);
    }

    private void setIdPToNaver(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Naver_ByXPath);
    }

    private void setIdPToGoogle(WebDriverFunction webDrvFn) throws InterruptedException
    {
        webDrvFn.clickElementByXpath(webDrvFn.driver, webInfo.auth_IdPOption_Google_ByXPath);
    }


    // Login Action
    private void loginRunner(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {
        switch(gbInfo.getIDPIndex())
        {
            case 1:
            {
                this.guestLogin(webDrvFn, gbInfo, fi);
                break;
            }

            case 2:
            {
                this.facebookLogin(webDrvFn, gbInfo, fi);
                break;
            }

            case 3:
            {
                this.paycoLogin(webDrvFn, gbInfo, fi);
                break;
            }

            case 4:
            {
                this.naverLogin(webDrvFn, gbInfo, fi);
                break;
            }

            case 5:
            {
                this.googleLogin(webDrvFn, gbInfo, fi);
                break;
            }
        }
    }

    public void guestLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {

    }

    private void facebookLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {

    }

    private void paycoLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {

    }

    private void naverLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {

    }

    private void googleLogin(WebDriverFunction webDrvFn, GamebaseInformation gbInfo, FileIO fi) throws IOException, InterruptedException
    {

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
}
