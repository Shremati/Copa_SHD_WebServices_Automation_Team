package MODULES.WAVE3.SynchronizeTicketService;

import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Class;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Flight_No;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Name;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class SynchronizeTicketService
{

    @Description("Adjust_Flight_No")
    @Test
    public void Scenario1()
    {

        try
        {
            Adjust_Flight_No.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }



    }
    @Description("Adjust_Name")
    @Test
    public void Scenario2()
    {

        try
        {
            Adjust_Name.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }

    }
    @Description("Adjust_Class")
    @Test
    public void Scenario3()
    {

        try
        {
            Adjust_Class.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

}

