package com.wangr.test.aop;

import com.wangr.test.enums.FieldEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 王瑞
 * @description
 * @date 2018-12-11 9:29
 */
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    private List<FieldEnum> fieldEnums;

    public LogAspect(List<FieldEnum> fieldEnums){
        this.fieldEnums = fieldEnums;
    }

    @Pointcut("@annotation(com.wangr.test.annotate.MethodLog)")
    public void methodLog(){

    }

    @Pointcut("@within(com.wangr.test.annotate.ClassLog)")
    public void classLog(){

    }

    @Before("methodLog() || classLog()")
    public void logBefore(JoinPoint joinpoint){

        if (fieldEnums.contains(FieldEnum.BEFORE)){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String ip = request.getRemoteAddr();
            String className = joinpoint.getTarget().getClass().getName();
            String methodName = joinpoint.getSignature().getName();
            Object[] params = joinpoint.getArgs();
            LOGGER.info("来自[{}]访问类：[{}],方法：[{}],参数：{}",
                    ip, className, methodName, params);
        }
    }

    @AfterThrowing(pointcut = "methodLog() || classLog()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinpoint, Exception e){
        if(fieldEnums.contains(FieldEnum.EXCEPTION)){
            String className = joinpoint.getTarget().getClass().getName();
            String methodName = joinpoint.getSignature().getName();
            Object[] params = joinpoint.getArgs();
            LOGGER.error("访问类：[{}],方法：[{}],参数：{},异常类型：[{}],异常信息：[{}]",
                    className, methodName, params, e.getClass().getName(), e.getMessage());

        }
    }

    @AfterReturning(pointcut = "methodLog() || classLog()", returning = "result")
    public void logReturn(JoinPoint joinpoint, Object result){

        if(fieldEnums.contains(FieldEnum.RETURN)){
            String className = joinpoint.getTarget().getClass().getName();
            String methodName = joinpoint.getSignature().getName();
            Object[] params = joinpoint.getArgs();
            LOGGER.info("访问类：[{}],方法：[{}],参数：{},返回值：[{}]",
                    className, methodName, params, result);
        }

    }



}
