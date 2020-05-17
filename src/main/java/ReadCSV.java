import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadCSV {
    public static void main(String ags[]) throws IOException {
        try {
            List<String> lines = Files.readAllLines(Paths.get("src\\data\\mydata.csv"));
            for (String line : lines) {
                line = line.replace("\"", "");
                String[] result = line.split(",");
                for (String s : result)
                    System.out.println(s + " - ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}