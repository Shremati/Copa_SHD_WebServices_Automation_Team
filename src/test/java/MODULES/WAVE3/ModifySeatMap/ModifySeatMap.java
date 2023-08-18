package MODULES.WAVE3.ModifySeatMap;

import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_01_Block_Seat;
import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_02_Add_Seat;
import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_03_Delete_Error;
import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_04_Add_Error;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;

public class ModifySeatMap extends FrameworkConstants {

    ModifySeatMap()
    {
        createFolders(getResponseDirectory()+"ModifySeatMap");
    }

    @Description("Block Seat")
    @Test(priority = 1)
    public void Scenario1()
    {
        try
        {
            MSM_01_Block_Seat.Execute(); //The given seat should be available

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :"+e);
        }
    }

    @Description("Add Seat")
    @Test(priority = 2)
    public void Scenario2()
    {
        try
        {
            MSM_02_Add_Seat.Execute(); //The given seat should be available

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :"+e);
        }
    }

    @Description("Delete Error")
    @Test(priority = 3)
    public void Scenario3()
    {
        try
        {
            MSM_03_Delete_Error.Execute(); //The given seat should be available

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :"+e);
        }
    }

    @Description("Add Error")
    @Test(priority = 4)
    public void Scenario4()
    {
        try
        {
            MSM_04_Add_Error.Execute(); //The given seat should be available

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :"+e);
        }
    }

}
