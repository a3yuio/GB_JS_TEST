package per.yyu.gbjstest.automation;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class WebDriverAPI
{
    WebDriver driver;
    Alert alert;
    WebElementInformation webinfo = new WebElementInformation();

    private String mainWindow;
    private String subWindow;

    public WebDriverAPI()
    {
        this.mainWindow = "";
        this.subWindow = "";
    }

    public void browserRun(GamebaseInformation gbinfo, FileIO fi) throws InterruptedException, IOException
    {
        fi.testUrlSetter(gbinfo);

        switch(gbinfo.getBrowserIndex())
        {
            case 1:
            {
                System.out.println("[YYU][Browser Selector] : Chrome !");
                System.setProperty("webdriver.chrome.driver", gbinfo.getChromeDriverPath());
                driver = new ChromeDriver();
                break;
            }

            case 2:
            {
                System.out.println("[YYU][Browser Selector] : Firefox !");
                System.setProperty("webdriver.gecko.driver", gbinfo.getGeckoDriverPath());
                driver = new FirefoxDriver();
                break;
            }
            
            case 3:
            {
            	System.out.println("[YYU][Browser Selector] : IE !");
            	System.setProperty("webdriver.ie.driver", gbinfo.getIeDriverPath());
            	driver = new InternetExplorerDriver();
            	break;
            }

            case 4:
            {
                System.out.println("[YYU][Browser Selector] : Safari !");
                driver = new SafariDriver();
                break;
            }
        }

        this.browserSetter(gbinfo);
    }

    private void browserSetter(GamebaseInformation gbinfo) throws InterruptedException
    {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(gbinfo.getTestURL());

        Thread.sleep(3000);
    }

    public void browserCloser()
    {
        driver.close();
        driver.quit();
    }

    public void refreshPage() throws InterruptedException
    {
        driver.navigate().refresh();
        Thread.sleep(2000);
    }

    public void readyToWindowHandler(GamebaseInformation gbinfo) throws InterruptedException
    {
        if(gbinfo.getBrowserIndex() == 4)
        {
            this.finishWindowHandler();
            this.browserWindowCollectorForSafari();
        }

        else
        {
            this.browserWindowCollector();
        }
    }

    private void browserWindowCollector()
    {
        this.mainWindow = driver.getWindowHandle();

        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();

        while(iterator.hasNext())
        {
            this.subWindow = iterator.next();
        }
    }

    private void browserWindowCollectorForSafari() throws InterruptedException
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
                if(this.subWindow.equals(""))
                {
                    Thread.sleep(500);
                    this.subWindow = iterator.next();
                    pollingCount++;
                }

                else if(this.subWindow.equals(this.mainWindow))
                {
                    Thread.sleep(500);
                    this.subWindow = iterator.next();
                    pollingCount++;
                }

                else
                {
                    break;
                }
            }

            catch (NoSuchElementException e)
            {
                this.subWindow = "";
                break;
            }
        }
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

        catch (NoSuchWindowException e)
        {
            this.switchToMainWindow();
        }
    }

    public void finishWindowHandler()
    {
        this.mainWindow = "";
        this.subWindow = "";
    }

    public void alertDismiss()
    {
        this.alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public void alertAccept()
    {
        this.alert = driver.switchTo().alert();
        alert.accept();
    }

    public void textClearByXPath(WebDriver driver, String xPath)
    {
        driver.findElement(By.xpath(xPath)).clear();
    }

    public void textClearById(WebDriver driver, String id)
    {
        driver.findElement(By.id(id)).clear();
    }

    public void textClearByName(WebDriver driver, String name)
    {
        driver.findElement(By.name(name)).clear();
    }

    public void sendTextByXPath(WebDriver driver, String xPath, String text)
    {
        driver.findElement(By.xpath(xPath)).sendKeys(text);
    }

    public void sendTextById(WebDriver driver, String id, String text)
    {
        driver.findElement(By.id(id)).sendKeys(text);
    }

    public void sendTextByName(WebDriver driver, String name, String text)
    {
        driver.findElement(By.name(name)).sendKeys(text);
    }

    public void clickElementByXPath(WebDriver driver, String xPath)
    {
        try
        {
            driver.findElement(By.xpath(xPath)).click();
            Thread.sleep(1000);
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void clickElementById(WebDriver driver, String id)
    {
        try
        {
            driver.findElement(By.id(id)).click();
            Thread.sleep(1000);
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void clickElementByName(WebDriver driver, String name)
    {
        try
        {
            driver.findElement(By.name(name)).click();
            Thread.sleep(1000);
        }

        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public boolean findElementByXPath(WebDriver driver, String xPath)
    {
        return driver.findElement(By.xpath(xPath)).isDisplayed();
    }

    public boolean findElementById(WebDriver driver, String id)
    {
        return driver.findElement(By.id(id)).isDisplayed();
    }

    public boolean findElementByName(WebDriver driver, String name)
    {
        return driver.findElement(By.name(name)).isDisplayed();
    }

    public boolean findElementByXPathWithPolling(WebDriver driver, String xPath, int period, int timeout) throws InterruptedException
    {
        System.out.println("[YYU][Find Element] : Find the " + xPath + " Start");
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(xPath)).isDisplayed() == true)
                {
                    System.out.println("[YYU][Find Element] : Find " + xPath + " success");
                    return true;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Element] : Timeout !!!");
        return false;
    }

    public boolean findElementByIdWithPolling(WebDriver driver, String id, int period, int timeout) throws InterruptedException
    {
        System.out.println("[YYU][Find Element] : Find the " + id + " Start");
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(id)).isDisplayed() == true)
                {
                    System.out.println("[YYU][Find Element] : Find " + id + " success");
                    return true;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Element] : Timeout !!!");
        return false;
    }

    public boolean findElementByNameWithPolling(WebDriver driver, String name, int period, int timeout) throws InterruptedException
    {
        System.out.println("[YYU][Find Element] : Find the " + name + " Start");
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(name)).isDisplayed() == true)
                {
                    System.out.println("[YYU][Find Element] : Find " + name + " success");
                    return true;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Element][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Element] : Timeout !!!!!");
        return false;
    }

    public boolean findCheckBoxByXPath(WebDriver driver, String xPath)
    {
        return driver.findElement(By.xpath(xPath)).isSelected();
    }

    public boolean findCheckBoxById(WebDriver driver, String id)
    {
        return driver.findElement(By.id(id)).isSelected();
    }

    public boolean findCheckBoxByName(WebDriver driver, String name)
    {
        return driver.findElement(By.name(name)).isSelected();
    }

    public boolean findCheckBoxByXPathWithPolling(WebDriver driver, String xPath, int period, int timeout) throws InterruptedException
    {
        System.out.println("[YYU][Find CheckBox] : Find the " + xPath + " Start");
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(xPath)).isSelected() == true)
                {
                    System.out.println("[YYU][Find CheckBox] : Find " + xPath + " success");
                    return true;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find CheckBox] : Timeout !!!");
        return false;
    }

    public boolean findCheckBoxByIdWithPolling(WebDriver driver, String id, int period, int timeout) throws InterruptedException
    {
        System.out.println("[YYU][Find CheckBox] : Find the " + id + " Start");
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(id)).isSelected() == true)
                {
                    System.out.println("[YYU][Find CheckBox] : Find " + id + " success");
                    return true;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find CheckBox] : Timeout !!!");
        return false;
    }

    public boolean findCheckBoxByNameWithPolling(WebDriver driver, String name, int period, int timeout) throws InterruptedException
    {
        System.out.println("[YYU][Find CheckBox] : Find the " + name + " Start");
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(name)).isSelected() == true)
                {
                    System.out.println("[YYU][Find CheckBox] : Find " + name + " success");
                    return true;
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find CheckBox][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find CheckBox] : Timeout !!!!!");
        return false;
    }


    public String getTextByXPath(WebDriver driver, String xPath)
    {
        return driver.findElement(By.xpath(xPath)).getText();
    }

    public String getTextById(WebDriver driver, String id)
    {
       return driver.findElement(By.id(id)).getText();
    }

    public String getTextByName(WebDriver driver, String name)
    {
        return driver.findElement(By.name(name)).getText();
    }

    public String getTextByXPathWithPolling(WebDriver driver, String xPath, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;
        System.out.println("[YYU][Find Text] : Find the " + xPath + " Start");

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(xPath)).isDisplayed() == true)
                {
                    System.out.println("[YYU][Find Text] : Find " + xPath + " success");
                    return driver.findElement(By.xpath(xPath)).getText();
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Text] : Timeout !!!!!");
        return "";
    }

    public String getTextByIdWithPolling(WebDriver driver, String id, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;
        System.out.println("[YYU][Find Text] : Find the " + id + " Start");

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(id)).isDisplayed() == true)
                {
                    System.out.println("[YYU][Find Text] : Find " + id + " success");
                    return driver.findElement(By.xpath(id)).getText();
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Text] : Timeout !!!!!");
        return "";
    }

    public String getTextByNameWithPolling(WebDriver driver, String name, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;
        System.out.println("[YYU][Find Text] : Find the " + name + " Start");

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(name)).isDisplayed() == true)
                {
                    System.out.println("[YYU][Find Text] : Find " + name + " success");
                    return driver.findElement(By.xpath(name)).getText();
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Text] : Timeout !!!!!");
        return "";
    }
    
    public boolean detectorOfTextChangeByXPathWithPolling(WebDriver driver, String xPath, String target, int period, int timeout) throws InterruptedException
    {
    	int pollingCount = 0;
    	System.out.println("[YYU][Change Detector] : Detecting " + xPath + " Start");
    	
    	while(pollingCount < timeout/period)
    	{
    		try
    		{
    			if(driver.findElement(By.xpath(xPath)).getText().equals(target) == false)
    			{
    				System.out.println("[YYU][Change Detector] : Detecting " + xPath + " success");
    				return true;
    			}
    			
    			else
    			{
    				pollingCount++;
    				System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                    Thread.sleep(period);
    			}
    		}
    		
    		catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
    	}
    	
    	System.out.println("[YYU][Change Detector] : Timeout !!!!!");
        return false;
    }
    
    public boolean detectorOfTextChangeByIdWithPolling(WebDriver driver, String id, String target, int period, int timeout) throws InterruptedException
    {
    	int pollingCount = 0;
    	System.out.println("[YYU][Change Detector] : Detecting " + id + " Start");
    	
    	while(pollingCount < timeout/period)
    	{
    		try
    		{
    			if(driver.findElement(By.id(id)).getText().equals(target) == false)
    			{
    				System.out.println("[YYU][Change Detector] : Detecting " + id + " success");
    				return true;
    			}
    			
    			else
    			{
    				pollingCount++;
    				System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                    Thread.sleep(period);
    			}
    		}
    		
    		catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
    	}
    	
    	System.out.println("[YYU][Change Detector] : Timeout !!!!!");
        return false;
    }
    
    public boolean detectorOfTextChangeByNameWithPolling(WebDriver driver, String name, String target, int period, int timeout) throws InterruptedException
    {
    	int pollingCount = 0;
    	System.out.println("[YYU][Change Detector] : Detecting " + name + " Start");
    	
    	while(pollingCount < timeout/period)
    	{
    		try
    		{
    			if(driver.findElement(By.name(name)).getText().equals(target) == false)
    			{
    				System.out.println("[YYU][Change Detector] : Detecting " + name + " success");
    				return true;
    			}
    			
    			else
    			{
    				pollingCount++;
    				System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                    Thread.sleep(period);
    			}
    		}
    		
    		catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Change Detector][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
    	}
    	
    	System.out.println("[YYU][Change Detector] : Timeout !!!!!");
        return false;
    }

    public boolean containsLetterFindByXPath(WebDriver driver, String xPath, String targetLetter, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;
        System.out.println("[YYU][Find Letter] : Find the " + xPath + " Start");

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.xpath(xPath)).getText().contains(targetLetter) == true)
                {
                    System.out.println("[YYU][Find Letter] : Find " + targetLetter + " success");
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Letter] : Timeout !!!!!");
        return false;
    }

    public boolean containsLetterFindById(WebDriver driver, String id, String targetLetter, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;
        System.out.println("[YYU][Find Letter] : Find the " + id + " Start");

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.id(id)).getText().contains(targetLetter) == true)
                {
                    System.out.println("[YYU][Find Letter] : Find " + targetLetter + " success");
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Letter] : Timeout !!!!!");
        return false;
    }

    public boolean containsLetterFindByName(WebDriver driver, String name, String targetLetter, int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;
        System.out.println("[YYU][Find Letter] : Find the " + name + " Start");

        while(pollingCount < timeout/period)
        {
            try
            {
                if(driver.findElement(By.name(name)).getText().contains(targetLetter) == true)
                {
                    System.out.println("[YYU][Find Letter] : Find " + targetLetter + " success");
                    return true;
                }

                else
                {
                    pollingCount++;
                    System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                    Thread.sleep(period);
                }
            }

            catch(NoSuchElementException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NoSuchWindowException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(WebDriverException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }

            catch(NullPointerException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Letter][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Letter] : Timeout !!!!!");
        return false;
    }

    public boolean isThereAlertWithPolling(int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                alert = driver.switchTo().alert();
                return true;
            }

            catch (NoAlertPresentException e)
            {
                pollingCount++;
                System.out.println("[YYU][Find Alert][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Find Alert] : Timeout !!!!!");
        return false;
    }

    public String getTextInAlertWithPolling(int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                alert = driver.switchTo().alert();
                return alert.getText();
            }

            catch (NoAlertPresentException e)
            {
                pollingCount++;
                System.out.println("[YYU][Alert Text][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Alert Text] : Timeout !!!!!");
        return "";
    }

    public void acceptToAlertWithPolling(int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                alert = driver.switchTo().alert();
                alert.accept();
            }

            catch (NoAlertPresentException e)
            {
                pollingCount++;
                System.out.println("[YYU][Alert Accept][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Alert Accept] : Timeout !!!!!");
    }

    public void dismissToAlertWithPolling(int period, int timeout) throws InterruptedException
    {
        int pollingCount = 0;

        while(pollingCount < timeout/period)
        {
            try
            {
                alert = driver.switchTo().alert();
                alert.dismiss();
            }

            catch (NoAlertPresentException e)
            {
                pollingCount++;
                System.out.println("[YYU][Alert Dismiss][Polling Count] : " + pollingCount);
                Thread.sleep(period);
            }
        }

        System.out.println("[YYU][Alert Dismiss] : Timeout !!!!!");
    }
}