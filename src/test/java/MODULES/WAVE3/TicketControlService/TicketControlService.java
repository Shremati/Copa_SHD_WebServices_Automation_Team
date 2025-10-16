package MODULES.WAVE3.TicketControlService;


import MODULES.WAVE3.TicketControlService.API_Tests.*;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//13 Scenarios
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
            System.out.println("TCS_02 failed due to :" + e);
        }
    }

    @Test(description = "TCS_05 - Get control of one coupon of one ticket with involuntary indicator")
    public void TCS_05() {

        try {

            Get_control_of_one_coupon_of_one_ticket_with_involuntary_indicator.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_05 failed due to :" + e);
        }
    }

    @Test(description = "TCS_14 - Push the control of coupon that the partner already has the control")
    public void TCS_14() {

        try {

            Push_the_control_of_coupon_that_the_partner_already_has_the_control.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_14 failed due to :" + e);
        }
    }

    @Test(description = "TCS_13 - The Request specifies an invalid coupon for the control")
    public void TCS_13() {

        try {

            The_Request_specifies_an_invalid_coupon_for_the_control.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_13 failed due to :" + e);
        }
    }

    @Test(description = "TCS_12 - Request the control of coupon that already has the control by host")
    public void TCS_12() {

        try {

            Request_the_control_of_coupon_that_already_has_the_control_by_host.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_12 failed due to :" + e);
        }
    }

    @Test(description = "TCS_08 - Push control of multiple ticket/coupons for multiple PNR")
    public void TCS_08() {

        try {

            Push_control_of_multiple_ticket_coupons_for_multiple_PNR.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_08 failed due to :" + e);
        }
    }

    @Test(description = "TCS_03 - Create two booking; each booking has two passengers (two tickets). Get control of one coupon for each ticket in each PNR")
    public void TCS_03() {

        try {

            Create_two_bookings_Get_control_of_one_coupon_for_each_ticket_in_each_PNR.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_03 failed due to :" + e);
        }
    }

    @Test(description = "TCS_07 - Push control of multiple coupons within one ticket when Partner already has control")
    public void TCS_07() {

        try {

            Push_control_of_multiple_coupons_within_one_ticket_when_Partner_already_has_control.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("TCS_07 failed due to :" + e);
        }
    }
}
