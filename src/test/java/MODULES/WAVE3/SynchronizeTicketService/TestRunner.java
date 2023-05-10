package MODULES.WAVE3.SynchronizeTicketService;


import MODULES.WAVE3.SynchronizeTicketService.API_Tests.sync_ticket_adjust_flight_number_and_flight_date;
import MODULES.WAVE3.TicketControlService.API_Tests.Get_control_of_one_coupon_of_one_ticket;
import MODULES.WAVE3.TicketControlService.API_Tests.Push_control_of_multiple_coupons_within_one_ticket;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestRunner {

    @Description("Get control of one coupon of one ticket")
    @Test
    public void Scenario1() {

        try {
            Get_control_of_one_coupon_of_one_ticket.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Push control of multiple coupons within one ticket")
    @Test
    public void Scenario2() {

        try {
            Push_control_of_multiple_coupons_within_one_ticket.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Adjust flight number and flight date")
    @Test
    public void Scenario3() {

        try {
            sync_ticket_adjust_flight_number_and_flight_date.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

}
