package per.yyu.gbjstest.automation;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
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
    Actions actions;

    private String mainWindow;
    private String subWindow;

    public WebDriverFunction() {
        this.mainWindow = "";
        this.subWindow = "";
    }



    // WebDriver

    /**
     * 사용자가 선택한 Browser Type 에 따라 <br/>
     * 알맞은 WebDriver 를 생성하고 Test URL 로 이동함 <br/>
     */
    public void initWebDriver(GamebaseInformation gbInfo) throws InterruptedException, MalformedURLException {
        if(gbInfo.getDeviceTypeNo() < 1) {
            this.selectBrowserForPC(gbInfo);
            this.navigateToTestURLForPC(gbInfo);
        }

        else {
            this.selectBrowserForMobile(gbInfo);
            this.navigateToTestURLForMobile(gbInfo);
        }
    }

    private void selectBrowserForPC(GamebaseInformation gbInfo) {
        switch(gbInfo.getBrowserTypeNo()) {
            case 1: {
                System.out.println("[WebDriver Function][Select PC Browser] : Chrome");
                System.setProperty("webdriver.chrome.driver", gbInfo.getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            }

            case 2: {
                System.out.println("[WebDriver Function][Select PC Browser] : Firefox");
                System.setProperty("webdriver.gecko.driver", gbInfo.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            }

            case 3: {
                System.setProperty("webdriver.ie.driver", gbInfo.getIEDrivetPath());
                driver = new InternetExplorerDriver();

                Capabilities cap = ((InternetExplorerDriver)driver).getCapabilities();
                gbInfo.setIEVersion(cap.getVersion());
                System.out.println("[WebDriver Function][Select PC Browser] : Internet Explorer " + gbInfo.getIEVersion());
                break;
            }

            case 4: {
                System.out.println("[WebDriver Function][Select PC Browser] : Safari");
                driver = new SafariDriver();
                break;
            }
        }
    }

    private void selectBrowserForMobile(GamebaseInformation gbInfo) throws MalformedURLException {
        switch(gbInfo.getBrowserTypeNo()) {
            case 11: {
                System.out.println("[WebDriver Function][Select Mobile Browser] : Chrome");

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
    public void clearTextById(String elementId) {
        driver.findElement(By.id(elementId)).clear();
    }

    public void clearTextByName(String elementName) {
        driver.findElement(By.name(elementName)).clear();
    }

    public void clearTextByXpath(String elementXpath) {
        driver.findElement(By.xpath(elementXpath)).clear();
    }

    /**
     * 해당 엘리먼트에 text 를 작성함 <br/>
     * 텍스트 입력란에 가능 <br/>
     */
    public void sendTextById(String elementId, String text) {
        driver.findElement(By.id(elementId)).sendKeys(text);
    }

    /**
     * 해당 엘리먼트에 text 를 작성함 <br/>
     * 텍스트 입력란에 가능 <br/>
     */
    public void sendTextByName(String elementName, String text) {
        driver.findElement(By.name(elementName)).sendKeys(text);
    }

    /**
     * 해당 엘리먼트에 text 를 작성함 <br/>
     * 텍스트 입력란에 가능 <br/>
     */
    public void sendTextByXpath(String elementXpath, String text) {
        driver.findElement(By.xpath(elementXpath)).sendKeys(text);
    }

    public String getTextById(String elementId) {
        return driver.findElement(By.id(elementId)).getText();
    }

    public String getTextByName(String elementName) {
        return driver.findElement(By.name(elementName)).getText();
    }

    public String getTextByXpath(String elementXpath) {
        return driver.findElement(By.xpath(elementXpath)).getText();
    }

    public String getTextByClassName(String elementClassName) {
        return driver.findElement(By.className(elementClassName)).getText();
    }

    /**
     * Text Area 는 GetText 로 값을 바로 가져올 수 없기 때문에 <br/>
     * Get Attribute 로 가져와야 한다. <br/>
     */
    public boolean findTextFromTextAreaById(String elementId, String text) {
        return driver.findElement(By.id(elementId)).getAttribute("value").contains(text);
    }



    // Text Element (with polling)
    public String getTextById(String elementId, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).isDisplayed() == true)
                {
                    return driver.findElement(By.id(elementId)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public String getTextByName(String elementName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).isDisplayed() == true)
                {
                    return driver.findElement(By.name(elementName)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public String getTextByXpath(String elementXpath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).isDisplayed() == true)
                {
                    return driver.findElement(By.xpath(elementXpath)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public String getTextByClassName(String elementClassName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.className(elementClassName)).isDisplayed() == true)
                {
                    return driver.findElement(By.className(elementClassName)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public boolean detectTextChangeById(String elementId, String originalText, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(!driver.findElement(By.id(elementId)).getText().equals(originalText))
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Text Change Detector] : Text is not change");
        return false;
    }

    public boolean detextTextChangeByName(String elementName, String targetText, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).getText().equals(targetText) == false)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Text Change Detector] : Text is not change");
        return false;
    }

    public boolean detextTextChangeByXpath(String elementXpath, String targetText, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).getText().equals(targetText) == false)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Text Change Detector] : Text is not change");
        return false;
    }

    public boolean findTextById(String elementId, String text, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).getText().contains(text) == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Text] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Text] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Text] : Can't find " + text);
        return false;
    }

    public boolean findTextByName(String elementName, String text, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).getText().contains(text) == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Text] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Text] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Text] : Can't find " + text);
        return false;
    }

    public boolean findTextByXpath(String elementXpath, String text, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).getText().contains(text) == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Text] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Text] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Text] : Can't find " + text);
        return false;
    }


    // Find Element
    public boolean findElementById(String elementId)
    {
        if(driver.findElement(By.id(elementId)).isDisplayed() == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementByName(String elementName)
    {
        if(driver.findElement(By.name(elementName)).isDisplayed() == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementByXpath(String elementXpath)
    {
        if(driver.findElement(By.xpath(elementXpath)).isDisplayed() == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementByClassName(String elementClassName)
    {
        if(driver.findElement(By.className(elementClassName)).isDisplayed() == true)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementById(String elementId, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).isDisplayed() == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findElementByName(String elementName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).isDisplayed() == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findElementByXpath(String elementXpath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).isDisplayed() == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findElementByClassName(String elementClassName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.className(elementClassName)).isDisplayed() == true)
                {
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : \" + pollingCount");
                    Thread.sleep(period);
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findCheckedBoxById(String elementId, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).isSelected() == true)
                {
                    return true;
                }

                else if(driver.findElement(By.id(elementId)).isSelected() == false)
                {
                    return false;
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Checked Box] : Can't find");
        return false;
    }

    public boolean findCheckedBoxByName(String elementName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).isSelected() == true)
                {
                    return true;
                }

                else if(driver.findElement(By.name(elementName)).isSelected() == false)
                {
                    return false;
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Checked Box] : Can't find");
        return false;
    }

    public boolean findCheckedBoxByXpath(String elementXpath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).isSelected() == true)
                {
                    return true;
                }

                else if(driver.findElement(By.xpath(elementXpath)).isSelected() == false)
                {
                    return false;
                }
            }

            catch(Exception e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Checked Box] : Can't find");
        return false;
    }


    // Action Element
    public void clickElementById(String elementId) throws InterruptedException
    {
        driver.findElement(By.id(elementId)).click();
        Thread.sleep(500);
    }

    public void clickElementByName(String elementName) throws InterruptedException
    {
        driver.findElement(By.name(elementName)).click();
        Thread.sleep(500);
    }

    public void clickElementByXpath(String elementXpath) throws InterruptedException
    {
        driver.findElement(By.xpath(elementXpath)).click();
        Thread.sleep(500);
    }

    public void clickInvisibleElementById(String elementId) {
        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.id(elementId))).click().build().perform();
    }
}