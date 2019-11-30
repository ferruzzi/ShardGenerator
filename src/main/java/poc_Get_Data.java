import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class poc_Get_Data {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./src/main/resources/modules/size.module"));
            String line = reader.readLine();
            while (line != null){
                if (!line.isEmpty() && !line.startsWith("//")) {
                    System.out.println(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
