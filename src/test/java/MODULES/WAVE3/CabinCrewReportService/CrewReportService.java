package MODULES.WAVE3.CabinCrewReportService;

import MODULES.WAVE3.CabinCrewReportService.API_Tests.Crew_report_service_FR;
import MODULES.WAVE3.CabinCrewReportService.API_Tests.Crew_report_service_PR;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.failTest;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class CrewReportService {

    CrewReportService()
    {
        createFolders(getResponseDirectory()+"CrewReportService");
    }

    @Description("Crew Report Service FR")
    @Test
    public void Scenario1()
    {
        try
        {
            Crew_report_service_FR.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Crew Report Service PR")
    @Test
    public void Scenario2()
    {
        try
        {
            Crew_report_service_PR.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }

}
