import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Ian on 2/22/15.
 */
public class Writer {


    public void writeFile(Data data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        for (String stat: data.getAllStatisticNames()) {
            XSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(stat));
            writeToSheet(sheet, data);
        }
        writeBook(workbook);
    }

    private void writeToSheet(XSSFSheet sheet, Data data) {
        // row - stimuli
        // column - subject

        // row 0
        Row row = sheet.createRow(0);
        Set<String> stimuli = data.getAllStimulusNames();
        Iterator<String> stimuliIterator = stimuli.iterator();
        int cellIndex = 1;
        while (stimuliIterator.hasNext()) {
            Cell cell = row.createCell(cellIndex);
            cell.setCellValue(stimuliIterator.next());
            cellIndex++;
        }

        // rows 1-N.
        int rowIndex = 1;
        Set<String> subjects = data.getAllSubjectNames();
        Iterator<String> subjectIterator = subjects.iterator();
        cellIndex = 0;
        while (subjectIterator.hasNext()) {
            Row rowForValues = sheet.createRow(rowIndex);
            Cell headerCell = rowForValues.createCell(cellIndex);
            String currSub = subjectIterator.next();
            headerCell.setCellValue(currSub);
            cellIndex++;

            stimuliIterator = stimuli.iterator();
            while (stimuliIterator.hasNext()) {
                Cell valueCell = rowForValues.createCell(cellIndex);
                String stimulus = stimuliIterator.next();
                String stat = sheet.getSheetName();
                Double val = data.getVal(stat, stimulus, currSub);
                if (val != null) {
                    valueCell.setCellValue(val);
                }
                cellIndex++;
            }
            cellIndex = 0;
            rowIndex++;
        }
    }

    private void writeBook(XSSFWorkbook book) {
        try {
            FileOutputStream out = new FileOutputStream(new File("/Users/Ian/Desktop/test.xlsx"));
            book.write(out);
            out.close();
        } catch (Exception e) {
        }
    }
}
