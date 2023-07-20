package MODULES.WAVE3.AirScheduleService;

import MODULES.WAVE3.AirScheduleService.API_Tests.One_request;
import MODULES.WAVE3.AirScheduleService.API_Tests.One_request_with_vendor_preferences;
import MODULES.WAVE3.AirScheduleService.API_Tests.Request_with_departure_time_that_will_return_date_change_flights;
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

    @Description("One request")
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
}
