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

    @Description("TT_02_1 - Request Visa Info with multi destination, transit and visited points for normal")
    @Test
    public void TT_02_1() {

        try {

            Request_Visa_Info_with_multi_destination_transit_and_visited_points.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_02_1 failed due to :" + e);
        }
    }

    @Description("TT_02_2 - Request Visa Info with multi destination, transit and visited points for Seaman")
    @Test
    public void TT_02_2() {

        try {

            Request_Visa_Info_with_multi_destination_transit_and_visited_points_seamen.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_02_2 failed due to :" + e);
        }
    }

    @Description("TT_02_3 - Request Visa Info with multi destination, transit and visited points for Government")
    @Test
    public void TT_02_3() {

        try {

            Request_Visa_Info_with_multi_destination_transit_and_visited_points_Gov.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_02_3 failed due to :" + e);
        }
    }



    @Description("TT_03_1 - Request Health Info with one destination, one transit and one visited point for Normal")
    @Test
    public void TT_03_1() {

        try {

            Request_Health_Info_with_one_destination_one_transit_and_one_visited_point_for_Normal.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_03_1 failed due to :" + e);
        }
    }

    @Description("TT_03_2 - Request Health Info with one destination, one transit and one visited point for seamen")
    @Test
    public void TT_03_2() {

        try {

            Request_Health_Info_with_one_destination_one_transit_and_one_visited_point_for_Seamen.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_03_2 failed due to :" + e);
        }
    }

    @Description("TT_03_3 - Request Health Info with one destination, one transit and one visited point for Gov")
    @Test
    public void TT_03_3() {

        try {

            Request_Health_Info_with_one_destination_one_transit_and_one_visited_point_for_Gov.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_03_3 failed due to :" + e);
        }
    }

    @Description("TT_04_1 - Request Health Info with multi destination, transit and visited points for Normal")
    @Test
    public void TT_04_1() {

        try {

            Request_Health_Info_with_multi_destination_transit_and_visited_points_for_normal.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_04_1 failed due to :" + e);
        }
    }

    @Description("TT_04_2 - Request Health Info with multi destination, transit and visited points for Seamen")
    @Test
    public void TT_04_2() {

        try {

            Request_Health_Info_with_multi_destination_transit_and_visited_points_for_seamen.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_04_2 failed due to :" + e);
        }
    }

    @Description("TT_04_3 - Request Health Info with multi destination, transit and visited points for gov")
    @Test
    public void TT_04_3() {

        try {

            Request_Health_Info_with_multi_destination_transit_and_visited_points_for_seamen.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_04_3 failed due to :" + e);
        }
    }



    @Description("TT_05_1 - Request Vias&Health Info with one destination, one transit and one visited point for normal")
    @Test
    public void TT_05_1() {

        try {

            Request_Vias_Health_Info_with_one_destination_one_transit_and_one_visited_point.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_05_1 failed due to :" + e);
        }
    }

    @Description("TT_05_2 - Request Vias&Health Info with one destination, one transit and one visited point for seamen")
    @Test
    public void TT_05_2() {

        try {

            Request_Vias_Health_Info_with_one_destination_one_transit_and_one_visited_point_for_seamen.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_05_2 failed due to :" + e);
        }
    }

    @Description("TT_05_3 - Request Vias&Health Info with one destination, one transit and one visited point for gov")
    @Test 
    public void TT_05_3() {

        try {

            Request_Vias_Health_Info_with_one_destination_one_transit_and_one_visited_point_for_gov.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_05_3 failed due to :" + e);
        }
    }

    @Description("TT_07_1 - Request Visa / Health Info without specifying the Nationality / Embarkation visa NA missing")
    @Test
    public void TT_07_1() {
        try {

            Request_Visa_Health_Info_without_specifying_the_Nationality_Embarkation_visa_NA_missing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_07_1 failed due to :" + e);
        }
    }

    @Description("TT_07_2 - Request Visa / Health Info without specifying the Nationality / Embarkation health EM missing")
    @Test
    public void TT_07_2() {
        try {

            Request_Visa_Health_Info_without_specifying_the_Nationality_Embarkation_health_EM_missing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_07_2 failed due to :" + e);
        }
    }

    @Description("TT_07_3 - Request Visa / Health Info without specifying the Nationality / Embarkation both NA missing")
    @Test
    public void TT_07_3() {
        try {

            Request_Visa_Health_Info_without_specifying_the_Nationality_Embarkation_both_NA_missing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_07_3 failed due to :" + e);
        }
    }

    @Description("TT_07_4 - Request Visa / Health Info without specifying the Nationality / Embarkation both EM missing")
    @Test
    public void TT_07_4() {
        try {

            Request_Visa_Health_Info_without_specifying_the_Nationality_Embarkation_both_NA_missing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_07_4 failed due to :" + e);
        }
    }


    @Description("TT_09_1 - Display with sections customs")
    @Test
    public void TT_09_1() {
        try {

            display_with_sections_customs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_1 failed due to :" + e);
        }
    }

    @Description("TT_09_2 - Display with sections currency")
    @Test
    public void TT_09_2() {
        try {

            display_with_sections_currency.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_2 failed due to :" + e);
        }
    }

    @Description("TT_09_3 - Display with sections Geographical Information")
    @Test
    public void TT_09_3() {
        try {

            display_with_sections_Geographical_information.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_3 failed due to :" + e);
        }
    }

    @Description("TT_09_4 - Display with sections health")
    @Test
    public void TT_09_4() {
        try {

            display_with_sections_health.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_4 failed due to :" + e);
        }
    }

    @Description("TT_09_5 - Display with sections passport")
    @Test
    public void TT_09_5() {
        try {

            display_with_sections_passport.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_5 failed due to :" + e);
        }
    }

    @Description("TT_09_6 - Display with sections visa/passport")
    @Test
    public void TT_09_6() {
        try {

            display_with_sections_visa_passport.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_6 failed due to :" + e);
        }
    }

    @Description("TT_09_7 - Display with sections Stateless/Refugees")
    @Test
    public void TT_09_7() {
        try {

            display_with_sections_Stateless_Refugees.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_7 failed due to :" + e);
        }
    }

    @Description("TT_09_8 - Display with sections tax")
    @Test
    public void TT_09_8() {
        try {

            display_with_sections_tax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_8 failed due to :" + e);
        }
    }

    @Description("TT_09_9 - Display with sections tax")
    @Test
    public void TT_09_9() {
        try {

            display_with_sections_tax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_9 failed due to :" + e);
        }
    }


    @Description("TT_09_10 - Display with sections visa")
    @Test
    public void TT_09_10() {
        try {

            display_with_sections_visa.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_09_10 failed due to :" + e);
        }
    }

    @Description("TT_11_1 - Display for section Visa with its subsection Transit without Visa")
    @Test
    public void TT_11_1() {
        try {

            display_for_section_Visa_with_its_subsection_Transit_without_Visa.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_1 failed due to :" + e);
        }
    }

    @Description("TT_11_2 - Display for section Visa with its subsection Visa Exemptions")
    @Test
    public void TT_11_2() {
        try {

            display_for_section_Visa_with_its_subsection_Visa_Exemptions.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_2 failed due to :" + e);
        }
    }

    @Description("TT_11_3 - Display for section Visa with its subsection Merchant Seaman")
    @Test
    public void TT_11_3() {
        try {

            display_for_section_Visa_with_its_subsection_Merchant_Seaman.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_3 failed due to :" + e);
        }
    }

    @Description("TT_11_4 - Display for section Visa with its subsection Crew Members")
    @Test
    public void TT_11_4() {
        try {

            display_for_section_Visa_with_its_subsection_Crew_Members.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_4 failed due to :" + e);
        }
    }


    @Description("TT_11_5 - Display for section Visa with its subsection Military")
    @Test
    public void TT_11_5() {
        try {

            display_for_section_Visa_with_its_subsection_Military.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_5 failed due to :" + e);
        }
    }

    @Description("TT_11_6 - Display for section Visa with its subsection Visa Issuance")
    @Test
    public void TT_11_6() {
        try {

            display_for_section_Visa_with_its_subsection_Visa_Issuance.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_6 failed due to :" + e);
        }
    }

    @Description("TT_11_7 - Display for section Visa with its subsection Minors")
    @Test
    public void TT_11_7() {
        try {

            display_for_section_Visa_with_its_subsection_Minors.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_7 failed due to :" + e);
        }
    }

    @Description("TT_11_8 - Display for section Visa with its subsection Warning")
    @Test
    public void TT_11_8() {
        try {

            display_for_section_Visa_with_its_subsection_Warning.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_8 failed due to :" + e);
        }
    }

    @Description("TT_11_9 - Display for section Visa with its subsection Notes")
    @Test
    public void TT_11_9() {
        try {

            display_for_section_Visa_with_its_subsection_notes.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_9 failed due to :" + e);
        }
    }

    @Description("TT_11_10 - Display for section Visa with its subsection Additional Information")
    @Test
    public void TT_11_10() {
        try {

            display_for_section_Visa_with_its_subsection_Additional_Information.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_10 failed due to :" + e);
        }
    }

    @Description("TT_11_11 - Display for section Visa with its subsection Compulsory Currency Exchange")
    @Test
    public void TT_11_11() {
        try {

            display_for_section_Visa_with_its_subsection_Compulsory_Currency_Exchange.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_11_11 failed due to :" + e);
        }
    }

    @Description("TT_12_1 - Display for section Customs with its subsection Import")
    @Test
    public void TT_12_1() {
        try {

            display_for_section_Customs_with_its_subsection_import.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_1 failed due to :" + e);
        }
    }

    @Description("TT_12_2 - Display for section Customs with its subsection ARMS AND AMMUNITION")
    @Test
    public void TT_12_2() {
        try {

            display_for_section_Customs_with_its_subsection_arms_and_ammunition.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_2 failed due to :" + e);
        }
    }

    @Description("TT_12_3 - Display for section Customs with its subsection Wild Fauna and Flora")
    @Test
    public void TT_12_3() {
        try {

            display_for_section_Customs_with_its_subsection_Wild_Fauna_and_Flora.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_3 failed due to :" + e);
        }
    }

    @Description("TT_12_4 - Display for section Customs with its subsection export")
    @Test
    public void TT_12_4() {
        try {

            display_for_section_Customs_with_its_subsection_export.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_4 failed due to :" + e);
        }
    }

    @Description("TT_12_5 - Display for section Customs with its subsection Additional Information")
    @Test
    public void TT_12_5() {
        try {

            display_for_section_Customs_with_its_subsection_Additional_Information.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_5 failed due to :" + e);
        }
    }

    @Description("TT_12_6 - Display for section Customs with its subsection Crew Members")
    @Test
    public void TT_12_6() {
        try {

            display_for_section_Customs_with_its_subsection_Crew_Members.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_6 failed due to :" + e);
        }
    }

    @Description("TT_12_7 - Display for section Customs with its subsection Pets")
    @Test
    public void TT_12_7() {
        try {

            display_for_section_Customs_with_its_subsection_pets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_7 failed due to :" + e);
        }
    }

    @Description("TT_12_8 - Display for section Customs with its subsection Baggage Clearance")
    @Test
    public void TT_12_8() {
        try {

            display_for_section_Customs_with_its_subsection_Baggage_Clearance.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_8 failed due to :" + e);
        }
    }

    @Description("TT_12_9 - Display for section Customs with its subsection Exempt")
    @Test
    public void TT_12_9() {
        try {

            display_for_section_Customs_with_its_subsection_Exempt.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_9 failed due to :" + e);
        }
    }

    @Description("TT_12_10 - Display for section Customs with its subsection Notes")
    @Test
    public void TT_12_10() {
        try {

            display_for_section_Customs_with_its_subsection_Notes.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_12_10 failed due to :" + e);
        }
    }

        //Display for section Health with its subsection

    @Description("TT_14_1 - Display for section Health with its subsection for exempt")
    @Test
    public void TT_14_1() {
        try {

            display_for_section_Health_with_its_subsection_for_exempt.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_14_1 failed due to :" + e);
        }
    }


    @Description("TT_14_2 - Display for section Health with its subsection for recommended")
    @Test
    public void TT_14_2() {
        try {

            display_for_section_Health_with_its_subsection_for_recommended.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_14_2 failed due to :" + e);
        }
    }

    @Description("TT_14_3 - Display for section Health with its subsection for Notes")
    @Test
    public void TT_14_3() {
        try {

            display_for_section_Health_with_its_subsection_for_Notes.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_14_3 failed due to :" + e);
        }
    }

    @Description("TT_14_4 - Display for section Health with its subsection for warning")
    @Test
    public void TT_14_4() {
        try {

            display_for_section_Health_with_its_subsection_for_warning.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_14_4 failed due to :" + e);
        }
    }

    @Description("TT_16 - Invalid subsection")
    @Test
    public void TT_16() {
        try {

            Invalid_subsection.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_16 failed due to :" + e);
        }
    }

    @Description("TT_17 - Miss Required Data")
    @Test
    public void TT_17() {
        try {

            miss_Required_Data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_17 failed due to :" + e);
        }
    }

    @Description("TT_18 - No Information for the Topic")
    @Test
    public void TT_18() {
        try {

            no_Information_for_the_Topic.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_18 failed due to :" + e);
        }
    }

    @Description("TT_19 - Display Country Code list")
    @Test
    public void TT_19() {
        try {

            display_Country_Code_list.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_19 failed due to :" + e);
        }
    }

    @Description("TT_21 - Display City list by location code")
    @Test
    public void TT_21() {
        try {

            display_City_list_by_location_code.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_21 failed due to :" + e);
        }
    }

    @Description("TT_23 - Display Country Group")
    @Test
    public void TT_23() {
        try {

            display_Country_Group.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TT_23 failed due to :" + e);
        }
    }



}

