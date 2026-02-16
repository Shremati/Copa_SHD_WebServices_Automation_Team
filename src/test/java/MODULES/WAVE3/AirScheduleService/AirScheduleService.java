package MODULES.WAVE3.AirScheduleService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.AirScheduleService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//14 Scenarios
@Listeners(TestListener.class)
public class AirScheduleService {

    AirScheduleService() throws IOException {

        createFolders(getResponseDirectory() + "AirScheduleService");
        FlightBooking.bookFlight("AirScheduleService");
    }

    @Test(description = "AS_01 - One request with vendor preferences")
    public void AS_01() {

        try {
            One_request_with_vendor_preferences.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_01 failed due to :" + e);
        }
    }

    @Test(description = "AS_02 - Get Schedule Display - One request")
    public void AS_02() {

        try {
            One_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_02 failed due to :" + e);
        }
    }

    @Test(description = "AS_08 - Request with departure time that will return date change flights")
    public void AS_08() {

        try {
            Request_with_departure_time_that_will_return_date_change_flights.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_08 failed due to :" + e);
        }
    }

    @Test(description = "AS_09 - Get Direct Service Display - Error showing unimplemented method")
    public void AS_09() {

        try {
            Get_Direct_Service_Display_Error_showing_unimplemented_method.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_09 failed due to :" + e);
        }
    }

    @Test(description = "AS_10 - Get Flight Details - Error showing unimplemented method")
    public void AS_10() {

        try {
            Get_Flight_Details_Error_showing_unimplemented_method.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_10 failed due to :" + e);
        }
    }

    @Test(description = "AS_12 - Host airline, 2 leg flight")
    public void AS_12() {

        try {
            Host_airline_2_leg_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_12 failed due to :" + e);
        }
    }

    @Test(description = "AS_11 - Host airline, one leg flight")
    public void AS_11() {

        try {
            Host_airline_one_leg_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_11 failed due to :" + e);
        }
    }

    @Test(description = "AS_14 - Multiple requests, mixed host and other airline")
    public void AS_14() {

        try {
            Multiple_requests_mixed_host_and_other_airline.Execute();
            // 1 segment should be other airlines like UA

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_14 failed due to :" + e);
        }
    }

    @Test(description = "AS_03 - One request  with 7 vendor preferences")
    public void AS_03() {

        try {

            One_request_with_7_vendor_preferences.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_03 failed due to :" + e);
        }
    }


    @Test(description = "AS_13 - Other airline flight")
    public void AS_13() {

        try {

            Other_airline_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_13 failed due to :" + e);
        }
    }

    @Test(description = "AS_04 - Request missing departure date")
    public void AS_04() {

        try {

            Request_missing_departure_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_04 failed due to :" + e);
        }
    }


    @Test(description = "AS_06 - Request missing destination location and departure date")
    public void AS_06() {

        try {

            Request_missing_destination_location_and_departure_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_06 failed due to :" + e);
        }
    }

    @Test(description = "AS_05 - Request missing origin location")
    public void AS_05() {

        try {

            Request_missing_origin_location.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_05 failed due to :" + e);
        }
    }

    @Test(description = "AS_07 - Request with vendor preferences shows traffic restrictions as comments")
    public void AS_07() {

        try {

            Request_with_vendor_preferences_shows_traffic_restrictions_as_comments.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AS_07 failed due to :" + e);
        }
    }
}
