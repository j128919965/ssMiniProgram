package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.User;

@CacheNamespace(implementation = EhcacheCache.class)
public interface UserMapper extends Mapper<User> {

    void updateUser(@Param("opt") String opt, @Param("uid") String uid, @Param("data") String data);

}