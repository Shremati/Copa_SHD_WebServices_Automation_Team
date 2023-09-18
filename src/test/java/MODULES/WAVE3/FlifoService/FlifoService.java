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
    public void FS_21() {

        try {
            Flifo_for_a_flight_with_crossing_date.Execute();

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
            FLIFO_History_for_host_airline.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_26 failed due to :" + e);
        }
    }
    @Description("FS_27 - FLIFO_History_for_codeshare_airline")
    @Test

    public void FS_27() {

        try {
            FLIFO_History_for_codeshare_airline.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FS_27 failed due to :" + e);
        }
    }
}
