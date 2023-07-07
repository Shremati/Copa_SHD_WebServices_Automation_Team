package MODULES.WAVE3.AirInventoryService;

import io.qameta.allure.Description;
import MODULES.WAVE3.AirInventoryService.API_Tests.*;
import org.testng.annotations.Test;

public class AirInventoryService
{

    @Description("Host Airline Inventory Request")
    @Test
    public void Scenario1()
    {

        try
        {
            Host_Airline_Inventory_Request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("HA Inventory request with a date in the past")
    @Test
    public void Scenario2()
    {

        try
        {
            HA_Inventory_request_with_a_date_in_the_past.Execute();  //Negative Scenario

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("More than 7 HA Inventory requests at a time")
    @Test
    public void Scenario3()
    {

        try
        {
           More_than_7_HA_Inventory_requests_at_a_time.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

}

//properties file and hashmaps