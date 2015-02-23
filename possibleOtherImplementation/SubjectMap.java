import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ian on 2/22/15.
 */
public class SubjectMap {

    private HashMap<String, MediaMap> subjects;

    private SubjectMap() {
        this.subjects = new HashMap<String, MediaMap>();
    }

    public Set<String> getAllSubjectNames() {
        return this.subjects.keySet();
    }

    public static SubjectMap newSubjectMap() {
        SubjectMap s = new SubjectMap();
        return s;
    }

    public void put(String s, MediaMap m) {
        this.subjects.put(s, m);
    }

    public void put(String s, XSSFWorkbook book) {
        MediaMap mm = MediaMap.createMediaMap(book);
        put(s, mm);
    }

    void printContents() {
        for (String s: subjects.keySet()) {
            System.out.println("[\n\t{");
            System.out.println("\t\t name: " + s);
            System.out.println("\t\t sheets: [");
            subjects.get(s).printContents();
            System.out.println("\t\t]");
            System.out.println("\t},");
        }
        System.out.println("]");
    }
    public Set<String> getAllMediaNames() {
        Set<String> retVal = new HashSet<String>();
        for (MediaMap m : subjects.values()) {
            retVal.addAll(m.getAllMediaNames());
        }
        return retVal;
    }
    public Set<String> getAllStimulusNames() {
        Set<String> retVal = new HashSet<String>();
        for (MediaMap m: subjects.values()) {
            retVal.addAll(m.getAllStimulusNames());
        }
        return retVal;
    }
    public Set<String> getAllStatisticNames() {
        Set<String> retVal = new HashSet<String>();
        for (MediaMap m: subjects.values()) {
            retVal.addAll(m.getAllStatisticNames());
        }
        return retVal;
    }

    Double getVal(String statistic, String stimuli, String subject) {
        MediaMap subjectData = subjects.get(subject);
        if (subjectData == null) {
            return null;
        } else {
            return subjectData.getVal(statistic, stimuli);
        }
    }
}
