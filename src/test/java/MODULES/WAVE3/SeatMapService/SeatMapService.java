package MODULES.WAVE3.SeatMapService;

import MODULES.WAVE3.ScreenTextService.API_Tests.Error_screentext_not_allowed_entry;
import MODULES.WAVE3.ScreenTextService.API_Tests.Send_entry;
import MODULES.WAVE3.SeatMapService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//8 Scenario
@Listeners(TestListener.class)
public class SeatMapService {

    SeatMapService() {
        createFolders(getResponseDirectory() + "SeatMapService");
    }

    @Test(description = "SMS_01 - Display a single 737 aircraft on a one legged flight. Map contains 2 compartments")
    public void SMS_01() {

        try {
            Display_single_737_aircraft_on_one_leg_flight_map_contains_two_comp.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_01 failed due to :" + e);
        }
    }

    @Test(description = "SMS_02 - Display a single 737 aircraft on a two flights. Each map contains 2 compartments")
    public void SMS_02() {

        try {
            display_a_single_737_aircraft_on_a_two_flights_each_map_contains_2_compartments.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_02 failed due to :" + e);
        }
    }


    @Test(description = "SMS_03 - Requested flight is in 'open seating' mode, so no map is available.")
    public void SMS_03() {    // try to take, 138 flt, PTY-MEX, departure date 02 days

        try {
            requested_flight_is_in_open_seating_mode_so_no_map_is_available.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_03 failed due to :" + e);
        }
    }

    @Test(description = "SMS_04 - Requested flight is in 'manual mode', so no seat map is available")
    public void SMS_04() {  // try to take, 138 flt, PTY-MEX, departure date 02 days and also change the value in the Screenentry column

        try {

            Requested_flight_is_in_manual_mode_so_no_seat_map_is_available.Execute(); //Flight should be open

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_04 failed due to :" + e);
        }
    }


    @Test(description = "SMS_05 - Enroute ship change returns multiple seat maps")
    public void SMS_05() {

        try {

            Enroute_ship_change_returns_multiple_seat_maps.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_05 failed due to :" + e);
        }
    }


    @Test(description = "SMS_07 - Other airline seat map request with Business Cabin")
    public void SMS_07() {

        try {

            other_airline_seat_map_request_with_Business_Cabin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_07 failed due to :" + e);
        }
    }

    @Test(description = "SMS_08 - Request is for more greater than max allowed, 5")
    public void SMS_08() {

        try {

            Request_is_for_more_greater_than_max_allowed_5.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_08 failed due to :" + e);
        }
    }


    @Test(description = "SMS_09 - Request is for Extra LegRoomSeat Display")

    public void SMS_09() {

        try {

            request_is_for_Extra_LegRoomSeat_Display.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SMS_09 failed due to :" + e);
        }
    }



}
