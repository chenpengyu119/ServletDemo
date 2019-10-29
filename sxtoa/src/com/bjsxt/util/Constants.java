package com.bjsxt.util;

/**
 * 常量类
 * 提供可读性
 * 便于修改
 */
public abstract class Constants {

    /**
     * 报销单审核状态
     */
    // 新建
    public static final  String EXP_AUDIT_NEW = "0";
    // 审核中
    public static final String EXP_AUDIT_AUDITING = "1";
    // 审核通过
    public static final String EXP_AUDIT_PASS = "2";
    // 审核拒绝
    public static final String EXP_AUDIT_REJECT = "3";
    // 审核打回
    public static final String EXP_AUDIT_BACK = "4";

    /**
     * 特殊人员
     */
    // 尚学堂总裁
    public static final String SXT_CEO = "gaoqi";
    // 尚学堂财务官
    public static final String SXT_CFO = "liufuying";
}
