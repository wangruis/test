package com.wangr.test.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.wangr.test.annotate.ClassLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王瑞
 * @description 动态修改日志级别
 * @date 2018-12-08 19:17
 */
@ClassLog
@RestController
public class LogbackController {
    private Logger log = LoggerFactory.getLogger(LogbackController.class);

    @RequestMapping(value = "/logback")
    public String logj(){

        log.error("我是error");
        log.warn("我是warn");
        log.info("我是info");
        log.debug("我是debug");
        log.trace("我是trace");
        return "success";
    }


    /**
     * logback动态修改包名的日志级别
     * @param level 日志级别
     * @param packageName 包名
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/level")
    public String updateLogbackLevel( @RequestParam(value="level") String level,
                                      @RequestParam(value="packageName",defaultValue = "-1") String packageName) throws Exception {
        LoggerContext loggerContext =(LoggerContext) LoggerFactory.getILoggerFactory();
        if(packageName.equals("-1")) {
            // 默认值-1，更改全局日志级别；否则按传递的包名或类名修改日志级别。
            loggerContext.getLogger("root").setLevel(Level.toLevel(level));
        } else {
            loggerContext.getLogger(packageName).setLevel(Level.valueOf(level));
        }
        return "success";
    }
}
