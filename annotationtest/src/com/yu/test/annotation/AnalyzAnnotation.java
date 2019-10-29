package com.yu.test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射解析注解
 * @author pengyu
 */
public class AnalyzAnnotation {
    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("com.yu.test.annotation.Student");
            // 获取所有注解
            Annotation[] annotations = clazz.getAnnotations();
            // 获取指定注解
            Annotation annotationClass = clazz.getAnnotation(Table.class);
            Table tb = (Table)annotationClass;
            String clazzName = tb.value();
            System.out.println("类名："+clazzName);
            // 获取属性相关值
            Field[] fields = clazz.getDeclaredFields();

            for (Field f:fields){
                com.yu.test.annotation.Field field = f.getAnnotation(com.yu.test.annotation.Field.class);
                System.out.print("属性：");
                System.out.print(field.columnName()+"\t");
                System.out.print(field.length()+"\t");
                System.out.println(field.type()+"\t");

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
