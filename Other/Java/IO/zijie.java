package fuxi.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class zijie {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("src/fuxi/IO/zifu.txt");
            FileOutputStream fos = new FileOutputStream("src/fuxi/IO/zifu1.txt");
            byte[] input = new byte[50];
            while (fis.read(input) != -1) {//单个字节进行读取
                fos.write(input);//单个字节进行写入操作
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

