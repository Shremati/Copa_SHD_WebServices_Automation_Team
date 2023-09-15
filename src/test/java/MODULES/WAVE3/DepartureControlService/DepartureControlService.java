package MODULES.WAVE3.DepartureControlService;

import MODULES.WAVE3.DepartureControlService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//16 Scenarios
public class DepartureControlService {

    DepartureControlService()
    {
        createFolders(getResponseDirectory()+"DepartureControlService");
    }

    @Description("DPS_01 - Assign aircraft")
    @Test
    public void DPS_01() {

        try {
            assign_aircraft.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_01 failed due to :" + e);
        }
    }

    @Description("DPS_07 - Assign aircraft with generic and specific seat re accommodation")
    @Test
    public void DPS_07() {

        try {
            assign_aircraft_with_generic_specific_seat_reaccommodation.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_07 failed due to :" + e);
        }
    }


    @Description("DPS_10 - Start Checkin")
    @Test
    public void DPS_10() {

        try {
            start_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_10 failed due to :" + e);
        }
    }

    @Description("DPS_13 - Block more than one seat")
    @Test
    public void DPS_13() {

        try {
            block_more_than_one_seat.Execute(); //Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_13 failed due to :" + e);
        }
    }

    @Description("DPS_14 - Error on block seat - Invalid seat number")
    @Test
    public void DPS_14() {

        try {
            error_on_block_seat_invalid_seat_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_14 failed due to :" + e);
        }
    }

    @Description("DPS_16 - Unblock more than one seat")
    @Test
    public void DPS_16() {

        try {
            unblock_more_than_one_seat.Execute(); //Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_16 failed due to :" + e);
        }
    }

    @Description("DPS_18 - Define inoperative seat")
    @Test
    public void DPS_18() {

        try {
            define_inoperative_seat.Execute(); //Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_18 failed due to :" + e);
        }
    }

    @Description("DPS_20 - Clear inoperative seat")
    @Test
    public void DPS_20() {

        try {
            clear_inoperative_seat.Execute();//Give proper seat details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_20 failed due to :" + e);
        }
    }

    @Description("DPS_22 - Cancel Misconnect")
    @Test
    public void DPS_22() {

        try {
            cancel_misconnect.Execute();  //Give proper flight details in data sheet

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_22 failed due to :" + e);
        }
    }

    @Description("DPS_25 - Reinstate connecting passengers")
    @Test
    public void DPS_25() {

        try {
            reinstate_connecting_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_25 failed due to :" + e);
        }
    }

    @Description("DPS_28 - Restrict checkin")
    @Test
    public void DPS_28() {

        try {
            restrict_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_28 failed due to :" + e);
        }
    }

    @Description("DPS_32 - Display board point messages")
    @Test
    public void DPS_32() {

        try {
            display_board_point_messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_32 failed due to :" + e);
        }
    }

    @Description("DPS_36 - Add/Update/Delete board point messages")
    @Test
    public void DPS_36() {

        try {
            add_Update_Delete_board_point_messages.Execute(); //Before running test case , open the request in XML request directory and change the REMARKS which you want to add/delete

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_36 failed due to :" + e);
        }
    }

    @Description("DPS_38 - Restrict boarding")
    @Test
    public void DPS_38() {

        try {
            restrict_boarding.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_38 failed due to :" + e);
        }
    }

    @Description("DPS_39 - Remove boarding restriction")
    @Test
    public void DPS_39() {

        try {
            remove_boarding_restriction.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_39 failed due to :" + e);
        }
    }
}

//DPS_30	Close flight(Not Automated)
//DPS_02	Assign aircraft to downline station
