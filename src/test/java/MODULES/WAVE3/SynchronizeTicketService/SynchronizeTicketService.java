package MODULES.WAVE3.SynchronizeTicketService;

import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Class;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Flight_No;
import MODULES.WAVE3.SynchronizeTicketService.API_Tests.Adjust_Name;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import static GENERICS.Utils.createFolders;
import static GENERICS.Utils.failTest;
import static frameworkconstants.FrameworkConstants.getResponseDirectory;

public class SynchronizeTicketService
{
    SynchronizeTicketService() {
        createFolders(getResponseDirectory() + "SynchronizeTicketService");
    }

    @Description("STS_07 - Adjust Flight No and Date")
    @Test
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
    @Description("STS_01 - Adjust Name")
    @Test
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
    @Description("STS_03 - Adjust Class")
    @Test
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

