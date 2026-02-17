package MODULES.WAVE3.AirportPassengerList;

import GENERICS.FlightBooking;
import MODULES.WAVE3.AirportPassengerList.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

// 22 Scenarios
@Listeners(TestListener.class)
public class AirportPassengerList {

    AirportPassengerList() throws IOException {
        createFolders(getResponseDirectory()+"AirportPassengerList");
        FlightBooking.bookFlight("AirportPassengerList");
    }


    @Test(description = "APL_03 - Standard list: Code 2 - Eticketed passengers")
    public void APL_03() {
        try {

            Code_2_eticketed_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_03 failed due to :" + e);
        }
    }

    @Test(description = "APL_06 - Standard list: Code 5 - Interline eticket passengers")
    public void APL_06() {
        try {

            Code_5_Interline_eticket_passengers.Execute();  //Need to check

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_06  failed due to :" + e);
        }
    }

    @Test(description = "APL_11 - Standard list: Code 12 - Passengers with advance seats")
    public void APL_11() {
        try {

            Code_12_Passengers_with_advance_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_11 failed due to :" + e);
        }
    }

    @Test(description = "APL_18 - Standard list: Code 25 - passengers with held seats")
    public void APL_18() {
        try {

            Code_25_Pax_with_held_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_18 failed due to :" + e);
        }
    }

    @Test(description = "APL_41 - Custom  list: Code 0  - All passengers,  Response Data = Code 1, passenger name")
    public void APL_41() {
        try {

            Code_0_All_passengers_Response_Data_paxname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_41 failed due to :" + e);
        }
    }

    @Test(description = "APL_45 - Custom  list: Code 0  - All passengers,  Response Data = Code 5, passenger service info")
    public void APL_45() {

        try {

            Code_0_All_passengers_Response_Data_paxservice_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_45 failed due to :" + e);
        }
    }

    @Test(description = "APL_34 - Custom  list: Code 42  - Specific SSR")
    public void APL_34() {

        try {

            Code_42_specific_SSR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_34 failed due to :" + e);
        }
    }

    @Test(description = "APL_01 - Standard list Code 0 All Passengers")
    public void APL_01() {

        try {

            Standard_list_Code_0_All_Passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_01  failed due to :" + e);
        }
    }

    @Test(description = "APL_23 - Standard list Code 30 passengers with no meals")
//Give a flight which contain bookings having no meal. Need Shares PNR having no meal
    public void APL_23() {

        try {

            Standard_list_Code_30_passengers_with_no_meals.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_23 failed due to :" + e);
        }
    }

    @Test(description = "APL_36 - Standard list Code 44 passengers with elite frequent traveler numbers")
    public void APL_36() {

//       Only  LoyalLevel="PREMIER SILVER" works here. So, give FF number accordingly

        try {

            Standard_list_Code_44_passengers_with_elite_frequent_traveler_numbers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_36 failed due to :" + e);
        }
    }

    @Test(description = "APL_52 - Custom list Filter Value 0")
    public void APL_52() {

        try {
            Custom_list_Filter_Value_0.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_52 failed due to :" + e);
        }
    }

    @Test(description = "APL_07 - Standard list Code 6 Out of sync electronic tickets")
    public void APL_07() {

        try {

            Standard_list_Code_6_Out_of_synch_electronic_tickets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_07 failed due to :" + e);
        }
    }

    @Test(description = "APL_53 - Custom list Filter Value 1 Surname")
    public void APL_53() {

        try {

            Custom_list_Filter_Value_1_Surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_53 failed due to :" + e);
        }
    }

    @Test(description = "APL_54 - Find passengers with passcode SA3R09")
    public void APL_54() {

        try {

            Find_passengers_with_passcode_SA3R09.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_54 failed due to :" + e);
        }
    }

    @Test(description = "APL_57 - DCS reference number")
    public void APL_57() {

        try {

            DCS_reference_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_57 failed due to :" + e);
        }
    }

    @Test(description = "APL_19 - Standard list Code 26 passengers with inbound connections")
    public void APL_19() {

        try {

            Standard_list_Code_26_passengers_with_inbound_connections.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_19 failed due to :" + e);
        }
    }

    @Test(description = "APL_21 - Large parties")
    public void APL_21() {

        try {

            Large_parties.Execute();  //5 passengers PNR

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_21 failed due to :" + e);
        }
    }

    @Test(description = "APL_24 - Passengers with outbound connections")
    public void APL_24() {

        try {

            Passengers_with_outbound_connections.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_24 failed due to :" + e);
        }
    }

    @Test(description = "APL_26 - Code_33_Specific_passengers")
    public void APL_26() {

        try {

            Code_33_Specific_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_26 failed due to :" + e);
        }
    }

    @Test(description = "APL_27 - Code_34_specific_party")
    public void APL_27() {

        try {

            Code_34_specific_party.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_27 failed due to :" + e);
        }
    }

    @Test(description = "APL_29 - Code 36 requiring seats")
    public void APL_29() {

        try {

            Code_36_requiring_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_29 failed due to :" + e);
        }
    }

    @Test(description = "APL_05 - Code 4 Eticketed passengers, not checked in")
    public void APL_05() {

        try {

            Eticketed_passengers_not_checked_in_04.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APL_05 failed due to :" + e);
        }
    }

//APL_05 -	Code 4 - Eticketed passengers, not checked in (Not Automated)
}
