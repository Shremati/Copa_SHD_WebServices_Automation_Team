package MODULES.WAVE3.ConnectionFlightInfo;

import GENERICS.FlightBooking;
import MODULES.WAVE3.Checkin.API_Tests.Error_Change_seatInvalid;
import MODULES.WAVE3.Checkin.API_Tests.checkin_one_pax_and_baggage;
import MODULES.WAVE3.ConnectionFlightInfo.API_Tests.Get_inbound_flight_info;
import MODULES.WAVE3.ConnectionFlightInfo.API_Tests.Get_outbound_flight_info;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;
@Listeners(TestListener.class)
public class ConnectionFlightInfo {

//    ConnectionFlightInfo()
//    {
//        createFolders(getResponseDirectory()+"ConnectionFlightInfo");
//    }

    ConnectionFlightInfo() throws IOException
    {
        createFolders(getResponseDirectory() + "ConnectionFlightInfo");
       FlightBooking.bookFlight("ConnectionFlightInfo");
    }

    @Test(description = "CFI_01 - Get Inbound Flight Info")
    public void CFI_01()
    {
        try
        {
            Get_inbound_flight_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CFI_01 failed due to :"+e);
        }
    }

    @Test(description = "CFI_02 - Get Outbound Flight Info")
    public void CFI_02()
    {
        try
        {
            Get_outbound_flight_info.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CFI_02 failed due to :"+e);
        }
    }
}