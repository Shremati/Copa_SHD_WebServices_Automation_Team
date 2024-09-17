package MODULES.WAVE3.AgentSineService;

import MODULES.WAVE3.AgentSineService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

@Listeners(TestListener.class)
public class AgentSineService {

    AgentSineService()
    {
        createFolders(getResponseDirectory()+"AgentSineService");
    }

    @Test(description = "AS_03 - Add Agent Sine")
    public void AS_03()
    {
        try
        {
            Add_Agent_Sine.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AS_03 failed due to :"+e);
        }
    }

    @Test(description = "AS_05 - Add Agent Duty Code")
    public void AS_05()
    {
        try
        {
            Add_Agent_Duty_Code.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AS_05 failed due to :"+e);
        }
    }

    @Test(description = "AS_01 - Add Sine Display CST")
    public void AS_01()
    {
        try
        {
            Agent_Sine_Display_CST.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AS_01 failed due to :"+e);
        }
    }

    @Test(description = "AS_02 - Agent Sine Display CST Increased Security Users")
    public void AS_02()
    {
        try
        {
            Agent_Sine_DisplayCST_Increased_Security_Users.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AS_02 failed due to :"+e);
        }
    }

    @Test(description = "AS_06 - Remove Agent Duty Code")
    public void AS_06()
    {
        try
        {
            Remove_Agent_Duty_Code.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AS_06 failed due to :"+e);
        }
    }

    @Test(description = "AS_04 - Remove Agent Sine")
    //Once, agent sine is removed, run Scenario 1(Add Agent Sine), once again
    public void AS_04()
    {
        try
        {
            Remove_Agent_Sine.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AS_04 failed due to :"+e);
        }
    }
}