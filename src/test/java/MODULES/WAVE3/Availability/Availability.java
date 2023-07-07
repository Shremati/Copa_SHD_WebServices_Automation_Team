package MODULES.WAVE3.Availability;

import io.qameta.allure.Description;
import MODULES.WAVE3.Availability.API_Tests.*;
import org.testng.annotations.Test;

public class Availability
{

    @Description("Empty OriginDestinationInformation")
    @Test
    public void Scenario1()
    {

        try
        {
            Empty_OriginDestinationInformation.Execute();  //Negative Scenario

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("Regular availability with defaults")
    @Test
    public void Scenario2()
    {

        try
        {
            Regular_availability_with_defaults.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("Reward availability")
    @Test
    public void Scenario3()
    {

        try
        {
            Reward_availability.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

}

//properties file and hashmaps