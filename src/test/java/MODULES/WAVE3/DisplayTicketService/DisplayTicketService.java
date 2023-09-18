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
}

//Conjunctive ticket (primary + conjunctive) (Not Automated)