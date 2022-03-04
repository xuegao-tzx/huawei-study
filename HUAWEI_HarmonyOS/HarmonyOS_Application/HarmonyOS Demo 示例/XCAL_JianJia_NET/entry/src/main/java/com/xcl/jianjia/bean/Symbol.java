package com.xcl.jianjia.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author Xcl
 * @date 2022/2/14
 * @package com.xcl.calculator.net
 */
public class Symbol implements Serializable {
    //public Object symbols;
    public HashMap<String, Symbols> symbols;

    public static class Symbols implements Serializable {

    }
}
