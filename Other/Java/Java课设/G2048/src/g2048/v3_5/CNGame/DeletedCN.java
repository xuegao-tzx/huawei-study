package g2048.v3_5.CNGame;

import g2048.v3_5.itf.IData;
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

/**
 * @author Xcl
 * @date 2021/12/5 15:56
 * @package g2048.v3
 */
public class DeletedCN extends JFrame implements IData {
    static final long serialVersionUID = -6427279668342554557L;
    JFrame JFm = this;
    String panduancf = null;
    JTextField t_name;
    JLabel l_name;
    JButton add;
    JButton show;
    JButton clean;

    DeletedCN(String a1) {
        setTitle("管理员" + a1 + "的删除用户信息的界面");
        getJFm().setSize(450, 600);
        getJFm().setLocationRelativeTo(null);
        getJFm().setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        init(a1);
        setVisible(true);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public void init(String a2) {
        setLayout(new GridLayout(5, 1, 5, 5));
        setL_name(new JLabel("账号", JLabel.CENTER));
        setT_name(new JTextField());
        setMod(new JButton("删除"));
        getMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "如果删除用户信息成功则会弹窗！", " ", -1);
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
                if (getPanduancf().equals("cf")) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                        Statement stmt = conn.createStatement();
                        stmt.execute("delete from t_user where Tname='" + getT_name().getText() + "';");
                    } catch (Exception e_5) {
                        JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                "ERROR!", JOptionPane.ERROR_MESSAGE);
                        e_5.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "删除用户信息成功！", " ", -1);
                    getT_name().setText("");
                } else {
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
        setShow(new JButton("查看用户信息"));
        getShow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJFm().dispose();
                new UserInfoCN(a2);
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
}
