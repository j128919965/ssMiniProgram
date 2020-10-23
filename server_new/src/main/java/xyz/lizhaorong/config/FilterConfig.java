package xyz.lizhaorong.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.lizhaorong.filter.CorsFilter;
import xyz.lizhaorong.filter.LogFilter;

@Configuration
@Slf4j
public class FilterConfig {

    @Autowired
    private LogFilter logFilter;

    @Autowired
    private CorsFilter corsFilter;

    @Bean
    public FilterRegistrationBean<LogFilter> registerLogFilter(){
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<LogFilter>();
        registrationBean.setFilter(logFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("logFilter");
        registrationBean.setOrder(9);  //值越小，Filter越靠前。
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> registerCorsBean(){
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<CorsFilter>();
        registrationBean.setFilter(corsFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("corsFilter");
        registrationBean.setOrder(1);  //值越小，Filter越靠前。
        return registrationBean;
    }
}
