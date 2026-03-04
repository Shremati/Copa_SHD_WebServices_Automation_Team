package frameworkconstants;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FrameworkConstants {

    public static List<String[]> testResults = new ArrayList<>();
    public static String reportLocation=null;
    public static String BaseURL_Tulsa = "http://shdsitm.airservices.svcs.entsvcs.net:18119";      //TULSA SIT
    public static String BaseURL_Azure = "http://sit-shd-ws-lbi.dxctravel.svcs.entsvcs.com:8080";  //AZURE SIT
    public static String SharesCURL    = "http://mo-shd-ws-lbi.dxctravel.svcs.entsvcs.com:18117";
    public static String SharesBuild35 = "http://sit-shd-ws-lbi.dxctravel.svcs.entsvcs.com:18117";
    public static String UAT = "http://uat-shd-ws-lbi.dxctravel.svcs.entsvcs.com:18117";
    public static String VP1URL = "http://sitc-shd-ws-lbi.dxctravel.svcs.entsvcs.com:18117";

    public static String getBaseURL() { return SharesCURL;}

    public static long ResponseTime = 200000;

    //    ***************************** POST REQUESTS ***********************************

    public static String advancepassengerinfo = "/AirlineSOA/AdvancePassengerInfoService/API";
    public static String airportpassengerlist = "/AirlineSOA/AirportPassengerListService/passengerList";
    public static String airscheduleservice = "/AirlineSOA/AirScheduleService/getScheduleDisplay";
    public static String airinventoryservice = "/AirlineSOA/AirInventoryService/getAirInventory";
    public static String availability = "/AirlineSOA/AvailabilityService/getAvailability";
    public static String boarding = "/AirlineSOA/BoardingService/boarding";
    public static String checkin = "/AirlineSOA/CheckInService/checkIn";
    public static String createbookingservice = "/AirlineSOA/CreateBookingService/createBooking";
    public static String departurecontrolservice = "/AirlineSOA/DepartureControlService/departureControl";
    public static String displaybookingservice = "/AirlineSOA/DisplayBookingService/displayBooking";
    public static String displayloyaltyaccountservice = "/AirlineSOA/DisplayLoyaltyAccountService/displayAccount";
    public static String encodedecodeservice = "/AirlineSOA/EncodeDecodeService/encodeDecode";
    public static String modifybookingservice = "/AirlineSOA/ModifyBookingService/modifyBooking";
    public static String modifyinventoryservice = "/AirlineSOA/ModifyInventoryService/modifyInventory";
    public static String modifyticketingservice = "/AirlineSOA/ModifyTicketingService/modifyTicket";
    public static String queueservice = "/AirlineSOA/QueueService/queue";
    public static String referenceservice = "/AirlineSOA/ReferenceService/getReference";
    public static String screentextservice = "/AirlineSOA/ScreenTextService/screenText";
    public static String seatmapservice = "/AirlineSOA/SeatMapService/retrieveFlightSeatMap";
    public static String synchronizeticketservice = "/AirlineSOA/SynchronizeTicketService/synchronizeTicket";
    public static String ticketcontroloservice = "/AirlineSOA/TicketControlService/requestControl";
    public static String timaticservice = "/AirlineSOA/TimaticService/displayTimatic";
    public static String ticketing = "/AirlineSOA/TicketingService/ticketing";
    public static String flifo = "/AirlineSOA/FlifoService/getFlifo";
    public static String passengerlistservice = "/AirlineSOA/PassengerListService/passengerList";
    public static String flightdepartureinfoservice = "/AirlineSOA/FlightDepartureInfoService/getFlightDepartureInfo";
    public static String authorizationservice = "/AirlineSOA/AuthorizationService/authorize";
    public static String issueticketservice = "/AirlineSOA/TicketingService/ticketing";
    public static String emdairlinesystemupdateservice = "/AirlineSOA/EMDAirlineSystemUpdateService/systemUpdate";
    public static String managesessions = "/AirlineSOA/ManageSessionService/manageSession";
    public static String displayticketservices = "/AirlineSOA/DisplayTicketService/displayTickets";
    public static String issueticket = "/AirlineSOA/TicketingService/ticketing";
    public static String connectionFlightInfo = "/AirlineSOA/ConnectionFlightInfoService/connectionFlightInfo";
    public static String departureControlDisplay = "/AirlineSOA/DepartureControlDisplayService/departureControlDisplay";
    public static String modifySeatMap ="/AirlineSOA/ModifySeatMapService/modifySeatMap";
    public static String standby = "/AirlineSOA/StandbyService/standby";
    public static String agentsine ="/AirlineSOA/AgentSineService/agentSine";
    public static String bagtags = "/AirlineSOA/BagTagDisplayService/bagTagDisplay";
    public static String processmealreport = "/AirlineSOA/MealReportService/processMealReport";
    public static String crewreportservice="/AirlineSOA/CrewReportService/processCrewReport";


    public static String getAdvancepassengerinfo() {
        return advancepassengerinfo;
    }
    public static String getAirportpassengerlist() {
        return airportpassengerlist;
    }
    public static String getAirscheduleservice() {
        return airscheduleservice;
    }
    public static String getAirinventoryservice() {return airinventoryservice;}
    public static String getAvailability() {
        return availability;
    }
    public static String getBoarding() {
        return boarding;
    }
    public static String getCheckin() {
        return checkin;
    }
    public static String getCreatebookingservice() {
        return createbookingservice;
    }
    public static String getDeparturecontrolservice() {
        return departurecontrolservice;
    }
    public static String getDisplaybookingservice() {
        return displaybookingservice;
    }
    public static String getDisplayloyaltyaccountservice() {
        return displayloyaltyaccountservice;
    }
    public static String getEncodedecodeservice() {
        return encodedecodeservice;
    }
    public static String getModifybookingservice() {
        return modifybookingservice;
    }
    public static String getModifyinventoryservice() {
        return modifyinventoryservice;
    }
    public static String getModifyticketingservice() {
        return modifyticketingservice;
    }
    public static String getPassengerlistservice() {
        return passengerlistservice;
    }
    public static String getQueueservice() {
        return queueservice;
    }
    public static String getReferenceservice() {
        return referenceservice;
    }
    public static String getScreentextservice() {
        return screentextservice;
    }
    public static String getSeatmapservice() {
        return seatmapservice;
    }
    public static String getSynchronizeticketservice() {
        return synchronizeticketservice;
    }
    public static String getTicketcontroloservice() {return ticketcontroloservice;}
    public static String getTimaticservice() {
        return timaticservice;
    }
    public static String getTicketing() {return ticketing;}
    public static String getFlifo() {
        return flifo;
    }
    public static String getFlightdepartureinfoservice() {
        return flightdepartureinfoservice;
    }
    public static String getAuthorizationservice() {
        return authorizationservice;
    }
    public static String getIssueticketservice() {
        return issueticketservice;
    }
    public static String getEmdairlinesystemupdateservice() {
        return emdairlinesystemupdateservice;
    }
    public static String getDisplayticketservices() {
        return displayticketservices;
    }
    public static String getIssueticket() {
        return issueticket;
    }
    public static String getManagesessions() {
        return managesessions;
    }
    public static String getConnectionFlightInfo(){ return connectionFlightInfo;}
    public static String getDepartureControlDisplay(){ return departureControlDisplay; }
    public static String getModifySeatMap(){return modifySeatMap;}
    public static String getStandby(){ return standby;}
    public static String getAgentsine() { return agentsine; }
    public static String getCrewreportservice() { return crewreportservice; }
    public static String getProcessmealreport() { return processmealreport; }
    public static String getBagtags() { return bagtags; }


// ************************* FilePaths Wave3 ********************************

    public static String Temp_requestPath = ".\\src\\test\\java\\GENERICS\\Temp_Request.xml";
    public static String Temp_responsePath = ".\\src\\test\\java\\GENERICS\\Temp_Response.xml";
    public static String requestDirectory = ".\\src\\test\\java\\XMLRequestDirectory\\WAVE3\\";
    public static String responseDirectory = ".\\src\\test\\java\\SOAPResponses\\WAVE3\\";
    public static String TestData = ".\\src\\test\\java\\TestData\\Scenario_TestData.xlsx";
    public static String AllureResults = ".\\allure-results";
    public static String AllureReports = ".\\allure-report";
    public static String target = ".\\target";
    public static String Reports = System.getProperty("user.dir");

    public static HashMap<String, ArrayList<String>> availableFlights = new HashMap<>();


    public static String getTestData() {
        return TestData;
    }
    public static String getTemp_requestPath() {
        return Temp_requestPath;
    }
    public static String getTemp_responsePath() {
        return Temp_responsePath;
    }
    public static String getRequestDirectory() {
        return requestDirectory;
    }
    public static String getResponseDirectory() {
        return responseDirectory;
    }
    public static String getAllureReports() {
        return AllureReports;
    }
    public static String getAllureResults() {
        return AllureResults;
    }
    public static String getTarget() {
        return target;
    }
    public static String getReports() {
        return Reports;
    }


}
