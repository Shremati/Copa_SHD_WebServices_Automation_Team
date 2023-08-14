package MODULES.WAVE3.FlifoService;

import MODULES.WAVE3.FlifoService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class FlifoService {

    FlifoService() {
        createFolders(getResponseDirectory() + "FlifoService");
    }

    @Description("Flifo_for_one_flight_specifying_departure_arrival_city_actual_times")
    @Test
    public void Scenario1() {

        try {
            Flifo_for_one_flight_specifying_departure_arrival_city_actual_times.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Flifo_for_Two_flights")
    @Test
    public void Scenario2() {

        try {
            Flifo_for_Two_flights.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Flifo_for_codeshare_flight")
    @Test
    public void Scenario3() {

        try {
            Flifo_for_codeshare_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }
    @Description("Flifo_for_a_flight_with_crossing_date")
    @Test
    public void Scenario4() {

        try {
            Flifo_for_a_flight_with_crossing_date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("FLIFO_History_for_host_airline")
    @Test
    public void Scenario5() {

        try {
            FLIFO_History_for_host_airline.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }
    @Description("FLIFO_History_for_codeshare_airline")
    @Test
    public void Scenario6() {

        try {
            FLIFO_History_for_codeshare_airline.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }
}
