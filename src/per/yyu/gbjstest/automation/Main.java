package per.yyu.gbjstest.automation;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GamebaseInformation gbInfo = new GamebaseInformation();
        WebDriverFunction webDrvFn = new WebDriverFunction();
        FileIO fi = new FileIO();

        Launching launching = new Launching();
        Authentication_PC authPC = new Authentication_PC();

        Scanner scan = new Scanner(System.in);

        System.out.println("Please Select Device");
        System.out.println("0. PC");
        System.out.println("1. Mobile");
        gbInfo.setDeviceTypeNo(scan.nextInt());

        if(gbInfo.getDeviceTypeNo() == 0) {
            System.out.println("Please Select Browser");
            System.out.println("1. Chrome");
            System.out.println("2. Firefox");
            System.out.println("3. Internet Explorer");
            System.out.println("4. Safari (Mac OS X)");
            gbInfo.setBrowserTypeNo(scan.nextInt());

            System.out.println("Please Select IdP");
            System.out.println("1. Guest");
            System.out.println("2. Facebook");
            System.out.println("3. Payco");
            System.out.println("4. Naver");
            System.out.println("5. Google");
            gbInfo.setIdPTypeNo(scan.nextInt());

            scan.close();
        }

        else if(gbInfo.getDeviceTypeNo() == 1) {

        }

        fi.initializeFileIO(gbInfo);
        webDrvFn.initWebDriver(gbInfo);

        launching.initializeGamebase(webDrvFn, gbInfo, fi);
        authPC.authenticationGamebase(webDrvFn, gbInfo, fi);

        fi.closeCSVWriter();
        System.out.println("Done");
    }
}