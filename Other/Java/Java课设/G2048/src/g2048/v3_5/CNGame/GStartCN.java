package g2048.v3_5.CNGame;

import g2048.v3_5.itf.IData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static g2048.v3_5.lib.GMessage.GBackupPicture;
import static g2048.v3_5.lib.GMessage.GPictureIcon;

/**
 * @author Xcl
 * @date 2021/12/24 13:06
 * @package g2048.v3_5
 */
public class GStartCN extends JFrame implements IData {
    static final long serialVersionUID = -6718310314384392563L;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image img = getTk().getImage(GPictureIcon);

    public GStartCN(String st) {
        init(st);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public void init(String st) {
        JFrame jf = new JFrame(st);
        jf.setIconImage(getImg());
        jf.setSize(450, 450);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        ImageIcon image = new ImageIcon(GBackupPicture);
        image.setImage(image.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT));
        JLabel jLable = new JLabel(image);
        jLable.setBounds(0, 0, 450, 450);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JLabel lable = new JLabel(st);
        lable.setFont(font2);
        jp1.add(lable);
        jp1.setBounds(75, 85, 300, 300);
        jf.add(jp1);
        JButton jb1 = new JButton("开始游戏");
        JButton jb2 = new JButton("管理员");
        jb1.setFont(font0);
        jb2.setFont(font0);
        Dimension dms = new Dimension(100, 50);
        jb1.setPreferredSize(dms);
        jb2.setPreferredSize(dms);
        jp2.add(jb1);
        jp3.add(jb2);
        jp2.setBounds(75, 310, 150, 300);
        jp3.setBounds(230, 310, 150, 300);
        jf.add(jp2);
        jf.add(jp3);
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        jf.add(jLable);
        LoginCN.setStt1(null);
        LoginCN lgf = new LoginCN();
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nnn = JOptionPane.showConfirmDialog(null, "根据《中华人民共和国防沉迷游戏法》规定，" +
                                "您需要登录/注册后方可开始游戏，是否同意？", "防沉迷游戏申明"
                        , JOptionPane.YES_NO_OPTION);
                if (nnn == JOptionPane.YES_OPTION) {
                    LoginCN.setStt1("用户");
                    Thread t1 = new Thread(lgf);
                    t1.start();
                } else if (nnn == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginCN.setStt1("管理员");
                Thread t2 = new Thread(lgf);
                t2.start();
            }
        });
    }

    public Toolkit getTk() {
        return tk;
    }

    public void setTk(Toolkit tk) {
        this.tk = tk;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
