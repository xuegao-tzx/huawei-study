package g2048.v3_5.CNGame;

import g2048.v3_5.lib.GMessage;
import g2048.v3_5.sql.SqlMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Xcl
 * @date 2021/12/31 16:15
 * @package g2048.v3_5.CNGame
 */
public class LiuZhuanCN extends JFrame {
    static final long serialVersionUID = -6427279668342554557L;
    int aa2;
    String aa3;
    String jindu;
    String aa1;
    JFrame JFm = this;
    String panduancf = null;
    JTextField t_name;
    JTextField t_pswd;
    JLabel l_name;
    JButton add;
    JButton show;
    JButton clean;

    LiuZhuanCN(String a1, int aaa) {
        setTitle("用户" + a1 + "的'超级电脑'流转");
        setAa2(aaa);
        setAa1(a1);
        getJFm().setSize(450, 600);
        getJFm().setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        getJFm().setLocationRelativeTo(null);
        init(a1, aaa);
        getJFm().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void init(String a2, int aa) {
        setLayout(new GridLayout(5, 1, 5, 5));
        setL_name(new JLabel("账号", JLabel.CENTER));
        setT_name(new JTextField());
        setMod(new JButton("分享"));
        getMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "如果分享成功则会弹窗！", " ", -1);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                            SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select count(*) from t_user where Tname='" + getT_name().getText() + "';");
                    while (rs.next()) {
                        int panduan = rs.getInt(1);
                        if (panduan == 1) {
                            setPanduancf("cf");
                            break;
                        } else {
                            setPanduancf("ko");
                        }
                    }
                } catch (Exception e_2) {
                    JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                    e_2.printStackTrace();
                }
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                            SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select Tshare32 from t_user1 where Tname='" + getT_name().getText() + "';");
                    while (rs.next()) {
                        setAa3(rs.getString(1));
                        break;
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
                if (getPanduancf().equals("cf")) {
                    if (getAa3().equals("111")) {
                        String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
                        if (getAa2() == 3) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery("select Tjindu3 from t_user where Tname='" + getT_name().getText() + "';");
                                while (rs.next()) {
                                    setJindu(rs.getString(1));
                                    break;
                                }
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                e1.printStackTrace();
                            }
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                Statement stmt = conn.createStatement();
                                stmt.executeUpdate("update t_user1 set Ttime3= '" + datime + "',Tshare32='" + 222 +
                                        "',Tshare31='" + getJindu() + "',Tname3='" + getAa1() + "' where Tname = '" + getT_name().getText() + "';");
                            } catch (Exception e_5) {
                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                e_5.printStackTrace();
                            }
                        } else if (getAa2() == 4) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery("select Tjindu4 from t_user where Tname='" + getT_name().getText() + "';");
                                while (rs.next()) {
                                    setJindu(rs.getString(1));
                                    break;
                                }
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                e1.printStackTrace();
                            }
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                Statement stmt = conn.createStatement();
                                stmt.executeUpdate("update t_user1 set Ttime4= '" + datime + "',Tshare42='" + 222 +
                                        "',Tshare41='" + getJindu() + "',Tname4='" + getAa1() + "' where Tname = '" + getT_name().getText() + "';");
                            } catch (Exception e_5) {
                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                e_5.printStackTrace();
                            }
                        } else if (getAa2() == 5) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                Statement stmt = conn.createStatement();
                                ResultSet rs = stmt.executeQuery("select Tjindu5 from t_user where Tname='" + getT_name().getText() + "';");
                                while (rs.next()) {
                                    setJindu(rs.getString(1));
                                    break;
                                }
                            } catch (Exception e1) {
                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                e1.printStackTrace();
                            }
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                Statement stmt = conn.createStatement();
                                stmt.executeUpdate("update t_user1 set Ttime5= '" + datime + "',Tshare52='" + 222 +
                                        "',Tshare51='" + getJindu() + "',Tname5='" + getAa1() + "' where Tname = '" + getT_name().getText() + "';");
                            } catch (Exception e_5) {
                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                e_5.printStackTrace();
                            }
                        }
                        JOptionPane.showMessageDialog(null, "您的游戏记录分享成功！", " ", -1);
                    } else {
                        JOptionPane.showMessageDialog(null, "已经有用户分享给TA了，流转失败，请检查用户名！",
                                "WARN!", JOptionPane.ERROR_MESSAGE);
                        getT_name().setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "查无此用户，流转失败，请检查用户名！",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                    getT_name().setText("");
                }
            }
        });
        setClean(new JButton("重置"));
        getClean().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_name().setText("");
            }
        });
        setShow(new JButton("返回"));
        getShow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getAa2() == 3) {
                    new GView3CN(getAa1()).showView();
                } else if (getAa2() == 4) {
                    new GView4CN(getAa1()).showView();
                } else if (getAa2() == 5) {
                    new GView5CN(getAa1()).showView();
                }
                getJFm().dispose();
            }
        });
        add(getL_name());
        add(getT_name());
        add(getMod());
        add(getClean());
        add(getShow());
    }

    public JFrame getJFm() {
        return JFm;
    }

    public void setJFm(JFrame JFm) {
        this.JFm = JFm;
    }

    public JLabel getL_name() {
        return l_name;
    }

    public void setL_name(JLabel l_name) {
        this.l_name = l_name;
    }

    public JButton getMod() {
        return getAdd();
    }

    public void setMod(JButton add) {
        this.setAdd(add);
    }

    public JButton getShow() {
        return show;
    }

    public void setShow(JButton show) {
        this.show = show;
    }

    public String getPanduancf() {
        return panduancf;
    }

    public void setPanduancf(String panduancf) {
        this.panduancf = panduancf;
    }

    public JTextField getT_name() {
        return t_name;
    }

    public void setT_name(JTextField t_name) {
        this.t_name = t_name;
    }

    public JButton getAdd() {
        return add;
    }

    public void setAdd(JButton add) {
        this.add = add;
    }

    public JButton getClean() {
        return clean;
    }

    public void setClean(JButton clean) {
        this.clean = clean;
    }

    public int getAa2() {
        return aa2;
    }

    public void setAa2(int aa2) {
        this.aa2 = aa2;
    }

    public String getAa3() {
        return aa3;
    }

    public void setAa3(String aa3) {
        this.aa3 = aa3;
    }

    public String getJindu() {
        return jindu;
    }

    public void setJindu(String jindu) {
        this.jindu = jindu;
    }

    public String getAa1() {
        return aa1;
    }

    public void setAa1(String aa1) {
        this.aa1 = aa1;
    }

    public JTextField getT_pswd() {
        return t_pswd;
    }

    public void setT_pswd(JTextField t_pswd) {
        this.t_pswd = t_pswd;
    }
}

