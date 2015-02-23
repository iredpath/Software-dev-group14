import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Ian on 2/22/15.
 */
public class StimulusMap {

    private HashMap<String, StatisticMap> stimuli;

    private StimulusMap() {
        this.stimuli = new HashMap<String, StatisticMap>();
    }

    public static StimulusMap newStimulusMap() {
        StimulusMap sm = new StimulusMap();
        return sm;
    }

    public static StimulusMap createStimulusMap(XSSFSheet sheet) {
        StimulusMap sm = new StimulusMap();
        sm.populate(sheet);
        return sm;
    }
    public Set<String> getAllStimulusNames() {
        return this.stimuli.keySet();
    }

    public void put(String s, StatisticMap sm) {
        this.stimuli.put(s, sm);
    }

    private void populate(XSSFSheet sheet) {
        populateSlideMetric(sheet);
    }
    private void populateSlideMetric(XSSFSheet sheet) {
        int rowIndex = 3;
        String name = sheet.getSheetName() + "-SM";
        StatisticMap statisticMap = StatisticMap.newStatisticMap();
        while (true) {
            XSSFRow row = sheet.getRow(rowIndex);
            if (row == null) { // no value
                break;
            } else {
                XSSFCell statCell = row.getCell(0, Row.CREATE_NULL_AS_BLANK);
                String statName = statCell.getStringCellValue();
                XSSFCell valueCell = row.getCell(5, Row.CREATE_NULL_AS_BLANK);
                double value = valueCell.getNumericCellValue();
                statisticMap.put(statName, (Double) value);
                //System.out.println("\"" + fileName + "\"" + " | " + "\"" + stimuli + "\"" + " | " + "\"" + statName + "\"" + " | " + "\"" + value + "\"");
                //set(fileName, stimuli, stimuli + "-SM", statName, value);
                rowIndex++;
            }

            //TODO: loop through SM, LZ data and add statistics
        }
        put(name, statisticMap);
    }

    void printContents() {
        for (String s: stimuli.keySet()) {
            System.out.println("\t\t\t\t\t{");
            System.out.println("\t\t\t\t\t\ttype: " + s);
            System.out.println("\t\t\t\t\t\tstatistics: [");
            stimuli.get(s).printContents();
            System.out.println("\t\t\t\t\t\t]");
            System.out.println("\t\t\t\t\t}");
        }
    }

}
