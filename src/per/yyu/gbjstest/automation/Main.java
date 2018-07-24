package per.yyu.gbjstest.automation;

import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        GamebaseInformation gbInfo = new GamebaseInformation();
        WebDriverFunction webDrvFn = new WebDriverFunction();
        FileIO fi = new FileIO();

        Launching launching = new Launching();
        Authentication_PC authPC = new Authentication_PC();
        Authentication_Mobile authMobile = new Authentication_Mobile();

        Scanner scan = new Scanner(System.in);

        System.out.println("What's your browser ?");
        System.out.println("1. Chrome // 2. Firefox // 3. IE // 4. Safari");
        System.out.println("11. Android Chrome");
        gbInfo.setBrowserIndex(scan.nextInt());

        System.out.println("Select Client Version");
        System.out.println("1. Test // 2. Inspection in Store // 3. In Service // 4. Recommend Update");
        System.out.println("5. Must Update // 6. Out of Service // 7. Maintenance // 8. Notice");
        gbInfo.setClientVersionIndex(scan.nextInt());

        System.out.println("Select Auth Test");
        System.out.println("1. Guest // 2. Facebook // 3. Payco // 4. Naver // 5. Google");
        gbInfo.setIDPIndex(scan.nextInt());

        scan.close();

        fi.fileIOInitialize(gbInfo);
        webDrvFn.webDriverInitialize(gbInfo);

//        launching.gamebaseInitialize(webDrvFn, gbInfo, fi);

        if(gbInfo.getClientVersionIndex() < 350)
        {
            launching.gamebaseInitialize(webDrvFn, gbInfo, fi);
        }

        else
        {
            launching.eachOfLaunchingStatusInitRegressionTest(webDrvFn, gbInfo, fi);
        }

        switch(gbInfo.getDeviceType())
        {
            case 0:
            {
                authPC.gamebaseAuthentication_PC(webDrvFn, gbInfo, fi);
                break;
            }

            case 1:
            {
                authMobile.gamebaseAuthentication_Mobile(webDrvFn, gbInfo, fi);
            }
        }

        fi.closeCSVWriter();
        webDrvFn.webDriverCloser();
        System.out.println("Done");
    }
}