package com.wangr.test.def;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-07 10:34
 */
public interface Sink<T> {
    void flush(T t);
}
