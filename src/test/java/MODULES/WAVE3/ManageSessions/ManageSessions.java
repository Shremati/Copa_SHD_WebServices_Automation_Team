package MODULES.WAVE3.ManageSessions;

import MODULES.WAVE3.ManageSessions.API_Tests.Create_a_booking_for_a_group;
import MODULES.WAVE3.ManageSessions.API_Tests.Create_a_booking_for_two_segments;
import MODULES.WAVE3.ManageSessions.API_Tests.Modify_name;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 Scenarios
@Listeners(TestListener.class)
public class ManageSessions {

    ManageSessions() {
        createFolders(getResponseDirectory() + "ManageSessions");
    }

    @Test(description = "MS_24 - Modify_name")
    public void MS_24() {

        try {
            Modify_name.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MS_24 failed due to :" + e);
        }

    }

    @Test(description = "MS_31 - Create_a_booking_for_a_group_FinalizeSession")
    public void MS_31() {

        try {
            Create_a_booking_for_a_group.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MS_31 failed due to :" + e);
        }

    }

    @Test(description = "MS_03 - Create a booking with 2 segments, 2 passengers (1 frequent flyer), stored fare, 2 telephones, 1 remark and ticketing")
    public void MS_03() {

        try {
            Create_a_booking_for_two_segments.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MS_03 failed due to :" + e);
        }
    }
}
