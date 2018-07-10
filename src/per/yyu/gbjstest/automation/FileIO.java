package per.yyu.gbjstest.automation;

import java.io.*;

public class FileIO
{
    BufferedWriter writer;

    public void csvOpener(GamebaseInformation gbinfo) throws FileNotFoundException, UnsupportedEncodingException
    {
        writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(gbinfo.getTestResultFilePath()), "UTF-8")
        );
    }

    public void csvHeadLineWriter(String category1, String category2, String category3, String category4, String category5, String category6) throws IOException
    {
        writer.write(category1 + "," + category2 + "," + category3 + "," + category4 + "," + category5 + "," + category6);
        writer.newLine();
    }

    public void csvWriter(String testCaseName, String userId, String launchingStatusCode, String loginStatus, String result, float runningTime) throws IOException
    {
        writer.write(testCaseName + "," + userId + "," + launchingStatusCode + "," + loginStatus + "," + result + "," + runningTime);
        writer.newLine();
    }

    public void csvCloser() throws IOException
    {
        writer.close();
    }
    
    public void testUrlSetter(GamebaseInformation gbinfo) throws IOException
    {
    	try
    	{
    		FileReader filereader = new FileReader(new File(gbinfo.getTestURLFilePath()));
    		BufferedReader bufReader = new BufferedReader(filereader);
    		
    		gbinfo.setTestURL(bufReader.readLine());
    		bufReader.close();
    	}
    	
    	catch (FileNotFoundException e)
    	{
    		System.out.println("Test URL File is not exist");
    	}
    }
    
    public void testAccountSetter(GamebaseInformation gbinfo) throws IOException
    {
    	try
    	{
    		int index = 0;
    		int count = 1;
    		String line = "";
    		
    		FileReader filereader = new FileReader(new File(gbinfo.getTestAccountFilePath()));
    		BufferedReader bufReader = new BufferedReader(filereader);
    		
    		while((line = bufReader.readLine()) != null)
    		{
    			if(count % 2 != 0) // Odd Number
    			{
    				gbinfo.setTestId(line, index);
    			}
    			
    			else // Even Number
    			{
    				gbinfo.setTestPw(line, index);
    				index++;
    			}
    			
    			count++;
    		}
    		
    		bufReader.close();
    	}
    	
    	catch (FileNotFoundException e)
    	{
    		System.out.println("Test Account File is not exist");
    	}
    }

    public void loginTestResultWriter(GamebaseInformation gbinfo, String testCaseName) throws IOException, InterruptedException
    {
        if(gbinfo.getUserId().contains("@") == true)
        {
            System.out.println("[YYU][" + testCaseName + "] : Success");
            gbinfo.setTestCaseEnd();
            this.csvWriter(testCaseName, gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Success", gbinfo.getAPIRunningTime());
            Thread.sleep(500);
        }

        else
        {
            System.out.println("[YYU][" + testCaseName + "] : Fail !!!!!");
            gbinfo.setTestCaseEnd();
            this.csvWriter(testCaseName, gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
            Thread.sleep(500);
        }
    }

    public void logoutTestResultWriter(GamebaseInformation gbinfo, String testCaseName) throws IOException, InterruptedException
    {
        if(gbinfo.getUserId().contains("@") == false)
        {
            System.out.println("[YYU][" + testCaseName + "] : Success");
            gbinfo.setTestCaseEnd();
            this.csvWriter(testCaseName, gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Success", gbinfo.getAPIRunningTime());
            Thread.sleep(500);
        }

        else
        {
            System.out.println("[YYU][" + testCaseName + "] : Fail !!!!!");
            gbinfo.setTestCaseEnd();
            this.csvWriter(testCaseName, gbinfo.getUserId(), gbinfo.getLaunchingStatusCode(), gbinfo.getLoginStatusText(), "Failure", gbinfo.getAPIRunningTime());
            Thread.sleep(500);
        }
    }
}