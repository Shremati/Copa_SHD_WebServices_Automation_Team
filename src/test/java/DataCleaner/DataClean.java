package DataCleaner;

import java.io.File;

public class DataClean
{

    public static void main(String[] args)
    {


        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\AdvancePassengerInfo");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }

        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\AirScheduleService");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }

        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\AirportPassengerList");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }

        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\Availability");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }

        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\Boarding");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }

        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\Checkin");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }

        for(int i=1;i<=1;i++)
        {
            File fileReports = new File(".\\src\\test\\java\\SOAPResponses\\WAVE3\\CreateBookingService");
            String[] myReports;
            if (fileReports.isDirectory()) {
                myReports = fileReports.list();
                for (int j = 0; j < myReports.length; j++) {
                    File myFile = new File(fileReports, myReports[j]);
                    myFile.delete();
                }
            }
        }





    }

}