package MODULES.WAVE3.AdvancePassengerInfo;

import io.qameta.allure.Description;
import MODULES.WAVE3.AdvancePassengerInfo.API_Tests.*;
import org.testng.annotations.Test;

public class TestRunner
{

    @Description("Display API requirements for a single passenger in booking.")
    @Test
    public void Scenario1()
    {

        try
        {
            Single_Pax.Execute();

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
            Single_surname_multiple_names.Execute();

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
            DisplayAPI_error_Invalid_passenger_name.Execute();  //Negative Scenario

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
            Passengers_in_different_booking_in_single_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 4 failed due to :"+e);
        }

    }

}

//properties file and hashmaps