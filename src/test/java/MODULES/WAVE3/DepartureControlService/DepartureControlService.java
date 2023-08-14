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

    @Description("Assign aircraft")
    @Test
    public void Scenario1() {

        try {
            assign_aircraft.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Assign aircraft with generic and specific seat reaccommodation")
    @Test
    public void Scenario2() {

        try {
            assign_aircraft_with_generic_specific_seat_reaccommodation.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }


    @Description("Assign aircraft")
    @Test
    public void Scenario3() {

        try {
            start_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Block more than one seat")
    @Test
    public void Scenario4() {

        try {
            block_more_than_one_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Error on block seat - Invalid seat number")
    @Test
    public void Scenario5() {

        try {
            error_on_block_seat_invalid_seat_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("Unblock more than one seat")
    @Test
    public void Scenario6() {

        try {
            unblock_more_than_one_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("Define inoperative seat")
    @Test
    public void Scenario7() {

        try {
            define_inoperative_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("Clear inoperative seat")
    @Test
    public void Scenario8() {

        try {
            define_inoperative_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("Cancel Misconnect")
    @Test
    public void Scenario9() {

        try {
            cancel_misconnect.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("Reinstate connecting passengers")
    @Test
    public void Scenario10() {

        try {
            reinstate_connecting_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }

    @Description("Restrict checkin")
    @Test
    public void Scenario11() {

        try {
            restrict_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("Display board point messages")
    @Test
    public void Scenario12() {

        try {
            display_board_point_messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("Add/Update/Delete board point messages")
    @Test
    public void Scenario13() {

        try {
            add_Update_Delete_board_point_messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("Restrict boarding")
    @Test
    public void Scenario14() {

        try {
            restrict_boarding.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("Remove boarding restriction")
    @Test
    public void Scenario15() {

        try {
            remove_boarding_restriction.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }
    @Description("clear_inoperative_seat")
    @Test
    public void Scenario16() {

        try {
            clear_inoperative_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }
}