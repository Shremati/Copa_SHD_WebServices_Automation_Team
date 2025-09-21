package MODULES.WAVE3.DepartureControlService;

import MODULES.WAVE3.DepartureControlService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//17 Scenarios
@Listeners(TestListener.class)
public class DepartureControlService {

    DepartureControlService()
    {
        createFolders(getResponseDirectory()+"DepartureControlService");
    }

    @Test(description = "DPS_01 - Assign aircraft")
    public void DPS_01() {

        try {
            assign_aircraft.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_01 failed due to :" + e);
        }
    }

    @Test(description = "DPS_07 - Assign aircraft with generic and specific seat re accommodation")
    public void DPS_07() {

        try {
            assign_aircraft_with_generic_specific_seat_reaccommodation.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_07 failed due to :" + e);
        }
    }


    @Test(description = "DPS_10 - Start Checkin")
    public void DPS_10() {

        try {
            start_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_10 failed due to :" + e);
        }
    }

    @Test(description = "DPS_13 - Block more than one seat")
    //ensure that 3 consecutive seats should be available, as we have given seat count as 3
    public void DPS_13() {

        try {
            block_more_than_one_seat.Execute(); //Give proper seat details in data sheet, in GUI - Tools menu --> Seat Map, give the flt details

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_13 failed due to :" + e);
        }
    }

    @Test(description = "DPS_14 - Error on block seat - Invalid seat number")
    public void DPS_14() {

        try {
            error_on_block_seat_invalid_seat_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_14 failed due to :" + e);
        }
    }

    @Test(description = "DPS_16 - Unblock more than one seat")
    //use DPS_13 to block one/more than one seat and use that blocked seat here
    public void DPS_16() {

        try {
            unblock_more_than_one_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_16 failed due to :" + e);
        }
    }

    @Test(description = "DPS_18 - Define inoperative seat")
    public void DPS_18() {

        try {
            define_inoperative_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_18 failed due to :" + e);
        }
    }

    @Test(description = "DPS_20 - Clear inoperative seat")
    //use DPS_18 to define inoperative seat and use that inoperative seat here
    public void DPS_20() {

        try {
            clear_inoperative_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_20 failed due to :" + e);
        }
    }

    @Test(description = "DPS_22 - Cancel Misconnect")
    public void DPS_22() {

        try {
            cancel_misconnect.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_22 failed due to :" + e);
        }
    }

    @Test(description = "DPS_25 - Reinstate connecting passengers")
    //We need to give the same test data details as DPS_22, because it reinstates the same cancel misconnect flight, taken in DPS_22
    public void DPS_25() {

        try {
            reinstate_connecting_passengers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_25 failed due to :" + e);
        }
    }

    @Test(description = "DPS_28 - Restrict checkin")
    public void DPS_28() {

        try {
            restrict_checkin.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_28 failed due to :" + e);
        }
    }

    @Test(description = "DPS_32 - Display board point messages")
    public void DPS_32() {

        try
        {
            display_board_point_messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_32 failed due to :" + e);
        }
    }

    @Test(description = "DPS_36 - Add/Update/Delete board point messages + Invalid item to process")
    public void DPS_36() {

        try {
            add_Update_Delete_board_point_messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_36 failed due to :" + e);
        }
    }

    @Test(description = "DPS_38 - Restrict boarding")
    public void DPS_38() {

        try {
            restrict_boarding.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_38 failed due to :" + e);
        }
    }

    @Test(description = "DPS_39 - Remove boarding restriction")
    //Compartment code is the class, and we dont have to change the compartment code and seat count
    public void DPS_39() {

        try {
            remove_boarding_restriction.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_39 failed due to :" + e);
        }
    }

    @Test(description = "DPS_02 - Assign aircraft to downline station.")
    public void DPS_02() {

        try {
            assign_aircraft_to_downline_station.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_02 failed due to :" + e);
        }
    }

    @Test(description = "DPS_30 - Restrict checkin and close flight")
    public void DPS_30() {

        try {
            close_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DPS_30 failed due to :" + e);
        }
    }

}

//DPS_30	Close flight(Not Automated)  automated on 14-04-2025
//DPS_02	Assign aircraft to downline station
