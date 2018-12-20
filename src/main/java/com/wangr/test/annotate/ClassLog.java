package com.wangr.test.annotate;

import java.lang.annotation.*;

/**
 * @author 王瑞
 * @description 类日志注解
 * @date 2018-12-10 15:58
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ClassLog {
}
