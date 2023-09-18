package MODULES.WAVE3.ReferenceService;

import MODULES.WAVE3.ReferenceService.API_Tests.Display_Category_List;
import MODULES.WAVE3.ReferenceService.API_Tests.Display_Page_Data;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//2 Scenarios
public class ReferenceService {
    ReferenceService() {
        createFolders(getResponseDirectory() + "ReferenceService");
    }

    @Description("RS_01 - Display Category list")
    @Test
    public void RS_01() {
        try {
            Display_Category_List.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_01 failed due to :" + e);
        }

    }

    @Description("RS_06 - Display Page Data")
    @Test
    public void RS_06() {

        try {

            Display_Page_Data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_06 failed due to :" + e);
        }
    }
}
