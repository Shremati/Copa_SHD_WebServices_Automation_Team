package frameworkconstants;

public class FrameworkConstants
{

    public static String BaseURL="http://shdsitm.airservices.svcs.entsvcs.net:18119";
    public static String BaseURLBre="http://sit-bre-ws-lbi.dxccopaair.svcs.entsvcs.com:8581";

    public static String getBaseURL() {
        return BaseURL;
    }

    public static String getBaseURLBre() {
        return BaseURLBre;
    }


    //    ***************************** POST REQUESTS WAVE1 AND WAVE2 ***********************************

    public static String maximumBaggage="/bre-rest-1.0.1-SNAPSHOT/webapi/valuelookup?carrierCode=CM&type=maximumBaggage";
    public static String channelLookup="/bre-rest-1.0.1-SNAPSHOT/webapi/valuelookup?carrierCode=CM&type=ChannelLookup&channel=ATO";
    public static String fOPQuery="/bre-rest-1.0.1-SNAPSHOT/webapi/fop/query";

    public static String getMaximumBaggage() {
        return maximumBaggage;
    }

    public static String getChannelLookup() {
        return channelLookup;
    }

    public static String getfOPQuery() {
        return fOPQuery;
    }


    //    ***************************** POST REQUESTS ***********************************

    public static String advancepassengerinfo="/AirlineSOA/AdvancePassengerInfoService/API";
    public static String airportpassengerlist="/AirlineSOA/AirportPassengerListService/passengerList";
    public static String airscheduleservice="/AirlineSOA/AirScheduleService/getScheduleDisplay";
    public static String airinventoryservice="/AirlineSOA/AirInventoryService/getAirInventory";
    public static String availability="/AirlineSOA/AvailabilityService/getAvailability";
    public static String boarding="/AirlineSOA/BoardingService/boarding";
    public static String checkin="/AirlineSOA/CheckInService/checkIn";
    public static String createbookingservice="/AirlineSOA/CreateBookingService/createBooking";
    public static String departurecontrolservice="/AirlineSOA/DepartureControlService/departureControl";
    public static String displaybookingservice="/AirlineSOA/DisplayBookingService/displayBooking";
    public static String displayloyaltyaccountservice="/AirlineSOA/DisplayLoyaltyAccountService/displayAccount";
    public static String encodedecodeservice="/AirlineSOA/EncodeDecodeService/encodeDecode";
    public static String modifybookingservice="/AirlineSOA/ModifyBookingService/modifyBooking";
    public static String modifyinventoryservice="/AirlineSOA/ModifyInventoryService/modifyInventory";
    public static String modifyticketingservice="/AirlineSOA/ModifyTicketingService/modifyTicket";
    public static String queueservice="/AirlineSOA/QueueService/queue";
    public static String referenceservice="/AirlineSOA/ReferenceService/getReference";
    public static String screentextservice="/AirlineSOA/ScreenTextService/screenText";
    public static String seatmapservice="/AirlineSOA/SeatMapService/retrieveFlightSeatMap";
    public static String synchronizeticketservice="/AirlineSOA/SynchronizeTicketService/synchronizeTicket";
    public static String ticketcontroloservice="/AirlineSOA/TicketControlService/requestControl";
    public static String timaticservice="/AirlineSOA/TimaticService/displayTimatic";
    public static String ticketing="/AirlineSOA/TicketingService/ticketing";
    public static String flifo="/AirlineSOA/FlifoService/getFlifo";
    public static String passengerlistservice="/AirlineSOA/PassengerListService/passengerList";


    public static String getAdvancepassengerinfo() {
        return advancepassengerinfo;
    }

    public static String getAirportpassengerlist() {
        return airportpassengerlist;
    }

    public static String getAirscheduleservice() {
        return airscheduleservice;
    }

    public static String getAirinventoryservice() {
        return airinventoryservice;
    }

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

    public static String Passengerlistservice() {
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

    public static String getTicketcontroloservice() {
        return ticketcontroloservice;
    }

    public static String getTimaticservice() {
        return timaticservice;
    }

    public static String getTicketing() {
        return ticketing;
    }

    public static String getFlifo() {
        return flifo;
    }


// ************************* FilePaths Wave1 and Wave2 ********************************
    public static String requestDirectoryWave1_2=".\\src\\test\\java\\XMLRequestDirectory\\WAVE1_WAVE2\\";
    public static String responseDirectoryWave1_2=".\\src\\test\\java\\SOAPResponses\\WAVE1_WAVE2\\";


    public static String getRequestDirectoryWave1_2() {
        return requestDirectoryWave1_2;
    }

    public static String responseDirectoryWave1_2() {
        return responseDirectoryWave1_2;
    }

// ************************* FilePaths Wave3 ********************************

    public static String Temp_requestPath=".\\src\\test\\java\\GENERICS\\Temp_Request.xml";
    public static String Temp_responsePath=".\\src\\test\\java\\GENERICS\\Temp_Response.xml";
    public static String requestDirectory=".\\src\\test\\java\\XMLRequestDirectory\\WAVE3\\";
    public static String responseDirectory=".\\src\\test\\java\\SOAPResponses\\WAVE3\\";
    public static String TestData=".\\src\\test\\java\\TestData\\Scenario_TestData.xlsx";

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


}
