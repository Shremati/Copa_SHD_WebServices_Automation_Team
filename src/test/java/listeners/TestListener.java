package listeners;

import frameworkconstants.FrameworkConstants;
import org.testng.*;
import reports.ExcelUtil;
import reports.ExtentLogger;
import reports.ExtentReport;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import static frameworkconstants.FrameworkConstants.getBaseURL;
import static frameworkconstants.FrameworkConstants.testResults;

public class TestListener implements ITestListener, ISuiteListener, IClassListener, IInvokedMethodListener {

    String testDescription;
    String testName;
    public void onBeforeClass(org.testng.ITestClass testClass) {

        String name = testClass.getName();
        name=name.substring(14);
        String rName[] = name.split("\\.");
        ExtentLogger.setReportName(rName[0]);
        try {
            ExtentReport.initReports();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void onAfterClass(org.testng.ITestClass testClass) {
        ExtentReport.tearDownReports(); }

    @Override
    public void onStart(ISuite suite) {
        testResults.clear();

        testResults.add(new String[]{
                "Module Name",
                "Test Name",
                "Status",
                "Description"
        });
    }

    @Override
    public void onFinish(ISuite suite) {

        try {
            String filePath = FrameworkConstants.reportLocation + "/TestResults.xlsx";
            ExcelUtil.writeTestResults(filePath, testResults);
            System.out.println("Excel report generated successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {

        testName = result.getName();
        testDescription = result.getMethod().getDescription();

        ExtentReport.createTest(testName.toUpperCase());
    }


    @Override
    public void onTestSuccess(ITestResult result) {

        String moduleName = result.getTestClass().getRealClass().getSimpleName();

        testResults.add(new String[]{
                moduleName,
                result.getName(),
                "PASS",
                result.getMethod().getDescription()
        });

        ExtentLogger.pass(result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String moduleName = result.getTestClass().getRealClass().getSimpleName();

        testResults.add(new String[]{
                moduleName,
                result.getName(),
                "FAIL",
                String.valueOf(result.getThrowable())
        });

        ExtentLogger.fail(String.valueOf(result.getThrowable()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String moduleName = result.getTestClass().getRealClass().getSimpleName();

        testResults.add(new String[]{
                moduleName,
                result.getName(),
                "SKIPPED",
                result.getMethod().getDescription()
        });
    }

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
//            testDescription  = method.getTestMethod().getDescription();
//            System.out.println(testDescription);
        }
    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // Can be used for post-invocation logic
    }
    public String getTestName()
    {
        return testName;
    }
}

