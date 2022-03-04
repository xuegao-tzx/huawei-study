package fuxi.bigexam;
/**
 * @author Xcl
 * @date 2021/12/1 09:48
 * @package g2048.v2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 本类继承自JFrame实现swing图形界面，实现Runnable, ILogin接口的方法
 */
public class Login extends JFrame implements Runnable, ILogin {
    static final long serialVersionUID = 3878971623272611814L;
    static String stt1 = null;
    JFrame jfm = this;
    JLabel l_name;
    JLabel l_password;
    JTextField t_name;
    JPasswordField t_password;
    JButton b_login;
    JButton b_reset;
    JButton b_register;
    Register rf_1 = null;

    /**
     * Login类的显示
     */
    Login() {
        setTitle(getStt1() + "登录窗体");
        setVisible(getStt1() != null);
        init(getStt1());
        setSize(380, 300);
        setLocation(200, 100);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static String getStt1() {
        return stt1;
    }

    public static void setStt1(String stt1) {
        Login.stt1 = stt1;
    }

    /**
     * 实现接口中的init展示登录信息的界面
     */
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
                    try {
                        File file = new File("src/g2048/db/user.txt.txt");
                        if (file.isFile() && file.exists()) {
                            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                            BufferedReader br = new BufferedReader(isr);
                            String data;
                            while ((data = br.readLine()) != null) {
                                String[] split = data.split(":");
                                String[] split2 = split[0].split("\\.");
                                if (String.valueOf(getT_name().getText()).equals(split2[0])) {
                                    if (String.valueOf(getT_password().getPassword()).equals(split2[2])) {
                                        getJfm().dispose();
                                        new GView4(split2[0]).showView();
                                    }
                                }
                            }
                            br.close();
                        } else System.out.println("文件不存在！");
                    } catch (Exception e_1) {
                        e_1.printStackTrace();
                    }
                    /**
                     * 写死的用户管理员
                     */
                } else if (st.equals("管理员")) {
                    if (String.valueOf(getT_name().getText()).equals("admin123")) {
                        if (String.valueOf(getT_password().getPassword()).equals("111111")) {
                            getJfm().dispose();
                            new UserInfo(String.valueOf(getT_name().getText()));
                        }
                    }
                } else {
                    System.out.println("不可能执行到这里...");
                }
            }
        });
        /**
         * 重置按钮事件监听
         */
        setB_reset(new JButton("重置"));
        getB_reset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getT_name().setText("");
                getT_password().setText("");
            }
        });
        /**
         * 注册按钮事件监听
         */
        setB_register(new JButton("点我注册"));
        getB_register().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (st.equals("管理员"))
                    JOptionPane.showMessageDialog(null, "注意：管理员模式登录禁止注册！",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    if (getRf_1() != null) getRf_1().dispose();
                    Register rf = new Register();
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

    /**
     * 所有变量的set、get方法封装
     */
    @Override
    public void run() {
        new Login();
    }

    public JFrame getJfm() {
        return jfm;
    }

    public void setJfm(JFrame jfm) {
        this.jfm = jfm;
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

    public Register getRf_1() {
        return rf_1;
    }

    public void setRf_1(Register rf_1) {
        this.rf_1 = rf_1;
    }
}
