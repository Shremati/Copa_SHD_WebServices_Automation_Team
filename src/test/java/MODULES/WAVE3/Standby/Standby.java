package MODULES.WAVE3.Standby;


import MODULES.WAVE3.Standby.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class Standby {

    Standby()
    {
        createFolders(getResponseDirectory()+"Standby");
    }

    @Description("Start Standby")
    @Test
    public void Scenario1()
    {
        try
        {
            STB_01_Start_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Enable Standby")
    @Test
    public void Scenario2()
    {
        try
        {
            STB_02_Enable_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }

    @Description("Clear Standby")
    @Test
    public void Scenario3()
    {
        try
        {
            STB_03_Clear_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

    @Description("ClearAll Standby")
    @Test
    public void Scenario4()
    {
        try
        {
            STB_04_ClearAll_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :"+e);
        }
    }

    @Description("Release Held Seats")
    @Test
    public void Scenario5()
    {
        try
        {
            STB_05_Release_Held_Seats.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :"+e);
        }
    }

    @Description("Release Advance Seats")
    @Test
    public void Scenario6()
    {
        try
        {
            STB_06_Release_Advance_Seats.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :"+e);
        }
    }

    @Description("Advance Seats Class Specific")
    @Test
    public void Scenario7()
    {
        try
        {
            STB_07_Advance_Seats_Class_Specific.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :"+e);
        }
    }

    @Description("Standby Transfer")
    @Test
    public void Scenario8()
    {
        try
        {
            STB_08_Standby_Transfer.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :"+e);
        }
    }

    @Description("Cancel Immediate Standby")
    @Test
    public void Scenario9()
    {
        try
        {
            STB_10_Cancel_Immediate_Standby.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :"+e);
        }
    }


}
