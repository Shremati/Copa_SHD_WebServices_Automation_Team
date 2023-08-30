package MODULES.WAVE3.AirScheduleService;

import MODULES.WAVE3.AirScheduleService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AirScheduleService {

    AirScheduleService() {
        createFolders(getResponseDirectory() + "AirScheduleService");
    }

    @Description("One request with vendor preferences")
    @Test
    public void Scenario1() {

        try {
            One_request_with_vendor_preferences.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Get Schedule Display - One request")
    @Test
    public void Scenario2() {

        try {
            One_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Request with departure time that will return date change flights")
    @Test
    public void Scenario3() {

        try {
            Request_with_departure_time_that_will_return_date_change_flights.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Get Direct Service Display - Error showing unimplemented method")
    @Test
    public void Scenario4() {

        try {
            Get_Direct_Service_Display_Error_showing_unimplemented_method.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Get Flight Details - Error showing unimplemented method")
    @Test
    public void Scenario5() {

        try {
            Get_Flight_Details_Error_showing_unimplemented_method.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("Host airline, 2 leg flight")
    @Test
    public void Scenario6() {

        try {
            Host_airline_2_leg_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("Host airline, one leg flight")
    @Test
    public void Scenario7() {

        try {
            Host_airline_one_leg_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("Multiple requests, mixed host and other airline")
    @Test
    public void Scenario8() {

        try {
            Multiple_requests_mixed_host_and_other_airline.Execute();
            // 1 segment should be other airlines like UA

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("One request  with 7 vendor preferences")
    @Test
    public void Scenario9() {

        try {

            One_request_with_7_vendor_preferences.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }


    @Description("Other airline flight")
    @Test
    public void Scenario10() {

        try {

            Other_airline_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }

    @Description("Request missing departure date")
    @Test
    public void Scenario11() {

        try {

            Request_missing_departure_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }


    @Description("Request missing destination location and departure date")
    @Test
    public void Scenario12() {

        try {

            Request_missing_destination_location_and_departure_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }

    @Description("Request missing origin location")
    @Test
    public void Scenario13() {

        try {

            Request_missing_origin_location.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("Request with vendor preferences shows traffic restrictions as comments")
    @Test
    public void Scenario14() {

        try {

            Request_with_vendor_preferences_shows_traffic_restrictions_as_comments.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }
}
