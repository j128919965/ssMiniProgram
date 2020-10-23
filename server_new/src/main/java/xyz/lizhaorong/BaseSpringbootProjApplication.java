package xyz.lizhaorong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "xyz.lizhaorong.dao")
@EnableTransactionManagement
@EnableAsync
public class BaseSpringbootProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSpringbootProjApplication.class, args);
    }

}
