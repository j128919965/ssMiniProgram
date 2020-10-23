package xyz.lizhaorong.service;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lizhaorong.dao.MessageMapper;
import xyz.lizhaorong.entity.Activity;
import xyz.lizhaorong.entity.Message;
import xyz.lizhaorong.entity.User;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public void welcome(User user){
        Message message = new Message();
        message.setTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
        message.setContext("您好,欢迎来到sunshine动漫社！请尽快在个人资料页面更新您的个人信息！");
        message.setTitle("欢迎您的加入！");
        message.setTo(user.getUid());
        messageMapper.insertMessage(message);
    }

    public void signUpActivity(Integer uid){
        Message message = new Message();
        message.setTime(DateUtil.format(new Date(),"yyyy-MM-dd"));
        message.setTitle("报名成功啦！");
        message.setContext("恭喜，你已经成功报名我们的面试了哦，请务必尽快在个人信息页面完善您的信息，后续我们将通过短信下达面试的时间的地点哦。");
        message.setTo(uid);
        messageMapper.insertMessage(message);
    }

    public List<Message> getAllMessageByUid(Integer uid) {
        return messageMapper.selectByUid(uid);
    }

    public void readMsg(Integer id) {
        messageMapper.readMsg(id);
    }

    public Integer getNotReadCount(Integer uid) {
        return messageMapper.getNotReadCount(uid);
    }
}
