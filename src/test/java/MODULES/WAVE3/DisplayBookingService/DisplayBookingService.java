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

public class DisplayBookingService
{

    DisplayBookingService() {
        createFolders(getResponseDirectory() + "DisplayBookingService");
    }

    @Description("DBS_01 - Display a host airline booking")
    @Test
    public void Scenario1() {

        try {
            Display_a_host_airline_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("DBS_07 - Display booking by both record locator and Eticket number")
    @Test
    public void Scenario2() {

        try {
            Display_booking_by_both_record_locator_and_eticket_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("DBS_10 - Display a booking from a specific address (LNIATA) without reloc given")
    @Test
    public void Scenario3() {

        try {
            Display_a_booking_from_a_specific_address_lniata_without_recloc_given.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }


    @Description("DBS_12 - Display confirmed booking list(including multiple name entries in list on same booking)")
    @Test
    public void Scenario4() {

        try {
            Display_confirmed_booking_list.Execute(); //It will show all the  confirmed PNRs created for the same location and date and for same flight

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }


    @Description("DBS_15 - Display confirmed and waitlist booking list")
    @Test
    public void Scenario5() {

        try {

            Display_confirmed_and_waitlist_booking_list.Execute(); //It will show all the  confirmed and waitlisted PNRs created for the same location and date and for same flight

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }


    @Description("DBS_18 - Search a booking by frequent traveler number")
    @Test
    public void Scenario6() {

        try {

            Search_a_booking_by_frequent_traveler_number.Execute(); //It will fail because pnr with fqtv cant be created due to a defect

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("DBS_23 - TC13 - Display confirmed booking by 2nd flight in booking")
    @Test
    public void Scenario7() {

        try {

            Display_confirmed_booking_by_2nd_flight_in_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("DBS_24 - Display cancelled booking")
    @Test
    public void Scenario8() {

        try {

            Display_cancelled_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("DBS_29 - Credit Card search(multiple results) (Negative scenario)")  //negative scenario
    @Test
    public void Scenario9() {

        try {

            Credit_card_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("DBS_30 - Credit Card search(partial CC number) (Negative scenario)")
    @Test
    public void Scenario10() {

        try {

            Credit_card_search_partial_cc_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }


    @Description("DBS_33 - FQTV search")
    @Test
    public void Scenario11() {

        try {

            Fqtv_search.Execute(); //It will give all the PNRs related to that FQTV

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("DBS_42 - Date range search (single result)")
    @Test
    public void Scenario12() {

        try {

            Date_range_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }

    @Description("DBS_49 - Search for a non-existent credit card(Negative Scenario)")
    @Test
    public void Scenario13() {

        try {

            Search_for_a_non_existent_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("DBS_70 - Flight data search + given name + surname")
    @Test
    public void Scenario14() {

        try {

            Flight_data_search_given_name_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("DBS_73 - TC01 - Display active fare quote and fare quote history")
    @Test
    public void Scenario15() {

        try {

            Display_active_fare_quote_and_fare_quote_history.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("DBS_76 - TC04 - No fare quote history in booking")
    @Test
    public void Scenario16() {

        try {

            No_fare_quote_history_in_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }

    @Description("DBS_77 - TC01 - Display booking history with canceling the booking")
    @Test
    public void Scenario17() {

        try {

            Display_booking_history_with_canceling_the_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 17 failed due to :" + e);
        }
    }

    @Description("DBS_81 - Display booking history with adding/deleting remarks, SSR, OSI and phone")
    @Test
    public void Scenario18() {

        try {

            Display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 18 failed due to :" + e);
        }
    }

    @Description("DBS_90 - Display booking history for CM flight")
    @Test
    public void Scenario19() {

        try {

            Display_booking_history_for_cm_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 19 failed due to :" + e);
        }
    }

    @Description("DBS_93 - Advance Seat Assignment on OA flight")
    @Test
    public void Scenario20() {  //Use Other Airline flights and markets in Data sheet

        try {

            Advance_seat_assignment_on_oa_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 20 failed due to :" + e);
        }
    }

    @Description("DBS_100 - Advance Seat Assignment on a flight with multiple legs")
    @Test
    public void Scenario21() {

        try {

            Advance_seat_assignment_on_a_flight_with_multiple_legs.Execute();//Seat for multiple legs is not working, so we have taken only 1 leg and assigned seat.

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 21 failed due to :" + e);
        }
    }
    @Description("DBS_02 - Display_booking_on_another_airline_booking")
    @Test
    public void Scenario22() {

        try {

            Display_booking_on_another_airline_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 22 failed due to :" + e);
        }
    }

    @Description("DBS_04 - Error_displaying_the_booking")
    @Test
    public void Scenario23() {

        try {

            Error_displaying_the_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 23 failed due to :" + e);
        }
    }


    @Description("DBS_05 - Error_displaying_a_booking_from_a_specific_address_LNIATA")
    @Test
    public void Scenario24() {

        try {

            Error_displaying_a_booking_from_a_specific_address_LNIATA.Execute();  //We need a LNIATA where PNR is not created for a given day

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 24 failed due to :" + e);
        }
    }

    @Description("DBS_06 - Display_booking_by_eticket_number")
    @Test
    public void Scenario25() {

        try {

            Display_booking_by_eticket_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 25 failed due to :" + e);
        }
    }

    @Description("DBS_08 - Error_display_booking_for_Incorrect_Eticket_Number")
    @Test
    public void Scenario26() {

        try {

            Error_display_booking_for_Incorrect_Eticket_Number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 26 failed due to :" + e);
        }
    }

    @Description("DBS_09 - Error_display_booking_for_no_Eticket_Number_and_no_Record_locator")
    @Test
    public void Scenario27() {

        try {

            Error_display_booking_for_no_Eticket_Number_and_no_Record_locator.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 27 failed due to :" + e);
        }
    }

    @Description("DBS_11 - Display_Confirmed_Booking")
    @Test
    public void Scenario28() {

        try {

            Display_Confirmed_Booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 28 failed due to :" + e);
        }
    }

    @Description("DBS_13 - Display_waitlist_booking")
    @Test
    public void Scenario29() {

        try {

            Display_waitlist_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 29 failed due to :" + e);
        }
    }

    @Description("DBS_14 - Display waitlist booking list(including_multiple_name_entries_in_list_on_same_booking)")
    @Test
    public void Scenario30() {

        try {

            Display_Booking_multiple_name_entries_in_list_on_same_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 30 failed due to :" + e);
        }
    }


    @Description("DBS_17 - Display_booking_same_passenger_name_in_both_lists")
    @Test
    public void Scenario31() {

        try {

            Display_booking_same_passenger_name_in_both_lists.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 31 failed due to :" + e);
        }
    }

    @Description("DBS_21 - Display_booking_missing_required_data_flt_numb_flt_date_or_pax_surname")
    @Test
    public void Scenario32() {

        try {

            Display_booking_missing_required_data_flt_numb_flt_date_or_pax_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 32 failed due to :" + e);
        }
    }

    @Description("DBS_22 - Display_booking_No_bookings_found")
    @Test
    public void Scenario33() {

        try {

            Display_booking_No_bookings_found.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 33 failed due to :" + e);
        }
    }

    @Description("DBS_25 - Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking")
    @Test
    public void Scenario34() {

        try {

            Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 34 failed due to :" + e);
        }
    }
}

//    DBS_19	Search a booking by telephone number (Not Automated)
//    DBS_16	Display confirmed and waitlist booking list (multiple name entries in list on same booking)

