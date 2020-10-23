package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.Message;

import java.util.List;

@CacheNamespace(implementation = EhcacheCache.class)
public interface MessageMapper extends Mapper<Message> {

    void insertMessage(Message message);

    @Select("select * from message where `to` = #{uid}")
    List<Message> selectByUid(@Param("uid") Integer uid);

    @Update("update message set `read`=1 where id = #{id};")
    void readMsg(Integer id);

    @Select("select count(*) as num from message where `to` = #{uid}  and `read` = 0;")
    Integer getNotReadCount(Integer uid);
}