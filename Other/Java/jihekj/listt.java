package fuxi.jihekj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Xcl
 * @date 2021/12/20 15:47
 * @package fuxi.jihekj
 */
public class listt {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        Student s1 = new Student(1009, "li", 20);
        list.add(s1);
        list.add(new Student(1002, "li", 20));
        list.add(new Student(1004, "赵", 18));
        list.add(new Student(1001, "wang", 18));
        Student s2 = new Student(1010, "薛", 22);
        list.set(3, s2);
        //采用for循环实现对List中元素的遍历
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println("---------");
        list.remove(2);
        //删除索引是“2”的对象后，遍历List中元素
        for (Student stu : list) {
            System.out.println(stu);
        }
        Iterator iter = list.iterator();
        //返回与ArrayList对应的迭代器
        System.out.println("---------");
        while (iter.hasNext()) {  //判断集合中是否还有元素
            System.out.println(iter.next());//next方法取出下一个元素
        }
    }
}

class Student {
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "学生的学号是：" + id + ",年龄是："
                + age + "。";
    }
}
