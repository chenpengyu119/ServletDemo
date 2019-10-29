package com.bjsxt.test.decorator;

public interface ICar {
    void move();
}
// 真实对象，具体构建角色
class Car implements ICar{

    @Override
    public void move() {
        System.out.println("陆地上跑");
    }
}
class SuperCar implements ICar{
    private ICar car;

    public SuperCar(ICar car) {
        super();
        this.car = car;
    }
    @Override
    public void move() {
        car.move();
    }
}
class FlyCar extends SuperCar{
    public FlyCar(ICar car) {
        super(car);
    }
    public void fly(){
        System.out.println("天上飞");
    }

    @Override
    public void move() {
        super.move();
        fly();
    }
}

class WaterCar extends SuperCar{
    public WaterCar(ICar car) {
        super(car);
    }
    public void swim(){
        System.out.println("水中游");
    }

    @Override
    public void move() {
        super.move();
        swim();
    }
}