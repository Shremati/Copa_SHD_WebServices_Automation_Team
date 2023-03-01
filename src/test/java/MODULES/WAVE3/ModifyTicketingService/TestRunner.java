package MODULES.WAVE3.ModifyTicketingService;

import MODULES.WAVE3.ModifyTicketingService.API_Tests.Refund_multiple_tickets;
import MODULES.WAVE3.ModifyTicketingService.API_Tests.Void_a_ticket;
import MODULES.WAVE3.PassengerListService.API_Tests.Display_passenger_list_Inbound_connection_option;
import MODULES.WAVE3.PassengerListService.API_Tests.Multiple_passenger_list_request;
import MODULES.WAVE3.PassengerListService.API_Tests.display_passenger_list_All_option;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestRunner
{

    @Description("Void a Ticket")
    @Test
    public void Scenario1()
    {

        try
        {
            Void_a_ticket.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 1 failed due to :"+e);
        }

    }

    @Description("Multiple passenger list request")
    @Test
    public void Scenario2()
    {

        try
        {
            Refund_multiple_tickets.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 2 failed due to :"+e);
        }



    }

//    @Description("Display the passenger list Inbound connection option")
//    @Test
//    public void Scenario3() {
//
//        try {
//            Display_passenger_list_Inbound_connection_option.Execute();
//
//        } catch (Exception e) {
//            System.out.println("SCENARIO 3 failed due to :" + e);
//        }
//
//    }

    }
