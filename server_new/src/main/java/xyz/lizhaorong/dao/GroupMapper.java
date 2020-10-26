package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;
import xyz.lizhaorong.entity.st.GroupSelect;

public interface GroupMapper {

    @Select("select * from st_select where uid = #{uid}")
    GroupSelect getIfMember(@Param("uid") Integer uid);

}
