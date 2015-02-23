import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.HashMap;
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
}
