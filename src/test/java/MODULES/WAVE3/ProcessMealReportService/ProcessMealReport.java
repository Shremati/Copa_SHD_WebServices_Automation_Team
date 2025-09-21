package MODULES.WAVE3.ProcessMealReportService;

import MODULES.WAVE3.ProcessMealReportService.API_Tests.Meal_Report_Final;
import MODULES.WAVE3.ProcessMealReportService.API_Tests.Meal_Report_Invalid_Flight_Number;
import MODULES.WAVE3.ProcessMealReportService.API_Tests.Meal_Report_Preliminary;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 scenarios
@Listeners(TestListener.class)
public class ProcessMealReport {

    ProcessMealReport()
    {
        createFolders(getResponseDirectory()+"ProcessMealReport");
    }

    @Test(description = "PMR_01 - Meal Report Preliminary")
    public void PMR_01()
    {
        try
        {
            Meal_Report_Preliminary.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("PMR_01 failed due to :"+e);
        }
    }

    @Test(description = "PMR_03 - Meal Report Invalid Flight Number")
    public void PMR_03()
    {
        try
        {
            Meal_Report_Invalid_Flight_Number.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("PMR_03 failed due to :"+e);
        }
    }

    @Test(description = "PMR_02 - Meal Report Final")
    public void PMR_02()
    {
        try
        {
            Meal_Report_Final.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("PMR_02 failed due to :"+e);
        }
    }
}
