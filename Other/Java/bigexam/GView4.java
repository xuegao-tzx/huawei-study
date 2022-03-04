package fuxi.bigexam;

/**
 * @author Xcl
 * @date 2021/12/1 09:48
 * @package g2048.v2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 本类继承自IGView实现接口的所有方法
 */
public class GView4 implements IGView {
    static int score = 0;
    static int scorem = 0;
    String user = null;
    JFrame JFm;
    JLabel JSnm;
    JLabel JSn;
    JLabel Jsm;
    JLabel Js;
    GameMb gamemb;
    String gcbl = null;
    JLabel JTip;
    JMenu jm;
    JMenu jm1;
    JMenuBar jmb;
    JMenuItem v3;
    JMenuItem v4;
    JMenuItem v5;
    JMenuItem About;
    JMenuItem chart;
    JMenuItem exit;

    /**
     * 本类主显示
     *
     * @param st
     */
    GView4(String st) {
        init(st);
        setUser(st);
    }

    /**
     * 大量get、set方法进行封装数据
     *
     * @return
     */
    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        GView4.score = score;
    }

    public static int getScorem() {
        return scorem;
    }

    public static void setScorem(int scorem) {
        GView4.scorem = scorem;
    }

    @Override
    public void showView() {
        getJFm().setVisible(true);
    }

    /**
     * 游戏进入玩耍的核心逻辑代码
     *
     * @param st
     */
    @Override
    public void init(String st) {
        JOptionPane.showMessageDialog(null, "您的所有游戏数据将被保存在本地，" +
                "继续游戏默认您同意我的隐私协议。", "隐私申明", JOptionPane.INFORMATION_MESSAGE);
        setJFm(new JFrame(st + "的2048小游戏"));
        getJFm().setSize(450, 600);
        getJFm().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getJFm().setLocationRelativeTo(null);
        getJFm().setResizable(false);
        getJFm().setLayout(null);
        setJmb(new JMenuBar());
        /**
         * 预留出V3.0版本的所有功能区域
         */
        setJm(new JMenu("功能"));
        setV3(new JMenuItem("3*3"));
        getV3().addActionListener(this);
        setV4(new JMenuItem("4*4"));
        getV4().addActionListener(this);
        setV5(new JMenuItem("5*5"));
        getV5().addActionListener(this);
        getJm().add(getV3());
        getJm().add(getV4());
        getJm().add(getV5());
        setJm1(new JMenu("其它"));
        setAbout(new JMenuItem("关于"));
        getAbout().addActionListener(this);
        setChart(new JMenuItem("排行榜"));
        getChart().addActionListener(this);
        setExit(new JMenuItem("退出"));
        getExit().addActionListener(this);
        getJm1().add(getAbout());
        getJm1().add(getExit());
        getJmb().add(getJm());
        getJmb().add(getJm1());
        getJFm().setJMenuBar(getJmb());
        setJSnm(new JLabel("目前最高分", JLabel.CENTER));
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
        setJSn(new JLabel(st + "的当前得分", JLabel.CENTER));
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
        setJTip(new JLabel("操作：↑↓←→/WSAD, 按Esc/R重新开始", JLabel.CENTER));
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

    /**
     * 大量get、set方法进行封装数据
     *
     * @return
     */
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
    public void setAbout(JMenuItem about) {
        About = about;
    }

    @Override
    public JMenuItem getChart() {
        return chart;
    }

    @Override
    public void setChart(JMenuItem chart) {
        this.chart = chart;
    }

    @Override
    public JMenuItem getExit() {
        return exit;
    }

    @Override
    public void setExit(JMenuItem exit) {
        this.exit = exit;
    }

    /**
     * 监听选框数据
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getV3()) {
            JOptionPane.showMessageDialog(null, "下一版本会加入此功能",
                    "ERROR!", JOptionPane.ERROR_MESSAGE);
        } else if (e.getSource() == getV5()) {
            JOptionPane.showMessageDialog(null, "下一版本会加入此功能",
                    "ERROR!", JOptionPane.ERROR_MESSAGE);
        } else if (e.getSource() == getAbout()) {
            JOptionPane.showMessageDialog(null, "这是一个简单的2048小游戏\n版本号: V2.0\n通过'W，A，S，" +
                    "D'或'↑，↓，←，→'可以轻松控制\n如发现任何Bug,欢迎联系我\n我的联系方式:xcl@xuegao-tzx.top\n我的主页: xuegao-" +
                    "tzx.top\n我的Github: github.com/xuegao-tzx", "关于", -1);
            geturl("https://www.xuegao-tzx.top/g2048h.html");
        } else if (e.getSource() == getChart()) {
            JOptionPane.showMessageDialog(null, "下一版本会加入此功能",
                    "ERROR!", JOptionPane.ERROR_MESSAGE);
        } else if (e.getSource() == getExit()) {
            getJFm().dispose();
            System.exit(0);
        }
    }

    /**
     * 通过给定url，在浏览器中展示帮助界面
     *
     * @param url
     */
    @Override
    public void geturl(String url) {
        try {
            URI uri = URI.create(url);
            if (Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            }
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * 内部类实现2048运行核心功能
     */
    class GameMb extends JPanel implements KeyListener {
        private static final long serialVersionUID = -7237908387425382979L;
        private final GBackGroup[][] GBackGroups = new GBackGroup[4][4];
        private boolean added = true;

        GameMb() {
            initGame();
            addKeyListener(this);
        }

        /**
         * 监听键盘输入数据
         *
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    initGame();
                    break;
                case KeyEvent.VK_R:
                    initGame();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeft();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_A:
                    moveLeft();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRight();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_D:
                    moveRight();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_UP:
                    moveUp();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_W:
                    moveUp();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_DOWN:
                    moveDown();
                    createCheck();
                    judgeGameOver();
                    break;
                case KeyEvent.VK_S:
                    moveDown();
                    createCheck();
                    judgeGameOver();
                    break;
                default:
                    break;
            }
            repaint();
        }

        /**
         * 游戏初始化，并读取本地文件最高分信息
         */
        private void initGame() {
            GView4.setScore(0);
            getJsm().setText(GView4.getScorem() + "");
            try {
                File file = new File("src/g2048/db/2048maxcj.txt");
                if (file.isFile() && file.exists()) {
                    InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                    BufferedReader br = new BufferedReader(isr);
                    String data;
                    data = br.readLine();
                    GView4.setScorem(Integer.parseInt(data));
                    br.close();
                } else System.out.println("文件不存在！");
            } catch (Exception e_1) {
                e_1.printStackTrace();
            }
            for (int indexRow = 0; indexRow < 4; indexRow++)
                for (int indexCol = 0; indexCol < 4; indexCol++) GBackGroups[indexRow][indexCol] = new GBackGroup();
            added = true;
            createCheck();
            added = true;
            createCheck();
        }

        /**
         * 随机产生新的数字
         */
        private void createCheck() {
            List<GBackGroup> list = getEmptyChecks();
            if (!list.isEmpty() && added) {
                Random random = new Random();
                int index = random.nextInt(list.size());
                GBackGroup GBackGroup = list.get(index);
                // 2, 4，8出现概率6:4:1
                int sjj = random.nextInt(13);
                if (0 <= sjj && sjj <= 8) GBackGroup.setValue(2);
                else if (sjj == 9 || sjj == 10 || sjj == 11 || sjj == 12) GBackGroup.setValue(4);
                else if (sjj == 13) GBackGroup.setValue(8);
                else System.out.println("不可能执行到这里...");
                added = false;
            }
        }

        /**
         * 判断是否有空位
         *
         * @return
         */
        private List<GBackGroup> getEmptyChecks() {
            List<GBackGroup> GBackGroupList = new ArrayList<>();
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    if (GBackGroups[i][j].getValue() == 0) GBackGroupList.add(GBackGroups[i][j]);
            return GBackGroupList;
        }

        /**
         * 判断是否游戏结束
         *
         * @return
         */
        private boolean judgeGameOver() {
            if (GView4.getScorem() < GView4.getScore()) GView4.setScorem(GView4.getScore());
            String datime = new SimpleDateFormat("yyyy-MM-dd|HH-mm-ss").format(new Date());
            setGcbl(getUser() + "." + GBackGroups[0][0].getValue() + "." + GBackGroups[0][1].getValue() + "." + GBackGroups[0][2].getValue() + "." +
                    GBackGroups[0][3].getValue() + "." + GBackGroups[1][0].getValue() + "." + GBackGroups[1][1].getValue() + "." + GBackGroups[1][2].getValue() +
                    "." + GBackGroups[1][3].getValue() + "." + GBackGroups[2][0].getValue() + "." + GBackGroups[2][1].getValue() + "." + GBackGroups[2][2].getValue() +
                    "." + GBackGroups[2][3].getValue() + "." + GBackGroups[3][0].getValue() + "." + GBackGroups[3][1].getValue() + "." + GBackGroups[3][2].getValue() +
                    "." + GBackGroups[3][3].getValue() + "." + GView4.getScore() + "." + datime + ":");
            try {
                File f = new File("src/g2048/db/2048maxcj.txt");
                File f1 = new File("src/g2048/db/2048gd.txt");
                if (!f.exists()) f.createNewFile();
                if (!f1.exists()) f1.createNewFile();
                FileWriter fw1 = new FileWriter(f1);
                FileWriter fw = new FileWriter(f);
                BufferedWriter bw = new BufferedWriter(fw);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                bw.write(String.valueOf(GView4.getScorem()));
                bw1.write(String.valueOf(getGcbl()));
                bw.flush();
                bw1.flush();
                bw.close();
                bw1.close();
                fw.close();
                fw1.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            getJs().setText(GView4.getScore() + "");
            getJsm().setText(GView4.getScorem() + "");
            if (!getEmptyChecks().isEmpty()) return false;
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    if (GBackGroups[i][j].getValue() == GBackGroups[i][j + 1].getValue()
                            || GBackGroups[i][j].getValue() == GBackGroups[i + 1][j].getValue()) return false;
            return true;
        }

        /**
         * 2048游戏界面向右移动后数据变化
         */
        private void moveRight() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 0; i < 4; i++)
                        for (int j = 3; j > 0; j--)
                            if ((GBackGroups[i][j].getValue() == 0) && (GBackGroups[i][j - 1].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroups[i][j].getValue();
                                GBackGroups[i][j].setValue(GBackGroups[i][j - 1].getValue());
                                GBackGroups[i][j - 1].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 0; i < 4; i++)
                        for (int j = 3; j > 0; j--)
                            if ((GBackGroups[i][j].getValue() == GBackGroups[i][j - 1].getValue()) && GBackGroups[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroups[i][j].setValue(GBackGroups[i][j].getValue() * 2);
                                GBackGroups[i][j - 1].setValue(0);
                                GView4.setScore(GView4.getScore() + GBackGroups[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        /**
         * 2048游戏界面向左移动后数据变化
         */
        private void moveLeft() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 0; i < 4; i++)
                        for (int j = 0; j < 3; j++)
                            if ((GBackGroups[i][j].getValue() == 0) && (GBackGroups[i][j + 1].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroups[i][j].getValue();
                                GBackGroups[i][j].setValue(GBackGroups[i][j + 1].getValue());
                                GBackGroups[i][j + 1].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 0; i < 4; i++)
                        for (int j = 0; j < 3; j++)
                            if ((GBackGroups[i][j].getValue() == GBackGroups[i][j + 1].getValue()) && GBackGroups[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroups[i][j].setValue(GBackGroups[i][j].getValue() * 2);
                                GBackGroups[i][j + 1].setValue(0);
                                GView4.setScore(GView4.getScore() + GBackGroups[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        /**
         * 2048游戏界面向上移动后数据变化
         */
        private void moveUp() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 4; j++)
                            if ((GBackGroups[i][j].getValue() == 0) && (GBackGroups[i + 1][j].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroups[i][j].getValue();
                                GBackGroups[i][j].setValue(GBackGroups[i + 1][j].getValue());
                                GBackGroups[i + 1][j].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 0; i < 3; i++)
                        for (int j = 0; j < 4; j++)
                            if ((GBackGroups[i][j].getValue() == GBackGroups[i + 1][j].getValue()) && GBackGroups[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroups[i][j].setValue(GBackGroups[i][j].getValue() * 2);
                                GBackGroups[i + 1][j].setValue(0);
                                GView4.setScore(GView4.getScore() + GBackGroups[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        /**
         * 2048游戏界面向下移动后数据变化
         */
        private void moveDown() {
            boolean key = true;
            boolean flag = true;
            boolean mark = true;
            while (key) {
                while (mark) {
                    mark = false;
                    for (int i = 3; i > 0; i--)
                        for (int j = 0; j < 4; j++)
                            if ((GBackGroups[i][j].getValue() == 0) && (GBackGroups[i - 1][j].getValue() != 0)) {
                                mark = true;
                                int temp = GBackGroups[i][j].getValue();
                                GBackGroups[i][j].setValue(GBackGroups[i - 1][j].getValue());
                                GBackGroups[i - 1][j].setValue(temp);
                            }
                }
                key = false;
                while (flag) {
                    flag = false;
                    for (int i = 3; i > 0; i--)
                        for (int j = 0; j < 4; j++)
                            if ((GBackGroups[i][j].getValue() == GBackGroups[i - 1][j].getValue()) && GBackGroups[i][j].getValue() != 0) {
                                key = true;
                                mark = true;
                                GBackGroups[i][j].setValue(GBackGroups[i][j].getValue() * 2);
                                GBackGroups[i - 1][j].setValue(0);
                                GView4.setScore(GView4.getScore() + GBackGroups[i][j].getValue());
                            }
                }
            }
            added = true;
        }

        /**
         * 游戏界面绘画
         *
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < 4; i++) for (int j = 0; j < 4; j++) drawCheck(g, i, j);
            if (judgeGameOver()) {
                JOptionPane.showMessageDialog(null, "游戏结束，游戏数据将自动保存", "提示", JOptionPane.ERROR_MESSAGE);
                g.setColor(Color.darkGray);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.WHITE);
                g.setFont(IData.topf);
                FontMetrics fms = getFontMetrics(IData.topf);
                final String value = "Game Over!";
                g.drawString(value,
                        (getWidth() - fms.stringWidth(value)) / 2,
                        getHeight() / 2);
            }
        }

        /**
         * 使用Oracle.Java™ Tutorials-2D Graphics进行绘画2048游戏轮廓
         *
         * @param g
         * @param i
         * @param j
         */
        private void drawCheck(Graphics g, int i, int j) {
            Graphics2D gg = (Graphics2D) g;
            gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            gg.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                    RenderingHints.VALUE_STROKE_NORMALIZE);
            GBackGroup GBackGroup = GBackGroups[i][j];
            gg.setColor(GBackGroup.getBackground());
            gg.fillRoundRect(10 + 105 * j,
                    10 + 105 * i,
                    95, 95, 25, 25);
            gg.setColor(GBackGroup.getForeground());
            gg.setFont(GBackGroup.getCheckFont());
            FontMetrics fms = getFontMetrics(GBackGroup.getCheckFont());
            String value = String.valueOf(GBackGroup.getValue());
            gg.drawString(value,
                    10 + 105 * j +
                            (95 - fms.stringWidth(value)) / 2,
                    10 + 105 * i +
                            (95 - fms.getAscent() - fms.getDescent()) / 2
                            + fms.getAscent());
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
    }
}