package MODULES.WAVE3.AdvancePassengerInfo;

import MODULES.WAVE3.AdvancePassengerInfo.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AdvancePassengerInfo {

    AdvancePassengerInfo() {
        createFolders(getResponseDirectory() + "AdvancePassengerInfo");
    }


    @Description("Display API requirements for a single passenger in booking.")
    @Test
    public void Scenario1() {
        try {
            Single_Pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Display API requirements for the passengers as single surname-multiple names in booking")
    @Test
    public void Scenario2() {
        try {
            Single_surname_multiple_names.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("DisplayAPI error - Invalid passenger name (Negative Scenario)")
    @Test
    public void Scenario3() {
        try {
            DisplayAPI_error_Invalid_passenger_name.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Display API requirements in a single request for passengers in different bookings")
    @Test
    public void Scenario4() {
        try {
            Passengers_in_different_booking_in_single_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("ModifyAPI_Collect_API_for_a_Single_passenger")
    @Test
    public void Scenario5() {
        try {

            Collect_API_for_a_Single_passenger.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("ModifyAPI_Update and Delete API data")
    @Test
    public void Scenario6() {
        try {

            Modify_API_for_deleting_API_data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }
}
