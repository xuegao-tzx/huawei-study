package g2048.v3_5.CNGame;
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
import java.util.Timer;
import java.util.TimerTask;

import static g2048.v3_5.lib.GMessage.GMusicMessagePath;
import static g2048.v3_5.lib.GMessage.GPlayTime;

public class LoginCN extends JFrame implements Runnable, IData, ILogin {
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
    RegisterCN rf_1 = null;

    LoginCN() {
        setTitle(getStt1() + "登录窗体");
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
        LoginCN.stt1 = stt1;
    }

    public static String getFcm() {
        return fcm;
    }

    public static void setFcm(String fcm) {
        LoginCN.fcm = fcm;
    }

    public static String getStt2() {
        return stt2;
    }

    public static void setStt2(String stt2) {
        LoginCN.stt2 = stt2;
    }

    public static String getStt3() {
        return stt3;
    }

    public static void setStt3(String stt3) {
        LoginCN.stt3 = stt3;
    }

    public static String getStt4() {
        return stt4;
    }

    public static void setStt4(String stt4) {
        LoginCN.stt4 = stt4;
    }


    @Override
    public void init(String st) {
        setLayout(null);
        setL_name(new JLabel("用户名", JLabel.CENTER));
        setL_password(new JLabel("用户密码", JLabel.CENTER));
        setT_name(new JTextField());
        setT_password(new JPasswordField());
        setB_login(new JButton("登录"));
        getB_login().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "登录成功会跳转！", " ", -1);
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
                                        JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
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
                                            JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                                    "ERROR!", JOptionPane.ERROR_MESSAGE);
                                            e1.printStackTrace();
                                        }
                                        JOptionPane.showMessageDialog(null, "当前用户" + getT_name().getText() + "已于" + getStt3() + ",在其它设备登录，您当前无法登录，谢谢！",
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
                                            JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                                    "ERROR!", JOptionPane.ERROR_MESSAGE);
                                            e1.printStackTrace();
                                        }
                                        getJfmm().dispose();
                                        int nnn = JOptionPane.showConfirmDialog(null, "您的所有游戏数据将被自动保存" +
                                                        "在位于中国北京的服务器中,继续游戏默认同意我们的隐私协议,是否同意我们的隐私协议?", "隐私申明"
                                                , JOptionPane.YES_NO_OPTION);
                                        if (nnn == JOptionPane.YES_OPTION) {
                                            JOptionPane.showMessageDialog(null, "注意:" + "\n" +
                                                            "1.请通过菜单栏退出游戏." + "\n" + "2.默认会接着上一次记录." + "\n" +
                                                            "3.如果想重新开始请按Esc或R." + "\n" + "4.请勿在游戏中随意从主界面点X" +
                                                            "退出，否则下次会无法登录." + "\n" + "5.务必保证网络的状态良好，否则切换会卡顿，" +
                                                            "谢谢理解." + "\n" + "6.您上次登录时间为:" + getStt4() + ".",
                                                    "欢迎用户" + getT_name().getText() + ",的登录提示", JOptionPane.CANCEL_OPTION);
                                            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
                                            try {
                                                Class.forName("com.mysql.cj.jdbc.Driver");
                                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                                Statement stmt = conn.createStatement();
                                                stmt.executeUpdate("update t_user set StatusTime= '" + datime + "',Status=" + 111 + " where Tname = '" + getT_name().getText() + "';");
                                            } catch (Exception e_5) {
                                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
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
                                            new GView4CN(getT_name().getText()).showView();
                                            try {
                                                Class.forName("com.mysql.cj.jdbc.Driver");
                                                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                                Statement stmt = conn.createStatement();
                                                ResultSet rs = stmt.executeQuery("select Tage from t_user where Tname='" + getT_name().getText() + "';");
                                                while (rs.next()) {
                                                    setTage(Integer.parseInt(rs.getString(1)));
                                                    break;
                                                }
                                            } catch (Exception e1) {
                                                JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                                                e1.printStackTrace();
                                            }
                                            if (getTage() < 18) {
                                                Timer timer = new Timer();
                                                timer.schedule(new TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        setFcm("stop");
                                                        JOptionPane.showMessageDialog(null, "您是未成年人，今日游玩时间已达上限，程序将会退出！",
                                                                "ERROR!", JOptionPane.ERROR_MESSAGE);

                                                    }
                                                }, GPlayTime);
                                            }

                                        } else if (nnn == JOptionPane.NO_OPTION) {
                                            System.exit(0);
                                        }
                                        break;
                                    } else {
                                        System.out.println("不可能执行到这里...");
                                        break;
                                    }

                                }
                            }
                        }
                    } catch (Exception e_1) {
                        JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                "ERROR!", JOptionPane.ERROR_MESSAGE);
                        e_1.printStackTrace();
                    }
                    /*数据库结束*/
                } else if (st.equals("管理员")) {
                    if (String.valueOf(getT_name().getText()).equals("admin123")) {
                        if (String.valueOf(getT_password().getPassword()).equals("111111")) {
                            getJfmm().dispose();
                            new UserInfoCN(String.valueOf(getT_name().getText()));
                        }
                    }
                } else {
                    System.out.println("不可能执行到这里...");
                }
            }
        });
        setB_reset(new JButton("重置"));
        getB_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_name().setText("");
                getT_password().setText("");
            }
        });
        setB_register(new JButton("点我注册"));
        getB_register().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st.equals("管理员"))
                    JOptionPane.showMessageDialog(null, "注意：管理员模式登录禁止注册！",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    getJfmm().dispose();
                    if (getRf_1() != null) getRf_1().dispose();
                    RegisterCN rf = new RegisterCN();
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
        new LoginCN();
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

    public RegisterCN getRf_1() {
        return rf_1;
    }

    public void setRf_1(RegisterCN rf_1) {
        this.rf_1 = rf_1;
    }

    public int getTage() {
        return tage;
    }

    public void setTage(int tage) {
        this.tage = tage;
    }
}
