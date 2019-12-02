import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class poc_Get_Data {

    public static void main(String[] args) {

        String fileName = "./src/main/resources/modules/size.module";
        String outLine =  "";

        outLine = parseTemplate(readFile(fileName));
        System.out.println(outLine);

    }

    public static String parseTemplate(List<String> fileData){

        // TODO: for each key in madLibs
        // TODO:    find the index of the key in fileData
        // TODO:    find the index of the next key (value starts with '[')
        // TODO:    pick a random number betwixt those two
        // TODO:    String status = madLibs.replace(key, fileData[number])
        // TODO:        if (status == null) continue;
        // TODO: generate a string, replacing the keywords in template  with the results in madLibs

        HashMap<String, String> madLibs = new HashMap<>();
        HashMap<String, String> finalPicks = new HashMap<>();

        String template = fileData.get(0);
        String result = template;

        System.out.println(String.format("Original: %s \n", template));

        int startIndex = 0;
        int endIndex = 0;

        // parse through the template and create a map where the keys are the template variables
        while (startIndex != -1) {
            startIndex = template.indexOf('{',startIndex + 1);
            endIndex = template.indexOf('}', startIndex + 1) + 1;
            if (startIndex == -1) continue;

            System.out.println(template.substring(startIndex, endIndex) + " found at start: " + startIndex + " end: " + endIndex);
            madLibs.put(template.substring(startIndex, endIndex), "");
        }

        System.out.println("\nThere are a total of " + fileData.size() + " lines in the data file.");


        for (Map.Entry i : madLibs.entrySet()) {
            Random r = new Random();
            String keyword = (String)i.getKey();
            String choice = "";
            keyword = keyword.substring(1, keyword.length()-1);
            int optionStart = 0;
            int optionEnd = 0;

            for (int j = 0 ; j < fileData.size(); j++) {
                optionEnd = fileData.size();
                if (fileData.get(j).contains("[" + keyword + "]")){
                    optionStart = j;
                    for (int k = j + 1; k < fileData.size() - 1; k++) {
                        if (fileData.get(k+1).startsWith("[")) {
                            optionEnd = k;
                            break;
                        }
                    }
                    System.out.println(keyword + " options are from lines " + optionStart + " to " + optionEnd);
                    int rand = r.nextInt(optionEnd - optionStart) + optionStart + 1;
                    choice = fileData.get(rand);

                    System.out.println("\tpicking option " + rand + " for " + keyword + " which is " + choice);

                    finalPicks.put(keyword, choice);
                }
            }
        }

        System.out.println("\nFinal results:");
        for (String keys : finalPicks.keySet()) {
            System.out.println(keys + " : " + finalPicks.get(keys));
            result = result.replace("{" + keys + "}", finalPicks.get(keys));
        }

        return result;
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
