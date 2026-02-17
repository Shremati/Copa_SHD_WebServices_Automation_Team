package MODULES.WAVE3.BagTagsDisplayService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.BagTagsDisplayService.API_Tests.*;
//import MODULES.WAVE3.BagTagsDisplayService.API_Tests.Bag_Tag_Display_All;
//import MODULES.WAVE3.BagTagsDisplayService.API_Tests.Bag_Tag_Display_OA;
//import MODULES.WAVE3.BagTagsDisplayService.API_Tests.Bag_Tag_Display_by_Tag_Number_OA;
//import MODULES.WAVE3.BagTagsDisplayService.API_Tests.Display_Bag_Tag_By_Tag_Number;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;
@Listeners(TestListener.class)
public class BagTagDisplayService {

    BagTagDisplayService() throws IOException {
        createFolders(getResponseDirectory() + "BagTagDisplayService");
        FlightBooking.bookFlight("BagTags");
    }

    //@Description("BTD_01 - Bag Tag Display All")
    @Test(description = "BTD_01 - Bag Tag Display All")
    public void BTD_01() {
        try {
            Bag_Tag_Display_All.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BTD_01 failed due to :" + e);
        }
    }

    //@Description("BTD_02 - Bag Tag Display OA")      //Expected response: "No bagtags found"
    @Test(description = "BTD_02 - Bag Tag Display OA")
    public void BTD_02() {
        try {
            Bag_Tag_Display_OA.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("BTD_02 failed due to :" + e);
        }
    }

    //@Description("BTD_03 - Display Bag Tag By Tag Number")
    @Test(description = "BTD_03 - Display Bag Tag By Tag Number")
    public void BTD_03() {
        try {

            Display_Bag_Tag_By_Tag_Number.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }


    //@Description("BTD_04 - Bag Tag Display by Tag Number OA")
    @Test(description = "BTD_04 - Bag Tag Display by Tag Number OA")
    public void BTD_04() {
        try {

            Bag_Tag_Display_by_Tag_Number_OA.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }

}