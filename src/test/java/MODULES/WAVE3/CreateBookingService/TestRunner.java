package MODULES.WAVE3.CreateBookingService;

import MODULES.WAVE3.CreateBookingService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestRunner
{

    @Description(" Create a booking with 1 segment, 1 passenger, stored fare, 1 telephone and ticketing")
    @Test
    public void Scenario1()
    {

        try
        {
          create_booking_one_seg_telephone_ticketing.Execute();  //FQTV case, after sometimes it might get outdated

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("Create a booking with 4 segments (HA), 2 passengers (1 frequent flyer and 1 infant), 1 email, stored fare, 1 OSI, 1 remark, Advance Seat Assignment and ticketing.")
    @Test
    public void Scenario2()
    {

        try
        {
          create_booking_four_seg_two_pax_two_SSR.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("Check for invalid Ticketing in request")
    @Test
    public void Scenario3()
    {

        try
        {
            create_booking_four_seg_2_pax_one_remark_asa.Execute(); //It also contains FQTV, so update on timely basis

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }
    @Description("Add a default time limit when no data is specified in Ticketing.")
    @Test
    public void Scenario4()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 4 failed due to :"+e);
        }

    }

    @Description("Create a booking with 1 segment, 1 passenger, stored fare (1 fare basis code, base fare, not valid before/after date, fare calculation line, bagagge allowance, sale location, 1 free-flow remark, tour code and form of payment CASH) and time limit")
    @Test
    public void Scenario5()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 5 failed due to :"+e);
        }

    }
    @Description(" Create a booking with 2 segments, 2 passengers, stored fare (2 fare basis code, base fare, not valid before/after date, fare calculation line, 2 bagagge allowance, sale location, 3 free-flow remark, tour code, 2 endorsements, last ticketing date, bankers rate and form of payment CHECK) and ticketing")
    @Test
    public void Scenario6()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 6 failed due to :"+e);
        }

    }
    @Description("Create a booking with 2 segments, 2 passengers, stored fare per passenger (2 fare basis code, base fare, not valid before/after date, fare calculation line, 2 bagagge allowance, sale location, 1 free-flow remark, tour code, 2 endorsements, last ticketing date, original origin/destination city, bankers rate, original issue and form of payment Credit Card) and ticketing")
    @Test
    public void Scenario7()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 7 failed due to :"+e);
        }

    }

    @Description("Stored fare - Ticketing item: Invalid bagagge allowance.")
    @Test
    public void Scenario8()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 8 failed due to :"+e);
        }

    }
    @Description("Waitlist booking (action code - LL)")
    @Test
    public void Scenario9()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 9 failed due to :"+e);
        }

    }
    @Description("Group booking")
    @Test
    public void Scenario10()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 10 failed due to :"+e);
        }

    }
    @Description(" Special passenger type - Non Revenue Space Available staff travel")
    @Test
    public void Scenario11()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 11 failed due to :"+e);
        }

    }
    @Description("Special passenger type - Jumpseat")
    @Test
    public void Scenario12()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 12 failed due to :"+e);
        }

    }

    @Description("Passengers with frequent traveler number for reward redemption (SSR FQTR) and name remark")
    @Test
    public void Scenario13()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 13 failed due to :"+e);
        }

    }
    @Description("Add the Form Of Payment remark and the FOP in the stored fare")
    @Test
    public void Scenario14()
    {

        try
        {


        }catch(Exception e)
        {
            System.out.println("SCENARIO 14 failed due to :"+e);
        }

    }


}

//properties file and hashmaps