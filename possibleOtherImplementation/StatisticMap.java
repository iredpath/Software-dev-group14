import java.util.HashMap;
import java.util.Set;

/**
 * Created by Ian on 2/22/15.
 */
public class StatisticMap {

    private HashMap<String, Double> statistics;

    private StatisticMap() {
        this.statistics = new HashMap<String, Double>();
    }

    public static StatisticMap newStatisticMap() {
        StatisticMap sm = new StatisticMap();
        return sm;
    }

    public Set<String> getAllStatisticNames() {
        return this.statistics.keySet();
    }

    public void put(String s, Double d) {
        this.statistics.put(s, d);
    }

    void printContents() {
        for (String s: statistics.keySet()) {
            System.out.println("\t\t\t\t\t\t\t{");
            System.out.println("\t\t\t\t\t\t\t\tname: " + s);
            System.out.println("\t\t\t\t\t\t\t\tvalue: " + statistics.get(s));
            System.out.println("\t\t\t\t\t\t\t},");
        }
    }

}
