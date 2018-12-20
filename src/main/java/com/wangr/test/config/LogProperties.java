package com.wangr.test.config;

import com.wangr.test.enums.FieldEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-11 15:00
 */
@ConfigurationProperties("log.aspect")
public class LogProperties {

    private boolean enable;

    private List<FieldEnum> fieldEnums;

    /**
     * get enable
     *
     * @return boolean enable
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * set enable
     *
     * @param enable
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * get fieldEnums
     *
     * @return java.util.List<com.wangr.test.enums.FieldEnum> fieldEnums
     */
    public List<FieldEnum> getFieldEnums() {
        return fieldEnums;
    }

    /**
     * set fieldEnums
     *
     * @param fieldEnums
     */
    public void setFieldEnums(List<FieldEnum> fieldEnums) {
        this.fieldEnums = fieldEnums;
    }
}
