package com.sfauto.cloud.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SrciptCalculateTest {

    @Test
    void cal() {
        //String script = "function add(pr1,pr11){return pr1+pr11;} add(pr1,pr11)";
        String script = "{return pr1+pr11;}";
        Map<String, Object> map = new HashMap<>();
        map.put("pr1", 10.0);
        map.put("pr11", 12.9);

        Double d = SrciptCalculate.jsCal(script, map);
        System.out.println(d);
    }

    @Test
    void convertToCode() {
        String script = "if((a*2)==5){c=1.0;}else{c=2.0;} return c;";
        Map<String, Object> map = new HashMap<>();
        map.put("a", 10.0);

        Double d = SrciptCalculate.javaCal(script, map);
        System.out.println(d);
    }

    @Test
    void convertToCode2() {
        String script = "for(var b1 : b) a = a + b1; return a;";
        Map<String, Object> map = new HashMap<>();
        double[] arr = new double[]{0.1, 0.2, 0.3, 0.4};
        map.put("b", arr);
        map.put("a", 0.0);

        Double d = SrciptCalculate.javaCal(script, map);
        System.out.println(d);
    }

    @Test
    void refectCalTest() {
        double[] arr = new double[]{0.1, 0.2, 0.3, 0.4};
        Double d = SrciptCalculate.reflectArrayCal("com.sfauto.cloud.base.ArrayMath", "average",  arr);
        System.out.println(d);
    }
}