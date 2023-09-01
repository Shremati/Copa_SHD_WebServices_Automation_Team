package MODULES.WAVE3.QueueService;

import MODULES.WAVE3.QueueService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class QueueService {


    QueueService() {
        createFolders(getResponseDirectory() + "QueueService");
    }


    @Description("Display queue booking all items full data format not remove from queue")
    @Test
    public void Scenario1() {

        try {
            Display_queue_booking_all_items_full_data_format_not_remove_from_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }

    }

    @Description("Display queue booking from empty queue (Negative Scenario)")
    @Test
    public void Scenario2() {

        try {
            Display_queue_booking_from_empty_queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }

    }


    @Description("Queue booking")
    @Test
    public void Scenario3() {

        try {
            Queue_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }

    @Description("Display message")
    @Test
    public void Scenario4() {

        try {
            Display_message.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }

    @Description("Display And Remove Message Default City")
    @Test
    public void Scenario5() {

        try {
            Display_And_Remove_Message_Default_City.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }

    }

    @Description("Display and remove message")
    @Test
    public void Scenario6() {

        try {
            Display_and_remove_message.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }

    }

    @Description("Queue Multiple Messages")
    @Test
    public void Scenario7() {

        try {
            Queue_Multiple_Messages.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }

    }

    @Description("Display All Queue Cities")
    @Test
    public void Scenario8() {

        try {
            Display_All_Queue_Cities.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }

    }

    @Description("Transfer Queue Today to End Date")
    @Test
    public void Scenario9() {

        try {
            Transfer_Queue_Today_to_End_Date.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }

    }

    @Description("Abort schedule change queue transfer")
    @Test
    public void Scenario10() {

        try {
            Abort_schedule_change_queue_transfer.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }

    }

    @Description("Sort_Queue")
    @Test
    public void Scenario11() {

        try {
            Sort_Queue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }

    }

    @Description("Display Booking queue count for a given city")
    @Test
    public void Scenario12() {

        try {
            Display_Booking_queue_count_for_a_given_city.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }

    }

    @Description("Display queue count for Booking Message Queues")
    @Test
    public void Scenario13() {

        try {
            Display_queue_count_for_Booking_Message_Queues.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }

    }

    @Description("Queue Passenger List")
    @Test
    public void Scenario14() {

        try {
            Queue_Passenger_List.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }

    }

    @Description("Queue a specific passenger list")
    @Test
    public void Scenario15() {

        try {
            Queue_a_specific_passenger_list.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }

    }
}