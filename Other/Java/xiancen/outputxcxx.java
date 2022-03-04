package fuxi.xiancen;

/**
 * @author Xcl
 * @date 2021/12/20 19:03
 * @package fuxi.xiancen
 */
public class outputxcxx {
    public static void main(String[] args) {
        Thread mt1 = new Thread(new outputxcxx1(), "MAX");
        Thread mt2 = new Thread(new outputxcxx1(), "Normal");
        Thread mt3 = new Thread(new outputxcxx1(), "MIN");
        Thread mt4 = new Thread(new outputxcxx1(), "自定义优先级");
        mt1.setPriority(Thread.MAX_PRIORITY);
        mt2.setPriority(Thread.NORM_PRIORITY);
        mt3.setPriority(Thread.MIN_PRIORITY);
        mt4.setPriority(7);
        mt1.start();
        mt2.start();
        mt3.start();
        mt4.start();
        System.out.println("主线程的name=" + Thread.currentThread().getName() + ",优先级=" + Thread.currentThread().getPriority());

    }
}

class outputxcxx1 implements Runnable {
    @Override
    public void run() {
        System.out.println("***********************");
        System.out.println("当前线程的名字是：" + Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + "线程的priority=" + Thread.currentThread().getPriority()
                + ",它的ID=" + Thread.currentThread().getId() + ",它的state=" + Thread.currentThread().getState());
        System.out.println("************************");
    }
}

