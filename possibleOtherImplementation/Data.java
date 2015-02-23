import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ian on 2/22/15.
 */
public class Data {

    private SubjectMap subjects;

    public Data() {
        this.subjects = SubjectMap.newSubjectMap();
    }

    public void put(String s, MediaMap m) {
        this.subjects.put(s, m);
    }

    public void addFile(File f) throws IOException, InvalidFormatException {
        String name = f.getName().replace(".xls*", "");
        XSSFWorkbook workbook = new XSSFWorkbook(f);
        subjects.put(name, workbook);
        printContents();
    }

    void printContents() {
        subjects.printContents();
    }

    /*
    Set<String> subjectNames;
    Set<String> stimuliNames;
    Set<String> statisticNames;

    Data() {
        this.subjectNames = new HashSet<String>();
        this.stimuliNames = new HashSet<String>();
        this.statisticNames = new HashSet<String>();
    }


    private void addFile(File f) throws IOException, InvalidFormatException {
        String name = f.getName().replace(".xls*", "");
        if (!subjectNames.add(name)) {
            XSSFWorkbook workbook = new XSSFWorkbook(f);
        }
    }





    private void addFileData(File f) throws IOException, InvalidFormatException {
        String fileName = f.getName().replace(".xls*", "");
        subjectNames.add(fileName);
        XSSFWorkbook book = new XSSFWorkbook(f);
        int sheetIndex = 0;
        int numSheets = book.getNumberOfSheets();
        String prevSheetName = null;
        String prevSheetType = null;
        for (int i = 0; i < numSheets; i++) {
            XSSFSheet sheet = book.getSheetAt(i);
            String[] stimuliNamePieces = sheet.getSheetName().split(" - ");
            String sheetType = stimuliNamePieces[1];
            String stimuliNameFull = stimuliNamePieces[0];
            String stimuliName = (stimuliNameFull.length() > 20 ? stimuliNameFull.substring(0, 20) : stimuliNameFull);
            stimuliNames.add(stimuliName);
            if (sheetType.equals("STAT")) {
                //handleSheetData();
            } else if (missingStatSheet(prevSheetType, sheetType)) {
                //TODO: missing sheet logic
            }

        }


    }

    private boolean missingStatSheet(String prev, String curr) {
        return prev.equals("F") && curr.equals("G");
    }
    */
}
