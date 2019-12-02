import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class poc_Get_Filenames {

    public static List<String> main(){
        String dirName = "./src/main/resources/modules";
        List<String> list = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            list = paths
                    .map(Path::toString).filter(f -> f.endsWith("module"))
                    .collect(Collectors.toList());

//            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

         for (int i = 0; i < list.size(); i++)
            parsePath(list, i);

         return list;
    }

    public static void parsePath(List<String> list, int i) {
        String fullPath = list.get(i);
        String[] filenames = fullPath.split("\\\\", 6);
//        System.out.println(filenames[filenames.length-1]);
        list.set(i,filenames[filenames.length-1]);
    }
}

/*   private static void accept(String f) {
        System.out.println(Arrays.toString(new String[]{
                f.split("\\\\", 6)[5]
                 .split("\\.", 2)[0]}));
    }

  */

/*
        try (Stream<Path> paths = Files.walk(Paths.get(dirName), 2)) {
            paths.filter(Files::isRegularFile).forEach(System.out::println);
        }
 */

/*
        try (Stream<Path> paths = Files.walk(Paths.get(dirName), 2)) {
            paths.map(path -> path.toString()).filter(f -> f.endsWith("module"))
                    .forEach(System.out::println);
        }
 */

/*
        List<String> filenames = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            List<String> list = paths
                    .map(path -> path.toString()).filter(f -> f.endsWith("module"))
                    .collect(Collectors.toList());

            System.out.println(list);
            filenames = list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        filenames.forEach((f) -> System.out.println(Arrays.toString(new String[]{f.split("\\\\", 6)[5].split("\\.",2)[0]})));
//        System.out.println(Arrays.toString(new String[]{filenames.get(0).split("\\\\", 6)[5].split("\\.",2)[0]}));

 */

// This works perfectly
/*
        List<String> list = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(Paths.get(dirName))) {
            list = paths
                    .map(path -> path.toString()).filter(f -> f.endsWith("module"))
                    .collect(Collectors.toList());

            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.forEach((f) -> System.out.println(Arrays.toString(new String[]{f.split("\\\\", 6)[5].split("\\.",2)[0]})));
 */
