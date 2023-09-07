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
            // need to select the flight which not having seats

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

    @Description("Special passenger type - Jump seat")
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

    @Description("CreateBooking with 2 segments , 2pax (1 FF), stored fare, 2 phones, 1 remark and ticketing")
    @Test
    public void Scenario17() {

        try {

            CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 17 failed due to :" + e);
        }
    }

    @Description("CreateBooking with 4seg, 2pax, stored fare, 2phones,1remark, 2OSIs, 2SSRs and ticketing")
    @Test
    public void Scenario18() {

        try {

            CreateBbooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 18 failed due to :" + e);
        }
    }
    @Description("CreateBooking with 4seg(OA), 2pax (1FF and 1inf), 1email, stored fare, 1OSI, 1remark, SeatAssignment and ticketing")
    @Test
    public void Scenario19() {

        try {

            CreateBooking_with_4seg_OA_2pax_1FF_and_1inf_1email_stored_fare_1OSI_1remark_SeatAssignment_and_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 19 failed due to :" + e);
        }
    }

    @Description("CreateBooking with 3 seg(1ARNK),2pax(1FF),stored fare,2phones,1OSI,3remarks,SeatAssignment and ticketing")
    @Test
    public void Scenario20() {

        try {

            CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 20 failed due to :" + e);
        }
    }

    @Description("Check_for_invalid_AirItinerary_in_request")
    @Test
    public void Scenario21() {

        try {

            Check_for_invalid_AirItinerary_in_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 21 failed due to :" + e);
        }
    }
    @Description("Check for invalid AirTraveler in request")
    @Test
    public void Scenario22() {

        try {

            Check_for_invalid_AirTraveler_in_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 22 failed due to :" + e);
        }
    }

    @Description("CreateBooking Failure in one of the components of the system (SDS unavailable or SHARES unreachable)")
    @Test
    public void Scenario23() {

        try {

            CreateBooking_Failure_in_one_of_the_components_of_the_system_SDS_unavailable_or_SHARES_unreachable.Execute();
            //Should get null response if shares down.

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 23 failed due to :" + e);
        }
    }


    @Description("Invalid information specified in AirItinerary")
    @Test
    public void Scenario24() {

        try {

            Invalid_information_specified_in_AirItinerary.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 24 failed due to :" + e);
        }
    }

    @Description("Invalid information specified in AirTraveler")
    @Test
    public void Scenario25() {

        try {

            Invalid_information_specified_in_AirTraveler.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 25 failed due to :" + e);
        }
    }

    @Description("Invalid information specified in PriceInfo")
    @Test
    public void Scenario26() {

        try {

            Invalid_information_specified_in_PriceInfo.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 26 failed due to :" + e);
        }
    }

    @Description("CreateBooking with 1 seg, 1 pax, stored fare (base fare, NVA date, fare calculation line, BA and tour code) and TL")
    @Test
    public void Scenario27() {

        try {

            CreateBooking_with_1_seg_1_pax_stored_fare_base_fare_NVA_date_fare_calculation_line_BA_and_tour_code_and_TL.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 27 failed due to :" + e);
        }
    }

    @Description("Bundled segment - Invalid information in one segment")
    @Test
    public void Scenario28() {

        try {

            Bundled_segment_Invalid_information_in_one_segment.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 28 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item  Invalid tour code")
    @Test
    public void Scenario29() {

        try {

            Stored_fare_Ticketing_item_Invalid_tour_code.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 29 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item Invalid original issued info")
    @Test
    public void Scenario30() {

        try {

            Stored_fare_Ticketing_item_Invalid_original_issued_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 30 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item Invalid bankers rate info")
    @Test
    public void Scenario31() {

        try {

            Stored_fare_Ticketing_item_Invalid_bankers_rate_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 31 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item Invalid remark")
    @Test
    public void Scenario32() {

        try {

            Stored_fare_Ticketing_item_Invalid_remark.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 32 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item Invalid original origin destination city")
    @Test
    public void Scenario33() {

        try {

            Stored_fare_Ticketing_item_Invalid_original_origin_destination_city.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 33 failed due to :" + e);
        }
    }

//Stored fare - Ticketing item Invalid form of payment

    @Description("Stored fare - Ticketing item Invalid form of payment")
    @Test
    public void Scenario34() {

        try {

            Stored_fare_Ticketing_item_Invalid_form_of_payment.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 34 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item No form of payment")
    @Test
    public void Scenario35() {

        try {

            Stored_fare_Ticketing_item_No_form_of_payment.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 35 failed due to :" + e);
        }
    }

    @Description("Stored fare - Ticketing item too long remark")
    @Test
    public void Scenario36() {

        try {

            Stored_fare_Ticketing_item_too_long_remark.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 36 failed due to :" + e);
        }
    }

    @Description("Create a booking with 1 segment, 1 passenger, stored fare, ticketing and place it on queue")
    @Test
    public void Scenario37() {

        try {

            Create_booking_with_1_segment_1_passenger_stored_fare_ticketing_and_place_it_on_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 37 failed due to :" + e);
        }
    }

    @Description("CreateBooking with 1seg,1pax,stored fare(FBC, base fare, fare calculation line and issue in exchange without coupons) and TL")
    @Test
    public void Scenario38() {

        try {

            CreateBooking_with_1seg_1pax_storedfare_FBC_base_fare_fare_calculation_line_and_issue_in_exchange_without_coupons_and_TL.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 38 failed due to :" + e);
        }
    }

}

