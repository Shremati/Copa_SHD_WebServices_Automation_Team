package MODULES.WAVE3.DepartureControlService;

import MODULES.WAVE3.DepartureControlService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class DepartureControlService {

    DepartureControlService()
    {
        createFolders(getResponseDirectory()+"DepartureControlService");
    }

    @Description("DPS_01 - Assign aircraft")
    @Test
    public void Scenario1() {

        try {
            assign_aircraft.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("DPS_07 - Assign aircraft with generic and specific seat re accommodation")
    @Test
    public void Scenario2() {

        try {
            assign_aircraft_with_generic_specific_seat_reaccommodation.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }


    @Description("DPS_10 - Start Checkin")
    @Test
    public void Scenario3() {

        try {
            start_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("DPS_13 - Block more than one seat")
    @Test
    public void Scenario4() {

        try {
            block_more_than_one_seat.Execute(); //Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("DPS_14 - Error on block seat - Invalid seat number")
    @Test
    public void Scenario5() {

        try {
            error_on_block_seat_invalid_seat_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("DPS_16 - Unblock more than one seat")
    @Test
    public void Scenario6() {

        try {
            unblock_more_than_one_seat.Execute(); //Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("DPS_18 - Define inoperative seat")
    @Test
    public void Scenario7() {

        try {
            define_inoperative_seat.Execute(); //Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("DPS_20 - Clear inoperative seat")
    @Test
    public void Scenario8() {

        try {
            clear_inoperative_seat.Execute();//Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("DPS_22 - Cancel Misconnect")
    @Test
    public void Scenario9() {

        try {
            cancel_misconnect.Execute();  //Give proper flight details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("DPS_25 - Reinstate connecting passengers")
    @Test
    public void Scenario10() {

        try {
            reinstate_connecting_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }

    @Description("DPS_28 - Restrict checkin")
    @Test
    public void Scenario11() {

        try {
            restrict_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("DPS_32 - Display board point messages")
    @Test
    public void Scenario12() {

        try {
            display_board_point_messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("DPS_36 - Add/Update/Delete board point messages")
    @Test
    public void Scenario13() {

        try {
            add_Update_Delete_board_point_messages.Execute(); //Before running test case , open the request in XML request directory and change the REMARKS which you want to add/delete

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("DPS_38 - Restrict boarding")
    @Test
    public void Scenario14() {

        try {
            restrict_boarding.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("DPS_39 - Remove boarding restriction")
    @Test
    public void Scenario15() {

        try {
            remove_boarding_restriction.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }
}

//DPS_30	Close flight(Not Automated)
//DPS_02	Assign aircraft to downline station
