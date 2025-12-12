package MODULES.WAVE3.TicketingService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.ModifyTicketingService.API_Tests.*;
import MODULES.WAVE3.TicketingService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//6 Scenarios
@Listeners(TestListener.class)
public class TicketingService {

    TicketingService() throws IOException {
        createFolders(getResponseDirectory() + "TicketingService");
        FlightBooking.bookFlight("TicketingService");
    }

    @Test(description = "TIS_01 - Ticket a booking with one flight, one passenger with credit card form of payment")
    public void TIS_01() {

        try {
            Ticket_a_booking_with_one_flight_one_passenger_with_credit_card_fop.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TIS_01 failed due to :" + e);
        }

    }

    @Test(description = "TIS_03 - Ticket one passenger, 6 flights in booking with credit card form of payment (conjunction tickets)")
    public void TIS_03() {

        try {
            Ticket_1passenger_6flights_booking_with_creditcard_fop_conjunction_tickets.Execute();

            //Last Segment PTY-LAX doesn't always work fine, so PTY-SFO is taken. Give proper flights/markets for all segments which have seats
            //Dont change the flt numbers , change the dates instead
        } catch (Exception e) {
            failTest(e);
            System.out.println("TIS_03 failed due to :" + e);
        }
    }

    @Test(description = "TIS_20 - Issue bulk ticket for a PNR with two pax")
    //Shares direct 004 test cases shows TIS_20 as TIS_22
    public void TIS_20() {

        try {
            Issue_bulk_ticket_for_a_pnr_with_two_pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TIS_20 failed due to :" + e);
        }

    }

    @Test(description = "TIS_21 - Issue inclusive tour ticket for a pnr with two pax")
    public void TIS_21() {

        try {
            Issue_inclusive_tour_ticket_for_a_pnr_with_two_pax.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TIS_21 failed due to :" + e);
        }
    }

    @Test(description = "TIS_33 - Issue ticket for a booking with an infant")
    public void TIS_33() {

        try {
            Issue_ticket_for_a_booking_with_an_infant.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TIS_33 failed due to :" + e);
        }

    }

    @Test(description = "TIS_07 - Ticket with check form of payment")
    public void TIS_07() {

        try {
            Ticket_with_check_form_of_payment.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TIS_07 failed due to :" + e);
        }

    }

}

//    @Description("Print an Eticket")
//    @Test
//    public void Scenario3() {
//
//        try {
//            Print_an_eticket.Execute();
//
//        } catch (Exception e) {
//            System.out.println("SCENARIO 3 failed due to :" + e);
//        }
//
//    }
//
//
//    @Description("Reissue - add collect with credit card")
//    @Test
//    public void Scenario4() {
//
//        try {
//            Reissue_add_collect_with_credit_card.Execute();
//
//        } catch (Exception e) {
//            failTest(e);
//            System.out.println("SCENARIO 4 failed due to :" + e);
//        }
//
//    }

//    TIS_07 -	Ticket with check form of payment(Not Automated)