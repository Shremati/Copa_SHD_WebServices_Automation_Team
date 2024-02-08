package MODULES.WAVE3.DisplayTicketService;

import MODULES.WAVE3.DisplayTicketService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//5 Scenarios
public class DisplayTicketService {

    DisplayTicketService() {
        createFolders(getResponseDirectory() + "DisplayTicketService");
    }

    @Description("DTS_27 - Search_for_a_non_existent_PNR")
    @Test
    public void DTS_27() {

        try {
            Search_for_a_non_existent_PNR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_27 failed due to :" + e);
        }

    }

    @Description("DTS_26 - Search_for_a_PNR_with_no_ETKT")
    @Test
    public void DTS_26() {

        try {
            Search_for_a_PNR_with_no_ETKT.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_26 failed due to :" + e);
        }

    }

    @Description("DTS_01 - Retrieve_E_Ticket_History_information_for_a_given_PNR")
    @Test
    public void DTS_01() {

        try {
            Retrieve_E_Ticket_History_information_for_a_given_PNR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_01 failed due to :" + e);
        }

    }

    @Description("DTS_10 - Multiple_Tickets")
    @Test
    public void DTS_10() {

        try {
            Multiple_Tickets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_10 failed due to :" + e);
        }

    }

    @Description("DTS_19 - Bulk Ticket or Inclusive Tour ticket")
    @Test
    public void DTS_19() {

        try {
            Bulk_Ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_19 failed due to :" + e);
        }

    }


    @Description("DTS_02 - Display the history information for the given two tickets")
    @Test
    public void DTS_02() {

        try {
            Display_the_history_information_for_the_given_two_tickets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_02 failed due to :" + e);
        }

    }

    @Description("DTS_09 - Single ticket")
    @Test
    public void DTS_09() {

        try {
            Single_ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_09 failed due to :" + e);
        }
    }

    @Description("DTS_12 - Conjunctive ticket primary")
    @Test
    public void DTS_12() {

        try {
            Conjunctive_ticket_primary.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_12 failed due to :" + e);
        }

    }

    @Description("DTS_13 - Conjunctive ticket conjunctive")
    @Test
    public void DTS_13() {

        try {
            Conjunctive_ticket_conjunctive.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_13 failed due to :" + e);
        }

    }

    @Description("DTS_15 - Error on display ticket - no ticket number")
    @Test
    public void DTS_15() {

        try {
            Error_on_display_ticket_no_ticket_number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_15 failed due to :" + e);
        }

    }

    @Description("DTS_16 - Error on display ticket - too many ticket numbers")
    @Test
    public void DTS_16() {

        try {
            Error_on_display_ticket_too_many_ticket_numbers.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_16 failed due to :" + e);
        }

    }

    @Description("DTS_17 - Error on display ticket no ticket data")
    @Test
    public void DTS_17() {

        try {
            Error_on_display_ticket_no_ticket_data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_17 failed due to :" + e);
        }

    }

    @Description("DTS_18 - Error on display ticket no POS data")
    @Test
    public void DTS_18() {

        try {
            Error_on_display_ticket_no_POS_data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DTS_18 failed due to :" + e);
        }

    }



}

//Conjunctive ticket (primary + conjunctive) (Not Automated)