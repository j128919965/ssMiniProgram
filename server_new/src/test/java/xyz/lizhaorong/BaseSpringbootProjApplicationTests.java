package xyz.lizhaorong;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import xyz.lizhaorong.security.token.TokenManager;

import java.util.Date;

@SpringBootTest
class BaseSpringbootProjApplicationTests {

    @Autowired
    TokenManager tokenManager;

    @Test
    void contextLoads() {
        System.out.println("------------------------");
        System.out.println(tokenManager);
        System.out.println("------------------------");
    }

    @Test
    void testTime(){
        String date = DateUtil.format(new Date(),"yyyy-MM-dd");
        System.out.println(date);
    }

}
