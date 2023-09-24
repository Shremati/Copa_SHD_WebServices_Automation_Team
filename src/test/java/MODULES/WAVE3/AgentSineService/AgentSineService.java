package MODULES.WAVE3.AgentSineService;

import MODULES.WAVE3.AgentSineService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;
import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AgentSineService {

    AgentSineService()
    {
        createFolders(getResponseDirectory()+"AgentSineService");
    }

    @Description("Add Agent Sine")
    @Test
    public void Scenario1()
    {
        try
        {
            Add_Agent_Sine.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Add Agent Duty Code")
    @Test
    public void Scenario2()
    {
        try
        {
            Add_Agent_Duty_Code.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }

    @Description("Add Sine Display CST")
    @Test
    public void Scenario3()
    {
        try
        {
            Agent_Sine_Display_CST.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

    @Description("Agent Sine Display CST Increased Security Users")
    @Test
    public void Scenario4()
    {
        try
        {
            Agent_Sine_DisplayCST_Increased_Security_Users.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :"+e);
        }
    }

    @Description("Remove Agent Duty Code")
    @Test
    public void Scenario5()
    {
        try
        {
            Remove_Agent_Duty_Code.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :"+e);
        }
    }

    @Description("Remove Agent Sine")
    @Test
    //Once, agent sine is removed, run Scenario 1(Add Agent Sine), once again
    public void Scenario6()
    {
        try
        {
            Remove_Agent_Sine.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :"+e);
        }
    }
}
