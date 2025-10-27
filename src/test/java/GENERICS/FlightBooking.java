package GENERICS;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static frameworkconstants.FrameworkConstants.*;

public class FlightBooking {

    public void bookFlight(String sheetName) throws IOException
    {
        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);

        double flightNumber = 0;
        ArrayList<String> flights;
        for (int rowNumber = 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++)
        {
            if(sheet.getRow(rowNumber) != null && sheet.getRow(rowNumber).getCell(3) != null &&
                !sheet.getRow(rowNumber).getCell(3).getStringCellValue().isBlank() &&
                    sheet.getRow(rowNumber).getCell(4) != null && !sheet.getRow(rowNumber).getCell(4).getStringCellValue().isBlank())
            {
                String origin = sheet.getRow(rowNumber).getCell(3).getStringCellValue();
                String destination = sheet.getRow(rowNumber).getCell(4).getStringCellValue();
                flights = new ArrayList<>();
                if(!availableFlights.containsKey(origin + destination)){
                    availableFlights.put(origin+destination, flights);
                }
            }
        }

        wb.close();
        fis.close();

//        HashMap<String, ArrayList<String>>  = new HashMap<>();
    }
}
