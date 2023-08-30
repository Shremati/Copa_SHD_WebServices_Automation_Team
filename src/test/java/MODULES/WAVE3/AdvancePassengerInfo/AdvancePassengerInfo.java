package MODULES.WAVE3.AdvancePassengerInfo;

import MODULES.WAVE3.AdvancePassengerInfo.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class AdvancePassengerInfo {

    AdvancePassengerInfo() {
        createFolders(getResponseDirectory() + "AdvancePassengerInfo");
    }


    @Description("Display API requirements for a single passenger in booking.")
    @Test
    public void Scenario1() {
        try {
            Single_Pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Display API requirements for the passengers as single surname-multiple names in booking")
    @Test
    public void Scenario2() {
        try {
            Single_surname_multiple_names.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("DisplayAPI error - Invalid passenger name (Negative Scenario)")
    @Test
    public void Scenario3() {
        try {
            DisplayAPI_error_Invalid_passenger_name.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Display API requirements in a single request for passengers in different bookings")
    @Test
    public void Scenario4() {
        try {
            Passengers_in_different_booking_in_single_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("ModifyAPI_Collect_API_for_a_Single_passenger")
    @Test
    public void Scenario5() {
        try {

            Collect_API_for_a_Single_passenger.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("ModifyAPI_Update and Delete API data")
    @Test
    public void Scenario6() {
        try {

            Modify_API_for_deleting_API_data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("Display_API_pax_types_2_adts_1_infant_without_seat")
    @Test
    public void Scenario7() {
        try {

            Display_API_pax_types_2_adts_1_infant_without_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("Collect_API_for_a_single_pax_alt")
    @Test
    public void Scenario8() {
        try {

            Collect_API_for_a_single_pax_alt.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("Collect_API_1adult_and_1infant_without_seats")
    @Test
    public void Scenario9() {
        try {

            Collect_API_1adult_and_1infant_without_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("Collect_API_for_2pax_in_different_bookings")
    @Test
    public void Scenario10() {
        try {

            Collect_API_for_2pax_in_different_bookings.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }

    @Description("Delete_API_data_Address")
    @Test
    public void Scenario11() {
        try {

            Delete_API_data_Address.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("Delete_API_data_Document_info")
    @Test
    public void Scenario12() {
        try {

            Delete_API_data_Document_info.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }

    @Description("Display_API_error_invalid_booking")
    //We are entering a random PNR in the Excel sheet, manually
    @Test
    public void Scenario13() {
        try {

            Display_API_error_invalid_booking.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("Display_API_error_invalid_passenger_name")
    @Test
    public void Scenario14() {
        try {

            Display_API_error_invalid_passenger_name.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("Display_API_error_not_API_flight")
    @Test
    public void Scenario15() {
        try {

            Display_API_error_not_API_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("Partially_collect_API_for_one_name_in_single_surname")
    @Test
    public void Scenario16() {
        try {

            Partially_collect_API_for_one_name_in_single_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }

    @Description("Display_API_pax_types_2adts_2infants_without_seats")
    @Test
    public void Scenario17() {
        try {

            Display_API_pax_types_2adts_2infants_without_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 17 failed due to :" + e);
        }
    }


    @Description("Display_API_pax_types_adt_infant_with_seat_infant_without_seat")
    @Test
    public void Scenario18() {
        try {

            Display_API_pax_types_adt_infant_with_seat_infant_without_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 18 failed due to :" + e);
        }
    }

    @Description("Display_API_requirements_for_Secure_Flight")
    @Test
    public void Scenario19() {
        try {

            Display_API_requirements_for_Secure_Flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 19 failed due to :" + e);
        }
    }

    @Description("Display_API_requirements_only_for_specific_flight")
    @Test
    public void Scenario20() {
        try {

            Display_API_requirements_only_for_specific_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 20 failed due to :" + e);
        }
    }

    @Description("Display_specific_flight_multiple_pax_names")
    //Here we are giving connecting segments, Eg.: PTY-SCL, SCL-PTY, PTY-SAL
    @Test
    public void Scenario21() {
        try {

            Display_specific_flight_multiple_pax_names.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 21 failed due to :" + e);
        }
    }
}
