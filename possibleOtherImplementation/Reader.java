import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ian on 2/22/15.
 */
public class Reader {

    Data data;

    Reader() {
        this.data = new Data();
    }

    public static void main(String[] args) throws IOException, InvalidFormatException{
        Reader reader = new Reader();
        for (String f: args) {
            reader.addFile(new File(f));
        }
    }

    private void addFile(File f) throws IOException, InvalidFormatException {
        data.addFile(f);/*
        String name = f.getName().replace(".xls*", "");
        XSSFWorkbook workbook = new XSSFWorkbook(f);
        data.put(name, getMedia(workbook));*/
    }

    private MediaMap getMedia(XSSFWorkbook book) {
        MediaMap media = MediaMap.newMediaMap();
        int sheetIndex = 0;
        int numSheets = book.getNumberOfSheets();
        String prevType = null;
        String prevName = null;
        for (int i = 0; i < numSheets; i++) {
            XSSFSheet sheet = book.getSheetAt(i);
            String[] sheetNamePieces = sheet.getSheetName().split(" - ");
            String currType = sheetNamePieces[1];
            String currName = sheetNamePieces[1].substring(0, 20); // name on stat page is first 20 of name on f,g tab
            if (currType.equals("STAT")) {
                media.put(currName, getStimuli(sheet));
            } else if (currType.equals("G") && prevType.equals("F")) {
                //media.put(prevName, null);
            }
            prevName = currName;
            prevType = currType;
        }
        return media;
    }

    private StimulusMap getStimuli(XSSFSheet sheet) {
        StimulusMap stimuli = StimulusMap.newStimulusMap();
        //TODO: loop through SM, LZ data and add statistics
        return stimuli;
    }
}
