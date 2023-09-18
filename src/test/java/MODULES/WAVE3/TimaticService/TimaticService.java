package MODULES.WAVE3.TimaticService;

import MODULES.WAVE3.TimaticService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//17Scenarios

public class TimaticService {

    TimaticService() {
        createFolders(getResponseDirectory() + "TimaticService");
    }

    @Description("TT_01 - Request_Visa_Info_one_destination_transit_visited_point")
    @Test
    public void TT_01_1() {

        try {
            Visa_singlepoint_gov_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_01_1 - Request_Visa_Info_one_destination_transit_visited_point failed due to :" + e);
        }

    }

    @Description("TT_01 - Request_Visa_Info_one_destination_transit_visited_point")
    @Test
    public void TT_01_2() {

        try {
            Visa_singlepoint_normal_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_01_2 Request_Visa_Info_one_destination_transit_visited_point failed due to :" + e);
        }

    }

    @Description("TT_01 - Visa_singlepoint_seaman_request")
    @Test
    public void TT_01_3() {

        try {
            Visa_singlepoint_seaman_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_01_3 Request_Visa_Info_one_destination_transit_visited_point failed due to :" + e);
        }

    }

    @Description("TT_08 - Both_DEorTR_missing_request")
    @Test
    public void TT_08_1() {

        try {
            Both_DEorTR_missing_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_08_1 failed due to :" + e);
        }

    }

    @Description("TT_08 - Health_DEorTR_missing_request")
    @Test
    public void TT_08_2() {

        try {
            Health_DEorTR_missing_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_08_2 failed due to :" + e);
        }

    }

    @Description("TT_08 - Visa_DEorTR_missing_request")
    @Test
    public void TT_08_3() {

        try {
            Visa_DEorTR_missing_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_08_3 failed due to :" + e);
        }

    }

    @Description("TT_06 - Both_multipoint_gov_request")
    @Test
    public void TT_06_1() {

        try {
            Both_multipoint_gov_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_06_1 failed due to :" + e);
        }

    }

    @Description("TT_06 - Both_multipoint_normal_request")
    @Test
    public void TT_06_2() {

        try {
            Both_multipoint_normal_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_06_2 failed due to :" + e);
        }

    }

    @Description("TT_06 - Both_multipoint_seaman_request")
    @Test
    public void TT_06_3() {

        try {

            Both_multipoint_seaman_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_06_3 failed due to :" + e);
        }

    }

    @Description("TT_24 - Display_Country_List_in_a_Group")
    @Test
    public void TT_24() {

        try {

            Display_Country_List_in_a_Group.Execute();


        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_24 failed due to :" + e);
        }

    }

    @Description("TT_26 - Display_Rules_with_Rule_Index")
    @Test
    public void TT_26() {

        try {
            Display_Rules_with_Rule_Index.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_26 failed due to :" + e);
        }

    }

    @Description("TT_28 - Display_the_List_of_News_Items")
    @Test
    public void TT_28() {

        try {
            Display_the_List_of_News_Items.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_28 failed due to :" + e);
        }

    }

    @Description("TT_20 - Display_City_list_by_Country_code")
    @Test
    public void TT_20() {

        try {
            Display_City_list_by_Country_code.Execute();


        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_20 failed due to :" + e);
        }

    }

    @Description("TT_22 - Display_City_list_by_country_starting_letter")
    @Test
    public void TT_22() {

        try {
            Display_City_list_by_country_starting_letter.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_22 failed due to :" + e);
        }
    }

    @Description("TT_10 - Display_for_section_Passport_with_its_subsection")
    @Test
    public void TT_10() {

        try {

            Display_for_section_Passport_with_its_subsection.Execute();


        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_10 failed due to :" + e);
        }

    }

    @Description("TT_13 - Display_for_section_Currency_with_its_subsection")
    @Test
    public void TT_13() {

        try {
            Display_for_section_Currency_with_its_subsection.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_13 failed due to :" + e);
        }
    }

    @Description("TT_15 - Display_all_sections_for_a_country")
    @Test
    public void TT_15() {

        try {
            Display_all_sections_for_a_country.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_15 failed due to :" + e);
        }
    }

//    @Description("TT_02 - Request Visa Info with multi destination, transit and visited points for normal")
//    @Test
//    public void TT_02() {
//
//        try {
//
//            Request_Visa_Info_with_multi_destination_transit_and_visited_points.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("TT_02 failed due to :" + e);
//        }
//    }

}

