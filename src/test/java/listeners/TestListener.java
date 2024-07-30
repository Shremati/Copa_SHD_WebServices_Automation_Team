package listeners;

import org.testng.*;
import reports.ExtentLogger;
import reports.ExtentReport;

public class TestListener implements ITestListener, ISuiteListener, IClassListener, IInvokedMethodListener {

    String testDescription;
    String testName;
    public void onBeforeClass(org.testng.ITestClass testClass) {

        String name = testClass.getName();
        name=name.substring(14);
        String rName[] = name.split("\\.");
        ExtentLogger.setReportName(rName[0]);
        ExtentReport.initReports();
        }

    public void onAfterClass(org.testng.ITestClass testClass) {
        ExtentReport.tearDownReports(); }

    public void onStart(org.testng.ISuite suite) {
    }

    public void onFinish(org.testng.ISuite suite) {
    }
    @Override
    public void onTestStart(org.testng.ITestResult result) {
        int n=50;

        testName = result.getName();
        testDescription = result.getMethod().getDescription();
        if(testDescription!="")
            testName = testDescription.substring(0, Math.min(testDescription.length(), n));

        ExtentReport.createTest(testName.toUpperCase());
    }

    public void onTestSuccess(org.testng.ITestResult result) {
        ExtentLogger.pass(result.getName());
         }

    public void onTestFailure(org.testng.ITestResult result) {
        ExtentLogger.fail(String.valueOf(result.getThrowable())); }


    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
//            testDescription  = method.getTestMethod().getDescription();
//            System.out.println(testDescription);
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Can be used for post-invocation logic
    }

}
