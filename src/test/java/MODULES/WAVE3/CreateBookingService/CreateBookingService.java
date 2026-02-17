package MODULES.WAVE3.CreateBookingService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.CreateBookingService.API_Tests.*;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//38 Scenarios
@Listeners(TestListener.class)
public class CreateBookingService {

    CreateBookingService() throws IOException
    {
        createFolders(getResponseDirectory() + "CreateBookingService");
        FlightBooking.bookFlight("CreateBookingService");
    }

//    @Test(description = "CBS_02 - Create a booking with 1 segment, 1 passenger, stored fare, 1 telephone and ticketing")
//    public void CBS_02() {
//
//        try {
//            create_booking_1seg_1pax_stored_fare_1telephone_ticketing.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_02 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_05 - Create a booking with 4 segments, 2 passengers (1 frequent flyer and 1 infant), stored fare, 1 telephone, 1 remark, 2 SSRs and ticketing.")
//    public void CBS_05() {
//
//        try {
//            create_booking_four_seg_two_pax_two_SSR.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_05 failed due to :" + e);
//        }
//
//    }

//    @Test(description = "CBS_06 - Create a booking with 4 segments (HA), 2 passengers (1 frequent flyer and 1 infant), 1 email, stored fare, 1 OSI, 1 remark, Advance Seat Assignment and ticketing.")
//    public void CBS_06() {     // All the 4 flights should be UA flights only
//
//        try {
//            create_booking_four_seg_2_pax_one_remark_asa.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_06 failed due to :" + e);
//        }
//
//    }
//
//    @Test(description = "CBS_13 - Check for invalid Ticketing in request")
//    public void CBS_13() {
//
//        try {
//            check_for_invalid_ticketing_in_request.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_13 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_14 - Check for invalid PriceInfo in request")
//    public void CBS_14() {
//        try {
//            check_for_invalid_priceinfo_in_request.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_14 failed due to :" + e);
//        }
//
//    }
//
//    @Test(description = "CBS_19 - Add a default time limit when no data is specified in Ticketing")
//    public void CBS_19() {
//
//        try {
//            add_a_default_time_limit_when_no_data_is_specified_in_ticketing.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_19 failed due to :" + e);
//        }
//
//    }
//
    @Test(description = "CBS_23 - Create a booking with 1 segment, 1 passenger, stored fare (1 fare basis code, base fare, not valid before/after date, fare calculation line, bagagge allowance, sale location, 1 free-flow remark, tour code and form of payment CASH) and time limit")
    public void CBS_23() {
        try {
            create_booking_1seg_1pax_stored_fare_time_limit.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_23 failed due to :" + e);
        }

    }

//    @Test(description = "CBS_25 - Create a booking with 2 segments, 2 passengers, stored fare (2 fare basis code, base fare, not valid before/after date, fare calculation line, 2 bagagge allowance, sale location, 3 free-flow remark, tour code, 2 endorsements, last ticketing date, bankers rate and form of payment CHECK) and ticketing")
//    public void CBS_25() {
//
//        try {
//            create_booking_2seg_2pax_stored_fare.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_25 failed due to :" + e);
//        }
//
//    }
//
//    @Test(description = "CBS_27 - Create a booking with 2 segments, 2 passengers, stored fare per passenger (2 fare basis code, base fare, not valid before/after date, fare calculation line, 2 bagagge allowance, sale location, 1 free-flow remark, tour code, 2 endorsements, last ticketing date, original origin/destination city, bankers rate, original issue and form of payment Credit Card) and ticketing")
//
//    public void CBS_27() {
//
//        try {
//
//            create_booking_2seg_2pax_stored_fare_per_pax.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_27 failed due to :" + e);
//        }
//
//    }
//
//    @Test(description = "CBS_33 - Stored fare - Ticketing item: Invalid bagagge allowance")
//    public void CBS_33() {
//
//        try {
//
//            stored_fare_ticketing_item_invalid_bagagge_allowance.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_33 failed due to :" + e);
//        }
//    }

    @Test(description = "CBS_55 - Waitlist booking (action code - LL)")
    public void CBS_55() {

        try {

            waitlist_booking_action_code_LL.Execute();
            // need to select the flight which not having seats

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_55 failed due to :" + e);
        }

    }

