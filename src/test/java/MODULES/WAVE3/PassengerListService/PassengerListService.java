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

//4 Scenarios
public class PassengerListService {

    PassengerListService() {
        createFolders(getResponseDirectory() + "PassengerListService");
    }

    @Description("PLS_01 - Display the passenger list All option")
    @Test
    public void PLS_01() {

        try {
            display_passenger_list_All_option.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("PLS_01 failed due to :" + e);
        }
    }

    @Description("PLS_18 - Multiple passenger list request")
    @Test
    public void PLS_18() {

        try {
            Multiple_passenger_list_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("PLS_18 failed due to :" + e);
        }
    }

    @Description("PLS_07 - Display the passenger list Inbound connection option")
    @Test
    public void PLS_07() {

        try {
            Display_passenger_list_Inbound_connection_option.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("PLS_07 failed due to :" + e);
        }

    }

    @Description("PLS_06 - Pax List Request Positive Non Revenue")
    @Test
    public void PLS_06() {

        try {
            Pax_List_Request_Positive_Non_Revenue.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("PLS_06 failed due to :" + e);
        }

    }
}
