package MODULES.WAVE3.CreateBookingService;

import MODULES.WAVE3.CreateBookingService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class CreateBookingService {

    CreateBookingService()
    {
        createFolders(getResponseDirectory()+"CreateBookingService");
    }

    @Description(" Create a booking with 1 segment, 1 passenger, stored fare, 1 telephone and ticketing")
    @Test
    public void Scenario1() {

        try {
            create_booking_1seg_1pax_stored_fare_1telephone_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Create a booking with 4 segments, 2 passengers (1 frequent flyer and 1 infant), stored fare, 1 telephone, 1 remark, 2 SSRs and ticketing.")
    @Test
    public void Scenario2() {

        try {
            create_booking_four_seg_two_pax_two_SSR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }

    }

    @Description("Create a booking with 4 segments (HA), 2 passengers (1 frequent flyer and 1 infant), 1 email, stored fare, 1 OSI, 1 remark, Advance Seat Assignment and ticketing.")
    @Test
    public void Scenario3() {

        try {
            create_booking_four_seg_2_pax_one_remark_asa.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }

    @Description("Check for invalid Ticketing in request")
    @Test
    public void Scenario4() {

        try {
            check_for_invalid_ticketing_in_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Check for invalid PriceInfo in request")
    @Test
    public void Scenario5() {
        try {
            check_for_invalid_priceinfo_in_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }

    }

    @Description("Add a default time limit when no data is specified in Ticketing")
    @Test
    public void Scenario6() {

        try {
            add_a_default_time_limit_when_no_data_is_specified_in_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }

    }

    @Description("Create a booking with 1 segment, 1 passenger, stored fare (1 fare basis code, base fare, not valid before/after date, fare calculation line, bagagge allowance, sale location, 1 free-flow remark, tour code and form of payment CASH) and time limit")
    @Test
    public void Scenario7() {
        try {
            create_booking_1seg_1pax_stored_fare_time_limit.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }

    }

    @Description("Create a booking with 2 segments, 2 passengers, stored fare (2 fare basis code, base fare, not valid before/after date, fare calculation line, 2 bagagge allowance, sale location, 3 free-flow remark, tour code, 2 endorsements, last ticketing date, bankers rate and form of payment CHECK) and ticketing")
    @Test
    public void Scenario8() {

        try {
            create_booking_2seg_2pax_stored_fare.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8failed due to :" + e);
        }

    }

    @Description("Create a booking with 2 segments, 2 passengers, stored fare per passenger (2 fare basis code, base fare, not valid before/after date, fare calculation line, 2 bagagge allowance, sale location, 1 free-flow remark, tour code, 2 endorsements, last ticketing date, original origin/destination city, bankers rate, original issue and form of payment Credit Card) and ticketing")
    @Test
    public void Scenario9() {

        try {

            create_booking_2seg_2pax_stored_fare_per_pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }

    }

    @Description("Stored fare - Ticketing item: Invalid bagagge allowance")
    @Test
    public void Scenario10() {

        try {

            stored_fare_ticketing_item_invalid_bagagge_allowance.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }

    }

    @Description("Waitlist booking (action code - LL)")
    @Test
    public void Scenario11() {

        try {

            waitlist_booking_action_code_LL.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }

    }

    @Description("Group booking")
    @Test
    public void Scenario12() {

        try {

            group_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }

    }

    @Description("Special passenger type - Non Revenue Space Available staff travel")
    @Test
    public void Scenario13() {

        try {

            special_passenger_type_non_revenue_space_available_staff_travel.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }

    }

    @Description("Special passenger type - Jumpseat")
    @Test
    public void Scenario14() {

        try {

            special_passenger_type_jumpseat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }

    }

    @Description("Passengers with frequent traveler number for reward redemption (SSR FQTR) and name remark")
    @Test
    public void Scenario15() {

        try {

            passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }

    }

    @Description("Add the Form Of Payment remark and the FOP in the stored fare")
    @Test
    public void Scenario16() {

        try {

            add_the_form_of_payment_remark_and_the_fop_in_the_stored_fare.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }
}

