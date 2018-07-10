package per.yyu.gbjstest.automation;

public class GamebaseInformation
{
    // Common
    private String testURLFilePath;
    private String testResultFilePath;
    private String chromeDriverPath;
    private String geckoDriverPath;
    private String ieDriverPath;
    private String testURL;
    private int browserIndex;
    private long testStartTime;
    private long testEndTime;

    // Test Account
    private String testAccountFilePath;
    private String[] testId;
    private String[] testPw;

    // Launching
    private String appId;
    private int launchingZoneIndex;
    private int clientVersionIndex;
    private String launchingStatusCode;
    private boolean launchingStatus;

    // Authentication
    private int idPIndex;
    private boolean loginStatus;
    private String loginStatusText;
    private String userId;

    public GamebaseInformation()
    {
        // Common
        this.testURLFilePath = "D:\\test\\testURL.txt";
        this.testResultFilePath = "D:\\test\\test.csv";
        this.chromeDriverPath = "D:\\test\\chromedriver.exe";
        this.geckoDriverPath = "D:\\test\\geckodriver.exe";
        this.ieDriverPath = "D:\\test\\IEDriverServer.exe";
        this.testURL = "";
        this.browserIndex = 0;
        this.testStartTime = 0;
        this.testEndTime = 0;

        // Test Account
        this.testAccountFilePath = "D:\\test\\testAccount.txt";
        this.testId = new String[10];
        this.testPw = new String[10];

        // Launching
        this.appId = "5egT8OTX";
        this.launchingZoneIndex = 2;
        this.clientVersionIndex = 3;
        this.launchingStatusCode = "";
        this.launchingStatus = false;

        // Authentication
        this.idPIndex = 0;
        this.loginStatus = false;
        this.loginStatusText = "";
        this.userId = "";
    }

    // Common
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

    public void setTestURL(String testURL)
    {
        this.testURL = testURL;
    }

    public String getTestURL()
    {
        return this.testURL;
    }

    public void setBrowserIndex(int browserIndex)
    {
        this.browserIndex = browserIndex;
    }

    public int getBrowserIndex()
    {
        return this.browserIndex;
    }

    public void setTestCaseStart()
    {
        this.testStartTime = System.currentTimeMillis();
    }

    public void setTestCaseEnd()
    {
        this.testEndTime = System.currentTimeMillis();
    }

    public float getAPIRunningTime()
    {
        return (float) ((this.testStartTime - this.testEndTime)) / 1000;
    }

    // Test Account
    public String getTestAccountFilePath()
    {
        return this.testAccountFilePath;
    }

    public void setTestId(String testId, int index)
    {
        this.testId[index] = testId;
    }

    public String getTestId(int index)
    {
        return this.testId[index - 2];
    }

    public void setTestPw(String testPW, int index)
    {
        this.testPw[index] = testPW;
    }

    public String getTestPw(int index)
    {
        return this.testPw[index - 2];
    }

    // Launching
    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    public String getAppId()
    {
        return this.appId;
    }

    public void setLaunchingZoneIndex(int launchingZoneIndex)
    {
        this.launchingZoneIndex = launchingZoneIndex;
    }

    public int getLaunchingZoneIndex()
    {
        return this.launchingZoneIndex;
    }

    public void setClientVersionIndex(int clientVersionIndex)
    {
        this.clientVersionIndex = clientVersionIndex;
    }

    public int getClientVersionIndex()
    {
        return this.clientVersionIndex;
    }

    public void setLaunchingStatusCode(String launchingStatusCode)
    {
        this.launchingStatusCode = launchingStatusCode;
    }

    public String getLaunchingStatusCode()
    {
        return this.launchingStatusCode;
    }

    public void setLaunchingStatus(boolean launchingStatus)
    {
        this.launchingStatus = launchingStatus;
    }

    public boolean getLaunchingStatus()
    {
        return this.launchingStatus;
    }

    // Authentication
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