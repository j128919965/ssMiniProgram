package xyz.lizhaorong.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.lizhaorong.entity.st.GroupSelect;

public interface GroupMapper {

    @Select("select * from st_select where uid = #{uid}")
    GroupSelect getIfMember(@Param("uid") Integer uid);

}
