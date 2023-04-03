package Extra_curriculum_performed;

import java.io.*;

/**
 * IO operations
 * @author Xinan Xu
 * @version 1.0
 */
public class IO {

    /**
     * read file from disk
     * @param fileName
     * @return the content of that file
     * @throws IOException
     */
    public static String read(String fileName) throws IOException {
        try (
            FileReader fr = new FileReader("./Data/" + fileName);
            BufferedReader br = new BufferedReader(fr)
        ) {
            StringBuilder sb = new StringBuilder();
            String temp = "";
            while ((temp = br.readLine()) != null) {
                sb.append(temp + "\n");
            }

            return sb.toString();
        }
    }

    /**
     * write to disk
     * @param fileName
     * @param str content of file
     * @throws IOException
     */
    public static void write(String fileName, String str) throws IOException {
        File file = new File("./Data/" + fileName);
        PrintStream ps = new PrintStream(new FileOutputStream(file));
        ps.println(str);
        ps.close();
    }
}
