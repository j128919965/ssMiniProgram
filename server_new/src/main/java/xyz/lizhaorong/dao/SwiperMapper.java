package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import org.mybatis.caches.ehcache.EhcacheCache;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.Swiper;

@CacheNamespace(implementation = EhcacheCache.class)
public interface SwiperMapper extends Mapper<Swiper> {
}