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
    public void DBS_01() {

        try {
            Display_a_host_airline_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_01 failed due to :" + e);
        }
    }

    @Description("DBS_07 - Display booking by both record locator and Eticket number")
    @Test
    public void DBS_07() {

        try {
            Display_booking_by_both_record_locator_and_eticket_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_07 failed due to :" + e);
        }
    }

    @Description("DBS_10 - Display a booking from a specific address (LNIATA) without reloc given")
    @Test
    public void DBS_10() {
 //Provide a LNIATA where PNR is created in SHARES for a given day
        try {
            Display_a_booking_from_a_specific_address_lniata_without_recloc_given.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_10 failed due to :" + e);
        }
    }


    @Description("DBS_12 - Display confirmed booking list(including multiple name entries in list on same booking)")
    @Test
    public void DBS_12() {

        try {
            Display_confirmed_booking_list.Execute(); //It will show all the  confirmed PNRs created for the same location and date and for same flight

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_12 failed due to :" + e);
        }
    }


    @Description("DBS_15 - Display confirmed and waitlist booking list")
    @Test
    public void DBS_15() {

        try {

            Display_confirmed_and_waitlist_booking_list.Execute(); //It will show all the  confirmed and waitlisted PNRs created for the same location and date and for same flight

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_15 failed due to :" + e);
        }
    }


    @Description("DBS_18 - Search a booking by frequent traveler number")
    @Test
    public void DBS_18() {

        try {

            Search_a_booking_by_frequent_traveler_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_18 failed due to :" + e);
        }
    }

    @Description("DBS_23 - TC13 - Display confirmed booking by 2nd flight in booking")
    @Test
    public void DBS_23() {

        try {

            Display_confirmed_booking_by_2nd_flight_in_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_23 failed due to :" + e);
        }
    }

    @Description("DBS_24 - Display cancelled booking")
    @Test
    public void DBS_24() {

        try {

            Display_cancelled_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_24 failed due to :" + e);
        }
    }

    @Description("DBS_29 - Credit Card search(multiple results) (Negative scenario)")  //negative scenario
    @Test
    public void DBS_29() {

        try {

            Credit_card_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_29 failed due to :" + e);
        }
    }

    @Description("DBS_30 - Credit Card search(partial CC number) (Negative scenario)")
    @Test
    public void DBS_30() {

        try {

            Credit_card_search_partial_cc_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_30 failed due to :" + e);
        }
    }


    @Description("DBS_33 - FQTV search")
    @Test
    public void DBS_33() {

        try {

            Fqtv_search.Execute(); //It will give all the PNRs related to that FQTV

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_33 failed due to :" + e);
        }
    }

    @Description("DBS_42 - Data range search (single result)")
    @Test
    public void DBS_42() {

        try {

            Date_range_search.Execute(); //PNR created instantly will not show up in the data range search instantly

            //Try to search for a historical PNR.
        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_42 failed due to :" + e);
        }
    }

    @Description("DBS_49 - Search for a non-existent credit card(Negative Scenario)")
    @Test
    public void DBS_49() {

        try {

            Search_for_a_non_existent_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_49 failed due to :" + e);
        }
    }

    @Description("DBS_70 - Flight data search + given name + surname")
    @Test
    public void DBS_70() {

        try {

            Flight_data_search_given_name_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_70 failed due to :" + e);
        }
    }

    @Description("DBS_73 - TC01 - Display active fare quote and fare quote history")
    @Test
    public void DBS_73() {

        try {

            Display_active_fare_quote_and_fare_quote_history.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_73 failed due to :" + e);
        }
    }

    @Description("DBS_76 - TC04 - No fare quote history in booking")
    @Test
    public void DBS_76() {

        try {

            No_fare_quote_history_in_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_76 failed due to :" + e);
        }
    }

    @Description("DBS_77 - TC01 - Display booking history with canceling the booking")
    @Test
    public void DBS_77() {

        try {

            Display_booking_history_with_canceling_the_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_77 failed due to :" + e);
        }
    }

    @Description("DBS_81 - Display booking history with adding/deleting remarks, SSR, OSI and phone")
    @Test
    public void DBS_81() {

        try {

            Display_booking_history_with_adding_deleting_remarks_ssr_osi_and_phone.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_81 failed due to :" + e);
        }
    }

    @Description("DBS_90 - Display booking history for CM flight")
    @Test
    public void DBS_90() {

        try {

            Display_booking_history_for_cm_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_90 failed due to :" + e);
        }
    }

    @Description("DBS_93 - Advance Seat Assignment on OA flight")
    @Test
    public void DBS_93() {  //Use Other Airline flights and markets in Data sheet

        try {

            Advance_seat_assignment_on_oa_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_93 failed due to :" + e);
        }
    }

    @Description("DBS_100 - Advance Seat Assignment on a flight with multiple legs")
    @Test
    public void DBS_100() {

        try {

            Advance_seat_assignment_on_a_flight_with_multiple_legs.Execute();//Seat for multiple legs is not working, so we have taken only 1 leg and assigned seat.

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_100 failed due to :" + e);
        }
    }
    @Description("DBS_02 - Display_booking_on_another_airline_booking")
    @Test
    public void DBS_02() {  //Need to check

        try {

            Display_booking_on_another_airline_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_02 failed due to :" + e);
        }
    }

    @Description("DBS_04 - Error_displaying_the_booking")
    @Test
    public void DBS_04() {

        try {

            Error_displaying_the_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_04 failed due to :" + e);
        }
    }


    @Description("DBS_05 - Error_displaying_a_booking_from_a_specific_address_LNIATA")
    @Test
    public void DBS_05() {

        try {

            Error_displaying_a_booking_from_a_specific_address_LNIATA.Execute();  //We need a LNIATA where PNR is not created for a given day

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_05 failed due to :" + e);
        }
    }

    @Description("DBS_06 - Display_booking_by_eticket_number")
    @Test
    public void DBS_06() {

        try {

            Display_booking_by_eticket_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_06 failed due to :" + e);
        }
    }

    @Description("DBS_08 - Error_display_booking_for_Incorrect_Eticket_Number")
    @Test
    public void DBS_08() {

        try {

            Error_display_booking_for_Incorrect_Eticket_Number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_08 failed due to :" + e);
        }
    }

    @Description("DBS_09 - Error_display_booking_for_no_Eticket_Number_and_no_Record_locator")
    @Test
    public void DBS_09() {

        try {

            Error_display_booking_for_no_Eticket_Number_and_no_Record_locator.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_09 failed due to :" + e);
        }
    }

    @Description("DBS_11 - Display_Confirmed_Booking")
    @Test
    public void DBS_11() {

        try {

            Display_Confirmed_Booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_11 failed due to :" + e);
        }
    }

    @Description("DBS_13 - Display_waitlist_booking")
    @Test
    public void DBS_13() {
//For waitlist scenarios , we need to give flights which are not available so that the pax can go into waitlist, Like Y0,B0, where seats are 0
        try {

            Display_waitlist_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_13 failed due to :" + e);
        }
    }

    @Description("DBS_14 - Display waitlist booking list(including_multiple_name_entries_in_list_on_same_booking)")
    @Test
    public void DBS_14() {
//Waitlist case, so give 0 availability flights i.e. where no seats are there
        try {

            Display_Booking_multiple_name_entries_in_list_on_same_booking.Execute(); // It will give 2 PNRS created under waitlist

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_14 failed due to :" + e);
        }
    }


    @Description("DBS_17 - Display_booking_same_passenger_name_in_both_lists")
    @Test
    public void DBS_17() {

        try {

            Display_booking_same_passenger_name_in_both_lists.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_17 failed due to :" + e);
        }
    }

    @Description("DBS_21 - Display_booking_missing_required_data_flt_numb_flt_date_or_pax_surname")
    @Test
    public void DBS_21() {

        try {

            Display_booking_missing_required_data_flt_numb_flt_date_or_pax_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_21 failed due to :" + e);
        }
    }

    @Description("DBS_22 - Display_booking_No_bookings_found")
    @Test
    public void DBS_22() {

        try {

            Display_booking_No_bookings_found.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_22 failed due to :" + e);
        }
    }

    @Description("DBS_25 - Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking")
    @Test
    public void DBS_25() {

        try {

            Display_confirmed_booking_list_1st_flt_in_one_booking_and_2nd_flt_in_another_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DBS_25 failed due to :" + e);
        }
    }
}

//    DBS_19	Search a booking by telephone number (Not Automated)
//    DBS_16	Display confirmed and waitlist booking list (multiple name entries in list on same booking)

