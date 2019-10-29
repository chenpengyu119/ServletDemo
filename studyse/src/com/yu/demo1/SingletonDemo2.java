package com.yu.demo1;

/**
 * 单例模式
 * 懒汉式实现，不立即加载，支持延时加载
 * 使用同步保证线程安全
 * @author pengyu
 */
public class SingletonDemo2 {

    /**
     * 私有的类变量，赋值为类的对象
     */
    private static SingletonDemo2 instance;

    /**
     * 私有构造器
     */
    private SingletonDemo2(){}

    /**
     * 对外提供开放的共享使用的访问方法
     */
    public synchronized static SingletonDemo2 getInstance(){
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}
