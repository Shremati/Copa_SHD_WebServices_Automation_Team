package MODULES.WAVE3.ScreenTextService;


import MODULES.WAVE3.ScreenTextService.API_Tests.Error_screentext_not_allowed_entry;
import MODULES.WAVE3.ScreenTextService.API_Tests.Send_entry;
import MODULES.WAVE3.ScreenTextService.API_Tests.error_screenText_missing_field;
import MODULES.WAVE3.ScreenTextService.API_Tests.stateful_screenText_send_entry;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//2 Scenarios
public class ScreenTextService {

    ScreenTextService() {
        createFolders(getResponseDirectory() + "ScreenTextService");
    }

    @Description("ST_01 - Send entry")
    @Test
    public void ST_01() {

        try {
            Send_entry.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ST_01 failed due to :" + e);
        }
    }

    @Description("ST_02 - Error ScreenText - Not allowed entry")
    @Test
    public void ST_02() {

        try {
            Error_screentext_not_allowed_entry.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ST_02 failed due to :" + e);
        }
    }

    @Description("ST_03 - Error ScreenText - Missing field")
    @Test
    public void ST_03() {

        try {
            error_screenText_missing_field.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ST_03 failed due to :" + e);
        }
    }


    @Description("ST_04 - Stateful ScreenText")
    @Test
    public void ST_04() {

        try {
            stateful_screenText_send_entry.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ST_04 failed due to :" + e);
        }
    }

}
