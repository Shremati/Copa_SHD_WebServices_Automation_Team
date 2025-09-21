package MODULES.WAVE3.Availability;

import MODULES.WAVE3.Availability.API_Tests.*;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//28 scenarios
@Listeners(TestListener.class)
public class Availability {

    Availability() {
        createFolders(getResponseDirectory() + "Availability");
    }

   // @Description("AVS_25 - Empty OriginDestinationInformation")
    @Test(description = "AVS_25 - Empty OriginDestinationInformation")
    public void AVS_25() {
        try {
            Empty_OriginDestinationInformation.Execute();  //Negative Scenario

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_25 failed due to :" + e);
        }
    }

    //@Description("AVS_02 - Regular availability with maximum responses specified")
    @Test(description = "AVS_02 - Regular availability with maximum responses specified")
    public void AVS_02() {
        try {
            Regular_availability_with_maximum_responses_specified.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_02 failed due to :" + e);
        }
    }
   // @Description("AVS_01 - Regular availability with defaults")
    @Test(description = "AVS_01 - Regular availability with defaults")
    public void AVS_01() {
        try {
            Regular_availability_with_defaults.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_01 failed due to :" + e);
        }
    }

    //@Description("AVS_20 - Reward availability")
    @Test(description = "AVS_20 - Reward availability")
    public void AVS_20() {
        try {
            Reward_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_20 failed due to :" + e);
        }
    }

  //  @Description("AVS_23 - Availability overriding Point Of Sale")
    @Test(description = "AVS_23 - Availability overriding Point Of Sale")
    public void AVS_23() {
        try {
            Availability_overriding_point_of_sale.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_23 failed due to :" + e);
        }
    }

   // @Description("AVS_21 - Combined reward with first available flight search")
    @Test(description = "AVS_21 - Combined reward with first available flight search")
    public void AVS_21() {
        try {
            Combined_reward_with_first_available_flight_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_21 failed due to :" + e);
        }
    }

   // @Description("AVS_06 - Direct and Nonstop Flights is false, applied to all requests")
    @Test(description = "AVS_06 - Direct and Nonstop Flights is false, applied to all requests")
    public void AVS_06() {
        try {
            Direct_and_nonstop_flights_is_false_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_06 failed due to :" + e);
        }
    }

 //   @Description("AVS_05 - Direct and Nonstop Flights only, applied to all requests")
    @Test(description = "AVS_05 - Direct and Nonstop Flights only, applied to all requests")
    public void AVS_05() {
        try {
            Direct_and_nonstop_flights_only_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_05 failed due to :" + e);
        }
    }

   // @Description("AVS_07 - Direct and Nonstop Flights only, applied to first request ")
    @Test(description = "AVS_07 - Direct and Nonstop Flights only, applied to first request")
    public void AVS_07() {
        try {
            Direct_and_nonstop_flights_only_applied_to_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_07 failed due to :" + e);
        }
    }

    //@Description("AVS_17 - Filter by equipment type, all requests")
    @Test(description = "AVS_17 - Filter by equipment type, all requests")
    public void AVS_17() {
        try {
            Filter_by_equipment_type_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_17 failed due to :" + e);
        }
    }

  //  @Description("AVS_19 - First available flight search find flights with 2 seats in Q class")
    @Test(description = "AVS_19 - First available flight search find flights with 2 seats in Q class")
    public void AVS_19() {
        try {
            First_available_flight_search_find_flights_with_2_seats_in_Q_class.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_19 failed due to :" + e);
        }
    }

    //@Description("AVS_12 - Get non stop flights only on first request")
    @Test(description = "AVS_12 - Get non stop flights only on first request")
    public void AVS_12() {
        try {
            Get_non_stop_flights_only_on_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_12 failed due to :" + e);
        }
    }

  //  @Description("AVS_11 - Get non stop flights, all requests")
    @Test(description = "AVS_11 - Get non stop flights, all requests")
    public void AVS_11() {
        try {
            Get_non_stop_flights_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_11 failed due to :" + e);
        }
    }

