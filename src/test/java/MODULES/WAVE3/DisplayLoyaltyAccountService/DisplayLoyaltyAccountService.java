package MODULES.WAVE3.DisplayLoyaltyAccountService;

import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Display_loyalty_account;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Display_partner_airline_elite_member_loyalty_account;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Error_On_Display_Loyalty_Account_Invalid_Loyalty_Account;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class DisplayLoyaltyAccountService {

    DisplayLoyaltyAccountService() {
        createFolders(getResponseDirectory() + "DisplayLoyaltyAccountService");
    }

    @Description("DLAS_01 - Display loyalty account")
    @Test
    public void Scenario1() {

        try {
            Display_loyalty_account.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("DLAS_04 - Display Partner Airline Elite member loyalty account")
    @Test
    public void Scenario2() {

        try {
            Display_partner_airline_elite_member_loyalty_account.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("DLAS_02 - Error On Display Loyalty Account Invalid Loyalty Account")
    @Test
    public void Scenario3() {

        try {
            Error_On_Display_Loyalty_Account_Invalid_Loyalty_Account.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }
}
