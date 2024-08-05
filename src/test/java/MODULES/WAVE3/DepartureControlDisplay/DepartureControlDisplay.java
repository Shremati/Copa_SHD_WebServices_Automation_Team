package MODULES.WAVE3.DepartureControlDisplay;

import MODULES.WAVE3.ConnectionFlightInfo.API_Tests.Get_inbound_flight_info;
import MODULES.WAVE3.DepartureControlDisplay.API_Tests.Get_flight_history_info;
import MODULES.WAVE3.DepartureControlDisplay.API_Tests.Get_seated_passenger_count;
import MODULES.WAVE3.DepartureControlDisplay.API_Tests.Get_summary_map_info;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 Scenarios
@Listeners(TestListener.class)
public class DepartureControlDisplay {

    DepartureControlDisplay()
    {
        createFolders(getResponseDirectory()+"DepartureControlDisplay");
    }

    @Test(description = "DCD_03 - Get Flight History Info")
    public void DCD_03()
    {
        try
        {
            Get_flight_history_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("DCD_03 failed due to :"+e);
        }
    }

    @Test(description = "DCD_02 - Get Seated Passenger Count")
    public void DCD_02()
    {
        try
        {
            Get_seated_passenger_count.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("DCD_02 failed due to :"+e);
        }
    }

    @Test(description = "DCD_01 - Get Summary Map Info")
    public void DCD_01()
    {
        try
        {
            Get_summary_map_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("DCD_01 failed due to :"+e);
        }
    }


}
