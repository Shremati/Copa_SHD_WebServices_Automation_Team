package MODULES.WAVE3.SynchronizeTicketService;

import GENERICS.FlightBooking;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Class;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Flight_No;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Name;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

//3 scenarios
@Listeners(TestListener.class)
public class SynchronizeTicketService
{
    SynchronizeTicketService() throws IOException {
        createFolders(getResponseDirectory()+"SynchronizeTicketService");
        FlightBooking.bookFlight("SynchronizeTicketService");
    }

    @Test(description = "STS_07 - Adjust flight number and flight Date")
    public void STS_07()
    {

        try
        {
            Adjust_Flight_No.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STS_07 failed due to :"+e);
        }



    }
    @Test(description = "STS_01 - Adjust Customer Name")
    public void STS_01()
    {

        try
        {
            Adjust_Name.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STS_01 failed due to :"+e);
        }

    }
    @Test(description = "STS_03 - Adjust Class of service")
    public void STS_03()
    {

        try
        {
            Adjust_Class.Execute();

        }catch(Exception e)
        {
            failTest(e);
            System.out.println("STS_03 failed due to :"+e);
        }
    }

}

