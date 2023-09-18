package MODULES.WAVE3.ModifyBookingService;

import MODULES.WAVE3.ModifyBookingService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class ModifyBookingService {

    ModifyBookingService() {
        createFolders(getResponseDirectory() + "ModifyBookingService");
    }

    @Description("MBS_01 - Cancel Booking")
    @Test
    public void MBS_01() {

        try {
            cancel_booking.Execute(); //ModificationType="1"  --> Cancel Booking

        } catch (Exception e) {
            failTest(e);
            System.out.println("MBS_01 failed due to :" + e);
        }
    }

    @Description("MBS_04 - Itinerary Changes")
    @Test
    public void MBS_04() {

        try {
            Itinerary_changes.Execute(); //ModificationType="5" --> Itenary Changes

        } catch (Exception e) {
            failTest(e);
            System.out.println("MBS_04 failed due to :" + e);
        }
    }

    @Description("MBS_07 - Other Changes")
    @Test
    public void MBS_07() {

        try {
            other_changes.Execute(); //ModificationType="5" -->Other Changes

        } catch (Exception e) {
            failTest(e);
            System.out.println("MBS_07 failed due to :" + e);
        }
    }


    @Description("MBS_08 - Divide PNR")
    @Test
    public void MBS_08() {

        try {
            divide_pnr.Execute(); //ModificationType="6" --> Divide PNR ( RPH=2 and RPH=4 , i.e. 2 pax getting divided)

        } catch (Exception e) {
            failTest(e);
            System.out.println("MBS_08 failed due to. :" + e);
        }

    }

    @Description("MBS_10 - Reduce PNR")
    @Test
    public void MBS_10() {

        try {
            reduce_pnr.Execute(); //ModificationType="8" -->Reduce PNR

        } catch (Exception e) {
            failTest(e);
            System.out.println("MBS_10 failed due to :" + e);
        }

    }
}
