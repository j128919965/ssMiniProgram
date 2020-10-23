package xyz.lizhaorong.security.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在controller方法上添加此注解，代表需要身份验证
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
    /**
     * value，即为权限值，用户的权限需要大于等于该权限值才予以放行
     * @return
     */
    public int value() default 1;
}