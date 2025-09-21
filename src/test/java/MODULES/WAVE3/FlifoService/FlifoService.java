package MODULES.WAVE3.FlifoService;

import MODULES.WAVE3.FlifoService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//25 Scenarios
@Listeners(TestListener.class)
public class FlifoService {

    FlifoService() {
        createFolders(getResponseDirectory() + "FlifoService");
    }


// ********* NOTE : In the screenText 2S392/08APRSJO-O/721A/809AGUA, SJO is the connecting city and GUA is the origin, to get this run 2+fltnumber in shares(Eg: 2360)

    @Test(description = "FS_03 - Flifo_for_one_flight_specifying_departure_arrival_city_actual_times")
    public void FS_03() {

        try {
            Flifo_for_one_flight_specifying_departure_arrival_city_actual_times.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_03 failed due to :" + e);
        }
    }

    @Test(description = "FS_06 - Flifo_for_Two_flights")
    public void FS_06() {

        try {
            Flifo_for_Two_flights.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_06 failed due to :" + e);
        }
    }

    @Test(description = "FS_15 - Flifo_for_codeshare_flight")
    public void FS_15() {

        try {
            Flifo_for_codeshare_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_15 failed due to :" + e);
        }
    }
    @Test(description = "FS_21 - Flifo_for_a_flight_with_crossing_date")
    public void FS_21() {  //Give today or future date

        try {
            Flifo_for_a_flight_with_crossing_date.Execute(); //Give history date, today or older. Make sure departure date and screen entry date match

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_21 failed due to :" + e);
        }
    }

    @Test(description = "FS_26 - FLIFO_History_for_host_airline")
    //Requires shares to run a command, 2P365/05SEP ETD CTG 1120A DELAY, in order to generate history details
    public void FS_26() {

        try {
            FLIFO_History_for_host_airline.Execute();  //Give history date, today or older. Make sure departure date and screen entry date match

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_26 failed due to :" + e);
        }
    }

    @Test(description = "FS_27 - FLIFO_History_for_codeshare_airline")
    public void FS_27() {

        try {
            FLIFO_History_for_codeshare_airline.Execute();  //Shares entry doesnt work here

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_27 failed due to :" + e);
        }
    }

    @Test(description = "FS_01 - Flifo for one flight departure date")
    public void FS_01() {

        try {
            Flifo_for_one_flight_departure_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_01 failed due to :" + e);
        }
    }

    @Test(description = "FS_02 - Flifo for one flt specifying dept city dept date")
    public void FS_02() {

        try {
            Flifo_for_one_flt_specifying_dept_city_dept_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_02 failed due to :" + e);
        }
    }

    @Test(description = "FS_04 - Flifo by departure time one flight matches departure time")
    public void FS_04() {

        try {
            Flifo_by_dept_time_one_flt_matches_dept_time.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_04 failed due to :" + e);
        }
    }

    @Test(description = "FS_08 - Flifo for one flight actual times in UTC format")
    public void FS_08() {

        try {
            Flifo_for_one_flight_actual_times_in_UTC_format.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_08 failed due to :" + e);
        }
    }

    @Test(description = "FS_13 - Required data missing flight number")
    public void FS_13() {

        try {
            Required_data_missing_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_13 failed due to :" + e);
        }
    }

    @Test(description = "FS_14 - Required data missing date")
    public void FS_14() {

        try {
            Required_data_missing_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_14 failed due to :" + e);
        }
    }

    @Test(description = "FS_19 - Flifo for past date")
    public void FS_19() {

        try {
            Flifo_for_past_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_19 failed due to :" + e);
        }
    }

    @Test(description = "FS_20 - Showing Aircraft Tail Number and the Air Equip Type in Flifo Service response")
    public void FS_20() {   //check the equipment number in ishares application using the command - "2" + fltNo

        try {
            Showing_Aircraft_Tail_Number_and_the_Air_Equip_Type_in_Flifo_Service_response.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_20 failed due to :" + e);
        }
    }

    @Test(description = "FS_28 - Return error message if it is more than 250 flights in the request")
    public void FS_28() {

        try {
            Return_error_msg_if_it_is_more_than_250_flts_in_the_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_28 failed due to :" + e);
        }
    }

    @Test(description = "FS_11 - Flifo with landing cancel")   //change the date in the ScreenEntry in the excel sheet
                                                              //can be run once, throws an error if ran multiple times
    public void FS_11() {       //Important note: Take todays date only

        try {
            Flifo_with_landing_cancel.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_11 failed due to :" + e);
        }
    }

    @Test(description = "FS_12 - Flifo with flight cancelled")   //change the date in the ScreenEntry in the excel sheet
                                                                //can be run once, throws an error if ran multiple times
    public void FS_12() {

        try {
            Flifo_with_flight_cancelled.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_12 failed due to :" + e);
        }
    }

    @Test(description = "FS_10 - Flifo with forecast")    //change the date in the ScreenEntry in the excel sheet
                                                         //can be run once, throws an error if ran multiple times
    public void FS_10() {

        try {
            Flifo_with_forecast.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_10 failed due to :" + e);
        }
    }

    @Test(description = "FS_16 - Flifo for codeshare flight UA without Origin and Destination")
    public void FS_16() {

        try {
            Flifo_for_codeshare_flight_UA_without_Origin_and_Destination.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_16 failed due to :" + e);
        }
    }

    @Test(description = "FS_22 - Flifo Flag Stop at the origin flight with 2 legs")   //change the date in the ScreenEntry in the excel sheet
                                    //391/392 flights can be taken                    //can be run once, throws an error if ran multiple times
    public void FS_22() {

        try {
            Flifo_Flag_Stop_at_the_origin_flight_with_2_legs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_22 failed due to :" + e);
        }
    }

    @Test(description = "FS_25 - Flifo Flag Stop An intermediate stop flag flying with 2 legs")      //change the date in the ScreenEntry in the excel sheet
                                                                                                  //can be run once, throws an error if ran multiple times
    public void FS_25() {

        try {
            Flifo_Flag_Stop_An_intermediate_stop_flag_flying_with_2_legs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_25 failed due to :" + e);
        }
    }

    @Test(description = "FS_24 - Flifo Flag Stop at the end flight with 2 legs")    //change the date in the ScreenEntry in the excel sheet
                                                                                  //can be run once, throws an error if ran multiple times
    public void FS_24() {

        try {
            Flifo_Flag_Stop_at_the_end_flight_with_2_legs.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_24 failed due to :" + e);
        }
    }

    @Test(description = "FS_05 - Flifo by departure time no flights with exact time")
    public void FS_05() {

        try {
            Flifo_by_departure_time_no_flights_with_exact_time.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_05 failed due to :" + e);
        }
    }

    @Test(description = "FS_07 - Flifo for flight with enroute date change")
    public void FS_07() {

        try {
            Flifo_for_flight_with_enroute_date_change.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_07 failed due to :" + e);
        }
    }

    @Test(description = "FS_23 - Flifo for a flight with crossing date: Scheduled time is few minutes before midnight and Actual time is few minutes after midnight")
    public void FS_23() {

        try {
            Flifo_for_a_flight_with_crossing_date_Scheduled_time_is_few_minutes_before_midnight_and_Actual_time_is_few_minutes_after_midnight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_23 failed due to :" + e);
        }
    }


}