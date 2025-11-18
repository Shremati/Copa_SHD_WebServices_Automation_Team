package GENERICS;

import org.apache.poi.ss.usermodel.CellType;
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
import java.util.Map;

import static frameworkconstants.FrameworkConstants.*;

public class FlightBooking {

    public static void bookFlight(String sheetName) throws IOException
    {
        FileInputStream fis = new FileInputStream(new File(getTestData()));
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet(sheetName);

        ArrayList<String> flights;

        for(int cellNo=0;cellNo<sheet.getRow(0).getLastCellNum();cellNo++)
        {
            if(sheet.getRow(0).getCell(cellNo).getCellType() != CellType.BLANK &&
                    sheet.getRow(0).getCell(cellNo).toString().trim().length() > 0 && sheet.getRow(0).getCell(cellNo).getStringCellValue().contains("DepartureAirport"))
            {
                for (int rowNumber = 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++)
                {

                    int originCellNo=cellNo;
                    int destinationCellNo=cellNo+1;

                    boolean isOriginNotBlank = sheet.getRow(rowNumber) != null && sheet.getRow(rowNumber).getCell(originCellNo).getCellType() != CellType.BLANK &&
                            sheet.getRow(rowNumber).getCell(originCellNo).toString().trim().length() > 0;
                    boolean isDestinationNotBlank =  sheet.getRow(rowNumber) != null && sheet.getRow(rowNumber).getCell(destinationCellNo).getCellType() != CellType.BLANK &&
                            sheet.getRow(rowNumber).getCell(destinationCellNo).toString().trim().length() > 0;

                    if(isOriginNotBlank && isDestinationNotBlank)
                    {
                        String origin = sheet.getRow(rowNumber).getCell(originCellNo).toString();
                        String destination = sheet.getRow(rowNumber).getCell(destinationCellNo).toString();
                        flights = new ArrayList<>();
                        if(!availableFlights.containsKey(origin + "-" + destination)){
                            availableFlights.put(origin + "-" + destination, flights);
                        }
                    }
                }
            }
        }


        wb.close();
        fis.close();

        fis = new FileInputStream(new File(  System.getProperty("user.dir") + "\\src\\test\\java\\TestData\\FlightNumbers.xlsx"));
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet("Flight Data");

        for (int rowNumber = 1; rowNumber < sheet.getPhysicalNumberOfRows(); rowNumber++)
        {
            String market = "";
            if(sheet.getRow(rowNumber) != null && sheet.getRow(rowNumber).getCell(0).getCellType() != CellType.BLANK &&
                    sheet.getRow(rowNumber).getCell(0).toString().trim().length() > 0)
                market = sheet.getRow(rowNumber).getCell(0).toString();

            for(Map.Entry<String, ArrayList<String>> flightData : availableFlights.entrySet())
            {
                if(flightData.getKey().equalsIgnoreCase(market))
                {
                    for(int cell = 1; cell < sheet.getRow(rowNumber).getLastCellNum(); cell++)
                    {
//                        System.out.println(sheet.getRow(rowNumber).getCell(cell).toString());
                        flightData.getValue().add(sheet.getRow(rowNumber).getCell(cell).toString());
                    }
                }
            }
        }    // outer for loop ending

//        for(Map.Entry<String, ArrayList<String>> flightData : availableFlights.entrySet())
//        {
//            System.out.println("Flight data: " + flightData.getKey() + " , " + flightData.getValue());
//        }
    }
}
