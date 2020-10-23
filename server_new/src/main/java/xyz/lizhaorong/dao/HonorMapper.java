package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.CacheNamespace;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.Honor;

@CacheNamespace
public interface HonorMapper extends Mapper<Honor> {
}