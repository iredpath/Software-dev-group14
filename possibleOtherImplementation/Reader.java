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
        reader.write();
    }

    private void write() {
        Writer writer = new Writer();
        writer.writeFile(data);
    }

    private void addFile(File f) throws IOException, InvalidFormatException {
        if (f.isDirectory()) {
            for (File file: f.listFiles()) {
                data.addFile(file);
            }
        } else {
            data.addFile(f);
        }
    }

}
