package com.lulobank.automation.utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportLuloBank {
    private static ExtentTest test;
    private static ExtentReports htmlReporter;
    private static String service;
    private ReportLuloBank() {

    }

    public static void startReport(String service){
        ReportLuloBank.service =service;
        htmlReporter = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html",false);
        test=htmlReporter.startTest(service.toUpperCase().concat(" - LULOBANK"));
        htmlReporter.flush();
    }

    public static void reports(String status, String message){
        switch (status){
            case "PASS":
                test.log(LogStatus.PASS, message );
                break;
            case "FAIL":
                test.log(LogStatus.FAIL, message );
                break;
            case "INFO":
                test.log(LogStatus.INFO, message );
                break;
            case "WARNING":
                test.log(LogStatus.WARNING, message );
                break;
        }
        htmlReporter.flush();
    }
  public static void finishReport(){
      test.log(LogStatus.INFO, "Se Finaliza el caso de '"+service+"' de manera exitosa!" );
      htmlReporter.endTest(test);
      htmlReporter.flush();
  }


}
