package MODULES.WAVE3.AuthorizationService;

import MODULES.WAVE3.AuthorizationService.API_Tests.Approval_for_visa_credit_card;
import MODULES.WAVE3.AuthorizationService.API_Tests.Error_text_for_Visa_credit_card;
import MODULES.WAVE3.AuthorizationService.API_Tests.Invalid_Request;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 Scenarios
public class AuthorizationService {

    AuthorizationService() {
        createFolders(getResponseDirectory() + "AuthorizationService");
    }

    @Description("AuthS_01 - Approval for Visa credit card")
    @Test
    public void AuthS_01() {

        try {
            Approval_for_visa_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AuthS_01 failed due to :" + e);
        }
    }

    @Description("AuthS_03 - Error_text_for_Visa_credit_card")
    @Test
    public void AuthS_03() {

        try {
            Error_text_for_Visa_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AuthS_03 failed due to :" + e);
        }
    }

    @Description("AuthS_12 - Invalid_Request")
    @Test
    public void AuthS_12() {

        try {
            Invalid_Request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AuthS_12 failed due to :" + e);
        }

    }
}

//    ******** Out of Scope *********

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


