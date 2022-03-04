package fuxi.bigexam;
/**
 * @author Xcl
 * @date 2021/12/1 09:48
 * @package g2048.v2
 */

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 本类继承自JFrame实现swing图形界面，实现IData接口的所有方法
 */
public class UserInfo extends JFrame implements IData {
    static final long serialVersionUID = -4598519373915560300L;
    JTable table_book;

    UserInfo(String st) {
        init(st);
        setVisible(true);
        setSize(800, 600);
        setTitle("管理员" + st + "的用户信息展示区");
    }

    /**
     * 获取文件中行数并返回
     *
     * @param filepath
     * @return
     */
    public static int getFileLine(String filepath) {
        try (LineNumberReader lnr = new LineNumberReader(new FileReader(filepath))) {
            lnr.skip(Long.MAX_VALUE);
            int lnm1 = lnr.getLineNumber();
            return lnm1 + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * 整个管理员展示用户信息界面的展示
     *
     * @param st
     */
    @Override
    public void init(String st) {
        setLayout(null);
        Object[] title = {"账号", "密码", "姓名", "性别", "身份证号", "注册时间"};
        int line = getFileLine("src/g2048/db/user.txt.txt");
        Object[][] bookList = new String[line][6];
        List<String> list1 = new ArrayList<>();
        try {
            File file = new File("src/g2048/db/user.txt.txt");
            if (file.isFile() && file.exists()) {
                InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                String data;
                while ((data = br.readLine()) != null) {
                    String[] split = data.split(":");
                    list1.add(split[0].split("\\.")[0]);
                    list1.add(split[0].split("\\.")[2]);
                    list1.add(split[0].split("\\.")[1]);
                    list1.add(split[0].split("\\.")[4]);
                    list1.add(split[0].split("\\.")[3]);
                    list1.add(split[0].split("\\.")[5]);
                }
                br.close();
            } else System.out.println("文件不存在！");
        } catch (Exception e_1) {
            e_1.printStackTrace();
        }
        for (int j = 0; j < 6; j++)
            for (int b = 0, k = 0; b < line; b++, k += 6)
                switch (j) {
                    case 0:
                        bookList[b][j] = list1.get(k);
                        break;
                    case 1:
                        bookList[b][j] = list1.get(k + 1);
                        break;
                    case 2:
                        bookList[b][j] = list1.get(k + 2);
                        break;
                    case 3:
                        bookList[b][j] = list1.get(k + 3);
                        break;
                    case 4:
                        bookList[b][j] = list1.get(k + 4);
                        break;
                    case 5:
                        bookList[b][j] = list1.get(k + 5);
                        break;
                }
        setTable_book(new JTable(bookList, title));
        JScrollPane pane = new JScrollPane();
        pane.setBounds(20, 20, 750, 500);
        pane.getViewport().add(getTable_book());
        getContentPane().add(pane);
    }

    public JTable getTable_book() {
        return table_book;
    }

    public void setTable_book(JTable table_book) {
        this.table_book = table_book;
    }
}

