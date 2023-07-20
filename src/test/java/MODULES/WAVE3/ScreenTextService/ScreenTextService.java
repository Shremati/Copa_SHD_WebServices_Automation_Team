package MODULES.WAVE3.ScreenTextService;


import MODULES.WAVE3.ScreenTextService.API_Tests.Error_screentext_not_allowed_entry;
import MODULES.WAVE3.ScreenTextService.API_Tests.Send_entry;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ScreenTextService {

    ScreenTextService() {
        createFolders(getResponseDirectory() + "ScreenTextService");
    }

    @Description("Send entry")
    @Test
    public void Scenario1() {

        try {
            Send_entry.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Error ScreenText - Not allowed entry")
    @Test
    public void Scenario2() {

        try {
            Error_screentext_not_allowed_entry.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }
}
