package MODULES.WAVE3.FlightDepartureInfoService;

import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Flight_has_one_leg_and_open_status;
import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Flight_has_two_legs_and_notopen_status;
import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Request_has_an_invalid_flight_number;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestRunner {

    @Description("Flight has two legs and NotOpen status")
    @Test
    public void Scenario1() {

        try {
            Flight_has_two_legs_and_notopen_status.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Flight has one leg and OPEN status")
    @Test
    public void Scenario2() {

        try {
            Flight_has_one_leg_and_open_status.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("The request has an invalid flight number")
    @Test
    public void Scenario3() {

        try {
            Request_has_an_invalid_flight_number.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }



}
