package com.sfauto.base.global.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class BeanUtil {
    @SuppressWarnings("unchecked")
    public static <T> T cloneTo(T src) throws RuntimeException {
        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T dist = null;
        try {
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(src);
            out.flush();
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            dist = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out != null)
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            if (in != null)
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return dist;
    }

    /**
     * 将来源Bean的属性值复制到目标Bean，如果目标Bean的属性值不为空， 并且来源Bean的属性值为空，刚不进行值覆盖
     *
     * @param dest
     *            目标Bean
     * @param orig
     *            来源Bean
     */
    public static void copy(Object dest, Object orig) {
        copy(dest, orig, false);
    }

    /**
     * 将来源Bean的属性值复制到目标Bean
     *
     * @param dest
     *            目标Bean
     * @param orig
     *            来源Bean
     * @param isOverride
     *            <font style='color:red'>如果来源Bean的属性值为空时是否执行覆盖</font>
     */
    @SuppressWarnings("unused")
    public static void copy(Object dest, Object orig, boolean isOverride) {
        try {
            // 来源Bean为空的话不执行BeanCopy动作
            if (orig != null) {
                // 验证是否存在指定Bean
                if (dest == null) {
                    throw new IllegalArgumentException(
                            "No destination bean specified");
                }
                if (orig == null) {
                    throw new IllegalArgumentException(
                            "No origin bean specified");
                }
                // 获取来源Bean的所有属性
                Field[] origFields = orig.getClass().getDeclaredFields();
                // 为目标Bean的每个属性复制值
                for (Field of : origFields) {
                    try {
                        PropertyDescriptor pd = new PropertyDescriptor(
                                of.getName(), orig.getClass());
                        Method method = pd.getReadMethod();
                        if(null==method){
                            continue;
                        }
                        Object origValue = method.invoke(orig);
                        // isOverride为true并且origValue为空，则进行覆盖
                        // isOverride为false并且origValue为空，则忽略复制
                        // origValue不为空直接进行复制
                        if (isOverride && origValue == null) {
                            BeanUtils.copyProperty(dest, of.getName(), null);
                        } else if (origValue != null) {
                            if (origValue instanceof String) {
                                String str = (String) origValue;
                                if (str.length() == 0) {
                                    BeanUtils.copyProperty(dest, of.getName(),
                                            null);
                                }
                            } else if (origValue instanceof BigDecimal) {
                                //
                            } else if (origValue instanceof Integer) {
                                //
                            }
                            BeanUtils.copyProperty(dest, of.getName(),
                                    origValue);
                        }
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
