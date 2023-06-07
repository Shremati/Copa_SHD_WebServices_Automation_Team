package MODULES.WAVE3.Checkin;

import MODULES.WAVE3.Checkin.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class Checkin
{

    @Description(" Check-in 1 passenger, 1 Baggage")
    @Test
    public void Scenario1()
    {

        try
        {
          checkin_one_pax_and_baggage.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("Display API requirements for the passengers as single surname-multiple names in booking")
    @Test
    public void Scenario2()
    {

        try
        {
            Error_Change_seatInvalid.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("DisplayAPI error - Invalid passenger name")
    @Test
    public void Scenario3()
    {

        try
        {
            Hold_seat_from_similar_name_list.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }
    @Description("Display API requirements in a single request for passengers in different bookings")
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

}

//properties file and hashmaps