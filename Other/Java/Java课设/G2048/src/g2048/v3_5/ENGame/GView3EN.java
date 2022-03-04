package g2048.v3_5.ENGame;

/**
 * @author Xcl
 * @date 2021/12/3 09:48
 * @package g2048.v3
 */

import g2048.v3_5.itf.IData;
import g2048.v3_5.itf.IGView;
import g2048.v3_5.lib.GMessage;
import g2048.v3_5.sql.SqlMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class GView3EN implements IGView {
    static int score = 0;
    static int scorem = 0;
    static String statuspd = "666";
    String lzname;
    String panduancf = null;
    String sstt;
    String user = null;
    String jindu = null;
    String huifu = null;
    String gcbl = null;
    JFrame JFm;
    JLabel JSnm;
    JLabel JSn;
    JLabel Jsm;
    JLabel Js;
    GameMb gamemb;
    JLabel JTip;
    JMenu jm;
    JMenu jm0;
    JMenu jm1;
    JMenuBar jmb;
    JMenuItem v3;
    JMenuItem v4;
    JMenuItem v5;
    JMenuItem L1;
    JMenuItem L2;
    JMenuItem L3;
    JMenuItem L4;
    JMenuItem About;
    JMenuItem chart;
    JMenuItem exit;

    GView3EN(String st) {
        setSstt(st);
        setUser(st);
        init(st);

    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GView3EN.score = score;
    }

    public static int getScorem() {
        return scorem;
    }

    public static void setScorem(int scorem) {
        GView3EN.scorem = scorem;
    }

    public static String getStatuspd() {
        return statuspd;
    }

    public static void setStatuspd(String statuspd) {
        GView3EN.statuspd = statuspd;
    }

    @Override
    public void L1() {
    }

    @Override
    public void L2() {
    }

    @Override
    public void L3() {
    }

    @Override
    public void L4() {
    }

    public String getJindu() {
        return jindu;
    }

    public void setJindu(String jindu) {
        this.jindu = jindu;
    }

    @Override
    public void showView() {
        getJFm().setVisible(true);
    }

    @Override
    public void init(String st) {
        setJFm(new JFrame(st + "'s 2048 Small Game"));
        getJFm().setSize(450, 600);
        getJFm().setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        getJFm().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getJFm().setLocationRelativeTo(null);
        getJFm().setResizable(false);
        getJFm().setLayout(null);
        setJmb(new JMenuBar());
        setJm(new JMenu("function"));
        setV3(new JMenuItem("3*3"));
        getV3().addActionListener(this);
        setV4(new JMenuItem("4*4"));
        getV4().addActionListener(this);
        setV5(new JMenuItem("5*5"));
        getV5().addActionListener(this);
        getJm().add(getV3());
        getJm().add(getV4());
        getJm().add(getV5());

        setJm1(new JMenu("Other"));
        setAbout(new JMenuItem("About"));
        getAbout().addActionListener(this);
        setChart(new JMenuItem("Chart"));
        getChart().addActionListener(this);
        setExit(new JMenuItem("Exit"));
        getExit().addActionListener(this);
        getJm1().add(getAbout());
        getJm1().add(getChart());
        getJm1().add(getExit());
        getJmb().add(getJm());
        getJmb().add(getJm1());
        getJFm().setJMenuBar(getJmb());
        setJSnm(new JLabel("Your highest score so far", JLabel.CENTER));
        getJSnm().setFont(IData.scf);
        getJSnm().setForeground(Color.WHITE);
        getJSnm().setOpaque(true);
        getJSnm().setBackground(Color.GRAY);
        getJSnm().setBounds(10, 1, 185, 29);
        getJFm().add(getJSnm());
        setJsm(new JLabel("0", JLabel.CENTER));
        getJsm().setFont(IData.scf);
        getJsm().setForeground(Color.WHITE);
        getJsm().setOpaque(true);
        getJsm().setBackground(Color.GRAY);
        getJsm().setBounds(10, 30, 185, 30);
        getJFm().add(getJsm());
        setJSn(new JLabel(st + "'s now score.", JLabel.CENTER));
        getJSn().setFont(IData.scf);
        getJSn().setForeground(Color.WHITE);
        getJSn().setOpaque(true);
        getJSn().setBackground(Color.GRAY);
        getJSn().setBounds(225, 1, 195, 29);
        getJFm().add(getJSn());
        setJs(new JLabel("0", JLabel.CENTER));
        getJs().setFont(IData.scf);
        getJs().setForeground(Color.WHITE);
        getJs().setOpaque(true);
        getJs().setBackground(Color.GRAY);
        getJs().setBounds(225, 30, 195, 30);
        getJFm().add(getJs());
        setJTip(new JLabel("Operation: ↑↓←→ / WSAD, press ESC / R to restart", JLabel.CENTER));
        getJTip().setFont(IData.nomf);
        getJTip().setForeground(Color.DARK_GRAY);
        getJTip().setBounds(26, 60, 400, 40);
        getJFm().add(getJTip());
        setGamemb(new GameMb());
        getGamemb().setBounds(0, 100, 450, 450);
        getGamemb().setBackground(Color.GRAY);
        getGamemb().setFocusable(true);
        getGamemb().setLayout(new FlowLayout());
        getJFm().add(getGamemb());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getV4()) {
            V4();
        } else if (e.getSource() == getV5()) {
            V5();
        } else if (e.getSource() == getAbout()) {
            About();
        } else if (e.getSource() == getChart()) {
            Chart();
        } else if (e.getSource() == getExit()) {
            Exit();
        }
    }

    void V4() {
        if (getStatuspd().equals("333")) {
            new GView4EN(getUser()).showView();
            getJFm().dispose();
        } else {
            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set Ttime3 = '" + datime + "',Tjindu3=" + getGcbl() + " where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            new GView4EN(getUser()).showView();
            getJFm().dispose();
        }
    }

    void V5() {
        if (getStatuspd().equals("333")) {
            new GView5EN(getUser()).showView();
            getJFm().dispose();
        } else {
            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set Ttime3 = '" + datime + "',Tjindu3=" + getGcbl() + " where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            new GView5EN(getUser()).showView();
            getJFm().dispose();
        }
    }

    @Override
    public void About() {
        if (getStatuspd().equals("333")) {
            getJFm().dispose();
            new GAboutEN(getUser(), 3);
        } else {
            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set Ttime3 = '" + datime + "',Tjindu3=" + getGcbl() + " where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            getJFm().dispose();
            new GAboutEN(getUser(), 3);
        }
    }

    @Override
    public void Chart() {
        if (getStatuspd().equals("333")) {
            getJFm().dispose();
            new ChartsEN(user, "3*3's 2048Game", 3);
        } else {
            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set Ttime4 = '" + datime + "',Tjindu4=" + getGcbl() + " where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            getJFm().dispose();
            new ChartsEN(user, "3*3's 2048Game", 3);
        }
    }

    @Override
    public void Exit() {
        String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
        if (getStatuspd().equals("333")) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set StatusTime= '" + datime + "',Status=" + 222 + " where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            getJFm().dispose();
            System.exit(0);
        } else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set Ttime3 = '" + datime + "',Tjindu3=" + getGcbl() + " where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("update t_user set StatusTime= '" + datime + "',Status='" + 222 + "' where Tname = '" + getUser() + "';");
            } catch (Exception e_5) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e_5.printStackTrace();
            }
            getJFm().dispose();
            System.exit(0);
        }
    }

    @Override
    public void fcmpd() {
        if (LoginEN.getFcm().equals("stop")) {
            Exit();
        }
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public JFrame getJFm() {
        return JFm;
    }

    @Override
    public void setJFm(JFrame JFm) {
        this.JFm = JFm;
    }

    @Override
    public JLabel getJSnm() {
        return JSnm;
    }

    @Override
    public void setJSnm(JLabel JSnm) {
        this.JSnm = JSnm;
    }

    @Override
    public JLabel getJSn() {
        return JSn;
    }

    @Override
    public void setJSn(JLabel JSn) {
        this.JSn = JSn;
    }

    @Override
    public JLabel getJsm() {
        return Jsm;
    }

    @Override
    public void setJsm(JLabel jsm) {
        Jsm = jsm;
    }

    @Override
    public JLabel getJs() {
        return Js;
    }

    @Override
    public void setJs(JLabel js) {
        Js = js;
    }

    public GameMb getGamemb() {
        return gamemb;
    }

    public void setGamemb(GameMb gamemb) {
        this.gamemb = gamemb;
    }

    @Override
    public String getGcbl() {
        return gcbl;
    }

    @Override
    public void setGcbl(String gcbl) {
        this.gcbl = gcbl;
    }

    @Override
    public JLabel getJTip() {
        return JTip;
    }

    @Override
    public void setJTip(JLabel JTip) {
        this.JTip = JTip;
    }

    @Override
    public String getHuifu() {
        return huifu;
    }

    @Override
    public void setHuifu(String huifu) {
        this.huifu = huifu;
    }

    @Override
    public JMenu getJm() {
        return jm;
    }

    @Override
    public void setJm(JMenu jm) {
        this.jm = jm;
    }

    @Override
    public JMenu getJm1() {
        return jm1;
    }

    @Override
    public void setJm1(JMenu jm1) {
        this.jm1 = jm1;
    }

    @Override
    public JMenuBar getJmb() {
        return jmb;
    }

    @Override
    public void setJmb(JMenuBar jmb) {
        this.jmb = jmb;
    }

    @Override
    public JMenuItem getV3() {
        return v3;
    }

    @Override
    public void setV3(JMenuItem v3) {
        this.v3 = v3;
    }

    @Override
    public JMenuItem getV4() {
        return v4;
    }

    @Override
    public void setV4(JMenuItem v4) {
        this.v4 = v4;
    }

    @Override
    public JMenuItem getV5() {
        return v5;
    }

    @Override
    public void setV5(JMenuItem v5) {
        this.v5 = v5;
    }

    @Override
    public JMenuItem getAbout() {
        return About;
    }

    @Override
    public void setAbout(JMenuItem About) {
        this.About = About;
    }

    @Override
    public JMenuItem getExit() {
        return exit;
    }

    @Override
    public void setExit(JMenuItem exit) {
        this.exit = exit;
    }

    @Override
    public JMenuItem getChart() {
        return chart;
    }

    @Override
    public void setChart(JMenuItem chart) {
        this.chart = chart;
    }

    public JMenu getJm0() {
        return jm0;
    }

    public void setJm0(JMenu jm0) {
        this.jm0 = jm0;
    }

    public JMenuItem getL1() {
        return L1;
    }

    public void setL1(JMenuItem l1) {
        L1 = l1;
    }

    public JMenuItem getL2() {
        return L2;
    }

    public void setL2(JMenuItem l2) {
        L2 = l2;
    }

    public JMenuItem getL3() {
        return L3;
    }

    public void setL3(JMenuItem l3) {
        L3 = l3;
    }

    public JMenuItem getL4() {
        return L4;
    }

    public void setL4(JMenuItem l4) {
        L4 = l4;
    }

    public String getPanduancf() {
        return panduancf;
    }

    public void setPanduancf(String panduancf) {
        this.panduancf = panduancf;
    }

    public String getLzname() {
        return lzname;
    }

    public void setLzname(String lzname) {
        this.lzname = lzname;
    }

    public String getSstt() {
        return sstt;
    }

    public void setSstt(String sstt) {
        this.sstt = sstt;
    }

    class GameMb extends JPanel implements KeyListener {
        private static final long serialVersionUID = -7237908387425382979L;
        private final GBackGroupEN[][] GBackGroupENS = new GBackGroupEN[3][3];
        private boolean added = true;

        GameMb() {
            huifu1();
            initGame();
            addKeyListener(this);
        }


        public void huifu1() {
            for (int indexRow = 0; indexRow < 3; indexRow++)
                for (int indexCol = 0; indexCol < 3; indexCol++) GBackGroupENS[indexRow][indexCol] = new GBackGroupEN();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn1 = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt1 = conn1.createStatement();
                ResultSet rs1 = stmt1.executeQuery("select Tjindu3 from t_user where Tname='" + getUser() + "';");
                while (rs1.next()) {
                    setJindu(rs1.getString(1));
                    if (getJindu() != null) {
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            if (getJindu().equals("333")) {
                added = true;
                createCheck();
                added = true;
                createCheck();
                setHuifu("yhf");
            } else {
                String[] split = getJindu().split(":");
                String[] split2 = split[0].split("\\.");
                GBackGroupENS[0][0].setValue(Integer.parseInt(split2[0]));
                GBackGroupENS[0][1].setValue(Integer.parseInt(split2[1]));
                GBackGroupENS[0][2].setValue(Integer.parseInt(split2[2]));
                GBackGroupENS[1][0].setValue(Integer.parseInt(split2[3]));
                GBackGroupENS[1][1].setValue(Integer.parseInt(split2[4]));
                GBackGroupENS[1][2].setValue(Integer.parseInt(split2[5]));
                GBackGroupENS[2][0].setValue(Integer.parseInt(split2[6]));
                GBackGroupENS[2][1].setValue(Integer.parseInt(split2[7]));
                GBackGroupENS[2][2].setValue(Integer.parseInt(split2[8]));
                setHuifu("fff");
            }
        }


        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_R) {
                fcmpd();
                initGame();
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
                fcmpd();
                moveLeft();
                createCheck();
                judgeGameOver();
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
                fcmpd();
                moveRight();
                createCheck();
                judgeGameOver();
            } else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
                fcmpd();
                moveUp();
                createCheck();
                judgeGameOver();
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
                fcmpd();
                moveDown();
                createCheck();
                judgeGameOver();
            }
            repaint();
        }


        private void initGame() {
            GView3EN.setScore(0);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                        SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select Tscore3 from t_user where Tname='" + getUser() + "';");
                while (rs.next()) {
                    GView3EN.setScorem(rs.getInt(1));
                    if (GView3EN.getScorem() != 0) {
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                        "ERROR!", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            if (GView3EN.getScorem() == 1) {
                GView3EN.setScorem(0);
            }
            getJsm().setText(GView3EN.getScorem() + "");
            if (getHuifu().equals("fff")) {
                setHuifu("yhf");
            } else {
                for (int indexRow = 0; indexRow < 3; indexRow++) {
                    for (int indexCol = 0; indexCol < 3; indexCol++) {
                        GBackGroupENS[indexRow][indexCol].setValue(0);
                    }
                }
                added = true;
                createCheck();
                added = true;
                createCheck();
            }
        }

        private void createCheck() {
            List<Object> list = getEmptyChecks();
            if (!list.isEmpty() && added) {
                Random random = new Random();
                int index = random.nextInt(list.size());
                GBackGroupEN GBackGroupEN = (GBackGroupEN) list.get(index);
                // 2, 4出现概率9:1
                int sjj = random.nextInt(12);
                if (0 <= sjj && sjj <= 11) GBackGroupEN.setValue(2);
                else if (sjj == 12) GBackGroupEN.setValue(4);
                else System.out.println("It's impossible to execute here.");
                added = false;
            }
        }

        private List<Object> getEmptyChecks() {
            List<Object> objectList = new ArrayList<>();
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (GBackGroupENS[i][j].getValue() == 0) objectList.add(GBackGroupENS[i][j]);
            return objectList;
        }

        private boolean judgeGameOver() {
            if (GView3EN.getScorem() < GView3EN.getScore()) {
                GView3EN.setScorem(GView3EN.getScore());
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                            SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("update t_user set Tscore3 = " + getScorem() + " where Tname = '" + getUser() + "';");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
            setGcbl("'" + GBackGroupENS[0][0].getValue() + "." + GBackGroupENS[0][1].getValue() + "." + GBackGroupENS[0][2].getValue() +
                    "." + GBackGroupENS[1][0].getValue() + "." + GBackGroupENS[1][1].getValue() + "." + GBackGroupENS[1][2].getValue() +
                    "." + GBackGroupENS[2][0].getValue() + "." + GBackGroupENS[2][1].getValue() + "." + GBackGroupENS[2][2].getValue() +
                    ":'");
            getJs().setText(GView3EN.getScore() + "");
            getJsm().setText(GView3EN.getScorem() + "");
            if (!getEmptyChecks().isEmpty()) return false;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (GBackGroupENS[i][j].getValue() == GBackGroupENS[i][j + 1].getValue()
                            || GBackGroupENS[i][j].getValue() == GBackGroupENS[i + 1][j].getValue()) {
                        return false;
                    }
                }
            }
            return true;
        }

        private void moveRight() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 0; i < 3; i++)
                        for (int j = 2; j > 0; j--)
                            if ((GBackGroupENS[i][j].getValue() == 0) && (GBackGroupENS[i][j - 1].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroupENS[i][j].getValue();
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i][j - 1].getValue());
                                GBackGroupENS[i][j - 1].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 0; i < 3; i++)
                        for (int j = 2; j > 0; j--)
                            if ((GBackGroupENS[i][j].getValue() == GBackGroupENS[i][j - 1].getValue()) && GBackGroupENS[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i][j].getValue() * 2);
                                GBackGroupENS[i][j - 1].setValue(0);
                                GView3EN.setScore(GView3EN.getScore() + GBackGroupENS[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        private void moveLeft() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 2; j++)
                            if ((GBackGroupENS[i][j].getValue() == 0) && (GBackGroupENS[i][j + 1].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroupENS[i][j].getValue();
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i][j + 1].getValue());
                                GBackGroupENS[i][j + 1].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 2; j++)
                            if ((GBackGroupENS[i][j].getValue() == GBackGroupENS[i][j + 1].getValue()) && GBackGroupENS[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i][j].getValue() * 2);
                                GBackGroupENS[i][j + 1].setValue(0);
                                GView3EN.setScore(GView3EN.getScore() + GBackGroupENS[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        private void moveUp() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 0; i < 2; i++)
                        for (int j = 0; j < 3; j++)
                            if ((GBackGroupENS[i][j].getValue() == 0) && (GBackGroupENS[i + 1][j].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroupENS[i][j].getValue();
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i + 1][j].getValue());
                                GBackGroupENS[i + 1][j].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 0; i < 2; i++)
                        for (int j = 0; j < 3; j++)
                            if ((GBackGroupENS[i][j].getValue() == GBackGroupENS[i + 1][j].getValue()) && GBackGroupENS[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i][j].getValue() * 2);
                                GBackGroupENS[i + 1][j].setValue(0);
                                GView3EN.setScore(GView3EN.getScore() + GBackGroupENS[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        private void moveDown() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 2; i > 0; i--)
                        for (int j = 0; j < 3; j++)
                            if ((GBackGroupENS[i][j].getValue() == 0) && (GBackGroupENS[i - 1][j].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroupENS[i][j].getValue();
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i - 1][j].getValue());
                                GBackGroupENS[i - 1][j].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 2; i > 0; i--)
                        for (int j = 0; j < 3; j++)
                            if ((GBackGroupENS[i][j].getValue() == GBackGroupENS[i - 1][j].getValue()) && GBackGroupENS[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroupENS[i][j].setValue(GBackGroupENS[i][j].getValue() * 2);
                                GBackGroupENS[i - 1][j].setValue(0);
                                GView3EN.setScore(GView3EN.getScore() + GBackGroupENS[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 3; i++) for (int j = 0; j < 3; j++) drawCheck(g, i, j);
            if (judgeGameOver()) {
                String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
                setStatuspd("333");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(SqlMessage.Sqlmessage_URL,
                            SqlMessage.Sqlmessage_USER, SqlMessage.Sqlmessage_PASSWORD);
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("update t_user set Ttime3 = '" + datime + "',Tjindu3='333' where Tname = '" + getUser() + "';");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Database synthesis error. If you see this pop-up window, please contact me immediately.",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, "At the end of the game, the game data will be saved automatically", "提示", JOptionPane.ERROR_MESSAGE);
                g.setColor(Color.darkGray);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(IData.topf);
                FontMetrics fms = getFontMetrics(IData.topf);
                final String value = "Game Over!";
                g.drawString(value, (getWidth() - fms.stringWidth(value)) / 2, getHeight() / 2);
            }
        }

        private void drawCheck(Graphics g, int i, int j) {
            Graphics2D gg = (Graphics2D) g;
            gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_NORMALIZE);
            GBackGroupEN GBackGroupEN = GBackGroupENS[i][j];
            gg.setColor(GBackGroupEN.getBackground());
            gg.fillRoundRect(30 + 136 * j, 30 + 136 * i, 106, 106, 35, 35);
            gg.setColor(GBackGroupEN.getForeground());
            gg.setFont(GBackGroupEN.getCheckFont());
            FontMetrics fms = getFontMetrics(GBackGroupEN.getCheckFont());
            String value = String.valueOf(GBackGroupEN.getValue());
            gg.drawString(value, 30 + 136 * j + (106 - fms.stringWidth(value)) / 2, 30 + 136 * i +
                    (106 - fms.getAscent() - fms.getDescent()) / 2 + fms.getAscent());
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}