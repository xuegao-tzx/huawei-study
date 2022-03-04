package com.xcl.jianjia.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 裴云飞
 * @date 2021/1/26
 */

public class Banner implements Serializable {


    /**
     * data : [{"desc":"扔物线",
     * "id":29,
     * "imagePath":"https://wanandroid.com/blogimgs/8690f5f9-733a-476a-8ad2-2468d043c2d4.png",
     * "isVisible":1,
     * "order":0,
     * "title":"Kotlin 的 Lambda，大部分人学得连皮毛都不算",
     * "type":0,
     * "url":"http://i0k.cn/5jhSp"
     * },{
     * "desc":"",
     * "id":6,
     * "imagePath":"https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
     * "isVisible":1,
     * "order":1,
     * "title":"我们新增了一个常用导航Tab~",
     * "type":1,"url":"https://www.wanandroid.com/navi"},{"desc":"一起来做个App吧","id":10,"imagePath":"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png","isVisible":1,"order":1,"title":"一起来做个App吧","type":1,"url":"https://www.wanandroid.com/blog/show/2"},{"desc":"","id":20,"imagePath":"https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png","isVisible":1,"order":2,"title":"flutter 中文社区 ","type":1,"url":"https://flutter.cn/"}]
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public List<Data> data;

    public static class Data implements Serializable {
        /**
         * desc : 扔物线
         * id : 29
         * imagePath : https://wanandroid.com/blogimgs/8690f5f9-733a-476a-8ad2-2468d043c2d4.png
         * isVisible : 1
         * order : 0
         * title : Kotlin 的 Lambda，大部分人学得连皮毛都不算
         * type : 0
         * url : http://i0k.cn/5jhSp
         */

        public String desc;
        public int id;
        public String imagePath;
        public int isVisible;
        public int order;
        public String title;
        public int type;
        public String url;

        @Override
        public String toString() {
            return "Data{" +
                    "desc='" + desc + '\'' +
                    ", id=" + id +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible=" + isVisible +
                    ", order=" + order +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Banner{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
