package MODULES.WAVE3.Standby;


import GENERICS.FlightBooking;
import MODULES.WAVE3.Standby.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//9 Scenarios
@Listeners(TestListener.class)
public class Standby {

    Standby() throws IOException {
        createFolders(getResponseDirectory()+"Standby");
        FlightBooking.bookFlight("Standby");
    }

    @Test(description = "STB_01 - Start Standby")
    public void STB_01()
    {
        try
        {
            STB_01_Start_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_01 failed due to :"+e);
        }
    }

    @Test(description = "STB_02 - Enable Standby")
    public void STB_02()
    {
        try
        {
            STB_02_Enable_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_02 failed due to :"+e);
        }
    }

    @Test(description = "STB_03 - Clear Standby")
    //We are removing the pax from standby
    //Normal pax cant go to standby checkin, we need to do Weight Balance Restriction in checkin
    //We used Non-revenue pax(NRPS), because they by default go to standby
    public void STB_03()
    {
        try
        {
            STB_03_Clear_Standby.Execute();//Standby in Gate Should not be initiated for the flight given in the request. So choose appropriate flight in data sheet

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_03 failed due to :"+e);
        }
    }

    @Test(description = "STB_04 - ClearAll Standby")
    //We need to keep the countries same for the markets, Eg.: PTY-LAX, PTY-MIA, so that agency requirements dont change
    //Make sure that the flt has not initiated standby
    public void STB_04()
    {
        try
        {
            STB_04_ClearAll_Standby.Execute(); //Standby in Gate Should not be initiated for the flight given in the request. So choose appropriate flight in data sheet

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_04 failed due to :"+e);
        }
    }

    @Test(description = "STB_05 - Release Held Seats")
    public void STB_05()
    {
        try
        {
            STB_05_Release_Held_Seats.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_05 failed due to :"+e);
        }
    }

    @Test(description = "STB_06 - Release Advance Seats")
    public void STB_06()
    {
        try
        {
            STB_06_Release_Advance_Seats.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_06 failed due to :"+e);
        }
    }

    @Test(description = "STB_07 - Release Advance Seats Class Specific")
    public void STB_07()
    {
        try
        {
            STB_07_Advance_Seats_Class_Specific.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_07 failed due to :"+e);
        }
    }

    @Test(description = "STB_08 - Standby Transfer")
    public void STB_08()
    {
        try
        {
            STB_08_Standby_Transfer.Execute(); //Mass Transfer Scenario. Both flights should be from same markets.

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_08 failed due to :"+e);
        }
    }

    @Test(description = "STB_10 - Cancel Immediate Standby Boarding")
    public void STB_10()
    {
        try
        {
            STB_10_Cancel_Immediate_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STB_10 failed due to :"+e);
        }
    }


}
