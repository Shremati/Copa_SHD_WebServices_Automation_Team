package MODULES.WAVE3.Boarding;

import MODULES.WAVE3.Boarding.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class Boarding {
    Boarding() {
        createFolders(getResponseDirectory() + "Boarding");
    }

    @Description("BS_01 - Start Boarding using Boarding option as ‘Sequence’")
    @Test
    public void Scenario1() {
        //change flt number or date before run
        try {
            Start_Boarding_using_Boarding_option_as_sequence.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("BS_08 - Input with more PassengerFlightInfo information with Function as BoardPassenger, BoardingOption as ‘Seat’ for a flight number")
    @Test
    public void Scenario2() {

        try {

            Input_with_more_PassengerFlightInfo.Execute();  //Negative Scenario

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("BS_11 - Start CancelBoardedPassenger function with BoardingOption as ‘Seat’")
    @Test
    public void Scenario3() {

        try {
            Start_CancelBoardedPassenger_function.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("BS_13 - AbortBoarding for a particular flight")
    @Test
    public void Scenario4() {

        try {
            AbortBoarding_for_a_particular_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("BS_14 - Start BoardingComplete function for a flight number")
    @Test
    public void Scenario5() {

        try {
            Start_BoardingComplete_function.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }
    @Description("BS_02 - Start_Boarding_function_using_Boarding_option_as_Seat")
    @Test
    public void Scenario6() {

        try {
            Start_Boarding_function_using_Boarding_option_as_Seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("BS_09 - Start_DisplayBoardingStatus_function_with_BoardingOption_as_Sequence_for_a_flight_number")
    @Test
    public void Scenario7() {

        try {
            Start_DisplayBoardingStatus_function_with_BoardingOption_as_Sequence_for_a_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("BS_04 - Start_BoardPassenger_function_using_BoardingOption_as_Sequence")
    @Test
    //Check for data with manual team before executing the Test Case
    public void Scenario8() {

        try {
            Start_BoardPassenger_function_using_BoardingOption_as_Sequence.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }
}