package reports;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentManager {
    private ExtentManager(){}
    private final static ThreadLocal<ExtentTest> exTest = new ThreadLocal<>();
    static void setTest(ExtentTest test)
    {
        exTest.set(test);
    }

    static ExtentTest getTest()
    {
        return exTest.get();
    }
}
