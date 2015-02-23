/**
 * Created by Ian on 2/22/15.
 */
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

public class WorkbookReader {

    public static void main(String [] args) throws Exception {
        WorkbookReader reader = new WorkbookReader();
        reader.readFile();
    }
    void readFile() throws Exception {
        File f = new File("/Users/Ian/Desktop/software-dev/176103.xlsx");
        FileInputStream file = new FileInputStream(f);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        String fileName = f.getName();
        List<String> stimuliNames = new ArrayList<String>();
        int numSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            String[] stimuliNamePieces = sheet.getSheetName().split(" - ");
            String stimuliName = stimuliNamePieces[0];
            String stimuliType = stimuliNamePieces[1];
            if (stimuliName.length() > 20) {
                stimuliName = stimuliName.substring(0, 20);
            }
            if (stimuliType.equals("F")) {
                stimuliNames.add(stimuliName);
            }
        }
        for (String stimuli: stimuliNames) {
            XSSFSheet sheet = workbook.getSheet(stimuli + " - STAT");
            if (sheet != null) {
                int rowIndex = 3;
                while (true) {
                    XSSFRow row = sheet.getRow(rowIndex);
                    if (row == null) { // no value
                        break;
                    } else {
                        XSSFCell statCell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
                        String statName = statCell.getStringCellValue();
                        XSSFCell valueCell = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
                        double value = valueCell.getNumericCellValue();
                        System.out.println("\"" + fileName + "\"" + " | " + "\"" + stimuli + "\"" + " | " + "\"" + statName + "\"" + " | " + "\"" + value + "\"");
                        //set(fileName, stimuli, stimuli + "-SM", statName, value);
                        rowIndex++;
                    }
                }
            }

        }

    }
}
