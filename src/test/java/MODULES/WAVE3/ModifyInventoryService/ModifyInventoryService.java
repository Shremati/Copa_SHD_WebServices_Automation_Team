package MODULES.WAVE3.ModifyInventoryService;

import MODULES.WAVE3.ModifyInventoryService.API_Tests.ModifyInventory_Request_contains_single_Authorization_level;
import MODULES.WAVE3.ModifyInventoryService.API_Tests.ModifyInventory_Request_contains_single_MaxSeatsAllotted;
import MODULES.WAVE3.ModifyInventoryService.API_Tests.ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

@Listeners(TestListener.class)
public class ModifyInventoryService {

    ModifyInventoryService() {
        createFolders(getResponseDirectory() + "ModifyInventoryService");
    }

    @Test(description = "MIS_01 - ModifyInventory Request contains single Authorization level")
    public void MIS_01() {
        try {
            ModifyInventory_Request_contains_single_Authorization_level.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MIS_01 failed due to :" + e);
        }


    }

    @Test(description = "MIS_17 - ModifyInventory Request contains single MaxSeatsAllotted")
    public void MIS_17() {

        try {
            ModifyInventory_Request_contains_single_MaxSeatsAllotted.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MIS_17 failed due to :" + e);
        }

    }

    @Test(description = "MIS_24 - ModifyInventory Request with AuthorizationLevel and MaxSeatsAllotted for CM carrier")
    public void MIS_24() {

        try {
            ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MIS_24 failed due to :" + e);
        }
    }
}
