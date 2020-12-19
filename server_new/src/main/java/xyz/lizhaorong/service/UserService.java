package xyz.lizhaorong.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.lizhaorong.dao.UserMapper;
import xyz.lizhaorong.entity.User;
import xyz.lizhaorong.util.http.HttpUtil;
import xyz.lizhaorong.util.support.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MessageService messageService;

    @Autowired
    HonorService honorService;

    public String getWxid(String code) throws IOException {
        final String APPID = "wx22285fd44506b373";
        final String SECRET = "f9e8167aec0927246682f992e9731fc2";

        HttpUtil httpUtil = new HttpUtil();
        Map<String,String> data = new HashMap<>();
        data.put("appid", APPID);
        data.put("secret", SECRET);
        data.put("js_code",code);
        data.put("grant_type","authorization_code");
        HashMap<String,String> ids = httpUtil.get("https://api.weixin.qq.com/sns/jscode2session", data, HashMap.class);
        return ids.get("openid");
    }

    public User getUserByWxid(String wxid){
        User u = new User();
        u.setWxid(wxid);
        return userMapper.selectOne(u);
    }

    @Transactional
    public User createUser(String wxid){
        User u = new User();
        u.setWxid(wxid);
        userMapper.insertSelective(u);
        log.debug("user created , id is "+u.getUid());
        return u;
    }

    @Async
    @Transactional
    public void afterCreateUser(User u){
        honorService.insertHonor(1,u.getUid());
        messageService.welcome(u);
        log.debug("after insert User.......");
    }

    public void updateUser(User u){
        userMapper.updateByPrimaryKeySelective(u);
    }

    public void updateUser(String uid, String opt, String data) {
        userMapper.updateUser(opt,uid,data);
        log.debug("update success");
    }

    public User getUserByUsernameAndPassword(String userName, String password) {
        User user = userMapper.getUserByUsernameAndPassword(userName, password);
        log.debug(user.toString());
        return user;
    }

    public User register(String username, String password) {
        Integer uid = userMapper.getUidByUsername(username);
        if (uid!=null){
            return null;
        }
        User user = new User();
        user.setWxid(username);
        userMapper.insertSelective(user);
        userMapper.updateUsernameAndPassword(user.getUid(),username,password);
        return user;
    }

    public User getUserByUid(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
}
