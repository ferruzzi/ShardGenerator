import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        String dirName = "./src/main/resources/modules";
        List<String> fileList = new ArrayList<>();

        fileList = poc_Get_Filenames.main();
//        System.out.println(fileList);
        for (String i: fileList) {
            String fullPath = dirName + "/" + i;
//            System.out.println("Parsing file: " + fullPath);
            poc_Get_Data.main(fullPath);
        }

    }

    public static List<String> readFile(String fileName){
        List<String> fileData = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while (line != null){
                if (!line.isEmpty() && !line.startsWith("//")) {
                    // System.out.println(line);
                    fileData.add(line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileData;
    }
}