    //@Description("AVS_04 - Manually constructured connections")
    @Test(description = "AVS_04 - Manually constructured connections")
    public void AVS_04() {
        try {
            Manually_constructured_connections.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_04 failed due to :" + e);
        }
    }

    //@Description("AVS_28 - Max requests exceed (Negative Scenario)")
    @Test(description = "AVS_28 - Max requests exceed (Negative Scenario)")
    public void AVS_28() {
        try {
            Max_requests_exceeded.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_28 failed due to :" + e);
        }
    }

    //@Description("AVS_13 - Maximum connections of 1 on all requests")
    @Test(description = "AVS_13 - Maximum connections of 1 on all requests")
    public void AVS_13() {
        try {
            Maximum_connections_of_1_on_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_13 failed due to :" + e);
        }
    }

    //@Description("AVS_08 - Maximum stops of 0, applied to all requests")
    @Test(description = "AVS_08 - Maximum stops of 0, applied to all requests")
    public void AVS_08() {
        try {
            Maximum_stops_of_0_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_08 failed due to :" + e);
        }
    }

    //@Description("AVS_10 - Maximum stops of 0, applied to only to 2nd request")
    @Test(description = "AVS_10 - Maximum stops of 1, applied to only to 2nd request")
    public void AVS_10() {
        try {
            Maximum_stops_of_1_applied_to_only_to_2nd_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_10 failed due to :" + e);
        }
    }

    //@Description("AVS_09 - Maximum stops of 1, applied to all requests")
    @Test(description = "AVS_09 - Maximum stops of 1, applied to all requests")
    public void AVS_09() {
        try {
            Maximum_stops_of_1_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_09 failed due to :" + e);
        }
    }

    //@Description("AVS_26 - Missing destination location (Negative Scenario)")
    @Test(description = "AVS_26 - Missing destination location (Negative Scenario)")
    public void AVS_26() {
        try {
            Missing_destination_location.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_26 failed due to :" + e);
        }
    }

    //@Description("AVS_24 - Untruncated availability")
    @Test(description = "AVS_24 - Untruncated availability")
    public void AVS_24() {
        try {
            Untruncated_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_24 failed due to :" + e);
        }
    }

    //@Description("AVS_27 - Missing origin location (Negative Scenario)")
    @Test(description = "AVS_27 - Missing origin location (Negative Scenario)")
    public void AVS_27() {
        try {
            Missing_origin_location.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_27 failed due to :" + e);
        }
    }

    //@Description("AVS_03 - Multiple requests")
    @Test(description = "AVS_03 - Multiple requests")
    public void AVS_03() {
        try {
            Multiple_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_03 failed due to :" + e);
        }
    }

    //@Description("AVS_14 - Nonstops on first request, Maximum connections of 1 on second request")
    @Test(description = "AVS_14 - Nonstops on first request, Maximum connections of 1 on second request")
    public void AVS_14() {
        try {
            Nonstops_on_first_request_Maximum_connections_of_1_on_second_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_14 failed due to :" + e);
        }
    }

    //@Description("AVS_16 - Online connections only for first request")
    @Test(description = "AVS_16 - Online connections only for first request")
    public void AVS_16() {
        try {
            Online_connections_only_for_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_16 failed due to :" + e);
        }
    }

    //@Description("AVS_15 - Online connections only, for all requests")
    @Test(description = "AVS_15 - Online connections only, for all requests")
    public void AVS_15() {
        try {
            Online_connections_only_for_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_15 failed due to :" + e);
        }
    }



    //@Description("AVS_18 - Request specific carriers, only first request")
    @Test(description = "AVS_18 - Request specific carriers, only first request")
    public void AVS_18() {
        try {
            Request_specific_carriers_only_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_18 failed due to :" + e);
        }
    }

    //@Description("AVS_22 - Traffic restriction in availability")
    @Test(description = "AVS_22 - Traffic restriction in availability")
    public void AVS_22() {
        try {
            Traffic_restriction_in_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("AVS_22 failed due to :" + e);
        }
    }

}