package per.yyu.gbjstest.automation;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.*;
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

public class WebDriverFunction
{
    WebDriver driver;
    Alert alert;

    private String mainWindow;
    private String subWindow;

    public WebDriverFunction()
    {
        this.mainWindow = "";
        this.subWindow = "";
    }


    // Web Driver Module
    public void webDriverInitialize(GamebaseInformation gbInfo) throws MalformedURLException, InterruptedException
    {
        if(((gbInfo.getBrowserIndex() / 10) < 1) == true)
        {
            this.pc_BrowserSelector(gbInfo);
            this.pc_MoveToTestURL(gbInfo);
        }

        else
        {
            this.mobile_BrowserSelector(gbInfo);
            this.mobile_MoveToTestURL(gbInfo);
        }
    }

    private void pc_BrowserSelector(GamebaseInformation gbInfo)
    {
        switch(gbInfo.getBrowserIndex())
        {
            case 1:
            {
                System.out.println("[WebDriver Function][Browser Selector] : PC Chrome");
                System.setProperty("webdriver.chrome.driver", gbInfo.getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            }

            case 2:
            {
                System.out.println("[WebDriver Function][Browser Selector] : PC Firefox");
                System.setProperty("webdriver.gecko.driver", gbInfo.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            }

            case 3:
            {
                System.out.println("[WebDriver Function][Browser Selector] : PC Internet Explorer");
                System.setProperty("webdriver.ie.driver", gbInfo.getIeDriverPath());
                driver = new InternetExplorerDriver();
                break;
            }

            case 4:
            {
                System.out.println("[WebDriver Function][Browser Selector] : PC Safari");
                driver = new SafariDriver();
                break;
            }
        }
    }

    private void mobile_BrowserSelector(GamebaseInformation gbInfo) throws MalformedURLException
    {
        switch(gbInfo.getBrowserIndex())
        {
            case 11:
            {
                System.out.println("[WebDriver Function][Browser Selector] : Android Chrome");

                URL url = new URL(gbInfo.getAppiumServerURL());
                DesiredCapabilities dc = DesiredCapabilities.android();

                dc.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
                dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
                dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Test Device");
                driver = new RemoteWebDriver(url, dc);
            }
        }
    }

    private void pc_MoveToTestURL(GamebaseInformation gbInfo) throws InterruptedException
    {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(gbInfo.getTestURL());

        Thread.sleep(3000);
    }

    private void mobile_MoveToTestURL(GamebaseInformation gbInfo) throws InterruptedException
    {
        driver.get(gbInfo.getTestURL());

        Thread.sleep(3000);
    }

    public void webDriverCloser()
    {
        driver.close();
        driver.quit();
    }


    // Browser Window
    public void refreshPage() throws InterruptedException
    {
        driver.navigate().refresh();

        Thread.sleep(3000);
    }

    public void readyToPopupWindowHandler(GamebaseInformation gbInfo) throws InterruptedException
    {
        if(gbInfo.getBrowserIndex() == 4)
        {
            this.mainWindow = "";
            this.subWindow = "";
            this.browserPopupWindowCollectorForSafari();
        }

        else
        {
            this.browserPopupWindowCollector();
        }
    }

    private void browserPopupWindowCollector()
    {
        this.mainWindow = driver.getWindowHandle();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        while(iterator.hasNext())
        {
            this.subWindow = iterator.next();
        }
    }

    private void browserPopupWindowCollectorForSafari() throws InterruptedException
    {
        int pollingCount = 0;

        this.mainWindow = driver.getWindowHandle();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        this.subWindow = iterator.next();

        while(pollingCount < 10)
        {
            try
            {
                if(this.subWindow == null)
                {
                    pollingCount++;
                    this.subWindow = iterator.next();
                    System.out.println("[Web Driver Function][Popup Collector For Safari] : Polling Count : " + pollingCount);
                    Thread.sleep(500);
                }

                else if(this.subWindow == "")
                {
                    pollingCount++;
                    this.subWindow = iterator.next();
                    System.out.println("[Web Driver Function][Popup Collector For Safari] : Polling Count : " + pollingCount);
                    Thread.sleep(500);
                }

                else if(this.subWindow == this.mainWindow)
                {
                    pollingCount++;
                    this.subWindow = iterator.next();
                    System.out.println("[Web Driver Function][Popup Collector For Safari] : Polling Count : " + pollingCount);
                    Thread.sleep(500);
                }

                else
                {
                    break;
                }
            }

            catch(NoSuchWindowException e)
            {
                this.subWindow = "";
                break;
            }
        }
    }

    public void resetBrowserPopupWindowCollectorForSafari()
    {
        this.subWindow = "";
        this.mainWindow = "";
    }

    public void switchToMainWindow()
    {
        driver.switchTo().window(this.mainWindow);
    }

    public void switchToSubWindow()
    {
        try
        {
            driver.switchTo().window(this.subWindow);
        }

        catch(NoSuchWindowException e)
        {
            this.switchToMainWindow();
        }
    }

    public String getSubWindow()
    {
        return this.subWindow = driver.getWindowHandle();
    }


    // Alert
    public void acceptToAlert()
    {
        alert.accept();
    }

    public void dismissToAlert()
    {
        alert.dismiss();
    }

    public String getTextByAlert()
    {
        System.out.println("[Web Driver Function][Get Text By Alert] : " + alert.getText());
        return alert.getText();
    }

    public boolean switchToAlert(int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                alert = driver.switchTo().alert();
                return true;
            }

            catch(NoAlertPresentException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Switch To Alert] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Switch To Alert] : Alert is not exist");
        return false;
    }


