package com.wangr.test.config;

import com.wangr.test.aop.LogAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;


/**
 * @author 王瑞
 * @description
 * @date 2018-12-11 15:02
 */
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(LogProperties.class)
@ConditionalOnProperty(prefix = "log.aspect", value = "enable", matchIfMissing = false)
public class LogConfiguration {

    private LogProperties logProperties;

    public LogConfiguration(LogProperties logProperties) {
        Assert.notNull(logProperties.getFieldEnums(), "必须指定日志打印维度");
        this.logProperties = logProperties;
    }

    @Bean
    public LogAspect logAspect() {
        return new LogAspect(logProperties.getFieldEnums());
    }

}
