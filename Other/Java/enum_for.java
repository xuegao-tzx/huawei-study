package fuxi;

import java.util.Arrays;
import java.util.Scanner;

enum Week {
    星期一, 星期二, 星期三, 星期四, 星期五, 星期六, 星期日
}

interface DD {
    double n3 = 6.666;

    double c(double x, double y);

    double d();

    double e(double x);
}

/**
 * @author Xcl
 * @date 2021/12/20 11:48
 * @package fuxi
 */
public class enum_for {
    static Enumm esim;

    public static void main(String[] args) {
        System.out.println("一周一共:" + Week.values().length + "天");
        System.out.println("共有:" + Arrays.toString(Week.values()));
        System.out.print("1.请输入星期名称:");
        Scanner sc = new Scanner(System.in);
        String num = sc.next();
        xqjs(num);
        System.out.println("----------");
        xqjs();
        System.out.println("----------");
        Instance(new AA());
        System.out.println("----------");
        enum_for enumf = new enum_for();
        esim = new Enumm(7, 7, 7);
        System.out.print("5.1调用内部类体积1=");
        esim.tim1();//343
        System.out.print("5.2调用内部类体积2=");
        enumf.tim();//343

        DD d = new DD() {
            @Override
            public double c(double x, double y) {
                return 0;
            }

            @Override
            public double d() {
                return 0;
            }

            @Override
            public double e(double x) {
                return x * x * x;
            }
        };
        System.out.print("5.3调用匿名类体积3=");
        System.out.println(d.e(6.6));//287.496
        System.out.println("----------");
    }

    static void xqjs() {
        System.out.print("2.请输入星期名称:");
        Scanner sc = new Scanner(System.in);
        String num1 = sc.next();
        for (Week week : Week.values()) {
            switch (week) {
                default:
                    if (String.valueOf(week).equals(num1)) {
                        System.out.println("2.Success:" + num1 + ",查找星期成功!");
                    }
                    break;
                case 星期六:
                    System.out.println("2.今天周六，该休息了!");
                    break;
            }
        }
    }

    static void xqjs(String num) {
        for (Week week : Week.values()) {
            switch (week) {
                default:
                    if (String.valueOf(week).equals(num)) {
                        System.out.println("1.Success:" + num + ",查找星期成功!");
                    }
                    break;
                case 星期五:
                    System.out.println("1.今天周五，马上就可以休息了!");
                    break;
            }
        }
    }

    static void Instance(CC c1) {
        AA a = new AA();
        BB b = new BB();
        AA a1 = new BB();/*AA a1;a1 = b;*/
        if (a instanceof BB) {
            System.out.println("3.1AA.a继承自B");
        } else if (b instanceof AA) {
            System.out.println("3.1BB.b继承自A");//3.b继承自A
        }
        if (a1 instanceof BB) {
            System.out.println("3.2AA.a1继承自B(上转型)");//3.AA.a1继承自B
        }
        System.out.println("4.1重写x+y为x*y=" + (b.c(6.6, 6.6)));//43.559999999999995
        System.out.println("4.2abstract继承int的x+y=" + (c1.c(6, 6)));//12
        System.out.println("4.2继承double的x+y=" + (a.c(6.6, 6.6)));//13.2
        System.out.println("4.3super用法=" + b.d());//98.666
    }

    void tim() {
        esim.tim1();
    }

    static class Enumm {
        int x1, y1, z1;

        Enumm(int x, int y, int z) {
            x1 = x;
            y1 = y;
            z1 = z;
        }

        void tim1() {
            System.out.println("体积为:" + (x1 * y1 * z1));
        }
    }
}

class AA extends CC {
    int n1 = 68;

    @Override
    double c(double x, double y) {
        return x + y;
    }

    @Override
    int c(int x, int y) {
        return x + y;
    }
    /*final double d(){
        return 0;
    }*///final修饰不可重写
}

class BB extends AA implements DD {
    double n1 = 86;
    double n2;

    @Override
    public double c(double x, double y) {
        return x * y;
    }

    @Override
    public double d() {
        super.n1 = (int) n1;//n1=86
        n2 = super.c(3, 3);//n2=6
        return n1 + n2 + n3;
    }

    @Override
    public double e(double x) {
        return 0;
    }
}

abstract class CC {
    abstract double c(double x, double y);

    abstract int c(int x, int y);
}
/**
 * UML类图
 * 类名(具体类[常规字形]、抽象类[斜体字形])
 * [public(+)/protected(#)/private(-)] 变量名字 : 类型
 * [public(+)/protected(#)/private(-)/static(下划线)] 方法名字 ( 参数列表 ) : 类型
 * <p>
 * 多用组合，少用继承
 * 高内聚-低耦合
 * <p>
 * <>List</>
 * List<Student> list = new ArrayList<Student>();
 * Student s1 = new Student(1009, "li", 20);
 * list.add(s1);
 * list.add(new Student(1002, "li", 20));
 * list.set(3, s2);//自定义位置插入
 * //采用for循环实现对List中元素的遍历1
 * for (int i = 0; i < list.size(); i++) {
 * System.out.println(list.get(i));
 * }
 * list.remove(2);//删除指定位置元素
 * //采用for循环实现对List中元素的遍历2[:]
 * for (Student stu : list) {
 * System.out.println(stu);
 * }
 * //采用while循环实现对List中元素的遍历3[迭代器]
 * Iterator iter = list.iterator();
 * while (iter.hasNext()) {  //判断集合中是否还有元素
 * System.out.println(iter.next());//next方法取出下一个元素
 * }
 * <p>
 * <>Map</>
 * Map<Integer, Student> map = new HashMap<Integer, Student>();
 * Student s1 = new Student(1003, "li", 20);
 * map.put(new Integer(1003), s1);
 * map.put(new Integer(1002), new Student(1002, "li", 20));
 * map.get(1002)//按照key值取出学生信息
 * Iterator iterkey = map.keySet().iterator();
 * while (iterkey.hasNext()) {
 * System.out.println(iterkey.next());
 * }//遍历所有的key值
 * Iterator itervalue = map.values().iterator();
 * while (itervalue.hasNext()) {
 * System.out.println(itervalue.next());
 * }//遍历所有的value值
 * Iterator iterentry = map.entrySet().iterator();
 * while (iterentry.hasNext()) {
 * Map.Entry entry = (Map.Entry) iterentry.next();
 * System.out.println("key=" + entry.getKey()+ ":" + "value=" + entry.getValue());
 * }//同时遍历所有的key和value值
 * map.remove(1004)//按照Key值删除学生信息
 * <p>
 * <>线程</>
 * runable1 sale = new runable1();
 * Thread thread = new Thread(sale);//参数是实现了runnable接口的类对象
 * thread.start();
 * 1.class thread1 extends Thread {
 * public void run() {}
 * }
 * 2.class runable1 implements Runnable {
 * public void run() {}
 * }
 * 线程锁
 * suo1 mt = new suo1();
 * //下面3个线程的第一个参数相同，保证了同一块代码块
 * Thread t1 = new Thread(mt, "salewindowA");
 * Thread t2 = new Thread(mt, "salewindowB");
 * t1.start();
 * t2.start();
 * class suo1 implements Runnable {
 * synchronized (this) {//对代码块进行加锁}
 * }
 */
