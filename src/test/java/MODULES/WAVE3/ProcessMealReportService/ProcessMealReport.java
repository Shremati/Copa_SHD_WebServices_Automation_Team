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

    @Description("PMR_01 - Meal Report Preliminary")
    @Test
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

    @Description("PMR_03 - Meal Report Invalid Flight Number")
    @Test
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

    @Description("PMR_02 - Meal Report Final")
    @Test
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
