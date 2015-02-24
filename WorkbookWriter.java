import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

/**
 * Created by Ian on 2/24/15.
 */
public class WorkbookWriter {

    public WorkbookWriter() {}

    public void write(FourDimArray data) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        List<String> statistics = data.getStatistics();
        List<String> medias = data.getMedias();
        List<String> stimuli;
        List<String> subjects = data.getSubjects();
        Row row;
        Cell cell;
        int rowIndex;
        int cellIndex;
        for (String stat: statistics) {
            // could be string length sheet name bug
            XSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(stat));
            rowIndex = 0;
            cellIndex = 0;
            row = sheet.createRow(rowIndex++);
            cell = row.createCell(cellIndex++);
            cell.setCellValue(stat);
            for (String media: medias) {
                stimuli = data.getStimuli(media);
                for (String stim: stimuli) {
                    cell = row.createCell(cellIndex++);
                    cell.setCellValue(stim);
                }
            }
            for (String subject: subjects) {
                cellIndex = 0;
                row = sheet.createRow(rowIndex++);
                cell = row.createCell(cellIndex++);
                cell.setCellValue(subject);
                for (String media: medias) {
                    stimuli = data.getStimuli(media);
                    for (String stim: stimuli) {
                        cell = row.createCell(cellIndex++);
                        cell.setCellValue(data.get(subject, media, stim, stat));
                    }
                }
            }
        }
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream("/Users/Ian/Desktop/test/workbook.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}
