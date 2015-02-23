import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ian on 2/22/15.
 */
public class MediaMap {

    private HashMap<String, StimulusMap> medias;

    private MediaMap() {
        this.medias = new HashMap<String, StimulusMap>();
    }

    public static MediaMap newMediaMap() {
        MediaMap m = new MediaMap();
        return m;
    }

    public static MediaMap createMediaMap(XSSFWorkbook book) {
        MediaMap mm = new MediaMap();
        mm.populate(book);
        return mm;
    }

    private void populate(XSSFWorkbook book) {
        int numSheets = book.getNumberOfSheets();
        String prevType = "";
        String prevName = null;
        for (int i = 0; i < numSheets; i++) {
            XSSFSheet sheet = book.getSheetAt(i);
            String[] sheetNamePieces = sheet.getSheetName().split(" - ");
            if (sheetNamePieces.length < 2) {
                // bad sheet, ignore
                continue;
            }
            String currType = sheetNamePieces[1];
            String currName = sheetNamePieces[0];
            if (currName.length() > 20) {
                currName = currName.substring(0, 20); // name on stat page is first 20 of name on f,g tab
            }
            if (currType.equals("STAT")) {
                medias.put(currName, StimulusMap.createStimulusMap(sheet));
            } else if (currType.equals("G") && prevType.equals("F")) {
                medias.put(prevName, null);
            }
            prevName = currName;
            prevType = currType;
        }
    }
    public Set<String> getAllMediaNames() {
        return this.medias.keySet();
    }

    public void put(String s, StimulusMap sm) {
        this.medias.put(s, sm);
    }

    void printContents() {
        for (String s: medias.keySet()) {
            System.out.println("\t\t\t{");
            System.out.println("\t\t\t\tname: " + s);
            System.out.println("\t\t\t\tstimuli: [");
            StimulusMap sm = medias.get(s);
            if (sm == null) {
                System.out.println("\t\t\t\t\tnull");
            } else {
                sm.printContents();
            }
            System.out.println("\t\t\t\t]");
            System.out.println("\t\t\t},");
        }
    }

    public Set<String> getAllStimulusNames() {
        Set<String> retVal = new HashSet<String>();
        for (StimulusMap sm: medias.values()) {
            retVal.addAll(sm.getAllStimulusNames());
        }
        return retVal;
    }
    public Set<String> getAllStatisticNames() {
        Set<String> retVal = new HashSet<String>();
        for (StimulusMap sm: medias.values()) {
            retVal.addAll(sm.getAllStatisticNames());
        }
        return retVal;
    }
}
