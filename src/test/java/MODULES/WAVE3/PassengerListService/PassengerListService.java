package MODULES.WAVE3.PassengerListService;

import MODULES.WAVE3.PassengerListService.API_Tests.Display_passenger_list_Inbound_connection_option;
import MODULES.WAVE3.PassengerListService.API_Tests.Multiple_passenger_list_request;
import MODULES.WAVE3.PassengerListService.API_Tests.Pax_List_Request_Positive_Non_Revenue;
import MODULES.WAVE3.PassengerListService.API_Tests.display_passenger_list_All_option;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class PassengerListService {

    PassengerListService() {
        createFolders(getResponseDirectory() + "PassengerListService");
    }

    @Description("Display the passenger list All option")
    @Test
    public void Scenario1() {

        try {
            display_passenger_list_All_option.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Multiple passenger list request")
    @Test
    public void Scenario2() {

        try {
            Multiple_passenger_list_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Display the passenger list Inbound connection option")
    @Test
    public void Scenario3() {

        try {
            Display_passenger_list_Inbound_connection_option.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }

    }
    @Description("Pax List Request Positive Non Revenue")
    @Test
    public void Scenario4() {

        try {
            Pax_List_Request_Positive_Non_Revenue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }

    }
}
