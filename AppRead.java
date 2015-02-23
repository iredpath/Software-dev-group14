import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class AppRead {
    public static void main(String[] args) {
        Workbook wb = null;
        
        // 1. Open the file
        try {
            wb = WorkbookFactory.create(new File("workbook.xlsx"));
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // 2. Open a sheet
        Sheet sheet = wb.getSheetAt(0);
        
        // 3. Get each cell by row & column number
        Cell cell = sheet.getRow(0).getCell(0);
        double numberVal = cell.getNumericCellValue();
        System.out.println("Row: 0 - Column: 0 = " + numberVal);
        // -----------------------------
        cell = sheet.getRow(0).getCell(1);
        String stringVal = cell.getStringCellValue();
        System.out.println("Row: 0 - Column: 1 = " + stringVal);
        // -----------------------------
        cell = sheet.getRow(0).getCell(2);
        Date dateVal = cell.getDateCellValue();
        System.out.println("Row: 0 - Column: 2 = " + dateVal);
        // -----------------------------
        cell = sheet.getRow(0).getCell(3);
        boolean booleanVal = cell.getBooleanCellValue();
        System.out.println("Row: 0 - Column: 3 = " + booleanVal);
        // -----------------------------
    }
}