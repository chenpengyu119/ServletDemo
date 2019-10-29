package com.yu.demo1;

/**
 * 单例模式
 * 饿汉式实现，立即加载，不支持延时加载
 * 因为是在类加载时创建，所以天然的线程安全
 * 因为不需要使用同步，所以效率高
 * @author pengyu
 */
public class SingletonDemo1 {

    /**
     * 私有的类变量，赋值为类的对象
     */
    private static SingletonDemo1 instance = new SingletonDemo1();

    /**
     * 私有构造器
     */
    private SingletonDemo1(){}

    /**
     * 对外提供开放的共享使用的访问方法
     */
    public static SingletonDemo1 getInstance(){
        return instance;
    }
}
