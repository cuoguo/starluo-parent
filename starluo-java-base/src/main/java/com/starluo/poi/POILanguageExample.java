package com.starluo.poi;

import java.io.File;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.DataFormatter;

/**
 * @author cong.zhang
 * @date 2018/5/14.
 * @time 20:04.
 */
public class POILanguageExample {

    public static void main(String[] args) throws Exception {
        read();
    }

    public static void read() throws IOException, InvalidFormatException {
        // Read the contents of the workbook
        File f = new File("C:\\5.xlsx");
        Workbook wb = WorkbookFactory.create(f);
        DataFormatter formatter = new DataFormatter();
        int i = 1;
        int numberOfSheets = wb.getNumberOfSheets();
        for ( Sheet sheet : wb ) {
            System.out.println("Sheet " + i + " of " + numberOfSheets + ": " + sheet.getSheetName());
            for ( Row row : sheet ) {
                Cell cell1 = row.getCell(1);
                String cellValue = formatter.formatCellValue(cell1);
                System.out.println("Row " + row.getRowNum());
                
                for ( Cell cell : row ) {
                    System.out.println(""+ cell.getAddress().formatAsString() + ": " + formatter.formatCellValue(cell));
                }
            }
        }

    }
}
