package xyz.lizhaorong;

import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import xyz.lizhaorong.dao.CardInfoMapper;
import xyz.lizhaorong.dao.CardMapper;
import xyz.lizhaorong.dao.GroupMapper;
import xyz.lizhaorong.dao.UserMapper;
import xyz.lizhaorong.entity.User;
import xyz.lizhaorong.entity.st.Card;
import xyz.lizhaorong.entity.st.CardInfo;
import xyz.lizhaorong.entity.st.GroupSelect;
import xyz.lizhaorong.security.token.TokenManager;
import xyz.lizhaorong.service.UserService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;

@SpringBootTest
class BaseSpringbootProjApplicationTests {


    @Autowired
    UserMapper userMapper;


    @Autowired
    GroupMapper groupMapper;

    @Autowired
    UserService userService;

//    @Test
//    void testInsertSelect(){
//        GroupSelect gs = new GroupSelect();
//        gs.setGid(1);
//        gs.setUid(5);
//        gs.setMaster(true);
//        groupMapper.insert(gs);
//    }

    @Test
    void testUserMapper(){

    }

    @Test
    void testUserService(){
        User register = userService.register("??", "??");
        System.out.println(register);
    }
   // @Test
    void checkUser() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("src\\main\\resources\\st_select.txt"));
        int group = 1;
        while (sc.hasNext()){
            String str = sc.nextLine();
            String[] cns = str.split(",");
            for (String cn : cns) {
                Integer uid = userMapper.getUidByCn(cn);
                uid = uid==null?userMapper.getUidByName(cn):uid;
                if(uid==null){
                    System.out.println("未找到用户： "+cn);
                    User u = userMapper.getUidByLikeCn(cn);
                    if(u!=null){
                        System.out.println("\t找到相似用户： cn : "+u.getCn());
                    }
                }
            }
        }
    }

    //@Test
    void insertSelect() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("src\\main\\resources\\st_select.txt"));
        int group = 1;
        while (sc.hasNext()){
            String str = sc.nextLine();
            String[] cns = str.split(",");
            boolean f = true;
            for (String cn : cns) {
                Integer uid = userMapper.getUidByCn(cn);
                uid = uid==null?userMapper.getUidByName(cn):uid;
                if(uid==null){
                    System.out.println("未找到用户： "+cn);
                    continue;
                }
                GroupSelect groupSelect = new GroupSelect();
                if(f){
                    groupSelect.setMaster(true);
                    f = false;
                }else {
                    groupSelect.setMaster(false);
                }
                groupSelect.setGid(group);
                groupSelect.setUid(uid);
                try{
                    groupMapper.insert(groupSelect);
                    System.out.println("插入成功！ " + cn);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            group++;
        }
    }

}
