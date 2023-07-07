package MODULES.WAVE3.AuthorizationService;

import MODULES.WAVE3.AuthorizationService.API_Tests.Approval_for_visa_credit_card;
import MODULES.WAVE3.EncodeDecodeService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class AuthorizationService {

    @Description("Approval for Visa credit card")
    @Test
    public void Scenario1() {

        try {
            Approval_for_visa_credit_card.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

//    @Description("Decode airline CO")
//    @Test
//    public void Scenario2() {
//
//        try {
//            Decode_airline_co.Execute();
//
//        } catch (Exception e) {
//            System.out.println("SCENARIO 2 failed due to :" + e);
//        }
//    }
//
//    @Description("Encode airline United")
//    @Test
//    public void Scenario3() {
//
//        try {
//            Encode_airline_united.Execute();
//
//        } catch (Exception e) {
//            System.out.println("SCENARIO 3 failed due to :" + e);
//        }
//    }


}
