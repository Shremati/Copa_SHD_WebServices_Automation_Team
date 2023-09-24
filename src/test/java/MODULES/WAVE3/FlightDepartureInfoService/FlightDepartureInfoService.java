package MODULES.WAVE3.FlightDepartureInfoService;

import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Flight_has_one_leg_and_open_status;
import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Flight_has_two_legs_and_notopen_status;
import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Request_has_an_invalid_flight_number;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 Scenarios
public class FlightDepartureInfoService {

    FlightDepartureInfoService() {
        createFolders(getResponseDirectory() + "FlightDepartureInfoService");
    }

    @Description("FDIS_03 - Flight has two legs and NotOpen status")
    @Test
    public void FDIS_03() {

        try {
            Flight_has_two_legs_and_notopen_status.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FDIS_03 failed due to :" + e);
        }
    }

    @Description("FDIS_04 - Flight has one leg and OPEN status")
    @Test
    public void FDIS_04() {

        try {
            Flight_has_one_leg_and_open_status.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FDIS_04 failed due to :" + e);
        }
    }

    @Description("FDIS_09 - The request has an invalid flight number")
    @Test
    public void FDIS_09() {

        try {
            Request_has_an_invalid_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FDIS_09 failed due to :" + e);
        }
    }
}
