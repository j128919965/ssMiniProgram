package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.st.CardInfo;


public interface CardInfoMapper extends Mapper<CardInfo> {
}
