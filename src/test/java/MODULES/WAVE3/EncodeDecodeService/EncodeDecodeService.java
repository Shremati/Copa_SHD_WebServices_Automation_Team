package MODULES.WAVE3.EncodeDecodeService;

import MODULES.WAVE3.EncodeDecodeService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class EncodeDecodeService {

    EncodeDecodeService() {
        createFolders(getResponseDirectory() + "EncodeDecodeService");
    }

    @Description("Decode city code LAX")
    @Test
    public void Scenario1() {

        try {
            Decode_city_code_lax.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Decode airline CO")
    @Test
    public void Scenario2() {

        try {
            Decode_airline_co.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Encode airline United")
    @Test
    public void Scenario3() {

        try {
            Encode_airline_united.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Request multiple mixed messages of city, airport, airline, country and flight")
    @Test
    public void Scenario4() {

        try {
            Request_mutliple_mixed_messages_of_city_airport_airline_country_flight.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Decode city code LAX specifying an agent sine and airport code for sign in")
    @Test
    public void Scenario5() {

        try {
            Decode_citycode_lax_specifying_agent_sine_airport_code_sign_in.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }
}
