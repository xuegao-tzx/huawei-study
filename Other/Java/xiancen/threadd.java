package fuxi.xiancen;

/**
 * @author Xcl
 * @date 2021/12/20 18:58
 * @package fuxi.xiancen
 */
public class threadd {
    public static void main(String[] args) {
        thread1 trd = new
                thread1();
        trd.start();
    }
}

class thread1 extends Thread {
    int ticket = 100;

    public void run() {
        for (int i = 0; i < 100; i++) {
            if (ticket > 0) {
                System.out.println("现在正在售出第" + ticket + "号座位票；");
                ticket--;
            }
        }
    }
}
