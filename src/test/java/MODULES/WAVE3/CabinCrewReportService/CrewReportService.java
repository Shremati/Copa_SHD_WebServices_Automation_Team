package MODULES.WAVE3.CabinCrewReportService;

import MODULES.WAVE3.CabinCrewReportService.API_Tests.Crew_report_service_FR;
import MODULES.WAVE3.CabinCrewReportService.API_Tests.Crew_report_service_PR;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static GENERICS.Utils.failTest;
import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;
@Listeners(TestListener.class)
public class CrewReportService {

    CrewReportService()
    {
        createFolders(getResponseDirectory()+"CrewReportService");
    }

    @Test(description = "Crew_report1 - Crew Report Service FR")
    public void Crew_report1()
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

    @Test(description = "Crew_report2 - Crew Report Service PR")
    public void Crew_report2()
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
