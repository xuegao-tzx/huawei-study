package g2048.v3_5.itf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Xcl
 * @date 2021/12/4 11:37
 * @package g2048.v3_mod
 */
public interface IGView extends IData, ActionListener {

    void showView();

    @Override
    void init(String st);

    String getUser();

    void setUser(String user);

    JFrame getJFm();

    void setJFm(JFrame JFm);

    JLabel getJSnm();

    void setJSnm(JLabel JSnm);

    JLabel getJSn();

    void setJSn(JLabel JSn);

    JLabel getJsm();

    void setJsm(JLabel jsm);

    JLabel getJs();

    void setJs(JLabel js);

    String getGcbl();

    void setGcbl(String gcbl);

    JLabel getJTip();

    void setJTip(JLabel JTip);

    @Override
    void actionPerformed(ActionEvent e);

    String getHuifu();

    void setHuifu(String huifu);

    JMenu getJm();

    void setJm(JMenu jm);

    JMenu getJm1();

    void setJm1(JMenu jm1);

    JMenuBar getJmb();

    void setJmb(JMenuBar jmb);

    JMenuItem getV3();

    void setV3(JMenuItem v3);

    JMenuItem getV4();

    void setV4(JMenuItem v4);

    JMenuItem getV5();

    void setV5(JMenuItem v5);

    JMenuItem getAbout();

    void setAbout(JMenuItem About);

    JMenuItem getExit();

    void setExit(JMenuItem exit);

    JMenuItem getChart();

    void setChart(JMenuItem chart);

    void Exit();

    void Chart();

    void About();

    void fcmpd();

    void L1();

    void L2();

    void L3();

    void L4();
}
