package fuxi.bigexam;

/**
 * @author Xcl
 * @date 2021/12/1 09:50
 * @package g2048.v2
 */

import java.awt.*;

public class GBackGroup {
    int value;

    /**
     * 调用清空函数
     */
    GBackGroup() {
        clear();
    }

    /**
     * 清空所有位置数组对应值为0
     */
    public void clear() {
        setValue(0);
    }

    /**
     * 设置游戏界面背景
     */
    Color getForeground() {
        switch (getValue()) {
            case 0:
                return new Color(0xcdc1b4);
            case 2:
                return Color.BLACK;
            case 4:
                return Color.BLACK;
            default:
                return Color.WHITE;
        }
    }

    /**
     * 设置游戏中数字2、0、4、8、16、32、64、128、256、512、1024、2048、4096、8192、16384颜色，
     * 已经为日后5*5模式预留
     */
    Color getBackground() {
        switch (getValue()) {
            case 0:
                return new Color(0xcdc1b4);
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            case 4096:
                return new Color(0x65da92);
            case 8192:
                return new Color(0x5abc65);
            case 16384:
                return new Color(0x248c51);
            default:
                return new Color(0x09572A);
        }
    }

    /**
     * 设置游戏界面中数字字体
     */
    Font getCheckFont() {
        if (getValue() < 10) return IData.font1;
        if (getValue() < 100) return IData.font2;
        if (getValue() < 1000) return IData.font3;
        if (getValue() < 10000) return IData.font4;
        return IData.font5;
    }

    /**
     * 获取value值的set、get方法
     */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
