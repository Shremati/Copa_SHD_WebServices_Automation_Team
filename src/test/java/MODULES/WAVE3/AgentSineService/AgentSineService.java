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

    @Description("AGS_03 - Add Agent Sine")
    @Test
    public void AGS_03()
    {
        try
        {
            Add_Agent_Sine.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AGS_03 failed due to :"+e);
        }
    }

    @Description("AGS_05 - Add Agent Duty Code")
    @Test
    public void AGS_05()
    {
        try
        {
            Add_Agent_Duty_Code.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AGS_05 failed due to :"+e);
        }
    }

    @Description("AGS_01 - Add Sine Display CST")
    @Test
    public void AGS_01()
    {
        try
        {
            Agent_Sine_Display_CST.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AGS_01 failed due to :"+e);
        }
    }

    @Description("AGS_02 - Agent Sine Display CST Increased Security Users")
    @Test
    public void AGS_02()
    {
        try
        {
            Agent_Sine_DisplayCST_Increased_Security_Users.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AGS_02 failed due to :"+e);
        }
    }

    @Description("AGS_06 - Remove Agent Duty Code")
    @Test
    public void AGS_06()
    {
        try
        {
            Remove_Agent_Duty_Code.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AGS_06 failed due to :"+e);
        }
    }

    @Description("AGS_04 - Remove Agent Sine")
    @Test
    //Once, agent sine is removed, run Scenario 1(Add Agent Sine), once again
    public void AGS_04()
    {
        try
        {
            Remove_Agent_Sine.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("AGS_04 failed due to :"+e);
        }
    }
}