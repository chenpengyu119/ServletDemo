package com.bjsxt.test;

import com.bjsxt.dao.PositionDao;
import com.bjsxt.dao.impl.PositionDaoImpl;
import com.bjsxt.entity.Position;
import org.junit.Test;

import java.util.List;

public class PositionTest {

    private PositionDao posDao = new PositionDaoImpl();

    @Test
    public void findAllTest(){
        List<Position> posList = posDao.findAll();
        for (Position p:posList){
            System.out.println(p);
        }
    }


    @Test
    public void saveTest(){
        int n = posDao.save(new Position(4, "打扫卫生", "扫地"));
        System.out.println(n);
    }

    @Test
    public void deleteTest(){
        int n = posDao.delete(4);
        System.out.println(n);
    }

    @Test
    public void findByIdTest(){
        Position pos = posDao.findById(3);
        System.out.println(pos);
    }

    @Test
    public void updateTest(){
        int n = posDao.update(new Position(4, "前台", "美女"));
        System.out.println(n);
    }
}
