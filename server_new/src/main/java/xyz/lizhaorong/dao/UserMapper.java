package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.User;

@CacheNamespace(implementation = EhcacheCache.class)
public interface UserMapper extends Mapper<User> {

    void updateUser(@Param("opt") String opt, @Param("uid") String uid, @Param("data") String data);

    @Select("select uid from user where cn=#{cn};")
    Integer getUidByCn(@Param("cn") String cn);

    @Select("select uid from user where name=#{name}")
    Integer getUidByName(@Param("name") String name);

    @Select("select * from user where cn like '%${cn}%';")
    User getUidByLikeCn(@Param("cn") String cn);

    User getUserByUsernameAndPassword(@Param("user") String userName, @Param("pswd") String password);

    @Select("select uid from user where username = #{username}")
    Integer getUidByUsername(@Param("username") String username);

    @Update("update user set username=#{username} , password=#{password} where uid=#{uid}")
    void updateUsernameAndPassword(@Param("uid") Integer uid, @Param("username") String username,@Param("password") String password);
}