package per.yyu.gbjstest.automation;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

public class WebDriverFunction {
    WebDriver driver;
    Alert alert;

    private String mainWindow;
    private String subWindow;

    public WebDriverFunction() {
        this.mainWindow = "";
        this.subWindow = "";
    }



    // WebDriver

    /**
     * @author YongUn Yi <br/>
     * 사용자가 선택한 Browser Type 에 따라 <br/>
     * 알맞은 WebDriver 를 생성하고 Test URL 로 이동함 <br/>
     */
    public void initWebDriver(GamebaseInformation gbInfo) throws InterruptedException, MalformedURLException {
        if(gbInfo.getBrowserTypeNo() < 1) {
            this.initBrowserForPC(gbInfo);
            this.navigateToTestURLForPC(gbInfo);
        }

        else {
            this.initBrowserForMobile(gbInfo);
            this.navigateToTestURLForMobile(gbInfo);
        }
    }

    private void initBrowserForPC(GamebaseInformation gbInfo) {
        switch(gbInfo.getBrowserTypeNo()) {
            case 1: {
                System.out.println("[WebDriver Function][PC Browser Selector] : Chrome");
                System.setProperty("webdriver.chrome.driver", gbInfo.getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            }

            case 2: {
                System.out.println("[WebDriver Function][PC Browser Selector] : Firefox");
                System.setProperty("webdriver.gecko.driver", gbInfo.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            }

            case 3: {
                System.setProperty("webdriver.ie.driver", gbInfo.getIEDrivetPath());
                driver = new InternetExplorerDriver();

                Capabilities cap = ((InternetExplorerDriver)driver).getCapabilities();
                gbInfo.setIEVersion(cap.getVersion());
                System.out.println("[WebDriver Function][PC Browser Selector] : Internet Explorer " + gbInfo.getIEVersion());
                break;
            }

            case 4: {
                System.out.println("[WebDriver Function][PC Browser Selector] : Safari");
                driver = new SafariDriver();
                break;
            }
        }
    }

    private void initBrowserForMobile(GamebaseInformation gbInfo) throws MalformedURLException {
        switch(gbInfo.getBrowserTypeNo()) {
            case 11: {
                System.out.println("[WebDriver Function][Mobile Browser Selector] : Chrome");

                URL url = new URL(gbInfo.getAppiumURL());
                DesiredCapabilities dc = DesiredCapabilities.android();

                dc.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
                dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Test Device");
                driver = new RemoteWebDriver(url, dc);
            }
        }
    }

    private void navigateToTestURLForPC(GamebaseInformation gbInfo) throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(gbInfo.getTestURL());

        Thread.sleep(3000); // For Loading (Firefox 는 wait 함수를 사용해도 정상적으로 웹 페이지 로딩을 기다리지 못함)
    }

    private void navigateToTestURLForMobile(GamebaseInformation gbInfo) throws InterruptedException {
        driver.manage().deleteAllCookies();
        driver.get(gbInfo.getTestURL());

        Thread.sleep(3000); // For Loading (PC Firefox 로 인해 혹시 모르는 예외사항을 대비하기 위함)
    }

    public void closeWebDriver() {
        driver.close();
        driver.quit();
    }



    // Browser
    public void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(3000); // For Loading
    }

    /**
     * @author YongUn Yi <br/>
     * Browser 의 Popup 을 핸들링 하기 위해 준비
     */
    public void readyToPopupHandle(GamebaseInformation gbInfo) {
        if(gbInfo.getBrowserTypeNo() == 4) {
            this.resetBrowserPopupForSafari();
            this.collectBrowserPopupForSafari();
        }

        else {
            this.collectBrowserPopup();
        }
    }

    /**
     * @author YongUn Yi <br/>
     * Browser 에서 Popup 이 생성되었을 때 <br/>
     * Main page 와 Popup Page 를 <br/>
     * 각각 mainWindow / subWindow 에 저장하여 <br/>
     * 원하는 때에 switch 할 수 있도록 준비 <br/>
     */
    private void collectBrowserPopup() {
        this.mainWindow = driver.getWindowHandle();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        while(iterator.hasNext()) {
            this.subWindow = iterator.next();
        }
    }

    /**
     * @author YongUn Yi <br/>
     * Safari Browser 에서는 Popup 수집이 정상적으로 이루어지지 않아 <br/>
     * 0.5초 주기로 10번 동안 수집을 시도하여 <br/>
     * 정상적으로 수집이 되었다고 판단되었을 때 종료 <br/>
     */
    private void collectBrowserPopupForSafari() {
        int pollingCount = 0;

        this.mainWindow = driver.getWindowHandle();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        this.subWindow = iterator.next();

        while(pollingCount < 10) {
            try {
                if(this.subWindow == null) {
                    pollingCount++;
                    this.subWindow = iterator.next();
                    System.out.println("[WebDriver Function][Popup Collect For Safari] : Polling " + pollingCount);
                    Thread.sleep(500);
                }

                else if(this.subWindow == "") {
                    pollingCount++;
                    this.subWindow = iterator.next();
                    System.out.println("[WebDriver Function][Popup Collect For Safari] : Polling " + pollingCount);
                    Thread.sleep(500);
                }

                else {
                    break;
                }
            }

            catch(Exception e) {
                this.subWindow = "";
                break;
            }
        }
    }

    private void resetBrowserPopupForSafari() {
        this.mainWindow = "";
        this.subWindow = "";
    }

    public void switchToMainWindow() {
        driver.switchTo().window(this.mainWindow);
    }

    public void switchToSubWindow() {
        try {
            driver.switchTo().window(this.subWindow);
        }

        catch(Exception e) {
            this.switchToMainWindow();
        }
    }



    // Alert
    public void acceptToAlert() {
        alert.accept();
    }

    public void dismissToAlert() {
        alert.dismiss();
    }

    public String getTextByAlert() {
        return alert.getText();
    }

    /**
     * Alert 이 다소 느리게 노출될 우려가 있으므로 <br/>
     * period 주기로 timeout 동안 Alert 을 찾을 후 이동한다. <br/>
     * @param timeout <br/>
     * Alert 을 탐색 최대 시간 <br/>
     * @param period <br/>
     * Alert 탐색 주기 <br/>
     */
    public boolean switchToAlert(int timeout, int period) throws InterruptedException {
        int pollingCount = 0;

        while(pollingCount < timeout/period) {
            try {
                alert = driver.switchTo().alert();
                return true;
            }

            catch(Exception e) {
                pollingCount++;
                System.out.println("[WebDriver Function][Switch to Alert] : Polling " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[WebDriver Function][Switch to Alert] : Alert is not exist");
        return false;
    }



    // Text Element (without polling)
}