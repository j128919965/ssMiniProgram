package xyz.lizhaorong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lizhaorong.dao.ActivityChooseMapper;
import xyz.lizhaorong.dao.ActivityMapper;
import xyz.lizhaorong.entity.Activity;
import xyz.lizhaorong.entity.ActivityChoose;

import java.util.List;

@Service
public class ActivityService {

    @Autowired
    ActivityMapper activityMapper;

    @Autowired
    ActivityChooseMapper chooseMapper;

    public List<Activity> getNowActivities(){
        return activityMapper.getNowActivities();
    }

    public Activity getActivity(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    public void insertActivityChoose(Integer aid,Integer uid){
        chooseMapper.signup(aid,uid);
    }

    public Boolean getHasSigned(Integer uid, Integer aid) {
        return activityMapper.selectSigned(uid,aid)>0;
    }
}
