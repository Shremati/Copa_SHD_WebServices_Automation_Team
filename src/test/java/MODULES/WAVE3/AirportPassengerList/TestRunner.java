package MODULES.WAVE3.AirportPassengerList;

import io.qameta.allure.Description;
import MODULES.WAVE3.AirportPassengerList.API_Tests.*;
import org.testng.annotations.Test;

public class TestRunner
{

    @Description("Standard list: Code 2 - Eticketed passengers")
    @Test
    public void Scenario1()
    {

        try
        {
            Code_2_eticketed_passengers.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("Standard list: Code 5 - Interline eticket passengers")
    @Test
    public void Scenario2()
    {

        try
        {
             Code_5_Interline_eticket_passengers.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("Standard list: Code 12 - Passengers with advance seats")
    @Test
    public void Scenario3()
    {

        try
        {
            Code_12_Passengers_with_advance_seats.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }
    @Description("Standard list: Code 25 - passengers with held seats")
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
    @Description("Custom  list: Code 0  - All passengers,  Response Data = Code 1, passenger name")
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
    @Description("Custom  list: Code 0  - All passengers,  Response Data = Code 5, passenger service info")
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

}

//properties file and hashmaps