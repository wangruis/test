package com.wangr.test.def;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-07 11:41
 */
public class TestExction extends RuntimeException {

    private String error;

    private String msg;


    public TestExction(String error, String msg){

        super("TestExction");
        this.error = error;
        this.msg = msg;
    }


}
