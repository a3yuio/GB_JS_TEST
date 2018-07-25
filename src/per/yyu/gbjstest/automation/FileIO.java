package per.yyu.gbjstest.automation;

import java.io.*;

public class FileIO {
    private BufferedWriter bufWriter;
    private BufferedReader bufReader;
    private FileReader fileReader;


    /**
     * @author YongUn Yi <br/>
     * Automation 시작 시 실행하여 <br/>
     * Test URL / Account 를 세팅하고 <br/>
     * CSV 파일을 생성하여 테스트를 준비함 <br/>
     */
    public void initFileIO(GamebaseInformation gbInfo) throws IOException {
        this.setTestURL(gbInfo);
        this.setTestAccount(gbInfo);
        this.initCSVWriter(gbInfo);
        this.makeCSVHeadline();
    }



    // Read Setting File

    /**
     * @author YongUn Yi <br/>
     * 파일의 경로를 Param 으로 받아서, 해당 파일을 불러옴 <br/>
     * 파일이 없을 경우 methodName 을 Param 으로 받아서 로그를 남김 <br/>
     */
    private void readyFileReader(String filePath, String methodNameForLog) {
        try {
            fileReader = new FileReader(new File(filePath));
            bufReader = new BufferedReader(fileReader);
        }

        catch(Exception e) {
            System.out.println("[File IO][" + methodNameForLog + "] : File is not exist");
        }
    }

    /**
     * @author YongUn Yi <br/>
     * TestURLFile 에 저장된 TestURL 을 읽어와서 변수에 저장 <br/>
     */
    private void setTestURL(GamebaseInformation gbInfo) throws IOException {
        this.readyFileReader(gbInfo.getTestURLFilePath(), "Set Test URL");
        gbInfo.setTestURL(bufReader.readLine());
        bufReader.close();
    }

    /**
     * @author YongUn Yi <br/>
     * TestAccountFile 에 저장된 ID, PW 를 읽어와서 변수에 저장 <br/>
     */
    private void setTestAccount(GamebaseInformation gbInfo) throws IOException {
        int stringLineIndex = 0;
        int readCount = 1;
        String textLine = "";

        this.readyFileReader(gbInfo.getTestAccountFilePath(), "Set Test Account");

        while((textLine = bufReader.readLine()) != null) {
            if(readCount % 2 != 0) {
                gbInfo.setTestID(stringLineIndex, textLine);
            }

            else {
                gbInfo.setTestPW(stringLineIndex, textLine);
                stringLineIndex++;
            }
            readCount++;
        }
        bufReader.close();
    }

    /**
     * @author YongUn Yi <br/>
     * TestLaunchingMessageFile 에 저장된 Launching Message 를 읽어와서 변수에 저장 <br/>
     */
    private void setLaunchingMessage(GamebaseInformation gbInfo) throws IOException {
        int languageIndex = 0;
        int messageIndex = 0;
        String textLine = "";

        this.readyFileReader(gbInfo.getLaunchingMessageFilePath(), "Set Launching Message");

        while((textLine = bufReader.readLine()) != null) {
            gbInfo.setLaunchingMessage(languageIndex, messageIndex, textLine);
            messageIndex++;

            if(messageIndex == 3) {
                languageIndex++;
                messageIndex = 0;
            }
        }
    }



    // CSV File Maker
    private void initCSVWriter(GamebaseInformation gbInfo) throws FileNotFoundException, UnsupportedEncodingException {
        bufWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(gbInfo.getTestResultFilePath()), "UTF-8")
        );
    }

    /**
     * @author YongUn Yi <br/>
     * CSV 파일 최상단에 카테고리를 생성 <br/>
     */
    private void makeCSVHeadline() throws IOException {
        bufWriter.write("Test Case Name, User ID, Launching Status Code, Test Result, Test Running Time");
    }

    /**
     * @author YongUn Yi <br/>
     * @param testCaseName <br/>
     * ex. Guest Login, Facebook Login, Logout... <br/>
     * @param testResult <br/>
     * Success / Pass <br/>
     * Failure / Fail <br/>
     */
    public void writeCSVTestResult(GamebaseInformation gbInfo, String testCaseName, String testResult) throws IOException {
        bufWriter.write(testCaseName + "," + gbInfo.getUserID() + "," + gbInfo.getLaunchingStatusCode() + "," + testResult + "," + gbInfo.getTestRunningTime());
        bufWriter.newLine();
    }

    public void closeCSVWriter() throws IOException {
        bufWriter.close();
    }

    /**
     * @author YongUn Yi <br/>
     * testResult 에 따라 성공/실패 결과를 CSV 에 기록함 <br/>
     * @param testCaseName <br/>
     * ex. Initialize, Facebook Login, Logout... <br/>
     * @param testResult <br/>
     * true = Success / Pass <br/>
     * false = Failure / Fail <br/>
     */
    public void write_TestResult(GamebaseInformation gbInfo, String testCaseName, boolean testResult) throws IOException {
        if(testResult) {
            System.out.println("[" + testCaseName + "] : Success");
            this.writeCSVTestResult(gbInfo, testCaseName, "Success");
        }

        else {
            System.out.println("[" + testCaseName + "] : !!!!! Failure !!!!!");
            this.writeCSVTestResult(gbInfo, testCaseName, "Failure");
        }
    }
}