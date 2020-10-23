package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.Activity;

import java.util.List;

@CacheNamespace(implementation = EhcacheCache.class)
public interface ActivityMapper extends Mapper<Activity> {

    @Select("SELECT * FROM sunshine.activity where fromdate<now() and todate>now()")
    List<Activity> getNowActivities();

    @Select("select count(*) as num from activity_choose where uid = #{uid} and aid = #{aid};")
    Integer selectSigned(@Param("uid") Integer uid, @Param("aid") Integer aid);
}