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

//3 Scenarios
public class EMDAirlineSystemUpdate {

    EMDAirlineSystemUpdate() {
        createFolders(getResponseDirectory() + "EMDAirlineSystemUpdate");
    }

    @Description("ASU_02 - Association: EMD coupon 1 with ETKT coupon 1 (POS info)")
    @Test
    public void ASU_02() {

        try {
            Association_emd_coupon_1_with_etkt_coupon1_pos_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ASU_02 failed due to :" + e);
        }
    }

    @Description("ASU_06 - Association multiple coupons for primary and conjunctive etkt")
    @Test
    public void ASU_06() {

        try {
            Association_multiple_coupons_for_primary_and_conjunctive_etkt.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ASU_06 failed due to :" + e);
        }
    }

    @Description("ASU_07 - Disassociation emd coupon 1 with etkt coupon")
    @Test
    public void ASU_07() {

        try {
            Disassociation_emd_coupon_1_with_etkt_coupon_1.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("ASU_07 failed due to :" + e);
        }
    }
}
