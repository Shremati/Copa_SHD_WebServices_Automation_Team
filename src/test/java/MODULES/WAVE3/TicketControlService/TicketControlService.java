package MODULES.WAVE3.TicketControlService;


import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Association_multiple_coupons_for_primary_and_conjunctive_etkt;
import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Disassociation_emd_coupon_1_with_etkt_coupon_1;
import MODULES.WAVE3.TicketControlService.API_Tests.Get_control_of_one_coupon_of_one_ticket;
import MODULES.WAVE3.TicketControlService.API_Tests.Push_control_of_multiple_coupons_within_one_ticket;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class TicketControlService {

    TicketControlService()
    {
        createFolders(getResponseDirectory()+"TicketControlService");
    }

    @Description("Get control of one coupon of one ticket")
    @Test
    public void Scenario1() {

        try {
            Get_control_of_one_coupon_of_one_ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Push control of multiple coupons within one ticket")
    @Test
    public void Scenario2() {

        try {
            Push_control_of_multiple_coupons_within_one_ticket.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

}
