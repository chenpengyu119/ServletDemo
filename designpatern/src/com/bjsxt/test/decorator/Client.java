package com.bjsxt.test.decorator;

public class Client {

    public static void main(String[] args) {
        Car car = new Car();
        car.move();
        // 新功能：飞
        FlyCar flyCar = new FlyCar(car);
        flyCar.move();
        // 新功能：游
        WaterCar waterCar = new WaterCar(car);
        waterCar.move();
    }
}
