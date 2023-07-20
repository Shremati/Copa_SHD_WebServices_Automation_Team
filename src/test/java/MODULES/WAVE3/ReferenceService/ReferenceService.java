package MODULES.WAVE3.ReferenceService;

import MODULES.WAVE3.ReferenceService.API_Tests.Display_Category_List;
import MODULES.WAVE3.ReferenceService.API_Tests.Display_Page_Data;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ReferenceService {

    ReferenceService() {
        createFolders(getResponseDirectory() + "ReferenceService");
    }

    @Description("Display Category list")
    @Test
    public void Scenario1() {
        try {
            Display_Category_List.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }

    }

    @Description("Display Category list")
    @Test
    public void Scenario2() {

        try {
            Display_Page_Data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }
}
