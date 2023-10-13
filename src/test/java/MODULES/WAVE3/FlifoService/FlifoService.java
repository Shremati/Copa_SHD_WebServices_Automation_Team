package MODULES.WAVE3.FlifoService;

import MODULES.WAVE3.FlifoService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//6 Scenarios
public class FlifoService {

    FlifoService() {
        createFolders(getResponseDirectory() + "FlifoService");
    }

    @Description("FS_03 - Flifo_for_one_flight_specifying_departure_arrival_city_actual_times")
    @Test
    public void FS_03() {

        try {
            Flifo_for_one_flight_specifying_departure_arrival_city_actual_times.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_03 failed due to :" + e);
        }
    }

    @Description("FS_06 - Flifo_for_Two_flights")
    @Test
    public void FS_06() {

        try {
            Flifo_for_Two_flights.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_06 failed due to :" + e);
        }
    }

    @Description("FS_15 - Flifo_for_codeshare_flight")
    @Test
    public void FS_15() {

        try {
            Flifo_for_codeshare_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_15 failed due to :" + e);
        }
    }
    @Description("FS_21 - Flifo_for_a_flight_with_crossing_date")
    @Test
    public void FS_21() {  //Give today or future date

        try {
            Flifo_for_a_flight_with_crossing_date.Execute(); //Give history date, today or older. Make sure departure date and screen entry date match

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_21 failed due to :" + e);
        }
    }

    @Description("FS_26 - FLIFO_History_for_host_airline")
    @Test
    //Requires shares to run a command, 2P365/05SEP ETD CTG 1120A DELAY, in order to generate history details
    public void FS_26() {

        try {
            FLIFO_History_for_host_airline.Execute();  //Give history date, today or older. Make sure departure date and screen entry date match

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_26 failed due to :" + e);
        }
    }
    @Description("FS_27 - FLIFO_History_for_codeshare_airline")
    @Test

    public void FS_27() {

        try {
            FLIFO_History_for_codeshare_airline.Execute();  //Shares entry doesnt work here

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_27 failed due to :" + e);
        }
    }

    @Description("FS_01 - Flifo for one flight departure date")
    @Test
    public void FS_01() {

        try {
            Flifo_for_one_flight_departure_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_01 failed due to :" + e);
        }
    }

    @Description("FS_02 - Flifo for one flt specifying dept city dept date")
    @Test
    public void FS_02() {

        try {
            Flifo_for_one_flt_specifying_dept_city_dept_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_02 failed due to :" + e);
        }
    }

    @Description("FS_04 - Flifo by departure time one flight matches departure time")
    @Test
    public void FS_04() {

        try {
            Flifo_by_dept_time_one_flt_matches_dept_time.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_04 failed due to :" + e);
        }
    }

    @Description("FS_08 - Flifo for one flight actual times in UTC format")
    @Test
    public void FS_08() {

        try {
            Flifo_for_one_flight_actual_times_in_UTC_format.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_08 failed due to :" + e);
        }
    }

    @Description("FS_13 - Required data missing flight number")
    @Test
    public void FS_13() {

        try {
            Required_data_missing_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_13 failed due to :" + e);
        }
    }

    @Description("FS_14 - Required data missing date")
    @Test
    public void FS_14() {

        try {
            Required_data_missing_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_14 failed due to :" + e);
        }
    }

    @Description("FS_19 - Flifo for past date")
    @Test
    public void FS_19() {

        try {
            Flifo_for_past_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_19 failed due to :" + e);
        }
    }

    @Description("FS_20 - Showing Aircraft Tail Number and the Air Equip Type in Flifo Service response")
    @Test
    public void FS_20() {

        try {
            Showing_Aircraft_Tail_Number_and_the_Air_Equip_Type_in_Flifo_Service_response.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_20 failed due to :" + e);
        }
    }

    @Description("FS_28 - Return error message if it is more than 250 flights in the request")
    @Test
    public void FS_28() {

        try {
            Return_error_msg_if_it_is_more_than_250_flts_in_the_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_28 failed due to :" + e);
        }
    }

    @Description("FS_11 - Flifo with landing cancel")
    @Test
    public void FS_11() {

        try {
            Flifo_with_landing_cancel.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_11 failed due to :" + e);
        }
    }

    @Description("FS_12 - Flifo with flight cancelled")
    @Test
    public void FS_12() {

        try {
            Flifo_with_flight_cancelled.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_12 failed due to :" + e);
        }
    }

    @Description("FS_10 - Flifo with forecast")
    @Test
    public void FS_10() {

        try {
            Flifo_with_forecast.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_10 failed due to :" + e);
        }
    }

    @Description("FS_16 - Flifo for codeshare flight UA without Origin and Destination")
    @Test
    public void FS_16() {

        try {
            Flifo_for_codeshare_flight_UA_without_Origin_and_Destination.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_16 failed due to :" + e);
        }
    }

    @Description("FS_22 - Flifo Flag Stop at the origin flight with 2 legs")
    @Test
    public void FS_22() {

        try {
            Flifo_Flag_Stop_at_the_origin_flight_with_2_legs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_22 failed due to :" + e);
        }
    }

    @Description("FS_25 - Flifo Flag Stop An intermediate stop flag flying with 2 legs")
    @Test
    public void FS_25() {

        try {
            Flifo_Flag_Stop_An_intermediate_stop_flag_flying_with_2_legs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_25 failed due to :" + e);
        }
    }

    @Description("FS_24 - Flifo Flag Stop at the end flight with 2 legs")
    @Test
    public void FS_24() {

        try {
            Flifo_Flag_Stop_at_the_end_flight_with_2_legs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_24 failed due to :" + e);
        }
    }

    @Description("FS_05 - Flifo by departure time no flights with exact time")
    @Test
    public void FS_05() {

        try {
            Flifo_by_departure_time_no_flights_with_exact_time.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_05 failed due to :" + e);
        }
    }

    @Description("FS_07 - Flifo for flight with en route date change")
    @Test
    public void FS_07() {

        try {
            Flifo_for_flight_with_enroute_date_change.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_07 failed due to :" + e);
        }
    }


}
