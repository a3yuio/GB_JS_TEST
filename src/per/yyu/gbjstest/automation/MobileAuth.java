package per.yyu.gbjstest.automation;

import java.io.IOException;

public class MobileAuth
{
    WebElementInformation webinfo = new WebElementInformation();

    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;

    private void logout(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi)
    {

    }

    private void withdraw(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi)
    {

    }

    private void m_FacebookLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        System.out.println("[YYU][Facebook Login] : Start");
        gbinfo.setTestCaseStart();

        if(gbinfo.getLoginStatus() == true)
        {
            System.out.println("[YYU][Facebook Login] : This api must called by Not Logged-In Status");
            this.concludeAuthentication(webapi, gbinfo);
            gbinfo.setTestCaseEnd();
            fi.csvWriter("Facebook Login", gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
        }

        else
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_IdPOption_FacebookXPath);
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_LoginWithButtonXPath);

            webapi.readyToWindowHandler(gbinfo);
            webapi.switchToSubWindow();

            if(webapi.findElementByNameWithPolling(webapi.driver, webinfo.m_Facebook_LoginView_LoginButtonName, 500, 5000) == true)
            {
                webapi.textClearById(webapi.driver, webinfo.m_Facebook_LoginView_IdTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.m_Facebook_LoginView_IdTextAreaId, gbinfo.getTestId(FACEBOOK));
                webapi.textClearById(webapi.driver, webinfo.m_Facebook_LoginView_PWTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.m_Facebook_LoginView_PWTextAreaId, gbinfo.getTestPw(FACEBOOK));
                webapi.clickElementByName(webapi.driver, webinfo.m_Facebook_LoginView_LoginButtonName);

                if(webapi.findElementByXPathWithPolling(webapi.driver, webinfo.m_Facebook_Permission_AgreeButtonXPath, 500, 5000) == true)
                {
                    webapi.clickElementByXPath(webapi.driver, webinfo.m_Facebook_Permission_AgreeButtonXPath);
                    webapi.switchToMainWindow();
                }

                else
                {
                    webapi.switchToMainWindow();
                }
            }
        }

        webapi.finishWindowHandler();
        this.concludeAuthentication(webapi, gbinfo);
        fi.loginTestResultWriter(gbinfo, "Facebook Login");
    }

    private void m_PaycoLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        System.out.println("[YYU][Payco Login] : Start");
        gbinfo.setTestCaseStart();

        if(this.isAPIsPanelOpened(webapi) == false)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.panel_APIsPanelButtonXPath);

            if(this.isAuthMenuOpened(webapi) == false)
            {
                webapi.clickElementByXPath(webapi.driver, webinfo.apis_AuthMenuButtonXpath);
            }
        }

        if(gbinfo.getLoginStatus() == true)
        {
            System.out.println("[YYU][Payco Login] : This api must called by Not Logged-In Status");
            this.concludeAuthentication(webapi, gbinfo);
            gbinfo.setTestCaseEnd();
            fi.csvWriter("Payco Login", gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
        }

        else
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_IdPOption_PaycoXPath);
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_LoginWithButtonXPath);

            webapi.readyToWindowHandler(gbinfo);
            webapi.switchToSubWindow();

            if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.m_Payco_LoginView_LoginButtonId, 500, 5000) == true)
            {
                webapi.textClearById(webapi.driver, webinfo.m_Payco_LoginView_IdTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.m_Payco_LoginView_IdTextAreaId, gbinfo.getTestId(PAYCO));
                webapi.textClearById(webapi.driver, webinfo.m_Payco_LoginView_PWTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.m_Payco_LoginView_PWTextAreaId, gbinfo.getTestPw(PAYCO));
                webapi.clickElementById(webapi.driver, webinfo.m_Payco_LoginView_LoginButtonId);

                webapi.switchToMainWindow();
            }

            else if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.m_Payco_SimpleLoginView_ConfirmButtonId, 500, 5000) == true)
            {
                webapi.clickElementById(webapi.driver, webinfo.m_Payco_SimpleLoginView_ConfirmButtonId);
                webapi.switchToMainWindow();
            }

            else
            {
                webapi.switchToMainWindow();
            }
        }

        webapi.finishWindowHandler();

        if(this.isAuthMenuOpened(webapi) == true)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.apis_CoreMenuButtonXPath);

            if(this.isAPIsPanelOpened(webapi) == true)
            {
                webapi.clickElementByXPath(webapi.driver, webinfo.panel_APIsPanelButtonXPath);
            }
        }

        this.concludeAuthentication(webapi, gbinfo);
        fi.loginTestResultWriter(gbinfo, "Payco Login");
    }

    private void openLoginMenu(WebDriverAPI webapi) throws InterruptedException
    {
        if(this.isAPIsPanelOpened(webapi) == false)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.panel_APIsPanelButtonXPath);
        }

        Thread.sleep(500); // UI Delay

        if(this.isAuthMenuOpened(webapi) == false)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.apis_AuthMenuButtonXpath);
        }
    }

    private boolean isAPIsPanelOpened(WebDriverAPI webapi)
    {
        if(webapi.findElementByXPath(webapi.driver, webinfo.view_APIsPanelView) == false)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    private boolean isAuthMenuOpened(WebDriverAPI webapi)
    {
        if(webapi.findElementByXPath(webapi.driver, webinfo.auth_LoginWithButtonXPath) == false)
        {
            return false;
        }

        else
        {
            return true;
        }
    }

    private void concludeAuthentication(WebDriverAPI webapi, GamebaseInformation gbinfo) throws InterruptedException
    {
        this.updateUserId(webapi, gbinfo);
        this.updateLoginStatus(gbinfo);
    }

    private void updateUserId(WebDriverAPI webapi, GamebaseInformation gbinfo) throws InterruptedException
    {
        if(webapi.detectorOfTextChangeByIdWithPolling(webapi.driver, webinfo.auth_UserIdTextId, gbinfo.getUserId(), 500, 5000) == true)
        {
            gbinfo.setUserId(webapi.getTextById(webapi.driver, webinfo.auth_UserIdTextId));
        }

        else
        {
            gbinfo.setUserId(webapi.getTextById(webapi.driver, webinfo.auth_UserIdTextId));
        }
    }

    private void updateLoginStatus(GamebaseInformation gbinfo)
    {
        if(gbinfo.getUserId().contains("@") == true)
        {
            gbinfo.setLoginStatus(true);
        }

        else
        {
            gbinfo.setLoginStatus(false);
        }
    }

    public void authRegressionMobileTest(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws IOException, InterruptedException
    {
        this.openLoginMenu(webapi);
        fi.testAccountSetter(gbinfo);
        gbinfo.setUserId(webapi.getTextById(webapi.driver, webinfo.auth_UserIdTextId));

        this.m_FacebookLogin(webapi, gbinfo, fi);
        this.logout(webapi, gbinfo, fi);
        this.m_FacebookLogin(webapi, gbinfo, fi);
        this.withdraw(webapi, gbinfo, fi);

        this.m_PaycoLogin(webapi, gbinfo, fi);
        this.logout(webapi, gbinfo, fi);
        this.m_PaycoLogin(webapi, gbinfo, fi);
        this.withdraw(webapi, gbinfo, fi);
    }
}
