package MODULES.WAVE3.ModifySeatMap;

import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_01_Block_Seat;
import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_02_Add_Seat;
import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_03_Delete_Error;
import MODULES.WAVE3.ModifySeatMap.API_Tests.MSM_04_Add_Error;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;

//4 scenarios
@Listeners(TestListener.class)
public class ModifySeatMap extends FrameworkConstants {

    ModifySeatMap()
    {
        createFolders(getResponseDirectory()+"ModifySeatMap");
    }

    @Test(description = "MSM_01 - Delete/Block seat", priority = 1)
    public void MSM_01()
    {
        try
        {
            MSM_01_Block_Seat.Execute(); //The given seat should be available and Ship should be assigned for the given flight

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("MSM_01 failed due to :"+e);
        }
    }

    @Test(description = "MSM_02 - Add/Unblock Seat",priority = 2)
    public void MSM_02()
    {
        try
        {
            MSM_02_Add_Seat.Execute(); // Use the same details(including seat number) as used in MSM_01

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("MSM_02 failed due to :"+e);
        }
    }

    @Test(description = "MSM_03 - Delete/Block Seat Error Scenario",priority = 3)
    public void MSM_03()
    {
        try
        {
            MSM_03_Delete_Error.Execute(); //The given seat should be taken and Ship should be assigned for the given flight

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("MSM_03 failed due to :"+e);
        }
    }

    @Test(description = "MSM_04 - Add/Unblock Seat Error Scenario",priority = 4)
    public void MSM_04()
    {
        try
        {
            MSM_04_Add_Error.Execute(); //The given seat should be taken and Ship should be assigned for the given flight

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("MSM_04 failed due to :"+e);
        }
    }

}
