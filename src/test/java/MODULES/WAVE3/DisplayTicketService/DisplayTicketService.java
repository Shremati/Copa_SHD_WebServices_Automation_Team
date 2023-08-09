package MODULES.WAVE3.DisplayTicketService;

import MODULES.WAVE3.DisplayTicketService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class DisplayTicketService {

    DisplayTicketService() {
        createFolders(getResponseDirectory() + "DisplayTicketService");
    }

    @Description("Search_for_a_non_existent_PNR")
    @Test
    public void Scenario1() {

        try {
            Search_for_a_non_existent_PNR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }

    }

    @Description("Search_for_a_PNR_with_no_ETKT")
    @Test
    public void Scenario2() {

        try {
            Search_for_a_PNR_with_no_ETKT.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }

    }

    @Description("Retrieve_E_Ticket_History_information_for_a_given_PNR")
    @Test
    public void Scenario3() {

        try {
            Retrieve_E_Ticket_History_information_for_a_given_PNR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }

    @Description("Multiple_Tickets")
    @Test
    public void Scenario4() {

        try {
            Multiple_Tickets.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }

    @Description("Bulk_Ticket")
    @Test
    public void Scenario5() {

        try {
            Bulk_Ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }
}
