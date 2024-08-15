package GENERICS;

import org.testng.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Utils
{

    public static String getDate_ddMMYYYY(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

        return dateformat.format(date);
    }
    public static String getDate_YYYYMMdd(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        return dateformat.format(date);
    }
    public static String getDate_ddMMYYYYThhmmss(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy'T'hh:mm:ss");
        return dateformat.format(date);
    }
    public static String getDate_YYYYMMddThhmmss(double Days)
    {
        int days;
        days=(int)Days;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
        Date date = cal.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        return dateformat.format(date);
    }

    //Create the folders + Sub-folder
    public static void createFolders(String folderPath)
    {
        File folder = new File(folderPath);

        // Create the folders
        if (folder.mkdirs()) {
            System.out.println("Folder created successfully.");
        } else {
            System.out.println("Folder Exists");
        }
    }

    // Delete the folder along with the contents
    public static void deleteFolder(File folder)
    {
        if (folder.exists())
        {
            File[] files = folder.listFiles();

            if (files != null)
            {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Recursively delete subdirectories
                        deleteFolder(file);
                    } else {
                        // Delete files within the folder
                        file.delete();
                    }
                }
            }

            // Delete the empty folder
            folder.delete();

            System.out.println("Folder deleted successfully.");
        }
        else {
            System.out.println("Folder does not exist.");
        }
    }

    public static void deleteReports(File folder)
    {

        if (folder.exists())
        {
            File[] files = folder.listFiles();

            if (files != null)
            {
                for (File file : files)
                {
                    if (file.isDirectory() && file.getName().contains("COPA_"))
                    {
                        // Recursively delete subdirectories
                        deleteFolder(file);
                    }
                    else
                    {
                        // Delete files within the folder
                        if(file.getParent().contains("COPA_"))
                        {
                            file.delete();
                        }

                    }
                }
            }

            // Delete the empty folder
            if(folder.getName().contains("COPA_"))
            {
                folder.delete();
            }


            System.out.println("Folder deleted successfully.");
        }
        else {
            System.out.println("Folder does not exist.");
        }


    }

    public static void failTest(Exception e)
    {
        Assert.fail("Test Failed --- " + e.getMessage());
    }
}
