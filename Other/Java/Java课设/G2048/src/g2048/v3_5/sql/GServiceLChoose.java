package g2048.v3_5.sql;

import g2048.v3_5.CNGame.GStartCN;
import g2048.v3_5.ENGame.GStartEN;
import g2048.v3_5.itf.IData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static g2048.v3_5.lib.GMessage.GBackupPicture;
import static g2048.v3_5.lib.GMessage.GPictureIcon;

/**
 * @author Xcl
 * @date 2022/1/4 10:02
 * @package g2048.v3_5.sql
 */
public class GServiceLChoose extends JFrame implements IData {
    static final long serialVersionUID = -6718310314384392563L;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Image img = getTk().getImage(GPictureIcon);
    JFrame jf;

    public GServiceLChoose(String st) {
        init(st);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public void init(String st) {
        jf = new JFrame(st);
        jf.setIconImage(getImg());
        jf.setSize(500, 500);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        ImageIcon image = new ImageIcon(GBackupPicture);
        image.setImage(image.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel jLable = new JLabel(image);
        jLable.setBounds(0, 0, 500, 500);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JLabel lable = new JLabel("2048游戏中心");
        JLabel lable1 = new JLabel("2048GameCenter");
        lable.setFont(font2);
        lable1.setFont(font3);
        jp1.add(lable);
        jp1.add(lable1);
        jp1.setBounds(75, 85, 300, 300);
        jf.add(jp1);
        JButton jb1 = new JButton("国服(CN service)");
        JButton jb2 = new JButton("外服(US service)");
        jb1.setFont(font0);
        jb2.setFont(font0);
        Dimension dms = new Dimension(190, 50);
        jb1.setPreferredSize(dms);
        jb2.setPreferredSize(dms);
        jp2.add(jb1);
        jp3.add(jb2);
        jp2.setBounds(15, 310, 210, 300);
        jp3.setBounds(255, 310, 210, 300);
        jf.add(jp2);
        jf.add(jp3);
        jp1.setOpaque(false);
        jp2.setOpaque(false);
        jp3.setOpaque(false);
        jf.add(jLable);
        jf.setResizable(false);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nnn = JOptionPane.showConfirmDialog(null, "根据您的选择，请您务必遵守中国(包括香港、澳门、台湾地区)" +
                                "及您所在地区的法律法规才可以继续游戏，本游戏为3+分类，您是否知悉？", "游戏申明"
                        , JOptionPane.YES_NO_OPTION);
                if (nnn == JOptionPane.YES_OPTION) {
                    SqlMessage.Sqlmessage_URL = "jdbc:mysql://152.136.182.127:6133/game2048";
                    SqlMessage.Sqlmessage_USER = "game2048";
                    SqlMessage.Sqlmessage_PASSWORD = "12233434";
                    new GStartCN("2048游戏主界面");
                    jf.dispose();
                } else if (nnn == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nnn = JOptionPane.showConfirmDialog(null, "According to your choice, you must" +
                                " abide by the laws and regulations of the United States, the European Union and your region" +
                                " before you can continue the game. This game is classified as E. this game only retains " +
                                "the basic user data for storing your game information. Do you know?", "Game statement"
                        , JOptionPane.YES_NO_OPTION);
                if (nnn == JOptionPane.YES_OPTION) {
                    SqlMessage.Sqlmessage_URL = "jdbc:mysql://107.148.206.54:6133/g2048";
                    SqlMessage.Sqlmessage_USER = "g2048";
                    SqlMessage.Sqlmessage_PASSWORD = "12233434";
                    new GStartEN("2048Game Main Interface");
                    jf.dispose();
                } else if (nnn == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
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
