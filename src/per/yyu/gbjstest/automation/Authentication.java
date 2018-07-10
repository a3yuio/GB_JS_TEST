package per.yyu.gbjstest.automation;

import java.io.IOException;

public class Authentication
{
    WebElementInformation webinfo = new WebElementInformation();
    
    final int FACEBOOK = 2;
    final int PAYCO = 3;
    final int NAVER = 4;
    final int GOOGLE = 5;

    public void idPLoginRun(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        this.openLoginMenu(webapi);
        fi.testAccountSetter(gbinfo);
        gbinfo.setUserId(webapi.getTextById(webapi.driver, webinfo.auth_UserIdTextId));

        switch(gbinfo.getIdPIndex())
        {
            case 1:
            {
                System.out.println("[YYU][IdP Selector] : Guest !");
                this.guestLogin(webapi, gbinfo, fi);
                break;
            }

            case 2:
            {
                System.out.println("[YYU][IdP Selector] : Facebook !");
                this.facebookLogin(webapi, gbinfo, fi);
                break;
            }

            case 3:
            {
                System.out.println("[YYU][IdP Selector] : Payco !");
                this.paycoLogin(webapi, gbinfo, fi);
                break;
            }

            case 4:
            {
                System.out.println("[YYU][IdP Selector] : Naver !");
                this.naverLogin(webapi, gbinfo, fi);
                break;
            }

            case 5:
            {
                System.out.println("[YYU][IdP Selector] : Google !");
                this.googleLogin(webapi, gbinfo, fi);
                break;
            }

            // IdP Regression Test
            case 352:
            {
                this.guestLogin(webapi, gbinfo, fi);
                this.guestLogin(webapi, gbinfo, fi); // Failure Test Case
                this.logout(webapi, gbinfo, fi);
                this.guestLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);
                this.guestLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);

                this.facebookLogin(webapi, gbinfo, fi);
                this.logout(webapi, gbinfo, fi);
                this.facebookLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);
                this.facebookLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);

                this.paycoLogin(webapi, gbinfo, fi);
                this.logout(webapi, gbinfo, fi);
                this.paycoLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);
                this.paycoLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);

                this.naverLogin(webapi, gbinfo, fi);
                this.logout(webapi, gbinfo, fi);
                this.naverLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);
                this.naverLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);

                this.googleLogin(webapi, gbinfo, fi);
                this.logout(webapi, gbinfo, fi);
                this.googleLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);
                this.googleLogin(webapi, gbinfo, fi);
                this.withdraw(webapi, gbinfo, fi);

                break;
            }
        }
    }

    private void logout(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        gbinfo.setTestCaseStart();

        if(gbinfo.getLoginStatus() == false)
        {
            System.out.println("[YYU][Logout] : This api must called by Logged-In Status");
            gbinfo.setTestCaseEnd();
            fi.logoutTestResultWriter(gbinfo, "Logout");
        }

        else
        {
            webapi.clickElementById(webapi.driver, webinfo.auth_LogoutButtonId);
            this.concludeAuthentication(webapi, gbinfo);
            fi.logoutTestResultWriter(gbinfo, "Logout");
        }
    }

    private void withdraw(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        gbinfo.setTestCaseStart();

        if(gbinfo.getLoginStatus() == false)
        {
            System.out.println("[YYU][Logout] : This api must called by Logged-In Status");
            gbinfo.setTestCaseEnd();
            fi.logoutTestResultWriter(gbinfo, "Withdraw");
        }

        else
        {
            webapi.clickElementById(webapi.driver, webinfo.auth_WithdrawButtonId);
            this.concludeAuthentication(webapi, gbinfo);
            fi.logoutTestResultWriter(gbinfo, "Withdraw");
        }
    }

    public void guestLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        gbinfo.setTestCaseStart();

        if(gbinfo.getLoginStatus() == true)
        {
            System.out.println("[YYU][Guest Login] : This api must called by Not Logged-In Status");
            this.concludeAuthentication(webapi, gbinfo);
            gbinfo.setTestCaseEnd();
            fi.csvWriter("Guest Login", gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
        }

        else
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_IdPOption_GuestXPath);
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_LoginWithButtonXPath);
            this.concludeAuthentication(webapi, gbinfo);
            fi.loginTestResultWriter(gbinfo, "Guest Login");
        }
    }

    private void facebookLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
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

            if(gbinfo.getBrowserIndex() == 4) // Safari
            {
                if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.facebook_LoginView_LoginButtonId, 500, 5000) == true)
                {
                    webapi.textClearById(webapi.driver, webinfo.facebook_LoginView_IdTextAreaId);
                    webapi.sendTextById(webapi.driver, webinfo.facebook_LoginView_IdTextAreaId, gbinfo.getTestId(FACEBOOK));
                    webapi.textClearById(webapi.driver, webinfo.facebook_LoginView_PWTextAreaId);
                    webapi.sendTextById(webapi.driver, webinfo.facebook_LoginView_PWTextAreaId, gbinfo.getTestPw(FACEBOOK));
                    webapi.clickElementById(webapi.driver, webinfo.facebook_LoginView_LoginButtonId);

                    if(webapi.findElementByNameWithPolling(webapi.driver, webinfo.facebook_Permission_AgreeButtonNameForSafari, 500, 5000) == true)
                    {
                        webapi.clickElementByXPath(webapi.driver, webinfo.facebook_Permission_AgreeButtonXPathForSafari);
                        webapi.switchToMainWindow();
                    }

                    else
                    {
                        webapi.switchToMainWindow();
                    }
                }

                else if(webapi.findElementByNameWithPolling(webapi.driver, webinfo.facebook_Permission_AgreeButtonNameForSafari, 500, 5000) == true)
                {
                    webapi.clickElementByXPath(webapi.driver, webinfo.facebook_Permission_AgreeButtonXPathForSafari);
                    webapi.switchToMainWindow();
                }

                else
                {
                    webapi.switchToMainWindow();
                }
            }

            else
            {
                if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.facebook_LoginView_LoginButtonId, 500, 5000) == true)
                {
                    webapi.textClearById(webapi.driver, webinfo.facebook_LoginView_IdTextAreaId);
                    webapi.sendTextById(webapi.driver, webinfo.facebook_LoginView_IdTextAreaId, gbinfo.getTestId(FACEBOOK));
                    webapi.textClearById(webapi.driver, webinfo.facebook_LoginView_PWTextAreaId);
                    webapi.sendTextById(webapi.driver, webinfo.facebook_LoginView_PWTextAreaId, gbinfo.getTestPw(FACEBOOK));
                    webapi.clickElementById(webapi.driver, webinfo.facebook_LoginView_LoginButtonId);

                    if(webapi.findElementByXPathWithPolling(webapi.driver, webinfo.facebook_Permission_AgreeButtonXPath, 500, 5000) == true)
                    {
                        webapi.clickElementByXPath(webapi.driver, webinfo.facebook_Permission_AgreeButtonXPath);
                        webapi.switchToMainWindow();
                    }

                    else
                    {
                        webapi.switchToMainWindow();
                    }
                }

                else  if(webapi.findElementByXPathWithPolling(webapi.driver, webinfo.facebook_Permission_AgreeButtonXPath, 500, 5000) == true)
                {
                    webapi.clickElementByXPath(webapi.driver, webinfo.facebook_Permission_AgreeButtonXPath);
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

    private void paycoLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        gbinfo.setTestCaseStart();

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

            if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.payco_LoginView_LoginButtonId, 500, 5000) == true)
            {
                webapi.textClearById(webapi.driver, webinfo.payco_LoginView_IdTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.payco_LoginView_IdTextAreaId, gbinfo.getTestId(PAYCO));
                webapi.textClearById(webapi.driver, webinfo.payco_LoginView_PWTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.payco_LoginView_PWTextAreaId, gbinfo.getTestPw(PAYCO));
                webapi.clickElementById(webapi.driver, webinfo.payco_LoginView_LoginButtonId);

                if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.payco_SecurityView_BirthdayTextAreaId, 500, 5000) == true)
                {
                    webapi.textClearById(webapi.driver, webinfo.payco_SecurityView_BirthdayTextAreaId);
                    webapi.sendTextById(webapi.driver, webinfo.payco_SecurityView_BirthdayTextAreaId, "19890228");
                    webapi.clickElementById(webapi.driver, webinfo.payco_SecurityView_LoginButtonId);
                    webapi.switchToMainWindow();
                }

                else
                {
                    webapi.switchToMainWindow();
                }
            }

            else if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.payco_SimpleLoginView_ConfirmButtonId, 500, 5000) == true)
            {
                webapi.clickElementById(webapi.driver, webinfo.payco_SimpleLoginView_ConfirmButtonId);
                webapi.switchToMainWindow();
            }

            else
            {
                webapi.switchToMainWindow();
            }
        }

        webapi.finishWindowHandler();
        this.concludeAuthentication(webapi, gbinfo);
        fi.loginTestResultWriter(gbinfo, "Payco Login");
    }

    private void naverLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        gbinfo.setTestCaseStart();

        if(gbinfo.getLoginStatus() == true)
        {
            System.out.println("[YYU][Naver Login] : This api must called by Not Logged-In Status");
            this.concludeAuthentication(webapi, gbinfo);
            gbinfo.setTestCaseEnd();
            fi.csvWriter("Naver Login", gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
        }

        else
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_IdPOption_NaverXPath);
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_LoginWithButtonXPath);

            webapi.readyToWindowHandler(gbinfo);
            webapi.switchToSubWindow();

            if(webapi.findElementByXPathWithPolling(webapi.driver, webinfo.naver_LoginView_LoginButtonXPath, 500, 5000) == true)
            {
                webapi.textClearById(webapi.driver, webinfo.naver_LoginView_IdTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.naver_LoginView_IdTextAreaId, gbinfo.getTestId(NAVER));
                webapi.textClearById(webapi.driver, webinfo.naver_LoginView_PWTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.naver_LoginView_PWTextAreaId, gbinfo.getTestPw(NAVER));
                webapi.clickElementByXPath(webapi.driver, webinfo.naver_LoginView_LoginButtonXPath);

                if(webapi.findElementByXPathWithPolling(webapi.driver, webinfo.naver_Permission_AgreeButtonXPath, 500, 5000) == true)
                {
                    webapi.clickElementByXPath(webapi.driver, webinfo.naver_Permission_AgreeButtonXPath);
                    webapi.switchToMainWindow();
                }

                else
                {
                    webapi.switchToMainWindow();
                }
            }

            else
            {
                webapi.switchToMainWindow();
            }
        }

        webapi.finishWindowHandler();
        this.concludeAuthentication(webapi, gbinfo);
        fi.loginTestResultWriter(gbinfo, "Naver Login");
    }

    private void googleLogin(WebDriverAPI webapi, GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        gbinfo.setTestCaseStart();

        if(gbinfo.getLoginStatus() == true)
        {
            System.out.println("[YYU][Google Login] : This api must called by Not Logged-In Status");
            this.concludeAuthentication(webapi, gbinfo);
            gbinfo.setTestCaseEnd();
            fi.csvWriter("Google Login", gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
        }

        else
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_IdPOption_GoogleXPath);
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_LoginWithButtonXPath);

            webapi.readyToWindowHandler(gbinfo);
            webapi.switchToSubWindow();

            if(webapi.findElementByXPathWithPolling(webapi.driver, webinfo.google_LoginView_IdNextButtonXPath, 500, 5000) == true)
            {
                webapi.textClearById(webapi.driver, webinfo.google_LoginView_IdTextAreaId);
                webapi.sendTextById(webapi.driver, webinfo.google_LoginView_IdTextAreaId, gbinfo.getTestId(GOOGLE));
                webapi.clickElementByXPath(webapi.driver, webinfo.google_LoginView_IdNextButtonXPath);

                if(webapi.findElementByNameWithPolling(webapi.driver, webinfo.google_LoginView_PWTextAreaName, 500, 5000) == true)
                {
                    webapi.textClearByName(webapi.driver, webinfo.google_LoginView_PWTextAreaName);
                    webapi.sendTextByName(webapi.driver, webinfo.google_LoginView_PWTextAreaName, gbinfo.getTestPw(GOOGLE));
                    webapi.clickElementByXPath(webapi.driver, webinfo.google_LoginView_PWNextButtonXPath);
                    Thread.sleep(2000);
                    webapi.switchToMainWindow();
                }

                else
                {
                    webapi.switchToMainWindow();
                }
            }

            else if(webapi.findElementByIdWithPolling(webapi.driver, webinfo.google_IDSelectView_OtherIdButtonId, 500, 5000) == true)
            {
                webapi.clickElementByXPath(webapi.driver, webinfo.google_IDSelectView_RecentIdButtonXPath);
                webapi.switchToMainWindow();
            }

            else
            {
                webapi.switchToMainWindow();
            }
        }

        webapi.finishWindowHandler();
        this.concludeAuthentication(webapi, gbinfo);
        fi.loginTestResultWriter(gbinfo, "Google Login");
    }

    private void openLoginMenu(WebDriverAPI webapi) throws InterruptedException
    {
        if(this.isAPIsPanelOpened(webapi) == false)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.panel_APIsButtonXPath);
        }

        Thread.sleep(500); // UI Delay

        if(this.isAuthMenuOpened(webapi) == false)
        {
            webapi.clickElementByXPath(webapi.driver, webinfo.auth_AuthMenuButtonXpath);
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
            gbinfo.setUserId("");
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
}