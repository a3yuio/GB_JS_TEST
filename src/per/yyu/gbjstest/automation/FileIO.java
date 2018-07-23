package per.yyu.gbjstest.automation;

import java.io.*;

public class FileIO
{
    private BufferedWriter bufWriter;
    private BufferedReader bufReader;

    public void fileIOInitialize(GamebaseInformation gbInfo) throws IOException
    {
        this.csvWriterInit(gbInfo);
        this.csvHeadlineWriter();
        this.readTestURLFile(gbInfo);
        this.readTestAccountFile(gbInfo);
    }


    // CSV File Writer Module
    private void csvWriterInit(GamebaseInformation gbInfo) throws FileNotFoundException, UnsupportedEncodingException
    {
        bufWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(gbInfo.getTestResultFilePath()), "UTF-8")
        );
    }

    private void csvHeadlineWriter() throws IOException
    {
        bufWriter.write("Test Case Name,User Id,Status Code,Login Status,Test Result,Test Running Time");
        bufWriter.newLine();
    }

    public void csvTestResultWriter(GamebaseInformation gbInfo, String testCaseName, String result) throws IOException
    {
        bufWriter.write(testCaseName + "," + gbInfo.getUserID() + "," + gbInfo.getLaunchingStatusCode() + "," + gbInfo.getLoginStatus() + "," + result + "," + gbInfo.getTestRunningTime());
        bufWriter.newLine();
    }

    public void csv_Closer() throws IOException
    {
        bufWriter.close();
    }


    // Read Setting File
    private void readTestURLFile(GamebaseInformation gbInfo) throws IOException
    {
        try
        {
            FileReader fileReader = new FileReader(new File(gbInfo.getTestURLFilePath()));
            bufReader = new BufferedReader(fileReader);

            gbInfo.setTestURL(bufReader.readLine());
            bufReader.close();
        }

        catch(Exception e)
        {
            System.out.println("[File IO][Read Test URL File] : File is not exist");
        }
    }

    private void readTestAccountFile(GamebaseInformation gbInfo) throws IOException
    {
        try
        {
            int stringIndex = 0;
            int readCount = 1;
            String line = "";

            FileReader fileReader = new FileReader(new File(gbInfo.getTestAccountFilePath()));
            bufReader = new BufferedReader(fileReader);

            while((line = bufReader.readLine()) != null)
            {
                if(readCount % 2 != 0) // Odd Number
                {
                    gbInfo.setTestID(line, stringIndex);
                }

                else // Even Number
                {
                    gbInfo.setTestPW(line, stringIndex);
                    stringIndex++;
                }

                readCount++;
            }

            bufReader.close();
        }

        catch(Exception e)
        {
            System.out.println("[File IO][Read Test Account File] : File is not exist");
        }
    }

    private void readLaunchingMessageFile(GamebaseInformation gbInfo) throws FileNotFoundException
    {
        try
        {
            int launguageIndex = 0; // 0 = en, 1 = ko, 2 = ja
            int messageIndex = 0;
            String line = "";

            FileReader fileReader = new FileReader(new File(gbInfo.getLaunchingMessageFilePath()));
            bufReader = new BufferedReader(fileReader);

            while((line = bufReader.readLine()) != null)
            {
                gbInfo.setLaunchingMessage(launguageIndex, messageIndex, line);
                messageIndex++;

                if(messageIndex == 3)
                {
                    messageIndex = 0;
                    launguageIndex++;
                }
            }
        }

        catch(Exception e)
        {
            System.out.println("[File IO][Read Launching Message] : File is not exist");
        }
    }


    // Test Result Writer
    public void gbInitTestResultWriter(GamebaseInformation gbInfo) throws IOException
    {
        if(gbInfo.getLaunchingStatus() == true)
        {
            System.out.println("[File IO][GB Init Test Result Writer] : Success");
            gbInfo.setTestEndTime();
            this.csvTestResultWriter(gbInfo, "Initialize", "Success");
        }

        else
        {
            System.out.println("[File IO][GB Init Test Result Writer] : Fail !!!!!");
            gbInfo.setTestEndTime();
            this.csvTestResultWriter(gbInfo, "Initialize", "Failure");
        }
    }

    public void gbLoginTestResultWriter(GamebaseInformation gbInfo, String testCaseName) throws IOException
    {
        if(gbInfo.getUserID().contains("@") == true)
        {
            System.out.println("[File IO][" + testCaseName + " Test Result] : Success");
            gbInfo.setTestEndTime();
            this.csvTestResultWriter(gbInfo, testCaseName, "Success");
        }

        else
        {
            System.out.println("[File IO][" + testCaseName + " Test Result] : Fail !!!!!");
            gbInfo.setTestEndTime();
            this.csvTestResultWriter(gbInfo, testCaseName, "Failure");
        }
    }

    public void gbLogoutTestResultWriter(GamebaseInformation gbInfo, String testCaseName) throws IOException
    {
        if(gbInfo.getUserID().contains("@") == false)
        {
            System.out.println("[File IO][" + testCaseName + " Test Result] : Success");
            gbInfo.setTestEndTime();
            this.csvTestResultWriter(gbInfo, testCaseName, "Success");
        }

        else
        {
            System.out.println("[File IO][" + testCaseName + " Test Result] : Fail !!!!!");
            gbInfo.setTestEndTime();
            this.csvTestResultWriter(gbInfo, testCaseName, "Failure");
        }
    }
}