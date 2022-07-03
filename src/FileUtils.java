import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class FileUtils {

    public static Stream<String> readFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.lines();
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
        throw new RuntimeException("Can't read file");
    }
}
