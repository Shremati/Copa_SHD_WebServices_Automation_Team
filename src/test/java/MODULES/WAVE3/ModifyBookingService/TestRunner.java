package MODULES.WAVE3.ModifyBookingService;

import MODULES.WAVE3.ModifyBookingService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestRunner
{

    @Description("Cancel Booking")
    @Test
    public void Scenario1()
    {

        try
        {
            cancel_booking.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }

//    @Description("Itinerary Changes")
//    @Test
//    public void Scenario2()
//    {
//
//        try
//        {
//            Itinerary_changes.Execute();
//
//        }catch(Exception e)
//        {
//            System.out.println("SCENARIO 1 failed due to :"+e);
//        }
//
//
//
//    }

    @Description("Other Changes")
    @Test
    public void Scenario3() {

        try {
            other_changes.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }


    @Description("Divide PNR")
    @Test
    public void Scenario4() {

        try {
            divide_pnr.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }

    @Description("Divide PNR")
    @Test
    public void Scenario5() {

        try {
            reduce_pnr.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 1 failed due to :" + e);
        }

    }


    }

//properties file and hashmaps