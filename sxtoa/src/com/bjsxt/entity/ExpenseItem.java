package com.bjsxt.entity;

import org.mockito.internal.matchers.Null;

/**
 * 报销明细
 */
public class ExpenseItem {

    // 明细编号
    private int itemId;
    // 报销类型
    private String type;
    // 明细金额
    private double amount;
    // 费用说明
    private String itemDesc;

    // 报销编号
    private int expId;
    // 所属报销
    private Expense exp;

    public ExpenseItem() {
    }

    public ExpenseItem(int itemId, String type, double amount, String itemDesc, int expId, Expense exp) {
        this.itemId = itemId;
        this.type = type;
        this.amount = amount;
        this.itemDesc = itemDesc;
        this.expId = expId;
        this.exp = exp;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public Expense getExp() {
        return exp;
    }

    public void setExp(Expense exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        String str = "ExpenseItem{" +
                "itemId=" + itemId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", itemDesc='" + itemDesc + '\'' +
                ", expId=" + expId +
                '}';
        if (exp!=null){
            str+=exp.toString();
        }
        return str;
    }
}
