package MODULES.WAVE3.DisplayBookingService;

import MODULES.WAVE3.DisplayBookingService.API_Tests.*;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Display_loyalty_account;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Display_partner_airline_elite_member_loyalty_account;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Error_On_Display_Loyalty_Account_Invalid_Loyalty_Account;
import frameworkconstants.FrameworkConstants;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class DisplayBookingService {
    DisplayBookingService() {
        createFolders(getResponseDirectory() + "DisplayBookingService");
    }

    @Description("Display a host airline booking")
    @Test
    public void Scenario1() {

        try {
            Display_a_host_airline_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Display booking by both record locator and Eticket number")
    @Test
    public void Scenario2() {

        try {
            Display_booking_by_both_record_locator_and_eticket_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Display a booking from a specific address (LNIATA) without reloc given")
    @Test
    public void Scenario3() {

        try {
            Display_a_booking_from_a_specific_address_lniata_without_recloc_given.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }


    @Description("Display confirmed booking list")
    @Test
    public void Scenario4() {

        try {
            Display_confirmed_booking_list.Execute(); //It will show all the  confirmed PNRs created for the same location and date and for same flight

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }


    @Description("Display confirmed and waitlist booking list")
    @Test
    public void Scenario5() {

        try {

            Display_confirmed_and_waitlist_booking_list.Execute(); //It will show all the  confirmed and waitlisted PNRs created for the same location and date and for same flight

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }


    @Description("Search a booking by frequent traveler number")
    @Test
    public void Scenario6() {

        try {

            Search_a_booking_by_frequent_traveler_number.Execute(); //It will fail because pnr with fqtv cant be created due to a defect

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("TC13 - Display confirmed booking by 2nd flight in booking")
    @Test
    public void Scenario7() {

        try {

            Display_confirmed_booking_by_2nd_flight_in_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("Display cancelled booking")
    @Test
    public void Scenario8() {

        try {

            Display_cancelled_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("Credit Card search (Negative scenario)")  //negative scenario
    @Test
    public void Scenario9() {

        try {

            Credit_card_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("Credit Card search(partial CC number) (Negative scenario))")
    @Test
    public void Scenario10() {

        try {

            Credit_card_search_partial_cc_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }


    @Description("FQTV search")
    @Test
    public void Scenario11() {

        try {

            Fqtv_search.Execute(); //It will give all the PNRs related to that FQTV

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("Date range search (single result)")
    @Test
    public void Scenario12() {

        try {

            Date_range_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }

    @Description("Search for a non-existent credit card(Negative Scenario)")
    @Test
    public void Scenario13() {

        try {

            Search_for_a_non_existent_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("Flight data search + given name + surname")
    @Test
    public void Scenario14() {

        try {

            Flight_data_search_given_name_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("TC01 - Display active fare quote and fare quote history")
    @Test
    public void Scenario15() {

        try {

            Display_active_fare_quote_and_fare_quote_history.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("TC04 - No fare quote history in booking")
    @Test
    public void Scenario16() {

        try {

            No_fare_quote_history_in_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }

    @Description("TC01-Display booking history with canceling the booking")
    @Test
    public void Scenario17() {

        try {

            Display_booking_history_with_canceling_the_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 17 failed due to :" + e);
        }
    }

    @Description("Display booking history with adding/deleting remarks, SSR, OSI and phone")
    @Test
    public void Scenario18() {

        try {

            Display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 18 failed due to :" + e);
        }
    }

    @Description("Display booking history for CM flight")
    @Test
    public void Scenario19() {

        try {

            Display_booking_history_for_cm_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 19 failed due to :" + e);
        }
    }

    @Description("Advance Seat Assignment on OA flight")
    @Test
    public void Scenario20() {  //Use Other Airline flights and markets in Data sheet

        try {

            Advance_seat_assignment_on_oa_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 20 failed due to :" + e);
        }
    }

    @Description("Advance Seat Assignment on a flight with multiple legs")
    @Test
    public void Scenario21() {

        try {

            Advance_seat_assignment_on_a_flight_with_multiple_legs.Execute();//Seat for multiple legs is not working, so we have taken only 1 leg and assigned seat.

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 21 failed due to :" + e);
        }
    }
}
