package MODULES.WAVE3.Boarding;

import MODULES.WAVE3.Boarding.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//8 Scenarios
public class Boarding {
    Boarding() {
        createFolders(getResponseDirectory() + "Boarding");
    }

    @Description("BS_01 - Start Boarding using Boarding option as ‘Sequence’")
    @Test
    public void BS_01() {
        //change flt number or date before run
        try {
            Start_Boarding_using_Boarding_option_as_sequence.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BS_01 failed due to :" + e);
        }
    }

    @Description("BS_08 - Input with more PassengerFlightInfo information with Function as BoardPassenger, BoardingOption as ‘Seat’ for a flight number")
    @Test
    public void BS_08() {

        try {

            Input_with_more_PassengerFlightInfo.Execute();  //Negative Scenario

        } catch (Exception e) {
            failTest(e);
            System.out.println("BS_08 failed due to :" + e);
        }
    }


    @Description("BS_13 - AbortBoarding for a particular flight")
    @Test
    //Run BS_02 as a pre-requisite(requires shares), and use the same flt number as BS_02
    public void BS_13() {

        try {
            AbortBoarding_for_a_particular_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BS_13 failed due to :" + e);
        }
    }

    @Description("BS_14 - Start BoardingComplete function for a flight number")
    @Test
    //We need to initiate boarding, using BS_01, before running this, and also give the same flt number
    public void BS_14() {

        try {
            Start_BoardingComplete_function.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BS_14 failed due to :" + e);
        }
    }


    @Description("BS_09 - Start_DisplayBoardingStatus_function_with_BoardingOption_as_Sequence_for_a_flight_number")
    @Test
    //We need to initiate boarding, using BS_01, before running this, and also give the same flt number
    public void BS_09() {

        try {
            Start_DisplayBoardingStatus_function_with_BoardingOption_as_Sequence_for_a_flight_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BS_09 failed due to :" + e);
        }
    }

    @Description("BS_04 - Start_BoardPassenger_function_using_BoardingOption_as_Sequence")
    @Test
    //Check for data with manual team before executing the Test Case
    //We need an active sequence number,which is present in the Unreconciled window or in the All-passengers list
    //To have a sequence number, the pax has to be checked-in
    public void BS_04() {

        try {
            Start_BoardPassenger_function_using_BoardingOption_as_Sequence.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BS_04 failed due to :" + e);
        }
    }
}

//    @Description("BS_02 - Start_Boarding_function_using_Boarding_option_as_Seat")
//    @Test
//    //Run this shares command first 6-PR470/21JULPTY.INIT#SEAT
//    public void BS_02() {
//
//        try {
//            Start_Boarding_function_using_Boarding_option_as_Seat.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("BS_02 failed due to :" + e);
//        }
//    }


//    @Description("BS_11 - Start CancelBoardedPassenger function with BoardingOption as ‘Seat’")
//    @Test
//    //Run BS_02 shares command
//    //Then run this shares command 6-PRS500/05SEP/PTY#22C
//    public void BS_11() {
//
//        try {
//            Start_CancelBoardedPassenger_function.Execute();  //The pax moves from reconciled to unreconciled
//
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("BS_11 failed due to :" + e);
//        }
//    }