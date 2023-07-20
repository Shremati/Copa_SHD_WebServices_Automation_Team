package MODULES.WAVE3.EMDAirlineSystemUpdate;


import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Association_emd_coupon_1_with_etkt_coupon1_pos_info;
import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Association_multiple_coupons_for_primary_and_conjunctive_etkt;
import MODULES.WAVE3.EMDAirlineSystemUpdate.API_Tests.Disassociation_emd_coupon_1_with_etkt_coupon_1;
import MODULES.WAVE3.TicketControlService.API_Tests.Get_control_of_one_coupon_of_one_ticket;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class EMDAirlineSystemUpdate {

    EMDAirlineSystemUpdate() {
        createFolders(getResponseDirectory() + "EMDAirlineSystemUpdate");
    }

    @Description("Association: EMD coupon 1 with ETKT coupon 1 (POS info)")
    @Test
    public void Scenario1() {

        try {
            Association_emd_coupon_1_with_etkt_coupon1_pos_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Error ScreenText - Not allowed entry")
    @Test
    public void Scenario2() {

        try {
            Association_multiple_coupons_for_primary_and_conjunctive_etkt.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Error ScreenText - Not allowed entry")
    @Test
    public void Scenario3() {

        try {
            Disassociation_emd_coupon_1_with_etkt_coupon_1.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }
}
