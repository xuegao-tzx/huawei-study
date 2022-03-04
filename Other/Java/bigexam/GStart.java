package fuxi.bigexam;
/**
 * @author Xcl
 * @date 2021/12/2 07:58
 * @package g2048.v2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 本类继承自JFrame实现swing的方法
 */
class GStart extends JFrame {
    static final long serialVersionUID = -6718310314384392563L;
    final JPanel jp = new JPanel(new GridLayout(3, 1, 5, 5));

    /**
     * 游戏开始界面通过线程分别执行管理员和普通用户登录
     *
     * @param st
     */
    GStart(String st) {
        super(st);
        setLayout(new BorderLayout());
        JButton[] btn;
        btn = new JButton[3];
        btn[0] = new JButton("开始游戏");
        btn[1] = new JButton("管理员登陆");
        btn[2] = new JButton("退出");
        getJp().add(btn[0]);
        getJp().add(btn[1]);
        getJp().add(btn[2]);
        getContentPane().add(getJp(), BorderLayout.CENTER);
        setSize(450, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        Login.setStt1(null);
        Login lgf = new Login();
        /**
         * 按钮动作事件监听
         */
        btn[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "根据《中华人民共和国防沉迷游戏法规定》，您需要登录/注册" +
                        "后方可开始游戏。", "提示", JOptionPane.WARNING_MESSAGE);
                Login.setStt1("用户");
                Thread t1 = new Thread(lgf);
                t1.start();
            }
        });
        btn[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login.setStt1("管理员");
                Thread t2 = new Thread(lgf);
                t2.start();
            }
        });
        btn[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public JPanel getJp() {
        return jp;
    }
}
