package MODULES.WAVE3.EncodeDecodeService;

import MODULES.WAVE3.EncodeDecodeService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//5 Scenarios
public class EncodeDecodeService {

    EncodeDecodeService() {
        createFolders(getResponseDirectory() + "EncodeDecodeService");
    }

    @Description("EDS_01 - Decode city code LAX")
    @Test
    public void EDS_01() {

        try {
            Decode_city_code_lax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_01 failed due to :" + e);
        }
    }

    @Description("EDS_03 - Decode airline CO")
    @Test
    public void EDS_03() {

        try {
            Decode_airline_co.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_03 failed due to :" + e);
        }
    }

    @Description("EDS_04 - Encode airline United")
    @Test
    public void EDS_04() {

        try {
            Encode_airline_united.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_04 failed due to :" + e);
        }
    }

    @Description("EDS_7 - Request mutliple mixed messages of airport, airline and country")
    @Test
    public void EDS_7() {

        try {
            Request_mutliple_mixed_messages_of_city_airport_airline_country_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_7 failed due to :" + e);
        }
    }

    @Description("EDS_15 - Decode city code LAX specifying an agent sine and airport code for sign in")
    @Test
    public void EDS_15() {

        try {
            Decode_citycode_lax_specifying_agent_sine_airport_code_sign_in.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_15 failed due to :" + e);
        }
    }

    @Description("EDS_02 - Encode city South")
    @Test
    public void EDS_02() {

        try {
            Encode_city_South.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_02 failed due to :" + e);
        }
    }

    @Description("EDS_05 - Decode country US")
    @Test
    public void EDS_05() {

        try {
            Decode_country_US.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_05 failed due to :" + e);
        }
    }

    @Description("EDS_06 - Encode country South")
    @Test
    public void EDS_06() {

        try {
            Encode_country_South.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_06 failed due to :" + e);
        }
    }

    @Description("EDS_08 - Request with empty conversion")
    @Test
    public void EDS_08() {

        try {
            Request_with_empty_conversion.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_08 failed due to :" + e);
        }
    }

    @Description("EDS_09 - Request with mixed errors and correct conversion types")
    @Test
    public void EDS_09() {

        try {
            Request_with_mixed_errors_and_correct_conversion_types.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_09 failed due to :" + e);
        }
    }

    @Description("EDS_10 - Encode decode codeshare flight")
    @Test
    public void EDS_10() {

        try {
            Encode_decode_codeshare_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_10 failed due to :" + e);
        }
    }

    @Description("EDS_13 - Decode city code LAX using default values for sign in")
    @Test
    public void EDS_13() {

        try {
            Decode_city_code_LAX_using_default_values_for_sign_in.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_13 failed due to :" + e);
        }
    }

    @Description("EDS_14 - Decode city code LAX specifying a duty code for sign in")
    @Test
    public void EDS_14() {

        try {
            Decode_city_code_LAX_specifying_a_duty_code_for_sign_in.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_14 failed due to :" + e);
        }
    }

    @Description("EDS_18 - Invalid information in POS for sign in invalid ERSP psswrd")
    @Test
    public void EDS_18() {

        try {
            Invalid_information_in_POS_for_sign_in_invalid_ERSP_psswrd.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_18 failed due to :" + e);
        }
    }

    @Description("EDS_17 - Decode city code LAX specifying an agent sine, duty code, airport code and ERSP password for sign in")
    @Test
    public void EDS_17() {

        try {
            Decode_city_code_LAX_specifying_agent_sine_duty_code_airport_code_and_ERSP_psswrd_for_sign_in.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("EDS_17 failed due to :" + e);
        }
    }
}

