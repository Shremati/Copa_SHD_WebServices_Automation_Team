package MODULES.WAVE3.AirportPassengerList;

import MODULES.WAVE3.AirportPassengerList.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AirportPassengerList {

    AirportPassengerList() {
        createFolders(getResponseDirectory() + "AirportPassengerList");
    }

    @Description("Standard list: Code 2 - Eticketed passengers")
    @Test
    public void Scenario1() {
        try {
            Code_2_eticketed_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Standard list: Code 5 - Interline eticket passengers")
    @Test
    public void Scenario2() {
        try {
            Code_5_Interline_eticket_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Standard list: Code 12 - Passengers with advance seats")
    @Test
    public void Scenario3() {
        try {
            Code_12_Passengers_with_advance_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Standard list: Code 25 - passengers with held seats")
    @Test
    public void Scenario4() {
        try {
           Code_25_Pax_with_held_seats.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Custom  list: Code 0  - All passengers,  Response Data = Code 1, passenger name")
    @Test
    public void Scenario5() {
        try {
            Code_0_All_passengers_Response_Data_paxname.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("Custom  list: Code 0  - All passengers,  Response Data = Code 5, passenger service info")
    @Test
    public void Scenario6() {

        try {
         Code_0_All_passengers_Response_Data_paxservice_info.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("Custom  list: Code 42  - Specific SSR")
    @Test
    public void Scenario7() {

        try {
            Code_42_specific_SSR.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("Standard_list_Code_0_All_Passengers")
    @Test
    public void Scenario8() {

        try {
            Standard_list_Code_0_All_Passengers.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("Standard_list_Code_30_passengers_with_no_meals")
    @Test
//Check for data with manual team before executing the Test Case
    public void Scenario9() {

        try {
            Standard_list_Code_30_passengers_with_no_meals.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("Standard_list_Code_44_passengers_with_elite_frequent_traveler_numbers")
    @Test
    public void Scenario10() {

        try {
            Standard_list_Code_44_passengers_with_elite_frequent_traveler_numbers.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }

    @Description("Custom_list_Filter_Value_0")
    @Test
    public void Scenario11() {

        try {
            Custom_list_Filter_Value_0.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("Standard_list_Code_6_Out_of_sync_electronic_tickets")
    @Test
    public void Scenario12() {

        try {
            Standard_list_Code_6_Out_of_synch_electronic_tickets.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }

    @Description("Custom_list_Filter_Value_1_Surname")
    @Test
//Check for data with manual team before executing the Test Case
    public void Scenario13() {

        try {
            Custom_list_Filter_Value_1_Surname.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("Find_passengers_with_passcode_SA3R09")
    @Test
    public void Scenario14() {

        try {
            Find_passengers_with_passcode_SA3R09.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("DCS_reference_number")
    @Test
    public void Scenario15() {

        try {
            DCS_reference_number.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("Standard_list_Code_26_passengers_with_inbound_connections")
    @Test
    public void Scenario16() {

        try {
            Standard_list_Code_26_passengers_with_inbound_connections.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }

    @Description("Large_parties")
    @Test
    public void Scenario17() {

        try {
            Large_parties.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 17 failed due to :" + e);
        }
    }

    @Description("Passengers_with_outbound_connections")
    @Test
    public void Scenario18() {

        try {
            Passengers_with_outbound_connections.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 18 failed due to :" + e);
        }
    }

    @Description("Code_33_Specific_passengers")
    @Test
    public void Scenario19() {

        try {
            Code_33_Specific_passengers.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 19 failed due to :" + e);
        }
    }

    @Description("Code_34_specific_party")
    @Test
    public void Scenario20() {

        try {
            Code_34_specific_party.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 20 failed due to :" + e);
        }
    }

    @Description("Code_36_requiring_seats")
    @Test
    public void Scenario21() {

        try {
            Code_36_requiring_seats.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 21 failed due to :" + e);
        }
    }


}
