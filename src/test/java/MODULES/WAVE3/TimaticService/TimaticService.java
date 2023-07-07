package MODULES.WAVE3.TimaticService;

import MODULES.WAVE3.TimaticService.API_Tests.*;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class TimaticService {
    @Description("Request_Visa_Info_one_destination_transit_visited_point")
    @Test
    public void Scenario1()
    {

        try
        {
            Request_Visa_Info_one_destination_transit_visited_point.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO Request_Visa_Info_one_destination_transit_visited_point failed due to :"+e);
        }

    }

    @Description("Request_Visa_Info_one_destination_transit_visited_point")
    @Test
    public void Scenario2()
    {

        try
        {
            Visa_singlepoint_normal_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO Request_Visa_Info_one_destination_transit_visited_point failed due to :"+e);
        }

    }
    @Description("Visa_singlepoint_seaman_request")
    @Test
    public void Scenario3()
    {

        try
        {
            Visa_singlepoint_seaman_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO Request_Visa_Info_one_destination_transit_visited_point failed due to :"+e);
        }

    }
    @Description("Both_DEorTR_missing_request")
    @Test
    public void Scenario4()
    {

        try
        {
            Both_DEorTR_missing_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 4 failed due to :"+e);
        }

    }
    @Description("Health_DEorTR_missing_request")
    @Test
    public void Scenario5()
    {

        try
        {
            Health_DEorTR_missing_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 5 failed due to :"+e);
        }

    }
    @Description("Visa_DEorTR_missing_request")
    @Test
    public void Scenario6()
    {

        try
        {
            Visa_DEorTR_missing_request.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 6 failed due to :"+e);
        }

    }
    @Description("Both_multipoint_gov_request")
    @Test
    public void Scenario7()
    {

        try
        {
            Both_multipoint_gov_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 7 failed due to :"+e);
        }

    }
    @Description("Both_multipoint_normal_request")
    @Test
    public void Scenario8()
    {

        try
        {
            Both_multipoint_normal_request.Execute();

        }catch(Exception e)
        {
            System.out.println("SCENARIO 8 failed due to :"+e);
        }

    }
    @Description("Both_multipoint_seaman_request")
    @Test
    public void Scenario9()
    {

        try
        {
            Both_multipoint_seaman_request.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 9 failed due to :"+e);
        }

    }
    @Description("Display_Country_List_in_a_Group")
    @Test
    public void Scenario10()
    {

        try
        {

            Display_Country_List_in_a_Group.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 10 failed due to :"+e);
        }

    }
    @Description("Display_Rules_with_Rule_Index")
    @Test
    public void Scenario11()
    {

        try
        {
            Display_Rules_with_Rule_Index.Execute();



        }catch(Exception e)
        {
            System.out.println("SCENARIO 11 failed due to :"+e);
        }

    }
    @Description("Display_the_List_of_News_Items")
    @Test
    public void Scenario12()
    {

        try
        {
            Display_the_List_of_News_Items.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 12 failed due to :"+e);
        }

    }
    @Description("Display_City_list_by_Country_code")
    @Test
    public void Scenario13()
    {

        try
        {
            Display_City_list_by_Country_code.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 13 failed due to :"+e);
        }

    }
    @Description("Display_City_list_by_country_starting_letter")
    @Test
    public void Scenario14()
    {

        try
        {

            Display_City_list_by_country_starting_letter.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 14 failed due to :"+e);
        }

    }
    @Description("Display_for_section_Passport_with_its_subsection")
    @Test
    public void Scenario15()
    {

        try
        {

            Display_for_section_Passport_with_its_subsection.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 15 failed due to :"+e);
        }

    }
    @Description("Display_for_section_Currency_with_its_subsection")
    @Test
    public void Scenario16()
    {

        try
        {

            Display_for_section_Currency_with_its_subsection.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 16 failed due to :"+e);
        }

    }
    @Description("Display_all_sections_for_a_country")
    @Test
    public void Scenario17()
    {

        try
        {

            Display_all_sections_for_a_country.Execute();


        }catch(Exception e)
        {
            System.out.println("SCENARIO 17 failed due to :"+e);
        }

    }
}

