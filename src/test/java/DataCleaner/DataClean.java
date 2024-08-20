package DataCleaner;

import java.io.File;

import static GENERICS.Utils.deleteFolder;
import static GENERICS.Utils.deleteReports;
import static frameworkconstants.FrameworkConstants.*;

public class DataClean
{

    public static void main(String[] args)
    {
       // Delete  the files and sub-folders of a directory - use below methods only before checkin to GitHub
        deleteFolder(new File(getResponseDirectory()));
        deleteFolder(new File(getAllureReports()));
        deleteFolder(new File(getAllureResults()));
        deleteFolder(new File(getTarget()));
        deleteReports(new File(getReports()));

        //Latest working code 20-Aug-2024

    }

}


