package fuxi.xiancen;

/**
 * @author Xcl
 * @date 2021/12/20 19:01
 * @package fuxi.xiancen
 */
public class runable {
    public static void main(String[] args) {
        runable1 sale = new runable1();
        Thread thread = new Thread(sale);
        //参数是实现了runnable接口的类对象
        thread.start();
    }
}

class runable1 implements Runnable {
    int ticket = 100;

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (ticket > 0) {
                System.out.println("现在正在售出第" + ticket + "号座位票；");
                ticket--;
            }
        }
    }
}

