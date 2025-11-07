package GENERICS;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assertions
{

    public static void AssertWarning(Response response, Boolean assertType)
    {
        if(assertType==true)
        {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(response.getBody().asString().contains("Warnings"),"It doesnt contain Warning in the response so it failed");
            //will give true if response contains "Warnings" otherwise false and if false it will show the message

        }
        else
        {
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertFalse(response.getBody().asString().contains("Warnings"),"It contains Warnings in the response so it failed");
            //AssertFalse checks if "Warnings" is present in body, it will check the fact that
            // if it's not present in the response, then it will pass the test case , otherwise if its present the testcase will fail and the message is shown
        }

    }


    public static void AssertResponseTime(Response response, Long expectedResponseTime)
    {
        response.then().time(Matchers.lessThan(expectedResponseTime));
    }

    public static String GetActualValue(Response response,String tag)
    {
        Pattern pattern = Pattern.compile("<"+tag+">(.*?)</"+tag+">");
        Matcher matcher = pattern.matcher(response.asPrettyString());
        if (matcher.find())
        {
            String nsBarValue = matcher.group(1);
            return nsBarValue;
        }
        else
        {
            return null;
        }
    }
}
