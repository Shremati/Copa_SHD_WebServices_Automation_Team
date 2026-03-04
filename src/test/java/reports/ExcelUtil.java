package reports;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {


    public static void writeTestResults(String filePath, List<String[]> data) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Results");

        // Create Header Style (Yellow Background)
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        int rowNum = 0;

        for (String[] rowData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            for (String field : rowData) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(field);

                // Apply yellow style only to header row
                if (rowNum == 1) {
                    cell.setCellStyle(headerStyle);
                }
            }
        }

        // Auto-size columns
        for (int i = 0; i < data.get(0).length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}