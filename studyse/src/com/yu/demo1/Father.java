package com.yu.demo1;

public abstract class Father {
    public void service() {
        son_service();
    }

    public void son_service() {
        System.out.println("father's son_service");
    }
}

class Son extends Father {
    @Override
    public void son_service() {
        System.out.println("Son's son_service");
    }
}

class Test {
    public static void main(String[] args) {
        Father father = new Son();
        father.service();

    }
}