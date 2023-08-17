package MODULES.WAVE3.BagTagsDisplayService;
import MODULES.WAVE3.BagTagsDisplayService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class BagTagDisplayService {

    BagTagDisplayService() {
        createFolders(getResponseDirectory() + "BagTagDisplayService");
    }

    @Description("Bag Tag Display All")
    @Test
    public void Scenario1() {
        try {
            Bag_Tag_Display_All.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Bag Tag Display OA")
    @Test
    public void Scenario2() {
        try {
            Bag_Tag_Display_OA.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Display Bag Tag By Tag Number")

    @Test
    public void Scenario3() {
        try {
            Display_Bag_Tag_By_Tag_Number.Execute();
        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Bag Tag Display by Tag Number OA")
    @Test
    public void Scenario4() {
        try {

            Bag_Tag_Display_by_Tag_Number_OA.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }


}