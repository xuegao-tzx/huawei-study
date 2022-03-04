package g2048.v3_5.ENGame;
/**
 * @author Xcl
 * @date 2021/12/2 09:48
 * @package g2048.v3
 */

import g2048.v3_5.itf.IData;
import g2048.v3_5.lib.GMessage;
import g2048.v3_5.sql.SqlMessage;
import g2048.v3_5.tool.GIdCard;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static g2048.v3_5.lib.GMessage.GMusicMessagePath;

public class RegisterEN extends JFrame implements IData {
    static final long serialVersionUID = -3035113424256798327L;
    static final RegisterEN pre_u2 = null;
    String sss = null;
    JFrame jfm = this;
    String panduancf = null;
    ButtonGroup bg;
    JLabel l_id;
    JLabel l_name;
    JLabel l_password1;
    JLabel l_password2;
    JLabel l_sex;
    JLabel l_card;
    JTextField t_id;
    JTextField t_name;
    JPasswordField p_password1;
    JPasswordField p_password2;
    JPasswordField p_card;
    JRadioButton r_male;
    JRadioButton r_female;
    JButton b_reg;
    JButton b_reset;
    String sex1;

    RegisterEN() {
        init(getSss());
        setTitle("Game Register");
        setSize(300, 450);
        setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        setLocationRelativeTo(null);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static RegisterEN getPre_u2() {
        return pre_u2;
    }

    @Override
    public void init(String sss) {
        setLayout(new GridLayout(7, 2, 5, 5));
        setL_id(new JLabel("Game name", JLabel.CENTER));
        setL_name(new JLabel("Real name", JLabel.CENTER));
        setL_password1(new JLabel("Passwd", JLabel.CENTER));
        setL_password2(new JLabel("Sure Passwd", JLabel.CENTER));
        setL_card(new JLabel("ID Card", JLabel.CENTER));
        setL_sex(new JLabel("Sex", JLabel.CENTER));
        setT_id(new JTextField());
        setT_name(new JTextField());
        setP_password1(new JPasswordField());
        setP_password2(new JPasswordField());
        setP_card(new JPasswordField());
        JPanel p = new JPanel();
        setBg(new ButtonGroup());
        setR_male(new JRadioButton("Man"));
        setR_female(new JRadioButton("Female"));
        getBg().add(getR_male());
        getBg().add(getR_female());
        setB_reg(new JButton("Register"));
        getB_reg().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Be sure to keep your user information in " +
                                "mind. Once the registration is successful, only the administrator can change the password.",
                        "Notice", JOptionPane.CANCEL_OPTION);
                if (RegisterEN.getPre_u2() != null) RegisterEN.getPre_u2().dispose();
                if (getR_female().isSelected()) setSex1("Female");
                else if (getR_male().isSelected()) setSex1("Man");
                /*数据库开始*/
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                            SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("select count(*) from t_user where Tname='" + getT_id().getText() + "';");
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
                /*数据库结束*/
                if (getPanduancf().equals("cf")) {
                    JOptionPane.showMessageDialog(null, "Duplicate game name, please re register!！",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    getT_id().setText("");
                    getT_name().setText("");
                    getP_password1().setText("");
                    getP_password2().setText("");
                    getP_card().setText("");
                } else {
                    if (String.valueOf(getP_card().getPassword()).length() == 18) {
                        if (GIdCard.isIDNumberAge(String.valueOf(getP_card().getPassword())) <= 85) {
                            if (String.valueOf(getP_password1().getPassword()).equals(String.valueOf((getP_password2().getPassword())))) {
                                UserEN u = new UserEN(String.valueOf(getT_id().getText()), String.valueOf(getT_name().getText()),
                                        String.valueOf(getP_password1().getPassword()), String.valueOf(getSex1()).charAt(0),
                                        String.valueOf(getP_card().getPassword()));
                                String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
                                List<String> listuser = new ArrayList<>();
                                listuser.add(u.getId());
                                listuser.add(u.getName());
                                listuser.add(u.getPassword());
                                listuser.add(u.getCard());
                                listuser.add(String.valueOf(u.getSex()));
                                listuser.add(datime);
                                String data = "insert into t_user(Tname, Trname, Tpasswd, Tcard, Tsex,Tage, Ttime,Status,StatusTime) values ('" +
                                        listuser.get(0) + "','" + listuser.get(1) + "','" + listuser.get(2) + "','" +
                                        listuser.get(3) + "','" + listuser.get(4) + "','" + GIdCard.isIDNumberAge(String.valueOf(getP_card().getPassword()))
                                        + "','" + listuser.get(5) + "','" + 111 + "','" + listuser.get(5) + "');";
                                String data1 = "insert into t_user1(Tname) values ('" +
                                        listuser.get(0) + "');";
                                /*数据库开始*/
                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection conn1 = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                                            SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                                    Statement stmt1 = conn1.createStatement();
                                    stmt1.execute(data);
                                    stmt1.execute(data1);
                                    JOptionPane.showMessageDialog(null, "Register Success！", " ", -1);
                                } catch (Exception e_3) {
                                    JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                                    e_3.printStackTrace();
                                }
                                /*数据库结束*/
                                getJfm().dispose();
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
                                                    "Thanks for your understanding.",
                                            "Welcome users :" + getT_id().getText() + "'s login prompt for.", JOptionPane.CANCEL_OPTION);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            GMusic gm = new GMusic(GMusicMessagePath);
                                            while (true) {
                                                gm.run();
                                            }
                                        }
                                    }).start();
                                    new GView4EN(listuser.get(0)).showView();
                                } else if (nnn == JOptionPane.NO_OPTION) {
                                    System.exit(0);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Here we thank you for " +
                                                "playing our 2048 game, but because you are old, for your health, you will not be able to play this game. Thank you for your understanding!",
                                        "WARN!", JOptionPane.ERROR_MESSAGE);
                                getT_id().setText("");
                                getT_name().setText("");
                                getP_password1().setText("");
                                getP_password2().setText("");
                                getP_card().setText("");
                                System.exit(0);
                            }
                        } else {
                            getP_password1().setText("");
                            getP_password2().setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ID number is not legal! Please re-enter!!!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                        getT_id().setText("");
                        getT_name().setText("");
                        getP_password1().setText("");
                        getP_password2().setText("");
                        getP_card().setText("");
                    }
                }
            }
        });
        setB_reset(new JButton("Reset!"));
        getB_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_id().setText("");
                getT_name().setText("");
                getP_password1().setText("");
                getP_password2().setText("");
                getP_card().setText("");
                JOptionPane.showMessageDialog(null, "Reset success！", " ", 1);
            }
        });
        add(getL_id());
        add(getT_id());
        add(getL_name());
        add(getT_name());
        add(getL_password1());
        add(getP_password1());
        add(getL_password2());
        add(getP_password2());
        add(getL_card());
        add(getP_card());
        add(getL_sex());
        p.add(getR_male());
        p.add(getR_female());
        add(p);
        add(getB_reg());
        add(getB_reset());
    }

    public String getSss() {
        return sss;
    }

    public void setSss(String sss) {
        this.sss = sss;
    }

    public JFrame getJfm() {
        return jfm;
    }

    public void setJfm(JFrame jfm) {
        this.jfm = jfm;
    }

    public String getPanduancf() {
        return panduancf;
    }

    public void setPanduancf(String panduancf) {
        this.panduancf = panduancf;
    }

    public ButtonGroup getBg() {
        return bg;
    }

    public void setBg(ButtonGroup bg) {
        this.bg = bg;
    }

    public JLabel getL_id() {
        return l_id;
    }

    public void setL_id(JLabel l_id) {
        this.l_id = l_id;
    }

    public JLabel getL_name() {
        return l_name;
    }

    public void setL_name(JLabel l_name) {
        this.l_name = l_name;
    }

    public JLabel getL_password1() {
        return l_password1;
    }

    public void setL_password1(JLabel l_password1) {
        this.l_password1 = l_password1;
    }

    public JLabel getL_password2() {
        return l_password2;
    }

    public void setL_password2(JLabel l_password2) {
        this.l_password2 = l_password2;
    }

    public JLabel getL_sex() {
        return l_sex;
    }

    public void setL_sex(JLabel l_sex) {
        this.l_sex = l_sex;
    }

    public JLabel getL_card() {
        return l_card;
    }

    public void setL_card(JLabel l_card) {
        this.l_card = l_card;
    }

    public JTextField getT_id() {
        return t_id;
    }

    public void setT_id(JTextField t_id) {
        this.t_id = t_id;
    }

    public JTextField getT_name() {
        return t_name;
    }

    public void setT_name(JTextField t_name) {
        this.t_name = t_name;
    }

    public JPasswordField getP_password1() {
        return p_password1;
    }

    public void setP_password1(JPasswordField p_password1) {
        this.p_password1 = p_password1;
    }

    public JPasswordField getP_password2() {
        return p_password2;
    }

    public void setP_password2(JPasswordField p_password2) {
        this.p_password2 = p_password2;
    }

    public JPasswordField getP_card() {
        return p_card;
    }

    public void setP_card(JPasswordField p_card) {
        this.p_card = p_card;
    }

    public JRadioButton getR_male() {
        return r_male;
    }

    public void setR_male(JRadioButton r_male) {
        this.r_male = r_male;
    }

    public JRadioButton getR_female() {
        return r_female;
    }

    public void setR_female(JRadioButton r_female) {
        this.r_female = r_female;
    }

    public JButton getB_reg() {
        return b_reg;
    }

    public void setB_reg(JButton b_reg) {
        this.b_reg = b_reg;
    }

    public JButton getB_reset() {
        return b_reset;
    }

    public void setB_reset(JButton b_reset) {
        this.b_reset = b_reset;
    }

    public String getSex1() {
        return sex1;
    }

    public void setSex1(String sex1) {
        this.sex1 = sex1;
    }
}
