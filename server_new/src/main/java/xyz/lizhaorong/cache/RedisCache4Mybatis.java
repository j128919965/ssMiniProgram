package xyz.lizhaorong.cache;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@Component
public class RedisCache4Mybatis implements Cache {

    private static RedisTemplate<String,Object> redisTemplate;

    private final String id;

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public ReadWriteLock getReadWriteLock()
    {
        return this.readWriteLock;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate) {
        RedisCache4Mybatis.redisTemplate = redisTemplate;
    }

    public RedisCache4Mybatis() {
        this.id = UUID.randomUUID().toString();
    }

    public RedisCache4Mybatis(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        log.debug("MybatisRedisCache:id=" + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        try{
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>putObject: key="+key+",value="+value);
            if(null!=value)
                redisTemplate.opsForValue().set(key.toString(),value,60, TimeUnit.SECONDS);//控制存放时间60s
        }catch (Exception e){
            e.printStackTrace();
            log.error("redis保存数据异常！");
        }
    }

    @Override
    public Object getObject(Object key) {
        try{
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>getObject: key="+key);
            if(null!=key)
                return redisTemplate.opsForValue().get(key.toString());
        }catch (Exception e){
            e.printStackTrace();
            log.error("redis获取数据异常！");
        }
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        try{
            if(null!=key)
                return redisTemplate.expire(key.toString(),1, TimeUnit.DAYS);//设置过期时间
        }catch (Exception e){
            e.printStackTrace();
            log.error("redis获取数据异常！");
        }
        return null;
    }

    @Override
    public void clear() {
        Long size=redisTemplate.execute((RedisCallback<Long>) redisConnection -> {
            Long size1 = redisConnection.dbSize();
            //连接清除数据
            redisConnection.flushDb();
            redisConnection.flushAll();
            return size1;
        });
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>clear: 清除了" + size + "个对象");
    }

    @Override
    public int getSize() {
        Long size = redisTemplate.execute((RedisCallback<Long>) RedisServerCommands::dbSize);
        return size.intValue();
    }


}
