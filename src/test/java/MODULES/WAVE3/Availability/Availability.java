package MODULES.WAVE3.Availability;

import MODULES.WAVE3.Availability.API_Tests.Empty_OriginDestinationInformation;
import MODULES.WAVE3.Availability.API_Tests.Regular_availability_with_defaults;
import MODULES.WAVE3.Availability.API_Tests.Reward_availability;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class Availability {
    Availability() {
        createFolders(getResponseDirectory() + "Availability");
    }

    @Description("Empty OriginDestinationInformation")
    @Test
    public void Scenario1() {
        try {
            Empty_OriginDestinationInformation.Execute();  //Negative Scenario

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Regular availability with defaults")
    @Test
    public void Scenario2() {
        try {
            Regular_availability_with_defaults.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Reward availability")
    @Test
    public void Scenario3() {
        try {
            Reward_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }
}