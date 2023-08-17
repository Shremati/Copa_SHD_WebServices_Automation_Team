package MODULES.WAVE3.DepartureControlDisplay;

import MODULES.WAVE3.ConnectionFlightInfo.API_Tests.Get_inbound_flight_info;
import MODULES.WAVE3.DepartureControlDisplay.API_Tests.Get_flight_history_info;
import MODULES.WAVE3.DepartureControlDisplay.API_Tests.Get_seated_passenger_count;
import MODULES.WAVE3.DepartureControlDisplay.API_Tests.Get_summary_map_info;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class DepartureControlDisplay {

    DepartureControlDisplay()
    {
        createFolders(getResponseDirectory()+"DepartureControlDisplay");
    }

    @Description("Get Flight History Info")
    @Test
    public void Scenario1()
    {
        try
        {
            Get_flight_history_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Get Seated Passenger Count")
    @Test
    public void Scenario2()
    {
        try
        {
            Get_seated_passenger_count.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }

    @Description("Get Summary Map Info")
    @Test
    public void Scenario3()
    {
        try
        {
            Get_summary_map_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }


}
