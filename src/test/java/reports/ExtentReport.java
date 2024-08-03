package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import listeners.TestListener;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class ExtentReport {

    private static ExtentReports extent;
    private ExtentReport(){}
    private static ExtentTest test;


    public static void initReports() throws IOException {
        String reportName=ExtentLogger.getReportName();;
        String folderName = "COPA_" + (new SimpleDateFormat("yyyyMMdd")).format(new Date());
        String reportLocation = System.getProperty("user.dir") + "\\" + folderName+"\\"
                + reportName+"_"+new SimpleDateFormat("HHmmss").format(new Date())+".html";
      //  String reportLocation = System.getProperty("user.dir") + "\\" + folderName+"\\"+ reportName+".html";
        extent  = new ExtentReports();

        ExtentSparkReporter spark = new ExtentSparkReporter(reportLocation);
        //spark.loadXMLConfig(System.getProperty("user.dir") + "\\src\\test\\resources\\extent-config.xml");
        spark.config().setReportName("COPA SHD Webservices");
        extent.attachReporter(spark);
    }

    public static void tearDownReports()
    {
        extent.flush();
    }

    public static void createTest(String name)
    {
        test = extent.createTest(name);
        ExtentManager.setTest(test);
    }

    private static String getcurrentdateandtime() {
        String str = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
            Date date = new Date();
            str = dateFormat.format(date);
            str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
        } catch (Exception e) {
        }
        return str;
    }
}
