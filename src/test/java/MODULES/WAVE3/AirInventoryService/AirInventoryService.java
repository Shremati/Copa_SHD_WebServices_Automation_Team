package MODULES.WAVE3.AirInventoryService;

import MODULES.WAVE3.AirInventoryService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AirInventoryService {

    AirInventoryService() {
        createFolders(getResponseDirectory() + "AirInventoryService");
    }

    @Description("Host Airline Inventory Request")
    @Test
    public void Scenario1() {
        try {
            Host_Airline_Inventory_Request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("HA Inventory request with a date in the past")
    @Test
    public void Scenario2() {
        try {
            HA_Inventory_request_with_a_date_in_the_past.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("More than 7 HA Inventory requests at a time")
    @Test
    public void Scenario3() {
        try {
            More_than_7_HA_Inventory_requests_at_a_time.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_with_invalid_AgentSine")
    @Test
    public void Scenario4() {
        try {

            HA_Inventory_request_with_invalid_AgentSine.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_with_invalid_flight_number")
    @Test
    public void Scenario5() {
        try {
            HA_Inventory_request_with_invalid_flight_number.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_without_specifying_a_Date")
    @Test
    public void Scenario6() {
        try {
            HA_Inventory_request_without_specifying_a_Date.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_with_invalid_carrier_code")
    @Test
    public void Scenario7() {
        try {
            HA_Inventory_request_with_invalid_carrier_code.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_with_invalid_city_airport_code")
    @Test
    public void Scenario8() {
        try {
            HA_Inventory_request_with_invalid_city_airport_code.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_with_O_D_parameter")
    @Test
    public void Scenario9() {
        try {
            HA_Inventory_request_with_O_D_parameter.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }
    @Description("Other_Airline_Inventory_request")
    @Test
    public void Scenario10() {
        try {
            Other_Airline_Inventory_request.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_Request_with_optional_Origin_Destination_parameters")
    @Test
    public void Scenario11() {
        try {
            HA_Inventory_Request_with_optional_Origin_Destination_parameters.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }
    @Description("HA_and_OA_Inventory_requests_with_optional_parameters")
    @Test
    //Check for data with manual team before executing the Test Case
    public void Scenario12() {
        try {
            HA_and_OA_Inventory_requests_with_optional_parameters.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }
    @Description("HA_Inventory_request_for_the_same_flight_but_different_routes")
    @Test
    public void Scenario13() {
        try {
            HA_Inventory_request_for_the_same_flight_but_different_routes.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

}

