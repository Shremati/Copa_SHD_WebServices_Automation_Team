package MODULES.WAVE3.TicketControlService;


import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Association_multiple_coupons_for_primary_and_conjunctive_etkt;
import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Disassociation_emd_coupon_1_with_etkt_coupon_1;
import MODULES.WAVE3.TicketControlService.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//2 Scenarios
@Listeners(TestListener.class)
public class TicketControlService {

    TicketControlService()
    {
        createFolders(getResponseDirectory()+"TicketControlService");
    }

    @Test(description = "TCS_01 - Get control of one coupon of one ticket")
    public void TCS_01() {

        try {
            Get_control_of_one_coupon_of_one_ticket.Execute();
//           2nd Segment needs to be UA(United Airlines Segment) , So provide the markets accordingly for 2nd segment

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_01 failed due to :" + e);
        }
    }

    @Test(description = "TCS_06 - Push control of multiple coupons within one ticket")
    public void TCS_06() {

        try {
            Push_control_of_multiple_coupons_within_one_ticket.Execute();
//            2nd Segment needs to be UA(United Airlines Segment) , So provide the markets accordingly for 2nd segment

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_06 failed due to :" + e);
        }
    }
    @Test(description = "TCS_09 - Missing Validating Airline code in RequestControl request")
    public void TCS_09() {

        try {
            Missing_Validating_Airline_code_in_RequestControl_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_09 failed due to :" + e);
        }
    }

    @Test(description = "TCS_10 - Missing Validating Airline code in RedirectControl request.")
    public void TCS_10() {

        try {

            Missing_Validating_Airline_code_in_RedirectControl_request.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_10 failed due to :" + e);
        }
    }

    @Test(description = "TCS_11 - Missing the ticket document number in the RequestControl request.")
    public void TCS_11() {

        try {

            Missing_the_ticket_document_number_in_the_RequestControl_request.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_11 failed due to :" + e);
        }
    }

    @Test(description = "TCS_02 - A booking with two passengers and two tickets. Get control of one coupon for each ticket within the same PNR")
    public void TCS_02() {

        try {

            Get_control_of_one_coupon_for_each_ticket_within_the_same_PNR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_11 failed due to :" + e);
        }
    }

    @Test(description = "TCS_11 - Missing the ticket document number in the RequestControl request.")
    public void TCS_03() {

        try {

            Missing_the_ticket_document_number_in_the_RequestControl_request.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_11 failed due to :" + e);
        }
    }
}