    @Test(description = "CBS_59 - Group booking")
    public void CBS_59() {

        try {

            group_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_59 failed due to :" + e);
        }

    }

    @Test(description = "CBS_65 - Special passenger type - Non Revenue Space Available staff travel")
    public void CBS_65() {

        try {

            special_passenger_type_non_revenue_space_available_staff_travel.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_65 failed due to :" + e);
        }

    }

    @Test(description = "CBS_67 - Special passenger type - Jump seat")
    public void CBS_67() {

        try {

            special_passenger_type_jumpseat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_67 failed due to :" + e);
        }

    }

    @Test(description = "CBS_80 - Passengers with frequent traveler number for reward redemption (SSR FQTR) and name remark")
    public void CBS_80() {

        try {

            passengers_with_frequent_traveler_number_for_reward_redemption_ssr_fqtr_and_name_remark.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_80 failed due to :" + e);
        }

    }

    @Test(description = "CBS_99 - Add the Form Of Payment remark and the FOP in the stored fare")
    public void CBS_99() {

        try {

            add_the_form_of_payment_remark_and_the_fop_in_the_stored_fare.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_99 failed due to :" + e);
        }
    }

//    @Test(description = "CBS_03 - CreateBooking with 2 segments , 2pax (1 FF), stored fare, 2 phones, 1 remark and ticketing")
//    public void CBS_03() {
//
//        try {
//
//            CreateBooking_with_2segm_2pax_1_FF_stored_fare_2_phones_1_remark_and_ticketing.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_03 failed due to :" + e);
//        }
//    }
//
    @Test(description = "CBS_04 - CreateBooking with 4seg, 2pax, stored fare, 2phones,1remark, 2OSIs, 2SSRs and ticketing")
    public void CBS_04() {

        try {

            CreateBooking_with_4seg_2pax_stored_fare_2phones_1remark_2OSIs_2SSRs_and_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_04 failed due to :" + e);
        }
    }
//    @Test(description = "CBS_07 - CreateBooking with 4seg(OA), 2pax (1FF and 1inf), 1email, stored fare, 1OSI, 1remark, SeatAssignment and ticketing")
//    public void CBS_07() {    // All the 4 flights should be UA flights only
//
//        try {
//
//            CreateBooking_with_4seg_OA_2pax_1FF_and_1inf_1email_stored_fare_1OSI_1remark_SeatAssignment_and_ticketing.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_07 failed due to :" + e);
//        }
//    }
//
    @Test(description = "CBS_10 - CreateBooking with 3 seg(1ARNK),2pax(1FF),stored fare,2phones,1OSI,3remarks,SeatAssignment and ticketing")
    public void CBS_10() {

        try {

            CreateBooking_with_3_seg_1ARNK_2pax_1FF_stored_fare_2phones_1OSI_3remarks_SeatAssignment_and_ticketing.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_10 failed due to :" + e);
        }
    }

//    @Test(description = "CBS_11 - Check_for_invalid_AirItinerary_in_request")
//    public void CBS_11() {
//
//        try {
//
//            Check_for_invalid_AirItinerary_in_request.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_11 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_12 - Check for invalid AirTraveler in request")
//    public void CBS_12() {
//
//        try {
//
//            Check_for_invalid_AirTraveler_in_request.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_12 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_16 - CreateBooking Failure in one of the components of the system (SDS unavailable or SHARES unreachable)")
//    public void CBS_16() {
//
//        try {
//
//            CreateBooking_Failure_in_one_of_the_components_of_the_system_SDS_unavailable_or_SHARES_unreachable.Execute();
//            //Should get null response if shares down.
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_16 failed due to :" + e);
//        }
//    }
//
//
//    @Test(description = "CBS_17 - Invalid information specified in AirItinerary")
//    public void CBS_17() {
//
//        try {
//
//            Invalid_information_specified_in_AirItinerary.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_17 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_18 - Invalid information specified in AirTraveler")
//    public void CBS_18() {
//
//        try {
//
//            Invalid_information_specified_in_AirTraveler.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_18 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_20 - Invalid information specified in PriceInfo")
//    public void CBS_20() {
//
//        try {
//
//            Invalid_information_specified_in_PriceInfo.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_20 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_29 - CreateBooking with 1 seg, 1 pax, stored fare (base fare, NVA date, fare calculation line, BA and tour code) and TL")
//    public void CBS_29() {
//
//        try {
//
//            CreateBooking_with_1_seg_1_pax_stored_fare_base_fare_NVA_date_fare_calculation_line_BA_and_tour_code_and_TL.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_29 failed due to :" + e);
//        }
//    }

