package g2048.v3_5.CNGame;
/**
 * @author Xcl
 * @date 2021/12/1 09:48
 * @package g2048.v3
 */

import g2048.v3_5.itf.IData;
import g2048.v3_5.lib.GMessage;
import g2048.v3_5.sql.SqlMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserInfoCN extends JFrame implements IData, ActionListener {
    static final long serialVersionUID = -4598519373915560300L;
    int line = 0;
    String admin = null;
    JTable table_book;
    JFrame JFm = this;
    JMenuBar jmb;
    JMenu jm;
    JMenuItem Moadd;
    JMenuItem Delete;

    UserInfoCN(String st) {
        setAdmin(st);
        init(st);
        setVisible(true);
        setSize(800, 600);
        setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        getJFm().setLocationRelativeTo(null);
        setTitle("管理员" + st + "的用户信息展示区");
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public void init(String st) {
        setLayout(null);
        Object[] title = {"账号", "密码", "姓名", "性别", "身份证号", "注册时间"};//1.3.2.5.4.6
        setJmb(new JMenuBar());
        setJm(new JMenu("功能"));
        setMoadd(new JMenuItem("更改"));
        getMoadd().addActionListener(this);
        setDelete(new JMenuItem("删除"));
        getDelete().addActionListener(this);
        getJm().add(getMoadd());
        getJm().add(getDelete());
        getJmb().add(getJm());
        getJFm().setJMenuBar(getJmb());
        List<String> list1 = new ArrayList<>();
        /*数据库开始*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                    SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from t_user ;");
            while (rs.next()) {
                setLine(rs.getInt(1));
                if (getLine() != 0) {
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                    "ERROR!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        /*数据库结束*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn1 = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                    SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
            Statement stmt1 = conn1.createStatement();
            ResultSet rs1 = stmt1.executeQuery("select * from t_user ;");
            while (rs1.next()) {
                list1.add(String.valueOf(rs1.getString(1)));
                list1.add(String.valueOf(rs1.getString(3)));
                list1.add(String.valueOf(rs1.getString(2)));
                list1.add(String.valueOf(rs1.getString(5)));
                list1.add(String.valueOf(rs1.getString(4)));
                list1.add(String.valueOf(rs1.getString(7)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                    "ERROR!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        /*数据库结束*/
        Object[][] bookList = new String[getLine()][6];
        for (int j = 0; j < 6; j++)
            for (int b = 0, k = 0; b < getLine(); b++, k += 6)
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getMoadd()) {
            getJFm().dispose();
            new ModedCN(getAdmin());
        } else if (e.getSource() == getDelete()) {
            getJFm().dispose();
            new DeletedCN(getAdmin());
        }
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public JTable getTable_book() {
        return table_book;
    }

    public void setTable_book(JTable table_book) {
        this.table_book = table_book;
    }

    public JMenuBar getJmb() {
        return jmb;
    }

    public void setJmb(JMenuBar jmb) {
        this.jmb = jmb;
    }

    public JMenu getJm() {
        return jm;
    }

    public void setJm(JMenu jm) {
        this.jm = jm;
    }

    public JMenuItem getMoadd() {
        return Moadd;
    }

    public void setMoadd(JMenuItem moadd) {
        Moadd = moadd;
    }

    public JMenuItem getDelete() {
        return Delete;
    }

    public void setDelete(JMenuItem delete) {
        Delete = delete;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public JFrame getJFm() {
        return JFm;
    }

    public void setJFm(JFrame JFm) {
        this.JFm = JFm;
    }
}

