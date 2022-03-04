package g2048.v3_5.ENGame;

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
public class DeletedEN extends JFrame implements IData {
    static final long serialVersionUID = -6427279668342554557L;
    JFrame JFm = this;
    String panduancf = null;
    JTextField t_name;
    JLabel l_name;
    JButton add;
    JButton show;
    JButton clean;

    DeletedEN(String a1) {
        setTitle("Admin" + a1 + "'s interface for deleting user information");
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
        setL_name(new JLabel("User", JLabel.CENTER));
        setT_name(new JTextField());
        setMod(new JButton("Del"));
        getMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "If the user information is deleted successfully, a window will pop up！", " ", -1);
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
                    JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
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
                        JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                "ERROR!", JOptionPane.ERROR_MESSAGE);
                        e_5.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "User information deleted successfully！", " ", -1);
                    getT_name().setText("");
                } else {
                    getT_name().setText("");
                }
            }
        });
        setClean(new JButton("Reset"));
        getClean().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_name().setText("");
            }
        });
        setShow(new JButton("View user information"));
        getShow().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJFm().dispose();
                new UserInfoEN(a2);
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
