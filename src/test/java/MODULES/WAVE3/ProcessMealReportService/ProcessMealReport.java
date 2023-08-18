package MODULES.WAVE3.ProcessMealReportService;

import MODULES.WAVE3.ProcessMealReportService.API_Tests.Meal_Report_Final;
import MODULES.WAVE3.ProcessMealReportService.API_Tests.Meal_Report_Invalid_Flight_Number;
import MODULES.WAVE3.ProcessMealReportService.API_Tests.Meal_Report_Preliminary;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ProcessMealReport {

    ProcessMealReport()
    {
        createFolders(getResponseDirectory()+"ProcessMealReport");
    }

    @Description("Meal Report Preliminary")
    @Test
    public void Scenario1()
    {
        try
        {
            Meal_Report_Preliminary.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Meal Report Invalid Flight Number")
    @Test
    public void Scenario2()
    {
        try
        {
            Meal_Report_Invalid_Flight_Number.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }

    @Description("Meal Report Final")
    @Test
    public void Scenario3()
    {
        try
        {
            Meal_Report_Final.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }



}
