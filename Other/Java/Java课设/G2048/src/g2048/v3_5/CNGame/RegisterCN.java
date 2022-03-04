package g2048.v3_5.CNGame;
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
import java.util.List;
import java.util.Timer;
import java.util.*;

import static g2048.v3_5.lib.GMessage.GMusicMessagePath;
import static g2048.v3_5.lib.GMessage.GPlayTime;

public class RegisterCN extends JFrame implements IData {
    static final long serialVersionUID = -3035113424256798327L;
    static final RegisterCN pre_u2 = null;
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

    RegisterCN() {
        init(getSss());
        setTitle("2048游戏注册");
        setSize(300, 450);
        setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        setLocationRelativeTo(null);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static RegisterCN getPre_u2() {
        return pre_u2;
    }

    @Override
    public void init(String sss) {
        setLayout(new GridLayout(7, 2, 5, 5));
        setL_id(new JLabel("用户名", JLabel.CENTER));
        setL_name(new JLabel("真实姓名", JLabel.CENTER));
        setL_password1(new JLabel("密码", JLabel.CENTER));
        setL_password2(new JLabel("确认密码", JLabel.CENTER));
        setL_card(new JLabel("身份证号", JLabel.CENTER));
        setL_sex(new JLabel("性别", JLabel.CENTER));
        setT_id(new JTextField());
        setT_name(new JTextField());
        setP_password1(new JPasswordField());
        setP_password2(new JPasswordField());
        setP_card(new JPasswordField());
        JPanel p = new JPanel();
        setBg(new ButtonGroup());
        setR_male(new JRadioButton("男"));
        setR_female(new JRadioButton("女"));
        getBg().add(getR_male());
        getBg().add(getR_female());
        setB_reg(new JButton("注册"));
        getB_reg().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "务必牢记自己的用户信息，一旦注册成功后只有管理员可以更改密码。", "提示", JOptionPane.CANCEL_OPTION);
                if (RegisterCN.getPre_u2() != null) RegisterCN.getPre_u2().dispose();
                if (getR_female().isSelected()) setSex1("女");
                else if (getR_male().isSelected()) setSex1("男");
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
                    JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                    e_2.printStackTrace();
                }
                /*数据库结束*/
                if (getPanduancf().equals("cf")) {
                    JOptionPane.showMessageDialog(null, "用户名重复，请重新注册！",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    getT_id().setText("");
                    getT_name().setText("");
                    getP_password1().setText("");
                    getP_password2().setText("");
                    getP_card().setText("");
                } else {
                    if (GIdCard.isIDNumber(String.valueOf(getP_card().getPassword()))) {
                        if (GIdCard.isIDNumberSex(String.valueOf(getP_card().getPassword())).equals(String.valueOf(getSex1()))) {
                            if (GIdCard.isIDNumberAge(String.valueOf(getP_card().getPassword())) <= 85) {
                                if (String.valueOf(getP_password1().getPassword()).equals(String.valueOf((getP_password2().getPassword())))) {
                                    UserCN u = new UserCN(String.valueOf(getT_id().getText()), String.valueOf(getT_name().getText()),
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
                                        JOptionPane.showMessageDialog(null, "注册成功！", " ", -1);
                                    } catch (Exception e_3) {
                                        JOptionPane.showMessageDialog(null, "数据库综合错误，如果你看到此弹窗，请立刻联系我。",
                                                "ERROR!", JOptionPane.ERROR_MESSAGE);
                                        e_3.printStackTrace();
                                    }
                                    /*数据库结束*/
                                    getJfm().dispose();
                                    int nnn = JOptionPane.showConfirmDialog(null, "您的所有游戏数据将被自动保存" +
                                                    "在位于中国北京的服务器中,继续游戏默认同意我们的隐私协议,是否同意我们的隐私协议?", "隐私申明"
                                            , JOptionPane.YES_NO_OPTION);
                                    if (nnn == JOptionPane.YES_OPTION) {
                                        JOptionPane.showMessageDialog(null, "注意:" + "\n" +
                                                        "1.请通过菜单栏退出游戏." + "\n" + "2.默认会接着上一次记录." + "\n" +
                                                        "3.如果想重新开始请按Esc或R." + "\n" + "4.请勿在游戏中随意从主界面点X" +
                                                        "退出，否则会概率报错." + "\n" + "5.务必保证网络的状态良好，否则切换会卡顿，" +
                                                        "谢谢理解.",
                                                "欢迎用户" + getT_id().getText() + ",的登录提示", JOptionPane.CANCEL_OPTION);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                GMusic gm = new GMusic(GMusicMessagePath);
                                                while (true) {
                                                    gm.run();
                                                }
                                            }
                                        }).start();
                                        if (GIdCard.isIDNumberAge(String.valueOf(getP_card().getPassword())) < 18) {
                                            Timer timer = new Timer();
                                            timer.schedule(new TimerTask() {
                                                @Override
                                                public void run() {
                                                    LoginCN.setFcm("stop");
                                                    JOptionPane.showMessageDialog(null, "您是未成年人，今日游玩时间已达上限，程序将会退出！",
                                                            "ERROR!", JOptionPane.ERROR_MESSAGE);

                                                }
                                            }, GPlayTime);
                                        }
                                        new GView4CN(listuser.get(0)).showView();
                                    } else if (nnn == JOptionPane.NO_OPTION) {
                                        System.exit(0);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "在此我们很感谢您游玩我们的2048小游戏，但是由于您年事已高，为了您的健康，本游戏您将无法游玩，感谢您的谅解！",
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
                            JOptionPane.showMessageDialog(null, "身份证号码与性别不符！请重新输入！！！",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                            getT_id().setText("");
                            getT_name().setText("");
                            getP_password1().setText("");
                            getP_password2().setText("");
                            getP_card().setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "身份证号码不合法！请重新输入！！！",
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
        setB_reset(new JButton("重置"));
        getB_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_id().setText("");
                getT_name().setText("");
                getP_password1().setText("");
                getP_password2().setText("");
                getP_card().setText("");
                JOptionPane.showMessageDialog(null, "重置成功！", " ", 1);
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
