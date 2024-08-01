package MODULES.WAVE3.FlightDepartureInfoService;

import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Flight_has_one_leg_and_open_status;
import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Flight_has_two_legs_and_notopen_status;
import MODULES.WAVE3.FlightDepartureInfoService.API_Tests.Request_has_an_invalid_flight_number;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

@Listeners(TestListener.class)
public class FlightDepartureInfoService {

    FlightDepartureInfoService() {
        createFolders(getResponseDirectory() + "FlightDepartureInfoService");
    }

    @Test(description = "FDIS_03 - Flight has two legs and NotOpen status")
    public void FDIS_03() {

        try {
            Flight_has_two_legs_and_notopen_status.Execute(); //PTY-SJO and SJO_GUA has 391 as 2 leg flight. So take this one

        } catch (Exception e) {
            failTest(e);
            System.out.println("FDIS_03 failed due to :" + e);
        }
    }

    @Test(description = "FDIS_04 - Flight has one leg and OPEN status")
    public void FDIS_04() {

        try {
            Flight_has_one_leg_and_open_status.Execute(); //1 leg and open flight , ex, 360,120 ,etc

        } catch (Exception e) {
            failTest(e);
            System.out.println("FDIS_04 failed due to :" + e);
        }
    }

    @Test(description = "FDIS_09 - The request has an invalid flight number")
    public void FDIS_09() {

        try {
            Request_has_an_invalid_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("FDIS_09 failed due to :" + e);
        }
    }
}
