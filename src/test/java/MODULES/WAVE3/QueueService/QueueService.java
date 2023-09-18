package MODULES.WAVE3.QueueService;

import MODULES.WAVE3.QueueService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//15 Scenarios
public class QueueService {


    QueueService() {
        createFolders(getResponseDirectory() + "QueueService");
    }


    @Description("QS_02 - Display queue booking all items full data format not remove from queue")
    @Test
    public void QS_02() {

        try {
            Display_queue_booking_all_items_full_data_format_not_remove_from_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_02 failed due to :" + e);
        }

    }

    @Description("QS_07 - Display queue booking from empty queue (Negative Scenario)")
    @Test
    public void QS_07() {

        try {
            Display_queue_booking_from_empty_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_07 failed due to :" + e);
        }

    }


    @Description("QS_11 - Queue booking")
    @Test
    public void QS_11() {

        try {
            Queue_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_11 failed due to :" + e);
        }

    }

    @Description("QS_15 - Display message")
    @Test
    public void QS_15() {

        try {
            Display_message.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_15 failed due to :" + e);
        }

    }

    @Description("QS_18 - Display And Remove Message Default City")
    @Test
    public void QS_18() {

        try {
            Display_And_Remove_Message_Default_City.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_18 failed due to :" + e);
        }

    }

    @Description("QS_20 - Display and remove message")
    @Test
    public void QS_20() {

        try {
            Display_and_remove_message.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_20 failed due to :" + e);
        }

    }

    @Description("QS_26 - Queue Multiple Messages")
    @Test
    public void QS_26() {

        try {
            Queue_Multiple_Messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_26 failed due to :" + e);
        }

    }

    @Description("QS_29 - Display All Queue Cities")
    @Test
    public void QS_29() {

        try {
            Display_All_Queue_Cities.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_29 failed due to :" + e);
        }

    }

    @Description("QS_33 - Transfer Queue Today to End Date")
    @Test
    public void QS_33() {

        try {
            Transfer_Queue_Today_to_End_Date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_33 failed due to :" + e);
        }

    }

    @Description("QS_36 - Abort schedule change queue transfer")
    @Test
    public void QS_36() {

        try {
            Abort_schedule_change_queue_transfer.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_36 failed due to :" + e);
        }

    }

    @Description("QS_38 - Sort_Queue")
    @Test
    public void QS_38() {

        try {
            Sort_Queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_38 failed due to :" + e);
        }

    }

    @Description("QS_41 - Display Booking queue count for a given city")
    @Test
    public void QS_41() {

        try {
            Display_Booking_queue_count_for_a_given_city.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_41 failed due to :" + e);
        }

    }

    @Description("QS_47 - Display queue count for Booking Message Queues")
    @Test
    public void QS_47() {

        try {
            Display_queue_count_for_Booking_Message_Queues.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_47 failed due to :" + e);
        }

    }

    @Description("QS_50 - Queue Passenger List")
    @Test
    public void QS_50() {

        try {
            Queue_Passenger_List.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_50 failed due to :" + e);
        }

    }

    @Description("QS_54 - Queue a specific passenger list")
    @Test
    public void QS_54() {

        try {
            Queue_a_specific_passenger_list.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("QS_54 failed due to :" + e);
        }

    }
}