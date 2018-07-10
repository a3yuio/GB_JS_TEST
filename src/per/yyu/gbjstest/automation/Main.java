package per.yyu.gbjstest.automation;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws InterruptedException, IOException
    {
        GamebaseInformation gbinfo = new GamebaseInformation();
        WebDriverAPI webapi = new WebDriverAPI();
        Launching launch = new Launching();
        Authentication auth = new Authentication();
        FileIO fi = new FileIO();

        Scanner scan = new Scanner(System.in);

        System.out.println("What's your browser ?");
        System.out.println("1. Chrome // 2. Firefox // 3. IE // 4. Safari");
        gbinfo.setBrowserIndex(scan.nextInt());

        System.out.println("Select Client Version");
        System.out.println("1. Test // 2. Inspection in Store // 3. In Service // 4. Recommend Update");
        System.out.println("5. Must Update // 6. Out of Service // 7. Maintenance // 8. Notice");
        gbinfo.setClientVersionIndex(scan.nextInt());

        System.out.println("Select Auth Test");
        System.out.println("1. Guest // 2. Facebook // 3. Payco // 4. Naver // 5. Google");
        gbinfo.setIdPIndex(scan.nextInt());

        scan.close();

        fi.csvOpener(gbinfo);
        fi.csvHeadLineWriter("Test Case", "UserId", "Launching Status Code", "Login Status", "Case Result", "Running Time");

        webapi.browserSelector(gbinfo);
        
        if(gbinfo.getClientVersionIndex() == 352)
        {
        	launch.clientVersionSelector(webapi, gbinfo, fi);
        }
        
        else
        {
        	launch.clientVersionSelector(webapi, gbinfo, fi);
        	launch.gamebaseInitialize(webapi, gbinfo);
            auth.idPSelector(webapi, gbinfo, fi);
        }

        fi.csvCloser();
        System.out.println("Test Done");
    }
}