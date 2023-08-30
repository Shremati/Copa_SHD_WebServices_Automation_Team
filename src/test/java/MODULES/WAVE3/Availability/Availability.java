package MODULES.WAVE3.Availability;

import MODULES.WAVE3.Availability.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class Availability {
    Availability() {
        createFolders(getResponseDirectory() + "Availability");
    }

    @Description("Empty OriginDestinationInformation")
    @Test
    public void Scenario1() {
        try {
            Empty_OriginDestinationInformation.Execute();  //Negative Scenario

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

    @Description("Regular availability with defaults")
    @Test
    public void Scenario2() {
        try {
            Regular_availability_with_defaults.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 2 failed due to :" + e);
        }
    }

    @Description("Reward availability")
    @Test
    public void Scenario3() {
        try {
            Reward_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 3 failed due to :" + e);
        }
    }

    @Description("Availability overriding Point Of Sale")
    @Test
    public void Scenario4() {
        try {
            Availability_overriding_point_of_sale.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 4 failed due to :" + e);
        }
    }

    @Description("Combined reward with first available flight search")
    @Test
    public void Scenario5() {
        try {
            Combined_reward_with_first_available_flight_search.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 5 failed due to :" + e);
        }
    }

    @Description("Direct and Nonstop Flights is false, applied to all requests")
    @Test
    public void Scenario6() {
        try {
            Direct_and_nonstop_flights_is_false_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 6 failed due to :" + e);
        }
    }

    @Description("Direct and Nonstop Flights only, applied to all requests")
    @Test
    public void Scenario7() {
        try {
            Direct_and_nonstop_flights_only_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 7 failed due to :" + e);
        }
    }

    @Description("Direct and Nonstop Flights only, applied to first request ")
    @Test
    public void Scenario8() {
        try {
            Direct_and_nonstop_flights_only_applied_to_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 8 failed due to :" + e);
        }
    }

    @Description("Filter by equipment type, all requests")
    @Test
    public void Scenario9() {
        try {
            Filter_by_equipment_type_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 9 failed due to :" + e);
        }
    }

    @Description("First available flight search find flights with 2 seats in Q class")
    @Test
    public void Scenario10() {
        try {
            First_available_flight_search_find_flights_with_2_seats_in_Q_class.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 10 failed due to :" + e);
        }
    }

    @Description("Get non stop flights only on first request")
    @Test
    public void Scenario11() {
        try {
            Get_non_stop_flights_only_on_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 11 failed due to :" + e);
        }
    }

    @Description("Get non stop flights, all requests")
    @Test
    public void Scenario12() {
        try {
            Get_non_stop_flights_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 12 failed due to :" + e);
        }
    }

    @Description("Manually constructured connections")
    @Test
    public void Scenario13() {
        try {
            Manually_constructured_connections.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 13 failed due to :" + e);
        }
    }

    @Description("Max requests exceeded")
    @Test
    public void Scenario14() {
        try {
            Max_requests_exceeded.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 14 failed due to :" + e);
        }
    }

    @Description("Maximum connections of 1 on all requests")
    @Test
    public void Scenario15() {
        try {
            Maximum_connections_of_1_on_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 15 failed due to :" + e);
        }
    }

    @Description("Maximum stops of 0, applied to all requests")
    @Test
    public void Scenario16() {
        try {
            Maximum_stops_of_0_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 16 failed due to :" + e);
        }
    }

    @Description("Maximum stops of 0, applied to only to 2nd request")
    @Test
    public void Scenario17() {
        try {
            Maximum_stops_of_0_applied_to_only_to_2nd_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 17 failed due to :" + e);
        }
    }

    @Description("Maximum stops of 1, applied to all requests")
    @Test
    public void Scenario18() {
        try {
            Maximum_stops_of_1_applied_to_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 18 failed due to :" + e);
        }
    }

    @Description("Missing destination location")
    @Test
    public void Scenario19() {
        try {
            Missing_destination_location.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 19 failed due to :" + e);
        }
    }

    @Description("Untruncated availability")
    @Test
    public void Scenario20() {
        try {
            Untruncated_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 20 failed due to :" + e);
        }
    }

    @Description("Missing origin location")
    @Test
    public void Scenario21() {
        try {
            Missing_origin_location.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 21 failed due to :" + e);
        }
    }

    @Description("Multiple requests")
    @Test
    public void Scenario22() {
        try {
            Multiple_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 22 failed due to :" + e);
        }
    }

    @Description("Nonstops on first request, Maximum connections of 1 on second request")
    @Test
    public void Scenario23() {
        try {
            Nonstops_on_first_request_Maximum_connections_of_1_on_second_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 23 failed due to :" + e);
        }
    }

    @Description("Online connections only for first request")
    @Test
    public void Scenario24() {
        try {
            Online_connections_only_for_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 24 failed due to :" + e);
        }
    }

    @Description("Online connections only, for all requests")
    @Test
    public void Scenario25() {
        try {
            Online_connections_only_for_all_requests.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 25 failed due to :" + e);
        }
    }

    @Description("Regular availability with maximum responses specified")
    @Test
    public void Scenario26() {
        try {
            Regular_availability_with_maximum_responses_specified.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 26 failed due to :" + e);
        }
    }

    @Description("Request specific carriers, only first request")
    @Test
    public void Scenario27() {
        try {
            Request_specific_carriers_only_first_request.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 27 failed due to :" + e);
        }
    }

    @Description("Traffic restriction in availability")
    @Test
    public void Scenario28() {
        try {
            Traffic_restriction_in_availability.Execute();

        } catch (Exception e) {
            failTest(e);
            System.out.println("SCENARIO 28 failed due to :" + e);
        }
    }

}