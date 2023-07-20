package MODULES.WAVE3.ModifyTicketingService;

import MODULES.WAVE3.ModifyTicketingService.API_Tests.*;
import MODULES.WAVE3.PassengerListService.API_Tests.Display_passenger_list_Inbound_connection_option;
import MODULES.WAVE3.PassengerListService.API_Tests.Multiple_passenger_list_request;
import MODULES.WAVE3.PassengerListService.API_Tests.display_passenger_list_All_option;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ModifyTicketingService {
    ModifyTicketingService() {
        createFolders(getResponseDirectory() + "ModifyTicketingService");
    }

    @Description("Void a Ticket")
    @Test
    public void Scenario1() {

        try {
            Void_a_ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }

    }

    @Description("Multiple passenger list request")
    @Test
    public void Scenario2() {

        try {
            Refund_multiple_tickets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Print an Eticket")
    @Test
    public void Scenario3() {

        try {
            Print_an_eticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }

    @Description("Refund Error - cancell all segments prior refund")
    @Test
    public void Scenario4() {

        try {
            Refund_error_cancell_all_segments_prior_refund.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }

    @Description("Void Error - no valid coupons to void (ticket already voided)")
    @Test
    public void Scenario5() {

        try {
            Void_error_no_valid_coupons_to_void_ticket_already_voided.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }

}
