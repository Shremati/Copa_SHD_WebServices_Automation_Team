package MODULES.WAVE3.SynchronizeTicketService;

import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Class;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Flight_No;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Name;
import io.qameta.allure.Description;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;
@Listeners(TestListener.class)
public class SynchronizeTicketService
{
    SynchronizeTicketService() {
        createFolders(getResponseDirectory() + "SynchronizeTicketService");
    }
    @Test(description = "STS_07 - Adjust Flight No and Date")
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
    @Test(description = "STS_01 - Adjust Name")
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
    @Test(description = "STS_03 - Adjust Class")
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

