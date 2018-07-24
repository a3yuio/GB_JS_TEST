package per.yyu.gbjstest.automation;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GamebaseInformation {

    // Base Data for Automation Test
    private String testURLFilePath;
    private String testResultFilePath;
    private String chromeDriverPath;
    private String geckoDriverPath;
    private String ieDrivetPath; // 64bit Driver is very slow. Recommend using 32-bit
    private String testURL;
    private String appiumURL;
    private int browserIndex;
    private String ieVersion;
    private int deviceType;
    private long testStartTime;
    private long testEndTime;


    // Test Account
    private String testAccountFilePath;
    private String[] testID;
    private String[] testPW;
    final int IDP_TYPE_NO = 10;


    // Launching
    private String launchingMessageFilePath;
    private String appID;
    private int launchingZoneIndex;
    private int clientVersionIndex;
    private String launchingStatusCode;
    private String languageCode;
    private String[][] launchingMessage;
    final int SUPPORT_LANGUAGE_QUANTITY = 3;
    final int LAUNCHING_MESSAGE_QUANTITY = 4;


    // Authentication
    private int idPIndex;
    private String userID;

    // Initialize
    public GamebaseInformation() {
        // Get current time
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd-hh:mm");


        // Base Data for Automation Test
        this.testURLFilePath = "C:\\GB_JS_TEST_Automation\\Resource\\txt\\GB_JS_Test_TestURL.txt";
        this.testResultFilePath = "C:\\GB_JS_TEST_Automation\\GB_JS_Test_Result" + sdf.format(cal.getTime()) + ".csv";
        this.chromeDriverPath = "C:\\GB_JS_TEST_Automation\\Resource\\WebDriver\\chromedriver.exe";
        this.geckoDriverPath = "C:\\GB_JS_TEST_Automation\\Resource\\WebDriver\\geckodriver.exe";
        this.ieDrivetPath = "C:\\GB_JS_TEST_Automation\\Resource\\WebDriver\\IEDriverServer.exe"; // 64bit Driver is very slow. Recommend using 32-bit
        this.testURL = "";
        this.appiumURL = "http://127.0.0.1:4723/wd/hub";
        this.browserIndex = 0;
        this.ieVersion = "";
        this.deviceType = 0;
        this.testStartTime = 0;
        this.testEndTime = 0;


        // Test Account
        this.testAccountFilePath = "C:\\GB_JS_TEST_Automation\\Resource\\txt\\GB_JS_Test_TestAccount.txt";
        this.testID = new String[IDP_TYPE_NO];
        this.testPW = new String[IDP_TYPE_NO];


        // Launching
        this.launchingMessageFilePath = "C:\\GB_JS_TEST_Automation\\Resource\\txt\\GB_JS_Test_Launching_Status_Message.txt";
        this.appID = "5egT8OTX";
        this.launchingZoneIndex = 0;
        this.clientVersionIndex = 0;
        this.launchingStatusCode = "";
        this.languageCode = "en";
        this.launchingMessage = new String[SUPPORT_LANGUAGE_QUANTITY][LAUNCHING_MESSAGE_QUANTITY];


        // Authentication
        this.idPIndex = 0;
        this.userID = "";
    }


    // Base Data for Automation Test
}