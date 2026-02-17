package MODULES.WAVE3.QueueService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.QueueService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//15 Scenarios
@Listeners(TestListener.class)
public class QueueService {
    QueueService() throws IOException {
        createFolders(getResponseDirectory() + "QueueService");
        FlightBooking.bookFlight("QueueService");
    }

//    QueueService() {
//        createFolders(getResponseDirectory() + "QueueService");
//    }

    @Test(description = "QS_02 - Display queue booking first item only, full data format and do not remove from queue")
    public void QS_02() {

        try {
            Display_queue_booking_first_item_only_full_data_format_do_not_remove_from_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_02 failed due to :" + e);
        }

    }

    @Test(description = "QS_07 - Display queue booking from empty queue (Negative Scenario)")
    //We need a pseudocity code with no queue PNRs
    public void QS_07() {

        try {
            Display_queue_booking_from_empty_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_07 failed due to :" + e);
        }

    }

    @Test(description = "QS_11 - Queue booking")
    //The psedocity code has to be the departure airport code
    public void QS_11() {

        try {
            Queue_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_11 failed due to :" + e);
        }

    }

    @Test(description = "QS_15 - Display message")
    public void QS_15() {

        try {
            Display_message.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_15 failed due to :" + e);
        }

    }

    @Test(description = "QS_18 - Display And Remove Message Default City")
    public void QS_18() {

        try {
            Display_And_Remove_Message_Default_City.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_18 failed due to :" + e);
        }

    }

    @Test(description = "QS_20 - Display and remove message")
    //Needs to be checked
    public void QS_20() {

        try {
            Display_and_remove_message.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_20 failed due to :" + e);
        }

    }

    @Test(description = "QS_26 - Queue Multiple Messages (default city Msg + Spv queue)")
    public void QS_26() {

        try {
            Queue_Multiple_Messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_26 failed due to :" + e);
        }

    }

    @Test(description = "QS_29 - Display All Queue Cities")
    public void QS_29() {

        try {
            Display_All_Queue_Cities.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_29 failed due to :" + e);
        }

    }

    @Test(description = "QS_33 - Transfer Queue Today to End Date")
    public void QS_33() {

        try {
            Transfer_Queue_Today_to_End_Date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_33 failed due to :" + e);
        }

    }

    @Test(description = "QS_36 - Abort schedule change queue transfer")
    public void QS_36() {

        try {
            Abort_schedule_change_queue_transfer.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_36 failed due to :" + e);
        }

    }

    @Test(description = "QS_38 - Sort_Queue")
    public void QS_38() {

        try {
            Sort_Queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_38 failed due to :" + e);
        }

    }

    @Test(description = "QS_41 - Display Booking queue count for a given city")
    public void QS_41() {

        try {
            Display_Booking_queue_count_for_a_given_city.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_41 failed due to :" + e);
        }

    }

    @Test(description = "QS_47 - Display queue count for Booking Message Queues")
    public void QS_47() {

        try {
            Display_queue_count_for_Booking_Message_Queues.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_47 failed due to :" + e);
        }

    }

    @Test(description = "QS_50 - Queue Passenger List")
    public void QS_50() {

        try {
            Queue_Passenger_List.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_50 failed due to :" + e);
        }

    }

    @Test(description = "QS_54 - Queue a specific passenger list")
    public void QS_54() {

        try {
            Queue_a_specific_passenger_list.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_54 failed due to :" + e);
        }

    }
}