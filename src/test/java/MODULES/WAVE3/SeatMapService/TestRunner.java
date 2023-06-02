package MODULES.WAVE3.SeatMapService;

import MODULES.WAVE3.ScreenTextService.API_Tests.Error_screentext_not_allowed_entry;
import MODULES.WAVE3.ScreenTextService.API_Tests.Send_entry;
import MODULES.WAVE3.SeatMapService.API_Tests.Display_single_737_aircraft_on_one_leg_flight_map_contains_two_comp;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TestRunner {

    @Description("Display a single 737 aircraft on a one legged flight. Map contains 2 compartments")
    @Test
    public void Scenario1() {

        try {
            Display_single_737_aircraft_on_one_leg_flight_map_contains_two_comp.Execute();

        } catch (Exception e) {
            System.out.println("SCENARIO 1 failed due to :" + e);
        }
    }

}
