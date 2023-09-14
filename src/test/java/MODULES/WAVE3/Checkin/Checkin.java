package MODULES.WAVE3.Checkin;

import MODULES.WAVE3.Checkin.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

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

    @Description("CS_01 - Check-in 1 passenger, 1 Baggage")
    @Test
    public void Scenario1()
    {
        try
        {
            checkin_one_pax_and_baggage.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("CS_34 - Error Change seat - Invalid seat (Negative Scenario)")
    @Test
    public void Scenario2()
    {
        try
        {
            Error_Change_seatInvalid.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }


    @Description("CS_36 - Hold seat from similar name list")

    @Test
    public void Scenario3()
    {
        try
        {
            Hold_seat_from_similar_name_list.Execute();
        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }
    @Description("CS_17 - Check-in Non-Revenue passenger")
    @Test
    public void Scenario4()
    {
        try
        {

            check_in_non_revenue_pax.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :"+e);
        }

    }


    @Description("CS_20 - Error Check-in - Invalid passenger")
    @Test
    public void Scenario5()
    {

        try
        {
            Error_check_in_invalid_pax.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :"+e);
        }

    }


    @Description("CS_21 - Check-in  a specific passenger in a group")
    @Test
    public void Scenario6()
    {

        try
        {
            Check_in_specific_pax_in_group.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :"+e);
        }

    }

    @Description("CS_26 - Thru-CheckIn 2 passengers in same PNR, same name and generic seating option")
    @Test
    public void Scenario7()
    {

        try
        {
            Thru_CheckIn_2_passengers_in_same_PNR.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :"+e);
        }

    }

    @Description("CS_07 - Checkin_Frequent Flyer")
    @Test
    public void Scenario8()
    {

        try
        {
            Checkin_FF_Pax.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :"+e);
        }

    }

    @Description("CS_12 - Check-in 3 passengers (under same surname) and specific seating option ")
    @Test
    public void Scenario9()
    {

        try
        {
            Checkin_3_pax_and_specific_seating_option.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :"+e);
        }

    }

    @Description("CS_39 - Cancel held seat")
    @Test
    public void Scenario10()
    {

        try
        {
            Cancel_held_seat.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :"+e);
        }

    }

    @Description("CS_31 - Basic seat change")
    @Test
    public void Scenario11()
    {

        try
        {
            Basic_seat_change.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :"+e);
        }

    }

    @Description("CS_18 - Checkin after add passenger message")
    @Test
    public void Scenario12()
    {

        try
        {
            Check_in_after_add_passenger_message.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :"+e);
        }

    }
}

