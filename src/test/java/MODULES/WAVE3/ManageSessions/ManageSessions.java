package MODULES.WAVE3.ManageSessions;

import MODULES.WAVE3.ManageSessions.API_Tests.Create_a_booking_for_a_group;
import MODULES.WAVE3.ManageSessions.API_Tests.Create_a_booking_for_two_segments;
import MODULES.WAVE3.ManageSessions.API_Tests.Modify_name;
import MODULES.WAVE3.QueueService.API_Tests.Display_queue_booking_all_items_full_data_format_not_remove_from_queue;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 Scenarios
public class ManageSessions {

    ManageSessions() {
        createFolders(getResponseDirectory() + "ManageSessions");
    }

    @Description("MS_24 - Modify_name")
    @Test
    public void MS_24() {

        try {
            Modify_name.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MS_24 failed due to :" + e);
        }

    }

    @Description("MS_31 - Create_a_booking_for_a_group_FinalizeSession")
    @Test
    public void MS_31() {

        try {
            Create_a_booking_for_a_group.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MS_31 failed due to :" + e);
        }

    }

    @Description("MS_03 - Create Booking 2 Segments - releaseToken")
    @Test
    public void MS_03() {

        try {
            Create_a_booking_for_two_segments.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("MS_03 failed due to :" + e);
        }
    }
}
