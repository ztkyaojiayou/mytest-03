package com.sfauto.cloud.model;

import org.apache.commons.jexl3.*;

import javax.script.*;
import java.lang.reflect.Method;
import java.util.Map;

public class SrciptCalculate {
    /*
    * 调用JavaScript脚本
     */
    public static Double jsCal(String script, Map<String, Object> map) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");  //创建引擎实例

        Object result = "";
        try {
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings();

            String funName = "func(";
            for (String key: map.keySet()) {
                funName += key;
                funName += ",";
            }

            funName = funName.substring(0, funName.length()-1)+")";
            script = "function " + funName + script + funName;
            //script = "function add(op1,op2){return op1+op2;} add(param1, param2)";

            // 定义函数并调用
            CompiledScript JSFunction = compilable.compile(script); // 解析编译脚本函数
            // 通过Bindings加入参数
            for (String key: map.keySet()) {
                bindings.put(key, map.get(key));
            }

            // 调用缓存着的脚本函数对象，Bindings作为参数容器传入
            result = JSFunction.eval(bindings);
            if(result != null) {
                return (Double) result;
            }
        }
        catch (Exception e) {
            System.out.println("表达式runtime错误:" + e.getMessage());
        }

        return null;
    }

    /*
    * 调用Java脚本
     */
    public static Double javaCal(String jexlExp, Map<String, Object> map) {
        JexlEngine jexl = new JexlBuilder().cache(1024).strict(true).silent(false).create();
        JexlScript script = jexl.createScript(jexlExp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }

        Object res = script.execute(jc);
        return (Double)res;
    }

    /*
    * 调用内置数组处理类
     */
    public static Double reflectArrayCal(String className, String funName, double[] arr) {
        Double res = 0.0;
        long t1 = System.currentTimeMillis();
        try {
            Class clazz = Class.forName(className);
            Method method = clazz.getMethod(funName, double[].class);
            res = (double)method.invoke(null, arr);
        }
        catch (Exception e) {
            System.out.println("err"+e.getMessage());
        }

        long t2 = System.currentTimeMillis();

        return res;
    }
}
