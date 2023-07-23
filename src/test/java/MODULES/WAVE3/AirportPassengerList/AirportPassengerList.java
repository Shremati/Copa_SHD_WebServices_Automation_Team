package MODULES.WAVE3.AirportPassengerList;

import MODULES.WAVE3.AirportPassengerList.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AirportPassengerList {

    AirportPassengerList() {
        createFolders(getResponseDirectory() + "AirportPassengerList");
    }

    @Description("Standard list: Code 2 - Eticketed passengers")
    @Test
    public void Scenario1() {
        try {
            Code_2_eticketed_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Standard list: Code 5 - Interline eticket passengers")
    @Test
    public void Scenario2() {
        try {
            Code_5_Interline_eticket_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Standard list: Code 12 - Passengers with advance seats")
    @Test
    public void Scenario3() {
        try {
            Code_12_Passengers_with_advance_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Standard list: Code 25 - passengers with held seats")
    @Test
    public void Scenario4() {
        try {
           Code_25_Pax_with_held_seats.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Custom  list: Code 0  - All passengers,  Response Data = Code 1, passenger name")
    @Test
    public void Scenario5() {
        try {
            Code_0_All_passengers_Response_Data_paxname.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("Custom  list: Code 0  - All passengers,  Response Data = Code 5, passenger service info")
    @Test
    public void Scenario6() {

        try {
         Code_0_All_passengers_Response_Data_paxservice_info.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("Custom  list: Code 42  - Specific SSR")
    @Test
    public void Scenario7() {

        try {
            Code_42_specific_SSR.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

}
