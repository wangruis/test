package com.wangr.test.enums;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-11 9:38
 */
public enum FieldEnum {

    BEFORE("before"),
    EXCEPTION("afterThrowing"),
    RETURN("afterReturning");

    private String name;

    FieldEnum(String name) {
        this.name = name;
    }

}
