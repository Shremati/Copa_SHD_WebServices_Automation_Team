package MODULES.WAVE3.DisplayLoyaltyAccountService;

import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Display_loyalty_account;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Display_partner_airline_elite_member_loyalty_account;
import MODULES.WAVE3.DisplayLoyaltyAccountService.API_Tests.Error_On_Display_Loyalty_Account_Invalid_Loyalty_Account;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

@Listeners(TestListener.class)
public class DisplayLoyaltyAccountService {

    DisplayLoyaltyAccountService() {
        createFolders(getResponseDirectory() + "DisplayLoyaltyAccountService");
    }

    @Test(description = "DLAS_01 - Display loyalty account")
    public void DLAS_01() {

        try {
            Display_loyalty_account.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DLAS_01 failed due to :" + e);
        }
    }

    @Test(description = "DLAS_04 - Display Partner Airline Elite member loyalty account")
    public void DLAS_04() {

        try {
            Display_partner_airline_elite_member_loyalty_account.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DLAS_04 failed due to :" + e);
        }
    }

    @Test(description = "DLAS_02 - Error On Display Loyalty Account Invalid Loyalty Account")
    public void DLAS_02() {

        try {
            Error_On_Display_Loyalty_Account_Invalid_Loyalty_Account.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("DLAS_02 failed due to :" + e);
        }
    }
}
