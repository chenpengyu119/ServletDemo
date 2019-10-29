package com.bjsxt.test;

import com.bjsxt.entity.ExpenseItem;
import com.bjsxt.service.ExpenseService;
import com.bjsxt.service.impl.ExpenseServiceImpl;
import org.junit.Test;

import java.util.List;

public class ExpenseTest {

    private ExpenseService expService = new ExpenseServiceImpl();
    @Test
    public void findListByExpIdTest(){

        List<ExpenseItem> itemList = expService.findListByExpId(9);
        for (ExpenseItem item:itemList){
            System.out.println(item);
        }
    }
    @Test
    public void getInDataTest(){
        System.out.println(expService.getInData());
    }

}
