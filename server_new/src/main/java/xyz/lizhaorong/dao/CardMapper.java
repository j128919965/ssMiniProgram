package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.st.Card;

import java.util.List;


public interface CardMapper extends Mapper<Card> {

    @Select("select * from st_card")
    List<Card> getAllCards();

    @Insert("insert into st_card(`id`,`type`) values(#{c.id},#{c.type})")
    void saveCard(@Param("c") Card card);

}
