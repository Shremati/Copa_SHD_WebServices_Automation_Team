package MODULES.WAVE3.AirportPassengerList;

import MODULES.WAVE3.AirportPassengerList.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AirportPassengerList {

    // 21 Scenarios
    AirportPassengerList() {
        createFolders(getResponseDirectory() + "AirportPassengerList");
    }

    @Description("APL_03 - Standard list: Code 2 - Eticketed passengers")
    @Test
    public void APL_03() {
        try {
            Code_2_eticketed_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_03 failed due to :" + e);
        }
    }

    @Description("APL_06 - Standard list: Code 5 - Interline eticket passengers")
    @Test
    public void APL_06() {
        try {
            Code_5_Interline_eticket_passengers.Execute();  //Need to check

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_06  failed due to :" + e);
        }
    }

    @Description("APL_11 - Standard list: Code 12 - Passengers with advance seats")
    @Test
    public void APL_11() {
        try {
            Code_12_Passengers_with_advance_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_11 failed due to :" + e);
        }
    }

    @Description("APL_18 - Standard list: Code 25 - passengers with held seats")
    @Test
    public void APL_18() {
        try {
            Code_25_Pax_with_held_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_18 failed due to :" + e);
        }
    }

    @Description("APL_41 - Custom  list: Code 0  - All passengers,  Response Data = Code 1, passenger name")
    @Test
    public void APL_41() {
        try {
            Code_0_All_passengers_Response_Data_paxname.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_41 failed due to :" + e);
        }
    }

    @Description("APL_45 - Custom  list: Code 0  - All passengers,  Response Data = Code 5, passenger service info")
    @Test
    public void APL_45() {

        try {

            Code_0_All_passengers_Response_Data_paxservice_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_45 failed due to :" + e);
        }
    }

    @Description("APL_34 - Custom  list: Code 42  - Specific SSR")
    @Test
    public void APL_34() {

        try {

            Code_42_specific_SSR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_34 failed due to :" + e);
        }
    }

    @Description("APL_01 - Standard list Code 0 All Passengers")
    @Test
    public void APL_01() {

        try {
            Standard_list_Code_0_All_Passengers.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_01  failed due to :" + e);
        }
    }

    @Description("APL_23 - Standard list Code 30 passengers with no meals")
    @Test
//Give a flight which contain bookings having no meal. Need Shares PNR having no meal
    public void APL_23() {

        try {

            Standard_list_Code_30_passengers_with_no_meals.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_23 failed due to :" + e);
        }
    }

    @Description("APL_36 - Standard list Code 44 passengers with elite frequent traveler numbers")
    @Test
    public void APL_36() {  //need to create a ff pnr

        try {
            Standard_list_Code_44_passengers_with_elite_frequent_traveler_numbers.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_36 failed due to :" + e);
        }
    }

    @Description("APL_52 - Custom list Filter Value 0")
    @Test
    public void APL_52() {

        try {
            Custom_list_Filter_Value_0.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_52 failed due to :" + e);
        }
    }

    @Description("APL_07 - Standard list Code 6 Out of sync electronic tickets")
    @Test
    public void APL_07() {//create PNR, modify it .

        try {
            Standard_list_Code_6_Out_of_synch_electronic_tickets.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_07 failed due to :" + e);
        }
    }

    @Description("APL_53 - Custom list Filter Value 1 Surname")
    @Test

//    Filter based on NAME
    public void APL_53() {

        try {
            Custom_list_Filter_Value_1_Surname.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_53 failed due to :" + e);
        }
    }

    @Description("APL_54 - Find passengers with passcode SA3R09")
    @Test
    public void APL_54() {

        //    Filter based on com:SurnamePrefix
        try {
            Find_passengers_with_passcode_SA3R09.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_54 failed due to :" + e);
        }
    }

    @Description("APL_57 - DCS reference number")
    @Test
    public void APL_57() {

        try {
            DCS_reference_number.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_57 failed due to :" + e);
        }
    }

    @Description("APL_19 - Standard list Code 26 passengers with inbound connections")
    @Test
    public void APL_19() {

        try {
            Standard_list_Code_26_passengers_with_inbound_connections.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_19 failed due to :" + e);
        }
    }

    @Description("APL_21 - Large parties")
    @Test
    public void APL_21() {

        try {
            Large_parties.Execute();  //5 passengers PNR
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_21 failed due to :" + e);
        }
    }

    @Description("APL_24 - Passengers with outbound connections")
    @Test
    public void APL_24() {

        try {
            Passengers_with_outbound_connections.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_24 failed due to :" + e);
        }
    }

    @Description("APL_26 - Code_33_Specific_passengers")
    @Test
    public void APL_26() {

        try {
            Code_33_Specific_passengers.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_26 failed due to :" + e);
        }
    }

    @Description("APL_27 - Code_34_specific_party")
    @Test
    public void APL_27() {

        try {
            Code_34_specific_party.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_27 failed due to :" + e);
        }
    }

    @Description("APL_29 - Code 36 requiring seats")
    @Test
    public void APL_29() {

        try {
            Code_36_requiring_seats.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_29 failed due to :" + e);
        }
    }

//APL_05 -	Code 4 - Eticketed passengers, not checked in (Not Automated)
}