    // Text Element (without Polling)
    public void clearTextById(WebDriver driver, String elementId)
    {
        driver.findElement(By.id(elementId)).clear();
    }

    public void clearTextByName(WebDriver driver, String elementName)
    {
        driver.findElement(By.name(elementName)).clear();
    }

    public void clearTextByXpath(WebDriver driver, String elementXpath)
    {
        driver.findElement(By.xpath(elementXpath)).clear();
    }

    public void sendTextById(WebDriver driver, String elementId, String text)
    {
        driver.findElement(By.id(elementId)).sendKeys(text);
    }

    public void sendTextByName(WebDriver driver, String elementName, String text)
    {
        driver.findElement(By.name(elementName)).sendKeys(text);
    }

    public void sendTextByXpath(WebDriver driver, String elementXpath, String text)
    {
        driver.findElement(By.xpath(elementXpath)).sendKeys(text);
    }


    // Text Element (with Polling)
    public String getTextById(WebDriver driver, String elementId, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).isDisplayed() == true)
                {
                    System.out.println("[Web Driver Function][Get Text By Element] : " + driver.findElement(By.id(elementId)).getText());
                    return driver.findElement(By.id(elementId)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public String getTextByName(WebDriver driver, String elementName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).isDisplayed() == true)
                {
                    System.out.println("[Web Driver Function][Get Text By Element] : " + driver.findElement(By.name(elementName)).getText());
                    return driver.findElement(By.name(elementName)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public String getTextByXpath(WebDriver driver, String elementXpath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).isDisplayed() == true)
                {
                    System.out.println("[Web Driver Function][Get Text By Element] : " + driver.findElement(By.xpath(elementXpath)).getText());
                    return driver.findElement(By.xpath(elementXpath)).getText();
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Get Text By Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Get Text By Element] : Element is not exist");
        return "";
    }

    public boolean textChangeDetectorById(WebDriver driver, String elementId, String targetText, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).getText().equals(targetText) == false)
                {
                    System.out.println("[Web Driver Function][Text Change Detector] : " + driver.findElement(By.id(elementId)).getText());
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Text Change Detector] : Text is not change");
        return false;
    }

    public boolean textChangeDetectorByName(WebDriver driver, String elementName, String targetText, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).getText().equals(targetText) == false)
                {
                    System.out.println("[Web Driver Function][Text Change Detector] : " + driver.findElement(By.name(elementName)).getText());
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Text Change Detector] : Text is not change");
        return false;
    }

    public boolean textChangeDetectorByXpath(WebDriver driver, String elementXpath, String targetText, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).getText().equals(targetText) == false)
                {
                    System.out.println("[Web Driver Function][Text Change Detector] : " + driver.findElement(By.xpath(elementXpath)).getText());
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Text Change Detector] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Text Change Detector] : Text is not change");
        return false;
    }

    public boolean findLetterById(WebDriver driver, String elementId, String letter, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).getText().contains(letter) == true)
                {
                    System.out.println("[Web Driver Function][Find Letter] : " + driver.findElement(By.id(elementId)).getText());
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Letter] : Can't find " + letter);
        return false;
    }

    public boolean findLetterByName(WebDriver driver, String elementName, String letter, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).getText().contains(letter) == true)
                {
                    System.out.println("[Web Driver Function][Find Letter] : " + driver.findElement(By.name(elementName)).getText());
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Letter] : Can't find " + letter);
        return false;
    }

    public boolean findLetterByXpath(WebDriver driver, String elementXpath, String letter, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).getText().contains(letter) == true)
                {
                    System.out.println("[Web Driver Function][Find Letter] : " + driver.findElement(By.xpath(elementXpath)).getText());
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Letter] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Letter] : Can't find " + letter);
        return false;
    }


    // Find Element
    public boolean findElementById(WebDriver driver, String elementId)
    {
        if(driver.findElement(By.id(elementId)).isDisplayed() == true)
        {
            System.out.println("[Web Driver Function][Find Element] : Success");
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementByName(WebDriver driver, String elementName)
    {
        if(driver.findElement(By.name(elementName)).isDisplayed() == true)
        {
            System.out.println("[Web Driver Function][Find Element] : Success");
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementByXpath(WebDriver driver, String elementXpath)
    {
        if(driver.findElement(By.xpath(elementXpath)).isDisplayed() == true)
        {
            System.out.println("[Web Driver Function][Find Element] : Success");
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean findElementById(WebDriver driver, String elementId, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).isDisplayed() == true)
                {
                    System.out.println("[Web Driver Function][Find Element] : Success");
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findElementByName(WebDriver driver, String elementName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).isDisplayed() == true)
                {
                    System.out.println("[Web Driver Function][Find Element] : Success");
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findElementByXpath(WebDriver driver, String elementXpath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).isDisplayed() == true)
                {
                    System.out.println("[Web Driver Function][Find Element] : Success");
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Element] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Element] : Can't find");
        return false;
    }

    public boolean findCheckedBoxById(WebDriver driver, String elementId, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(elementId)).isSelected() == true)
                {
                    System.out.println("[Web Driver Function][Find Checked Box] : Checked");
                    return true;
                }

                else if(driver.findElement(By.id(elementId)).isSelected() == false)
                {
                    System.out.println("[Web Driver Function][Find Checked Box] : Unchecked");
                    return false;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Checked Box] : Can't find");
        return false;
    }

    public boolean findCheckedBoxByName(WebDriver driver, String elementName, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(elementName)).isSelected() == true)
                {
                    System.out.println("[Web Driver Function][Find Checked Box] : Checked");
                    return true;
                }

                else if(driver.findElement(By.name(elementName)).isSelected() == false)
                {
                    System.out.println("[Web Driver Function][Find Checked Box] : Unchecked");
                    return false;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[Web Driver Function][Find Checked Box] : Can't find");
        return false;
    }

    public boolean findCheckedBoxByXpath(WebDriver driver, String elementXpath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(elementXpath)).isSelected() == true)
                {
                    System.out.println("[Web Driver Function][Find Checked Box] : Checked");
                    return true;
                }

                else if(driver.findElement(By.xpath(elementXpath)).isSelected() == false)
                {
                    System.out.println("[Web Driver Function][Find Checked Box] : Unchecked");
                    return false;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[Web Driver Function][Find Checked Box] : Polling Count : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
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
    public void clickElementById(WebDriver driver, String elementId) throws InterruptedException
    {
        driver.findElement(By.id(elementId)).click();
        Thread.sleep(500);
    }

    public void clickElementByName(WebDriver driver, String elementName) throws InterruptedException
    {
        driver.findElement(By.name(elementName)).click();
        Thread.sleep(500);
    }

    public void clickElementByXpath(WebDriver driver, String elementXpath) throws InterruptedException
    {
        driver.findElement(By.xpath(elementXpath)).click();
        Thread.sleep(500);
    }
}