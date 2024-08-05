package MODULES.WAVE3.Checkin;

import MODULES.WAVE3.Checkin.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;
@Listeners(TestListener.class)
public class Checkin
{
    //Note: If we execute a particular scenario multiple times,
    // multiple PNRs will be created with the same name, and the below warning is shown
    // <Warning Type="12" Tag="" RecordID="1">Multiple similar names match your request.</Warning>
   //If we want to execute multiple times, same scenario, then, either we can change pax name, or flt details

    Checkin()
    {
        createFolders(getResponseDirectory()+"Checkin");
    }

    @Test(description = "CS_01 - Check-in 1 passenger, 1 Baggage")
    public void CS_01()
    {
        try
        {
            checkin_one_pax_and_baggage.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_01 failed due to :"+e);
        }
    }

    @Test(description = "CS_34 - Error Change seat - Invalid seat (Negative Scenario)")
    public void CS_34()
    {
        try
        {
            Error_Change_seatInvalid.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_34 failed due to :"+e);
        }
    }


    @Test(description = "CS_36 - Hold seat from similar name list")
    public void CS_36()
    {
        try
        {
            Hold_seat_from_similar_name_list.Execute();
        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_36 failed due to :"+e);
        }
    }
    @Test(description = "CS_17 - Check-in Non-Revenue passenger")
    public void CS_17()
    {
        try
        {

            check_in_non_revenue_pax.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_17 failed due to :"+e);
        }

    }


    @Test(description = "CS_20 - Error Check-in - Invalid passenger")
    public void CS_20()
    {
        try
        {
            Error_check_in_invalid_pax.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_20 failed due to :"+e);
        }

    }


    @Test(description = "CS_21 - Check-in  a specific passenger in a group")
    public void CS_21()
    {

        try
        {
            Check_in_specific_pax_in_group.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_21 failed due to :"+e);
        }

    }

    @Test(description = "CS_26 - Thru-CheckIn 2 passengers in same PNR, same name and generic seating option")
    public void CS_26()
    {

        try
        {
            Thru_CheckIn_2_passengers_in_same_PNR.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_26 failed due to :"+e);
        }

    }

    @Test(description = "CS_07 - Checkin_Frequent Flyer")
    public void CS_07()
    {

        try
        {
            Checkin_FF_Pax.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_07 failed due to :"+e);
        }

    }

    @Test(description = "CS_12 - Check-in 3 passengers (under same surname) and specific seating option ")
    public void CS_12()
    {

        try
        {
            Checkin_3_pax_and_specific_seating_option.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_12 failed due to :"+e);
        }

    }

    @Test(description = "CS_39 - Cancel held seat")
    public void CS_39()
    {

        try
        {
            Cancel_held_seat.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_39 failed due to :"+e);
        }

    }

    @Test(description = "CS_31 - Basic seat change")
    public void CS_31()
    {

        try
        {
            Basic_seat_change.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_31 failed due to :"+e);
        }

    }

    @Test(description = "CS_18 - Checkin after add passenger message")
    public void CS_18()
    {

        try
        {
            Check_in_after_add_passenger_message.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("CS_18 failed due to :"+e);
        }

    }
}

