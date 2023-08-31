package MODULES.WAVE3.AuthorizationService;

import MODULES.WAVE3.AuthorizationService.API_Tests.Approval_for_visa_credit_card;
import MODULES.WAVE3.AuthorizationService.API_Tests.Error_text_for_Visa_credit_card;
import MODULES.WAVE3.AuthorizationService.API_Tests.Invalid_Request;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AuthorizationService {

    AuthorizationService() {
        createFolders(getResponseDirectory() + "AuthorizationService");
    }

    @Description("Approval for Visa credit card")
    @Test
    public void Scenario1() {

        try {
            Approval_for_visa_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Error_text_for_Visa_credit_card")
    @Test
    public void Scenario2() {

        try {
            Error_text_for_Visa_credit_card.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Invalid_Request")
    @Test
    public void Scenario3() {

        try {
            Invalid_Request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
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

}
