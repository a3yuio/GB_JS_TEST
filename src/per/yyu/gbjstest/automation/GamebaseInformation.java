package per.yyu.gbjstest.automation;

public class GamebaseInformation
{
    // Common
    private String testURL;
    private String testURLFilePath;
    private String testResultFilePath;
    private String chromeDriverPath;
    private String geckoDriverPath;
    private String ieDriverPath;
    private int browserIndex;
    private boolean launchingStatus;
    private long apiStart;
    private long apiEnd;
    
    // Test Account
    private String[] testID;
    private String[] testPW;
    private String testAccountFilePath;
    
    // Launching
    private String appId;
    private int launchingZoneIndex;
    private String launchingStatusCode;
    private int clientVersionIndex;

    // Authentication
    private int idPIndex;
    private boolean loginStatus;
    private String loginStatusText;
    private String userId;

    public GamebaseInformation()
    {
        this.testURL = "";
        this.testURLFilePath = "C:\\test\\testURL.txt";
        this.testResultFilePath = "C:\\test\\test.csv";
        this.chromeDriverPath = "C:\\test\\chromedriver.exe";
        this.geckoDriverPath = "C:\\test\\geckodriver.exe";
        this.ieDriverPath = "C:\\test\\IEDriverServer.exe";
        this.testAccountFilePath = "C:\\test\\testAccount.txt";
        this.browserIndex = 0;
        this.launchingStatus = false;
        this.apiStart = 0;
        this.apiEnd = 0;
        this.testID = new String[10];
        this.testPW = new String[10];
        this.appId = "5egT8OTX";
        this.launchingZoneIndex = 2;
        this.launchingStatusCode = "";
        this.clientVersionIndex = 3;
        this.idPIndex = 0;
        this.loginStatus = false;
        this.userId = "";
    }

    public void setTestURL(String testURL)
    {
        this.testURL = testURL;
    }

    public String getTestURL()
    {
        return this.testURL;
    }
    
    public String getTestURLFilePath()
    {
    	return this.testURLFilePath;
    }

    public void setTestResultFilePath(String testResultFilePath)
    {
        this.testResultFilePath = testResultFilePath;
    }

    public String getTestResultFilePath()
    {
        return this.testResultFilePath;
    }

    public void setChromeDriverPath(String chromeDriverPath)
    {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getChromeDriverPath()
    {
        return this.chromeDriverPath;
    }

    public void setGeckoDriverPath(String geckoDriverPath)
    {
        this.geckoDriverPath = geckoDriverPath;
    }

    public String getGeckoDriverPath()
    {
        return this.geckoDriverPath;
    }

    public void setIeDriverPath(String ieDriverPath)
    {
        this.ieDriverPath = ieDriverPath;
    }

    public String getIeDriverPath()
    {
        return this.ieDriverPath;
    }

    public void setBrowserIndex(int browserIndex)
    {
        this.browserIndex = browserIndex;
    }

    public int getBrowserIndex()
    {
        return this.browserIndex;
    }

    public void setLaunchingStatus(boolean launchingStatus)
    {
        this.launchingStatus = launchingStatus;
    }

    public boolean getLaunchingStatus()
    {
        return this.launchingStatus;
    }

    public void setAPIStart()
    {
        this.apiStart = System.currentTimeMillis();
    }

    public void setAPIEnd()
    {
        this.apiEnd = System.currentTimeMillis();
    }
    
    public void setTestId(String testId, int index)
    {
    	this.testID[index] = testId;
    }
    
    public String getTestId(int index)
    {
    	return this.testID[index - 2];
    }
    
    public void setTestPW(String testPW, int index)
    {
    	this.testPW[index] = testPW;
    }
    
    public String getTestPW(int index)
    {
    	return this.testPW[index - 2];
    }
    
    public String getTestAccountFilePath()
    {
    	return this.testAccountFilePath;
    }

    public void setLaunchingZoneIndex(int launchingZoneIndex)
    {
        this.launchingZoneIndex = launchingZoneIndex;
    }

    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppId()
    {
        return this.appId;
    }

    public int getLaunchingZoneIndex()
    {
        return this.launchingZoneIndex;
    }

    public void setLaunchingStatusCode(String launchingStatusCode)
    {
        this.launchingStatusCode = launchingStatusCode;
    }

    public String getLaunchingStatusCode()
    {
        return this.launchingStatusCode;
    }

    public void setClientVersionIndex(int clientVersionIndex)
    {
        this.clientVersionIndex = clientVersionIndex;
    }

    public int getClientVersionIndex()
    {
        return this.clientVersionIndex;
    }

    public float getAPIRunningTime()
    {
        return (float) ((this.apiEnd - this.apiStart)) / 1000;
    }

    public void setIdPIndex(int idPIndex)
    {
        this.idPIndex = idPIndex;
    }

    public int getIdPIndex()
    {
        return this.idPIndex;
    }

    public void setLoginStatus(boolean loginStatus)
    {
        this.loginStatus = loginStatus;
    }

    public boolean getLoginStatus()
    {
        return this.loginStatus;
    }

    public String getLoginStatusText()
    {
        this.loginStatusText = String.valueOf(this.loginStatus);
        return this.loginStatusText;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserId()
    {
        return this.userId;
    }
}