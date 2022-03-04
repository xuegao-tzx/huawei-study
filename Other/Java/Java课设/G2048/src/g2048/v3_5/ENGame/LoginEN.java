package g2048.v3_5.ENGame;
/**
 * @author Xcl
 * @date 2021/12/1 09:48
 * @package g2048.v3
 */

import g2048.v3_5.itf.IData;
import g2048.v3_5.itf.ILogin;
import g2048.v3_5.lib.GMessage;
import g2048.v3_5.sql.SqlMessage;
import g2048.v3_5.tool.GMusic;

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

import static g2048.v3_5.lib.GMessage.GMusicMessagePath;

public class LoginEN extends JFrame implements Runnable, IData, ILogin {
    static final long serialVersionUID = 3878971623272611814L;
    static String fcm = "18+";
    static String stt1 = null;
    static String stt2 = null;
    static String stt3 = null;
    static String stt4 = null;
    int tage = 0;
    JFrame jfmm = this;
    JLabel l_name;
    JLabel l_password;
    JTextField t_name;
    JPasswordField t_password;
    JButton b_login;
    JButton b_reset;
    JButton b_register;
    RegisterEN rf_1 = null;

    LoginEN() {
        setTitle("user's login form");
        setVisible(getStt1() != null);
        setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        init(getStt1());
        setSize(380, 300);
        setLocationRelativeTo(null);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getStt1() {
        return stt1;
    }

    public static void setStt1(String stt1) {
        LoginEN.stt1 = stt1;
    }

    public static String getFcm() {
        return fcm;
    }

    public static void setFcm(String fcm) {
        LoginEN.fcm = fcm;
    }

    public static String getStt2() {
        return stt2;
    }

    public static void setStt2(String stt2) {
        LoginEN.stt2 = stt2;
    }

    public static String getStt3() {
        return stt3;
    }

    public static void setStt3(String stt3) {
        LoginEN.stt3 = stt3;
    }

    public static String getStt4() {
        return stt4;
    }

    public static void setStt4(String stt4) {
        LoginEN.stt4 = stt4;
    }


    @Override
    public void init(String st) {
        setLayout(null);
        setL_name(new JLabel("Game name", JLabel.CENTER));
        setL_password(new JLabel("Passwd", JLabel.CENTER));
        setT_name(new JTextField());
        setT_password(new JPasswordField());
        setB_login(new JButton("Sign in"));
        getB_login().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Successful login will jump！", " ", -1);
                if (st.equals("用户")) {
                    /*数据库结束*/
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn1 = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                        Statement stmt1 = conn1.createStatement();
                        ResultSet rs1 = stmt1.executeQuery("select Tname,Tpasswd from t_user ;");
                        while (rs1.next()) {
                            if (String.valueOf(getT_name().getText()).equals(rs1.getString(1))) {
                                if (String.valueOf(getT_password().getPassword()).equals(rs1.getString(2))) {
                                    try {
                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                        Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                                SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                        Statement stmt = conn.createStatement();
                                        ResultSet rs = stmt.executeQuery("select Status from t_user where Tname='" + getT_name().getText() + "';");
                                        while (rs.next()) {
                                            setStt2(rs.getString(1));
                                            break;
                                        }
                                    } catch (Exception e1) {
                                        JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                                "ERROR!", JOptionPane.ERROR_MESSAGE);
                                        e1.printStackTrace();
                                    }
                                    if (getStt2().equals("111")) {
                                        try {
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                            Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                                    SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                            Statement stmt = conn.createStatement();
                                            ResultSet rs = stmt.executeQuery("select StatusTime from t_user where Tname='" + getT_name().getText() + "';");
                                            while (rs.next()) {
                                                setStt3(rs.getString(1));
                                                break;
                                            }
                                        } catch (Exception e1) {
                                            JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                                    "ERROR!", JOptionPane.ERROR_MESSAGE);
                                            e1.printStackTrace();
                                        }
                                        JOptionPane.showMessageDialog(null, "Now user " + getT_name().getText() + " has signed on " + getStt3() + " other devices, you can't log in at present. Thank you！",
                                                "WARN!", JOptionPane.ERROR_MESSAGE);
                                        break;
                                    } else if (getStt2().equals("222")) {
                                        try {
                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                            Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                                    SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                            Statement stmt = conn.createStatement();
                                            ResultSet rs = stmt.executeQuery("select StatusTime from t_user where Tname='" + getT_name().getText() + "';");
                                            while (rs.next()) {
                                                setStt4(rs.getString(1));
                                                break;
                                            }
                                        } catch (Exception e1) {
                                            JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                                    "ERROR!", JOptionPane.ERROR_MESSAGE);
                                            e1.printStackTrace();
                                        }
                                        getJfmm().dispose();
                                        int nnn = JOptionPane.showConfirmDialog(null, "All your game" +
                                                        " data will be automatically saved in the server located in New York," +
                                                        " USA. if you continue the game, you agree to our privacy agreement " +
                                                        "by default. Do you agree to our privacy agreement?", "Privacy Statement"
                                                , JOptionPane.YES_NO_OPTION);
                                        if (nnn == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "Notice:" + "\n" +
                                                            "1.Please exit the game through the menu bar." + "\n" +
                                                            "2.The last record will be followed by default." + "\n" +
                                                            "3.If you want to start over, press ESC or R." + "\n" +
                                                            "4.Do not exit from the main interface point 'x' at will in the " +
                                                            "game, otherwise an error will be reported." + "\n" +
                                                            "5.Be sure to ensure that the network is in good condition, " +
                                                            "otherwise the switching will get stuck，" + "\n" +
                                                            "6.Your last login time is: " + "\n" + getStt4() + ".",
                                                    "Welcome game user " + getT_name().getText() + "'s login prompt for", JOptionPane.CANCEL_OPTION);
                                            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
                                            try {
                                                Class.forName("com.mysql.cj.jdbc.Driver");
                                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                                Statement stmt = conn.createStatement();
                                                stmt.executeUpdate("update t_user set StatusTime= '" + datime + "',Status=" + 111 + " where Tname = '" + getT_name().getText() + "';");
                                            } catch (Exception e_5) {
                                                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                                e_5.printStackTrace();
                                            }
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    GMusic gm = new GMusic(GMusicMessagePath);
                                                    while (true) {
                                                        gm.run();
                                                    }
                                                }
                                            }).start();
                                            new GView4EN(getT_name().getText()).showView();
                                        } else if (nnn == JOptionPane.NO_OPTION) {
                                            System.exit(0);
                                        }
                                        break;
                                    } else {
                                        System.out.println("It's impossible to execute here.");
                                        break;
                                    }

                                }
                            }
                        }
                    } catch (Exception e_1) {
                        JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                "ERROR!", JOptionPane.ERROR_MESSAGE);
                        e_1.printStackTrace();
                    }
                    /*数据库结束*/
                } else if (st.equals("Admin")) {
                    if (String.valueOf(getT_name().getText()).equals("admin123")) {
                        if (String.valueOf(getT_password().getPassword()).equals("111111")) {
                            getJfmm().dispose();
                            new UserInfoEN(String.valueOf(getT_name().getText()));
                        }
                    }
                } else {
                    System.out.println("It's impossible to execute here.");
                }
            }
        });
        setB_reset(new JButton("Reset"));
        getB_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_name().setText("");
                getT_password().setText("");
            }
        });
        setB_register(new JButton("Register"));
        getB_register().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st.equals("Admin"))
                    JOptionPane.showMessageDialog(null, "Note: login in administrator mode prohibits registration！",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    getJfmm().dispose();
                    if (getRf_1() != null) getRf_1().dispose();
                    RegisterEN rf = new RegisterEN();
                    setRf_1(rf);
                    rf.setVisible(true);
                }
            }
        });
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2, 2, 5, 5));
        p.add(getL_name());
        p.add(getT_name());
        p.add(getL_password());
        p.add(getT_password());
        p.setBounds(5, 5, 350, 185);
        add(p);
        p = new JPanel();
        p.setLayout(new GridLayout(1, 3, 5, 5));
        p.add(getB_login());
        p.add(getB_reset());
        p.add(getB_register());
        p.setBounds(5, 205, 350, 50);
        add(p);
    }

    @Override
    public void run() {
        new LoginEN();
    }

    public JFrame getJfmm() {
        return jfmm;
    }

    public void setJfmm(JFrame jfmm) {
        this.jfmm = jfmm;
    }

    public JLabel getL_name() {
        return l_name;
    }

    public void setL_name(JLabel l_name) {
        this.l_name = l_name;
    }

    public JLabel getL_password() {
        return l_password;
    }

    public void setL_password(JLabel l_password) {
        this.l_password = l_password;
    }

    public JTextField getT_name() {
        return t_name;
    }

    public void setT_name(JTextField t_name) {
        this.t_name = t_name;
    }

    public JPasswordField getT_password() {
        return t_password;
    }

    public void setT_password(JPasswordField t_password) {
        this.t_password = t_password;
    }

    public JButton getB_login() {
        return b_login;
    }

    public void setB_login(JButton b_login) {
        this.b_login = b_login;
    }

    public JButton getB_reset() {
        return b_reset;
    }

    public void setB_reset(JButton b_reset) {
        this.b_reset = b_reset;
    }

    public JButton getB_register() {
        return b_register;
    }

    public void setB_register(JButton b_register) {
        this.b_register = b_register;
    }

    public RegisterEN getRf_1() {
        return rf_1;
    }

    public void setRf_1(RegisterEN rf_1) {
        this.rf_1 = rf_1;
    }

    public int getTage() {
        return tage;
    }

    public void setTage(int tage) {
        this.tage = tage;
    }
}
