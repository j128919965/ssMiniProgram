package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import xyz.lizhaorong.entity.ActivityChoose;

public interface ActivityChooseMapper extends Mapper<ActivityChoose> {

    @Insert("insert into activity_choose (`uid`,aid,time) values(#{uid},#{aid},now())")
    void signup(@Param("aid") Integer aid,@Param("uid") Integer uid);

}