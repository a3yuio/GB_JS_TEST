package per.yyu.gbjstest.automation;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GamebaseInformation {
    // Base Data for Automation Test
    private String testURLFilePath;
    private String testResultFilePath;
    private String chromeDriverPath;
    private String geckoDriverPath;
    private String ieDrivetPath;
    private String testURL;
    private String appiumURL;
    private int browserTypeNo;
    private int deviceTypeNo;
    private String ieVersion;
    private long testStartTime;
    private long testEndTime;

    // Test Account
    private String testAccountFilePath;
    private String[] testID;
    private String[] testPW;
    final int SUPPORT_IDP_QUANTITY = 10;

    // Launching
    private String launchingMessageFilePath;
    private String appId;
    private int launchingZoneIndex;
    private int clientVersionIndex;
    private String launchingStatusCode;
    private String languageCode;
    private String[][] launchingMessage;
    final int SUPPORT_LANGUAGE_QUANTITY = 3;
    final int LAUNCHING_MESSAGE_QUANTITY = 4;

    // Authentication
    private int idPTypeNo;
    private String userID;



    // Initialize
    public GamebaseInformation() {
        // Get current time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");

        // Base Data for Automation Test
        this.testURLFilePath = "C:\\GB_JS_TEST_Automation\\Resource\\txt\\GB_JS_Test_TestURL.txt";
        this.testResultFilePath = "C:\\GB_JS_TEST_Automation\\GB_JS_Test_Result_" + sdf.format(cal.getTime()) + ".csv";
//        this.testResultFilePath = "C:\\GB_JS_TEST_Automation\\GB_JS_Test_Result.csv";
        this.chromeDriverPath = "C:\\GB_JS_TEST_Automation\\Resource\\WebDriver\\chromedriver.exe";
        this.geckoDriverPath = "C:\\GB_JS_TEST_Automation\\Resource\\WebDriver\\geckodriver.exe";
        this.ieDrivetPath = "C:\\GB_JS_TEST_Automation\\Resource\\WebDriver\\IEDriverServer.exe"; // 64bit Driver is very slow. Recommend using 32-bit
        this.testURL = "";
        this.appiumURL = "http://127.0.0.1:4723/wd/hub";
        this.browserTypeNo = 0;
        this.deviceTypeNo = 0;
        this.ieVersion = "";
        this.testStartTime = 0;
        this.testEndTime = 0;

        // Test Account
        this.testAccountFilePath = "C:\\GB_JS_TEST_Automation\\Resource\\txt\\GB_JS_Test_TestAccount.txt";
        this.testID = new String[SUPPORT_IDP_QUANTITY];
        this.testPW = new String[SUPPORT_IDP_QUANTITY];

        // Launching
        this.launchingMessageFilePath = "C:\\GB_JS_TEST_Automation\\Resource\\txt\\GB_JS_Test_Launching_Status_Message.txt";
        this.appId = "5egT8OTX";
        this.launchingZoneIndex = 0;
        this.clientVersionIndex = 0;
        this.launchingStatusCode = "";
        this.languageCode = "en";
        this.launchingMessage = new String[SUPPORT_LANGUAGE_QUANTITY][LAUNCHING_MESSAGE_QUANTITY];

        // Authentication
        this.idPTypeNo = 0;
        this.userID = "";
    }



    // Base Data for Automation Test
    public String getTestURLFilePath() {
        return this.testURLFilePath;
    }

    public void setTestResultFilePath(String path) {
        this.testResultFilePath = path;
    }

    public String getTestResultFilePath() {
        return this.testResultFilePath;
    }

    public void setChromeDriverPath(String path) {
        this.chromeDriverPath = path;
    }

    public String getChromeDriverPath() {
        return this.chromeDriverPath;
    }

    public void setGeckoDriverPath(String path) {
        this.geckoDriverPath = path;
    }

    public String getGeckoDriverPath() {
        return this.geckoDriverPath;
    }

    public void setIEDrivetPath(String path) {
        this.ieDrivetPath = path;
    }

    public String getIEDrivetPath() {
        return this.ieDrivetPath;
    }

    public void setTestURL(String url) {
        this.testURL = url;
    }

    public String getTestURL() {
        return this.testURL;
    }

    public void setAppiumURL(String url) {
        this.appiumURL = url;
    }

    public String getAppiumURL() {
        return this.appiumURL;
    }

    public void setBrowserTypeNo(int typeNo) {
        this.browserTypeNo = typeNo;
    }

    public int getBrowserTypeNo() {
        return this.browserTypeNo;
    }

    public void setDeviceTypeNo(int typeNo) {
        this.deviceTypeNo = typeNo;
    }

    public int getDeviceTypeNo() {
        return this.deviceTypeNo;
    }

    public void setIEVersion(String version) {
        this.ieVersion = version;
    }

    public String getIEVersion() {
        return this.ieVersion;
    }

    public void setTestStartTime() {
        this.testStartTime = System.currentTimeMillis();
    }

    public void setTestEndTime() {
        this.testEndTime = System.currentTimeMillis();
    }

    public float getTestRunningTime() {
        return (float)((this.testEndTime - this.testStartTime)) / 1000;
    }



    // Test Account
    public String getTestAccountFilePath() {
        return this.testAccountFilePath;
    }

    public void setTestID(int idPTypeNo, String testID) {
        this.testID[idPTypeNo] = testID;
    }

    public String getTestId(int idPTypeNo) {
        return this.testID[idPTypeNo - 2];
    }

    public void setTestPW(int idPTypeNo, String testPW) {
        this.testPW[idPTypeNo] = testPW;
    }

    public String getTestPW(int idPTypeNo) {
        return this.testPW[idPTypeNo - 2];
    }



    // Launching
    public String getLaunchingMessageFilePath() {
        return this.launchingMessageFilePath;
    }

    public void setAppId(String appID) {
        this.appId = appID;
    }

    public String getAppId() {
        return this.appId;
    }

    /**
     * @param index <br/>
     * 1 = Alpha <br/>
     * 2 = Beta <br/>
     * 3 = Real <br/>
     */
    public void setLaunchingZoneIndex(int index) {
        this.launchingZoneIndex = index;
    }

    public int getLaunchingZoneIndex() {
        return this.launchingZoneIndex;
    }

    /**
     * @param index
     * 1 = Testing (테스트) <br/>
     * 2 = Inspect In Store (심사중) <br/>
     * 3 = In Service (서비스) <br/>
     * 4 = Recommend Update (업데이트 권장) <br/>
     * 5 = Require Update (업데이트 필수) <br/>
     * 6 = Out of Service (서비스 종료) <br/>
     * 7 = Maintenance (점검중) <br/>
     */
    public void setClientVersionIndex(int index) {
        this.clientVersionIndex = index;
    }

    public int getClientVersionIndex() {
        return this.clientVersionIndex;
    }

    public void setLaunchingStatusCode(String statusCode) {
        this.launchingStatusCode = statusCode;
    }

    public String getLaunchingStatusCode() {
        return this.launchingStatusCode;
    }

    public boolean getLaunchingStatus() {
        if(this.launchingStatusCode.equals("-")) {
            return false;
        }

        else {
            return true;
        }
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLaunchingMessage(int languageIndex, int messageIndex, String message) {
        this.launchingMessage[languageIndex][messageIndex] = message;
    }

    public String getLaunchingMessage(int languageIndex, int messageIndex) {
        return this.launchingMessage[languageIndex][messageIndex];
    }



    // Authentication

    /**
     * @param idPTypeNo <br/>
     * 1 = Guest <br/>
     * 2 = Facebook <br/>
     * 3 = Payco <br/>
     * 4 = Naver <br/>
     * 5 = Google <br/>
     */
    public void setIdPTypeNo(int idPTypeNo) {
        this.idPTypeNo = idPTypeNo;
    }

    public int getIdPTypeNo() {
        return this.idPTypeNo;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return this.userID;
    }
}