    @Test(description = "CBS_51 - Bundled segment - Invalid information in one segment")
    public void CBS_51() {
                    // one of the flights given should be a wrong flight number(Eg. 9999)
        try {

            Bundled_segment_Invalid_information_in_one_segment.Execute();
                // Currently having GUI Issue conjunctive PNR
        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_51 failed due to :" + e);
        }
    }

//    @Test(description = "CBS_34 - Stored fare - Ticketing item  Invalid tour code")
//    public void CBS_34() {
//
//        try {
//
//            Stored_fare_Ticketing_item_Invalid_tour_code.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_34 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_35 - Stored fare - Ticketing item Invalid original issued info")
//    public void CBS_35() {
//
//        try {
//
//            Stored_fare_Ticketing_item_Invalid_original_issued_info.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_35 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_36 - Stored fare - Ticketing item Invalid bankers rate info")
//    public void CBS_36() {
//
//        try {
//
//            Stored_fare_Ticketing_item_Invalid_bankers_rate_info.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_36 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_37 - Stored fare - Ticketing item Invalid remark")
//    public void CBS_37() {
//
//        try {
//
//            Stored_fare_Ticketing_item_Invalid_remark.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_37 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_38 - Stored fare - Ticketing item Invalid original origin destination city")
//    public void CBS_38() {
//
//        try {
//
//            Stored_fare_Ticketing_item_Invalid_original_origin_destination_city.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_38 failed due to :" + e);
//        }
//    }
//
    @Test(description = "CBS_39 - Stored fare - Ticketing item Invalid form of payment")
    public void CBS_39() {

        try {

            Stored_fare_Ticketing_item_Invalid_form_of_payment.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_39 failed due to :" + e);
        }
    }

    @Test(description = "CBS_40 - Stored fare - Ticketing item No form of payment")
    public void CBS_40() {

        try {

            Stored_fare_Ticketing_item_No_form_of_payment.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_40 failed due to :" + e);
        }
    }

    @Test(description = "CBS_41 - Stored fare - Ticketing item too long remark")
    public void CBS_41() {

        try {

            Stored_fare_Ticketing_item_too_long_remark.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_41 failed due to :" + e);
        }
    }

    @Test(description = "CBS_43 - Create a booking with 1 segment, 1 passenger, stored fare, ticketing and place it on queue")
    public void CBS_43() {

        try {

            Create_booking_with_1_segment_1_passenger_stored_fare_ticketing_and_place_it_on_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_43 failed due to :" + e);
        }
    }

//    @Test(description = "CBS_28 - CreateBooking with 1seg,1pax,stored fare(FBC, base fare, fare calculation line and issue in exchange without coupons) and TL")
//    public void CBS_28() {
//
//        try {
//
//            CreateBooking_with_1seg_1pax_storedfare_FBC_base_fare_fare_calculation_line_and_issue_in_exchange.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_28 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_01 - Create a booking with 1 segment, 1 passenger, stored fare and time limit")
//    public void CBS_01() {
//
//        try {
//
//            Create_a_booking_with_1_segment_1_passenger_stored_fare_and_time_limit.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_01 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_15 - Invalid data in request")
//    public void CBS_15() {
//
//        try {
//
//            Create_booking_Invalid_data_in_request.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_15 failed due to :" + e);
//        }
//    }
//
//    @Test(description = "CBS_21 - Create a booking with 1 segment, 1 passenger, stored fare (with basic info) and time limit")
//    public void CBS_21() {
//
//        try {
//
//            Create_a_booking_with_1_segment_1_passenger_stored_fare_with_basic_info_and_time_limit.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("CBS_21 failed due to :" + e);
//        }
//    }

    @Test(description = "CBS_44 - Queue Invalid queue number")
    public void CBS_44() {

        try {

            Queue_Invalid_queue_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("CBS_44 failed due to :" + e);
        }
    }

}
