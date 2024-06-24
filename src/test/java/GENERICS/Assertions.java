package GENERICS;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;

public class Assertions
{

    public static void AssertWarning(Response response, Boolean assertType)
    {
        if(assertType==true)
        {
            Assert.assertTrue(response.getBody().asString().contains("Warnings"),"It doesnt contain Warning in the response so it failed");
            //will give true if response contains "Warnings" otherwise false and if false it will show the message

        }
        else
        {
            Assert.assertFalse(response.getBody().asString().contains("Warnings"),"It contains Warnings in the response so it failed");
            //AssertFalse checks if "Warnings" is present in body, it will check the fact that
            // if it's not present in the response, then it will pass the test case , otherwise if its present the testcase will fail and the message is shown
        }

    }


    public static void AssertResponseTime(Response response, Long expectedResponseTime)
    {
        response.then().time(Matchers.lessThan(expectedResponseTime));
    }

}
