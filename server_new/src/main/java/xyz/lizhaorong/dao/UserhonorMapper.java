package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.Honor;
import xyz.lizhaorong.entity.Userhonor;

import java.util.List;
import java.util.Map;

public interface UserhonorMapper extends Mapper<Userhonor> {
    List<Map> getUserHonors(@Param("uid") Integer uid);
}