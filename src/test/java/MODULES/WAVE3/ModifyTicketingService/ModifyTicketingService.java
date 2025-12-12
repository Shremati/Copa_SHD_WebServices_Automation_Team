package MODULES.WAVE3.ModifyTicketingService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.ModifyTicketingService.API_Tests.*;
import MODULES.WAVE3.PassengerListService.API_Tests.Display_passenger_list_Inbound_connection_option;
import MODULES.WAVE3.PassengerListService.API_Tests.Multiple_passenger_list_request;
import MODULES.WAVE3.PassengerListService.API_Tests.display_passenger_list_All_option;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//5 Scenarios
@Listeners(TestListener.class)
public class ModifyTicketingService {
    ModifyTicketingService() throws IOException {
        createFolders(getResponseDirectory() + "ModifyTicketingService");
        FlightBooking.bookFlight("ModifyTicketingService");

    }

    @Test(description = "MTS_01 - Void a Ticket")
    public void MTS_01() {

        try {
            Void_a_ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MTS_01 failed due to :" + e);
        }

    }

    @Test(description = "MTS_04 - Refund multiple tickets")
    public void MTS_04() {

        try {
            Refund_multiple_tickets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MTS_04 failed due to :" + e);
        }
    }

    @Test(description = "MTS_05 - Print an Eticket")
    public void MTS_05() {

        try {
            Print_an_eticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MTS_05 failed due to :" + e);
        }

    }

    @Test(description = "MTS_12 - Refund Error - cancel all segments prior refund")
    public void MTS_12() {

        try {
            Refund_error_cancell_all_segments_prior_refund.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MTS_12 failed due to :" + e);
        }

    }

    @Test(description = "MTS_18 - Void Error - no valid coupons to void (ticket already voided)")
    public void MTS_18() {

        try {
            Void_error_no_valid_coupons_to_void_ticket_already_voided.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MTS_18 failed due to :" + e);
        }

    }

}

