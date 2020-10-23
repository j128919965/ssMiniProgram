package xyz.lizhaorong.service;

import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lizhaorong.dao.UserhonorMapper;
import xyz.lizhaorong.entity.Honor;
import xyz.lizhaorong.entity.Userhonor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HonorService {

    @Autowired
    UserhonorMapper userhonorMapper;

    public void insertHonor(Integer hid,Integer uid){
        Userhonor userhonor = new Userhonor();
        userhonor.setUid(uid);
        userhonor.setHid(hid);
        userhonor.setDate(DateUtil.format(new Date(),"yyyy-MM-dd"));
        userhonorMapper.insert(userhonor);
    }

    public List<Map> getHonors(Integer uid){
        return userhonorMapper.getUserHonors(uid);
    }

}
