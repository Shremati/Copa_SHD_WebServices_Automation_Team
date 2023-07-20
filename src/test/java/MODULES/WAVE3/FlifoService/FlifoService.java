package MODULES.WAVE3.FlifoService;

import MODULES.WAVE3.FlifoService.API_Tests.Flifo_for_Two_flights;
import MODULES.WAVE3.FlifoService.API_Tests.Flifo_for_codeshare_flight;
import MODULES.WAVE3.FlifoService.API_Tests.Flifo_for_one_flight_specifying_departure_arrival_city_actual_times;
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
}
