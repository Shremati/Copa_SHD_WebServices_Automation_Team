package MODULES.WAVE3.ReferenceService;

import MODULES.WAVE3.ReferenceService.API_Tests.*;
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

    @Description("RS_02 - Display Subject list")
    @Test
    public void RS_02() {

        try {

            display_subject_list.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_02 failed due to :" + e);
        }
    }

    @Description("RS_03 - Display Page list")
    @Test
    public void RS_03() {

        try {

            display_page_list.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_03 failed due to :" + e);
        }
    }

    @Description("RS_04 - Display data page")
    @Test
    public void RS_04() {

        try {

            display_data_page.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_04 failed due to :" + e);
        }
    }


    @Description("RS_07 - Invalid request (define category and page but no subject) - Negative")
    @Test
    public void RS_07() {

        try {

            invalid_request_define_category_and_page_but_no_subject.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_07 failed due to :" + e);
        }
    }

    @Description("RS_08 - Error - DATA PAGE DOES NOT EXIST - Negative")
    @Test
    public void RS_08() {

        try {

            error_data_page_does_not_exist.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_08 failed due to :" + e);
        }
    }

    @Description("RS_09 - Error - DATA PAGE DOES NOT EXIST - Negative")
    @Test
    public void RS_09() {

        try {

            error_category_does_not_exist.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("RS_09 failed due to :" + e);
        }
    }
}
