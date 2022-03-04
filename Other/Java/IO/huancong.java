package fuxi.IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Xcl
 * @date 2021/12/20 15:29
 * @package fuxi.IO
 */
public class huancong {
    public static void main(String[] args) {
        try {
            File file = new File("src/fuxi/IO/zifu.txt");//读的文件路径
            File file1 = new File("src/fuxi/IO/zifu1.txt");//写的文件路径
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String data = br.readLine();
                br.close();
                if (!file1.exists()) file1.createNewFile();
                FileWriter fw1 = new FileWriter(file1);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw1.write(String.valueOf(data));
                bw1.flush();
                bw1.close();
                fw1.close();
            } else System.out.println("文件不存在！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
