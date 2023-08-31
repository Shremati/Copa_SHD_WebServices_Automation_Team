package MODULES.WAVE3.TicketingService;

import MODULES.WAVE3.ModifyTicketingService.API_Tests.*;
import MODULES.WAVE3.TicketingService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class TicketingService {

    TicketingService() {
        createFolders(getResponseDirectory() + "TicketingService");
    }

    @Description("Ticket a booking with one flight, one passenger with credit card form of payment")
    @Test
    public void Scenario1() {

        try {
            Ticket_a_booking_with_one_flight_one_passenger_with_credit_card_fop.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }

    }

    @Description("Ticket one passenger, 6 flights in booking with credit card form of payment (conjunction tickets)")
    @Test
    public void Scenario2() {

        try {
            Ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets.Execute();

            //Last Segment PTY-LAX doesn't always work fine, so PTY-SFO is taken. Give proper flights/markets for all segments which have seats
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
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }


    @Description("Reissue - add collect with credit card")
    @Test
    public void Scenario4() {

        try {
            Reissue_add_collect_with_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }

    @Description("Issue bulk ticket for a PNR with two pax")
    @Test
    public void Scenario5() {

        try {
            Issue_bulk_ticket_for_a_pnr_with_two_pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }

    }

    @Description("Issue inclusive tour ticket for a pnr with two pax")
    @Test
    public void Scenario6() {

        try {
            Issue_inclusive_tour_ticket_for_a_pnr_with_two_pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }

    }


    @Description("Issue ticket for a booking with an infant")
    @Test
    public void Scenario7() {

        try {
            Issue_ticket_for_a_booking_with_an_infant.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }

    }

}
