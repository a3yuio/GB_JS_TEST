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
    private String appiumServerIPNumber;
    private String appiumPortNumber;
    private int browserIndex;
    private String ieVersion;
    private int deviceType;
    private long testStartTime;
    private long testEndTime;


    // Test Account
    private String testAccountFilePath;
    private String[] testID;
    private String[] testPW;


    // Launching
    private String launchingMessageFilePath;
    private String appID;
    private int launchingZoneIndex;
    private int clientVersionIndex;
    private String launchingStatusCode;
    private boolean launchingStatus;
    private String languageCode;
    private String[][] launchingMessage;


    // Authentication
    private int idPIndex;
    private boolean loginStatus;
    private String loginStatusToText;
    private String userID;


    public GamebaseInformation()
    {
        // Common
        this.testURLFilePath = "D:\\test\\testURL.txt";
        this.testResultFilePath = "D:\\test\\test.csv";
        this.chromeDriverPath = "D:\\test\\chromedriver.exe";
        this.geckoDriverPath = "D:\\test\\geckodriver.exe";
        this.ieDriverPath = "D:\\test\\IEDriverServer.exe";
        this.testURL = "";
        this.appiumServerIPNumber = "127.0.0.1";
        this.appiumPortNumber = "4723";
        this.browserIndex = 0;
        this.ieVersion = "";
        this.deviceType = 0;
        this.testStartTime = 0;
        this.testEndTime = 0;

        // Test Account
        this.testAccountFilePath = "D:\\test\\testAccount.txt";
        this.testID = new String[10];
        this.testPW = new String[10];

        // Launching
        this.launchingMessageFilePath = "D:\\test\\launchingMessage.txt";
        this.appID = "5egT8OTX";
        this.launchingZoneIndex = 2;
        this.clientVersionIndex = 3;
        this.launchingStatusCode = "";
        this.launchingStatus = false;
        this.languageCode = "en";
        this.launchingMessage = new String[3][4];

        // Authentication
        this.idPIndex = 0;
        this.loginStatus = false;
        this.loginStatusToText = "";
        this.userID = "";
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

    public void setAppiumServerIPNumber(String appiumServerIPNumber)
    {
        this.appiumServerIPNumber = appiumServerIPNumber;
    }

    public void setAppiumPortNumber(String appiumPortNumber)
    {
        this.appiumPortNumber = appiumPortNumber;
    }

    public String getAppiumServerURL()
    {
        return "http://" + this.appiumServerIPNumber + ":" + this.appiumPortNumber + "/wd/hub";
    }

    public void setBrowserIndex(int browserIndex)
    {
        this.browserIndex = browserIndex;
    }

    public int getBrowserIndex()
    {
        return this.browserIndex;
    }

    public void setIEVersion(String ieVersion)
    {
        this.ieVersion = ieVersion;
    }

    public String getIEVersion()
    {
        return this.ieVersion;
    }

    public int getDeviceType()
    {
        // 0 = pc , 1 = mobile
        return this.browserIndex / 10;
    }

    public void setTestStartTime()
    {
        this.testStartTime = System.currentTimeMillis();
    }

    public void setTestEndTime()
    {
        this.testEndTime = System.currentTimeMillis();
    }

    public float getTestRunningTime()
    {
        return (float) ((this.testEndTime - this.testStartTime)) / 1000;
    }


    // Test Account
    public String getTestAccountFilePath()
    {
        return this.testAccountFilePath;
    }

    public void setTestID(String testID, int index)
    {
        this.testID[index] = testID;
    }

    public String getTestID(int index)
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


    // Launching
    public String getLaunchingMessageFilePath()
    {
        return this.launchingMessageFilePath;
    }

    public void setAppID(String appID)
    {
        this.appID = appID;
    }

    public String getAppID()
    {
        return this.appID;
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

    public void setLanguageCode(String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getLanguageCode()
    {
        return this.languageCode;
    }

    public void setLaunchingMessage(int languageCodeIndex, int launchingMessageIndex, String launchingMessage)
    {
        this.launchingMessage[languageCodeIndex][launchingMessageIndex] = launchingMessage;
    }

    public String getLaunchingMessage(int languageCodeIndex, int launchingMessageIndex)
    {
        return this.launchingMessage[languageCodeIndex][launchingMessageIndex];
    }


    // Authentication
    public void setIDPIndex(int idPIndex)
    {
        this.idPIndex = idPIndex;
    }

    public int getIDPIndex()
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

    public String getLoginStatusToText()
    {
        this.loginStatusToText = String.valueOf(this.loginStatus);
        return this.loginStatusToText;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getUserID()
    {
        return this.userID;
    }
}