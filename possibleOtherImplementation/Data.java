import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        //printContents();
    }

    void printContents() {
        subjects.printContents();
    }

    public Set<String> getAllSubjectNames() {
        return subjects.getAllSubjectNames();
    }

    public Set<String> getAllMediaNames() {
        return subjects.getAllMediaNames();
    }

    public Set<String> getAllStimulusNames() {
        return subjects.getAllStimulusNames();
    }

    public Set<String> getAllStatisticNames() {
        return subjects.getAllStatisticNames();
    }

    Double getVal(String statistic, String stimuli, String subject) {
        return subjects.getVal(statistic, stimuli, subject);
    }
}