package MODULES.WAVE3.ConnectionFlightInfo;

import MODULES.WAVE3.Checkin.API_Tests.Error_Change_seatInvalid;
import MODULES.WAVE3.Checkin.API_Tests.checkin_one_pax_and_baggage;
import MODULES.WAVE3.ConnectionFlightInfo.API_Tests.Get_inbound_flight_info;
import MODULES.WAVE3.ConnectionFlightInfo.API_Tests.Get_outbound_flight_info;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ConnectionFlightInfo {

    ConnectionFlightInfo()
    {
        createFolders(getResponseDirectory()+"Checkin");
    }

    @Description("Get Inbound Flight Info")
    @Test
    public void Scenario1()
    {
        try
        {
            Get_inbound_flight_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Get Outbound Flight Info")
    @Test
    public void Scenario2()
    {
        try
        {
            Get_outbound_flight_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }
}
