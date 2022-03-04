package g2048.v3_5.ENGame;

import g2048.v3_5.lib.GMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

/**
 * @author Xcl
 * @date 2021/12/25 21:03
 * @package g2048.v3_5.CNGame
 */
public class GAboutEN extends JFrame {
    JLabel zfb;
    JLabel zfb1;
    JButton wechar;
    JButton wechar1;
    JFrame jfm = this;

    public GAboutEN(String st, int iii) {
        setLayout(new GridLayout(2, 3, 5, 5));
        ImageIcon image = new ImageIcon("src/g2048/v3_5/lib/zfb.jpg");
        ImageIcon image1 = new ImageIcon("src/g2048/v3_5/lib/wechat.png");
        getJfm().setIconImage(new ImageIcon(GMessage.GPictureIcon).getImage());
        getJfm().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getJfm().setResizable(false);
        image.setImage(image.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        image1.setImage(image1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel label = new JLabel(image);
        JLabel label1 = new JLabel(image1);
        setZfb((new JLabel("Alipay", JLabel.CENTER)));
        setZfb1((new JLabel("WeChat", JLabel.CENTER)));
        setWechar(new JButton("About"));
        getWechar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "This is a simple 2048 game .\n version number: V6.0 \n By 'W, a, s," +
                        "D 'or' ↑, ↓, ←, → 'can be easily controlled. \n If you find any bug, please contact me. \n " +
                        "My contact information: xcl@xuegao-tzx.top \n My home page: xuegao-" +
                        "tzx.top \n My GitHub: github.com/xuegao-tzx", "About", -1);
                geturl("https://www.xuegao-tzx.top/g2048h.html");
            }
        });
        setWechar1(new JButton("Back"));
        getWechar1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getJfm().dispose();
                if (iii == 3) {
                    new GView3EN(st).showView();
                } else if (iii == 4) {
                    new GView4EN(st).showView();
                } else if (iii == 5) {
                    new GView5EN(st).showView();
                }
            }
        });
        add(getContentPane().add(label), new GridLayout(1, 1));
        add(getWechar(), new GridLayout(1, 2));
        add(getContentPane().add(label1), new GridLayout(1, 3));
        add(getZfb(), new GridLayout(2, 1));
        add(getWechar1(), new GridLayout(2, 2));
        add(getZfb1(), new GridLayout(2, 3));
        setBounds(100, 100, 450, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(null);
    }

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

    public JLabel getZfb() {
        return zfb;
    }

    public void setZfb(JLabel zfb) {
        this.zfb = zfb;
    }

    public JLabel getZfb1() {
        return zfb1;
    }

    public void setZfb1(JLabel zfb1) {
        this.zfb1 = zfb1;
    }

    public JButton getWechar() {
        return wechar;
    }

    public void setWechar(JButton wechar) {
        this.wechar = wechar;
    }

    public JButton getWechar1() {
        return wechar1;
    }

    public void setWechar1(JButton wechar1) {
        this.wechar1 = wechar1;
    }

    public JFrame getJfm() {
        return jfm;
    }

    public void setJfm(JFrame jfm) {
        this.jfm = jfm;
    }
}
