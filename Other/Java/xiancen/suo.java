package fuxi.xiancen;

/**
 * @author Xcl
 * @date 2021/12/20 19:08
 * @package fuxi.xiancen
 */
public class suo {
    public static void main(String[] args) {
        suo1 mt = new suo1();
        //下面3个线程的第一个参数相同，保证了同一块代码块
        Thread t1 = new Thread(mt, "salewindowA");
        Thread t2 = new Thread(mt, "salewindowB");
        Thread t3 = new Thread(mt, "salewindowC");
        t1.start();
        t2.start();
        t3.start();
    }
}

class suo1 implements Runnable {
    private int ticket = 100;

    //synchronized public void run(){   //对方法进行加锁
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (this) {    //对代码块进行加锁
                if (ticket > 0) {
                    System.out.println("现在正在售出第" + ticket + "号座位票；");
                    ticket--;
                }
            }
        }
    }
}

