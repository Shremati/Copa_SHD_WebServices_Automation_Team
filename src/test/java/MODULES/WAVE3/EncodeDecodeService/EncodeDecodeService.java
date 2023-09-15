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
}
