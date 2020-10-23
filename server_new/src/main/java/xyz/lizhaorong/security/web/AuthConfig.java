package xyz.lizhaorong.security.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class AuthConfig extends WebMvcConfigurerAdapter {

    @Autowired
    AuthorizationInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor( interceptor)
                //添加需要拦截的路径
                .addPathPatterns("/*");
    }

}
