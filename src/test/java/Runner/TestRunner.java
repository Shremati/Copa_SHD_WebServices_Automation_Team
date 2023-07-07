package Runner;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;


public class TestRunner {


    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Test Suite");

        // Add test classes from different modules
        List<String> classes = new ArrayList<>();
//        classes.add("MODULES.WAVE3.ModifyTicketingService.TestRunner");
//        classes.add("MODULES.WAVE3.AdvancePassengerInfo.TestRunner");
        classes.add("MODULES.WAVE3.EMDAirlineSystemUpdate.TestRunner");
        classes.add("MODULES.WAVE3.EncodeDecodeService.TestRunner");
//        classes.add("MODULES.WAVE3.PassengerListService.API_Tests.Display_passenger_list_Inbound_connection_option");
//        classes.add("MODULES.WAVE3.PassengerListService.API_Tests.Multiple_passenger_list_request");
//        classes.add("MODULES.WAVE3.PassengerListService.API_Tests.display_passenger_list_All_option");

        suite.setFileName(String.valueOf(classes));

        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        testNG.setXmlSuites(suites);
        testNG.run();
    }
}

