package MODULES.WAVE3.Boarding;

import io.qameta.allure.Description;
import MODULES.WAVE3.Boarding.API_Tests.*;
import org.testng.annotations.Test;

public class Boarding
{

    @Description("Start Boarding using Boarding option as ‘Sequence’")
    @Test
    public void Scenario1()
    {
           //change flt number or date before run
        try
        {
            Start_Boarding_using_Boarding_option_as_sequence.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("Input with more PassengerFlightInfo information with Function as BoardPassenger, BoardingOption as ‘Seat’ for a flight number")
    @Test
    public void Scenario2()
    {

        try
        {

              Input_with_more_PassengerFlightInfo.Execute();  //Negative Scenario

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("Start CancelBoardedPassenger function with BoardingOption as ‘Seat’")
    @Test
    public void Scenario3()
    {

        try
        {
           Start_CancelBoardedPassenger_function.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

    @Description("AbortBoarding for a particular flight")
    @Test
    public void Scenario4()
    {

        try
        {
              AbortBoarding_for_a_particular_flight.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 5 failed due to :"+e);
        }
    }
    @Description("Start BoardingComplete function for a flight number")
    @Test
    public void Scenario5()
    {

        try
        {
              Start_BoardingComplete_function.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 5 failed due to :"+e);
        }
    }

}

//properties file and hashmaps