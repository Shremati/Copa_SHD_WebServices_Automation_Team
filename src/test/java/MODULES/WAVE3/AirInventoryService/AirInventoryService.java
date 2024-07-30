package MODULES.WAVE3.AirInventoryService;

import MODULES.WAVE3.AirInventoryService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//13 Scenarios
@Listeners(TestListener.class)
public class AirInventoryService {

    String class_name=this.getClass().getName();
    AirInventoryService() {
        createFolders(getResponseDirectory() + "AirInventoryService");
    }

    @Description("AIS_01 - Host Airline Inventory Request")
    @Test
    public void AIS_01() {
        try {
            Host_Airline_Inventory_Request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_01 failed due to :" + e);
        }
    }

    @Description("AIS_09 - HA Inventory request with a date in the past")
    @Test
    public void AIS_09() {
        try {
            HA_Inventory_request_with_a_date_in_the_past.Execute();  //older date has been given

        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_09 failed due to :" + e);
        }
    }

    @Description("AIS_06 - More than 7 HA Inventory requests at a time")
    @Test
    public void AIS_06() {
        try {

            More_than_7_HA_Inventory_requests_at_a_time.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_06 failed due to :" + e);
        }
    }
    @Description("AIS_13 - HA Inventory request with invalid AgentSine")
    @Test
    public void AIS_13() {
        try {

            HA_Inventory_request_with_invalid_AgentSine.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_13 failed due to :" + e);
        }
    }
    @Description("AIS_11 - HA Inventory request with invalid flight number")
    @Test
    public void AIS_11() {
        try {

            HA_Inventory_request_with_invalid_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_11 failed due to :" + e);
        }
    }
    @Description("AIS_08 - HA Inventory request without specifying a Date")
    @Test
    public void AIS_08() {
        try {
            HA_Inventory_request_without_specifying_a_Date.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_08 failed due to :" + e);
        }
    }
    @Description("AIS_12 - HA Inventory request with invalid carrier code")
    @Test
    public void AIS_12() {
        try {
            HA_Inventory_request_with_invalid_carrier_code.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_12 failed due to :" + e);
        }
    }
    @Description("AIS_10 - HA Inventory request with invalid city airport code")
    @Test
    public void AIS_10() {
        try {
            HA_Inventory_request_with_invalid_city_airport_code.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_10 failed due to :" + e);
        }
    }
    @Description("AIS_03 - HA Inventory request with O&D parameter")
    @Test
    public void AIS_03() {
        try {
            HA_Inventory_request_with_O_D_parameter.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_03 failed due to :" + e);
        }
    }
    @Description("AIS_04 - Other Airline Inventory request")
    @Test
    public void AIS_04() {
        try {
            Other_Airline_Inventory_request.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_04 failed due to :" + e);
        }
    }
    @Description("AIS_02 - HA Inventory Request with optional Origin Destination parameters")
    @Test
    public void AIS_02() {
        try {
            HA_Inventory_Request_with_optional_Origin_Destination_parameters.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_02 failed due to :" + e);
        }
    }
    @Description("AIS_05 - HA and OA Inventory requests with optional parameters")
    @Test
    //Check for data with manual team before executing the Test Case
    public void AIS_05() {
        try {
            HA_and_OA_Inventory_requests_with_optional_parameters.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_05 failed due to :" + e);
        }
    }
    @Description("AIS_07 - HA Inventory request for the same flight but different routes")
    @Test
    public void AIS_07() {
        try {
            HA_Inventory_request_for_the_same_flight_but_different_routes.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("AIS_07 failed due to :" + e);
        }
    }

}

