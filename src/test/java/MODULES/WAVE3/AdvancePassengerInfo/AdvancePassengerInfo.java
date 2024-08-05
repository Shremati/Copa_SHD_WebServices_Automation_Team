package MODULES.WAVE3.AdvancePassengerInfo;

import MODULES.WAVE3.AdvancePassengerInfo.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;

import static frameworkconstants.FrameworkConstants.getResponseDirectory;
@Listeners(TestListener.class)
public class AdvancePassengerInfo {

    AdvancePassengerInfo() {
        createFolders(getResponseDirectory() + "AdvancePassengerInfo");
    }

//    Choose an APIS Flight otherwise it will give "NOT APIS FLIGHT" msg ---> PTY-MEX  CM120 is an APIS FLight but not always , only on some days. So try changing dates

    @Test(description = "APIS_01 - Display API requirements for a single passenger in booking.")
    public void APIS_01() {
        try {
            Single_Pax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_01 failed due to :" + e);
        }
    }

    @Test(description = "APIS_02 -Display API requirements for the passengers as single surname-multiple names in booking")
    public void APIS_02() {
        try {
            Single_surname_multiple_names.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_02 failed due to :" + e);
        }
    }

    @Test(description = "APIS_11 - Display API requirements in a single request for passengers in different bookings")
    public void APIS_11() {
        try {
            Passengers_in_different_booking_in_single_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_11 failed due to :" + e);
        }
    }

    @Test(description = "APIS_13 - ModifyAPI  Collect API for a Single passenger")
    public void APIS_13() {
        try {

            Collect_API_for_a_Single_passenger.Execute();


        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_13 failed due to :" + e);
        }
    }


    @Test(description = "APIS_20 - ModifyAPI Update and Delete API data")
    public void APIS_20() {
        try {

            Modify_API_for_deleting_API_data.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_20 failed due to :" + e);
        }
    }

    @Test(description = "APIS_04 - Display API pax types 2 adults 1 infant without seat")
    public void APIS_04() {
        try {

            Display_API_pax_types_2_adts_1_infant_without_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_04 failed due to :" + e);
        }
    }

    @Test(description = "APIS_21 - Collect API for a single pax alt")
    public void APIS_21() {
        try {

            Collect_API_for_a_single_pax_alt.Execute();
//AgencyNames taken from DisplayAPIS response

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_21 failed due to :" + e);
        }
    }

    @Test(description = "APIS_17 - Collect API 1adult and 1infant without seats")
    public void APIS_17() {
        try {

            Collect_API_1adult_and_1infant_without_seats.Execute();
//AgencyNames taken from DisplayAPIS response
        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_17 failed due to :" + e);
        }
    }


    @Test(description = "APIS_16 - Collect API for 2pax in different bookings")
    public void APIS_16() {
        try {

            Collect_API_for_2pax_in_different_bookings.Execute();

          //AgencyNames taken from both DisplayAPIS responses
        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_16 failed due to :" + e);
        }
    }

    @Test(description = "APIS_18 - Delete API data Address")
    public void APIS_18() {
        try {

            Delete_API_data_Address.Execute();
//AgencyNames taken from DisplayAPIS response

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_18 failed due to :" + e);
        }
    }

    @Test(description = "APIS_19 - Delete API data Document info")
    public void APIS_19() {
        try {

            Delete_API_data_Document_info.Execute();
//AgencyNames taken from DisplayAPIS response

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_19 failed due to :" + e);
        }
    }

    @Test(description = "APIS_08 - Display API error invalid booking")
    public void APIS_08() {
        try {

            Display_API_error_invalid_booking.Execute();
//We are entering a random PNR in the Excel sheet, manually

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_08 failed due to :" + e);
        }
    }

    @Test(description = "APIS_06 - Display API error invalid passenger name")
    public void APIS_06() {
        try {

            Display_API_error_invalid_passenger_name.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_06 failed due to :" + e);
        }
    }

    @Test(description = "APIS_07 - Display API error not API flight")
    public void APIS_07() {
        try {

            Display_API_error_not_API_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_07 failed due to :" + e);
        }
    }

    @Test(description = "APIS_15 - Partially collect API for one name in a Single surname - multiple name passenger")
    public void APIS_15() {
        try {

            Partially_collect_API_for_one_name_in_single_surname.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_15 failed due to :" + e);
        }
    }

    @Test(description = "APIS_05 - Display API  2 Adults and 2 Infants without seat")
    public void APIS_05() {
        try {

            Display_API_pax_types_2adts_2infants_without_seats.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_05 failed due to :" + e);
        }
    }


    @Test(description = "APIS_03 - Display API Adult, Infant with seat and Infant without seat")
    public void APIS_03() {
        try {

            Display_API_pax_types_adt_infant_with_seat_infant_without_seat.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_03 failed due to :" + e);
        }
    }

    @Test(description = "APIS_12 - Display API Secure Flight requirements")
    public void APIS_12() {
        try {

            Display_API_requirements_for_Secure_Flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_12 failed due to :" + e);
        }
    }

    @Test(description = "APIS_09 - Display API requirements only for specific flight")
    public void APIS_09() {
        try {

            Display_API_requirements_only_for_specific_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_09 failed due to :" + e);
        }
    }

    @Test(description = "APIS_10 - Display API Specific flight and multiple passenger names")
    //Here we are giving connecting segments, Eg.: PTY-SCL, SCL-PTY, PTY-SAL
    public void APIS_10() {
        try {

            Display_specific_flight_multiple_pax_names.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("APIS_10 failed due to :" + e);
        }
    }
}

// APIS_23 Collect API for Secure Flight - Known Traveler Number(Not automated)