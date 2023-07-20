package MODULES.WAVE3.ModifyInventoryService;

import MODULES.WAVE3.ModifyInventoryService.API_Tests.ModifyInventory_Request_contains_single_Authorization_level;
import MODULES.WAVE3.ModifyInventoryService.API_Tests.ModifyInventory_Request_contains_single_MaxSeatsAllotted;
import MODULES.WAVE3.ModifyInventoryService.API_Tests.ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ModifyInventoryService {

    ModifyInventoryService() {
        createFolders(getResponseDirectory() + "ModifyInventoryService");
    }

    @Description("ModifyInventory Request contains single Authorization level")
    @Test
    public void Scenario1() {
        try {
            ModifyInventory_Request_contains_single_Authorization_level.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }


    }

    @Description("ModifyInventory Request contains single MaxSeatsAllotted")
    @Test
    public void Scenario2() {

        try {
            ModifyInventory_Request_contains_single_MaxSeatsAllotted.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }

    }

    @Description("ModifyInventory Request with AuthorizationLevel and MaxSeatsAllotted for CM carrier")
    @Test
    public void Scenario3() {

        try {
            ModifyInventory_Request_with_AuthorizationLevel_and_MaxSeatsAllotted_for_CM_carrier.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }
}
