package fuxi.IO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class zifu {
    public static void main(String[] args) throws IOException {
        try {
            FileReader fr = new FileReader("src/fuxi/IO/zifu.txt");//读的文件路径
            FileWriter fw = new FileWriter("src/fuxi/IO/zifu1.txt");//写的文件路径
            int ch = 0;
            while ((ch = fr.read()) != -1) {//单个字符进行读取
                fw.write(ch);//单个字符进行写入操作
            }
            fw.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}