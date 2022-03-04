package fuxi.jihekj;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Xcl
 * @date 2021/12/20 16:25
 * @package fuxi.jihekj
 */
public class mapp {
    public static void main(String[] args) {
        Map<Integer, Student> map = new HashMap<Integer, Student>();
        Student s1 = new Student(1003, "li", 20);
        map.put(new Integer(1003), s1);
        map.put(new Integer(1002), new Student(1002, "li", 20));
        map.put(1004, new Student(1004, "赵", 18));
        map.put(1001, new Student(1001, "wang", 18));
        Student s2 = new Student(1005, "薛", 22);
        map.put(1005, s2);//自动装箱
        System.out.println("按照key值取出学生信息:key=1002," + map.get(1002));
        System.out.println("******************");
        System.out.println("遍历所有的key值：");
        Iterator iterkey = map.keySet().iterator();
        while (iterkey.hasNext()) {
            System.out.println(iterkey.next());
        }
        System.out.println("*********************");
        System.out.println("遍历所有的value值：");
        Iterator itervalue = map.values().iterator();
        while (itervalue.hasNext()) {
            System.out.println(itervalue.next());
        }
        System.out.println("******************");
        System.out.println("同时遍历所有的key和value值：");
        Iterator iterentry = map.entrySet().iterator();
        while (iterentry.hasNext()) {
            Map.Entry entry = (Map.Entry) iterentry
                    .next();
            System.out.println("key=" + entry.getKey()
                    + ":" + "value=" + entry.getValue());
        }
        //按照Key值删除学生信息
        map.remove(1004);
        System.out.println("*********************");
        System.out.println("删除key=1004的学生对象后, 用迭代器遍历map中所有元素:");
        Iterator iterentrydel = map.entrySet().
                iterator();
        while (iterentrydel.hasNext()) {
            Map.Entry entry = (Map.Entry)
                    iterentrydel.next();
            System.out.println("key=" + entry.getKey()
                    + ":" + "value=" + entry.getValue());
        }
    }
}
