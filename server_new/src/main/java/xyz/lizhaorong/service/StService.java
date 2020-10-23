package xyz.lizhaorong.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lizhaorong.dao.CardInfoMapper;
import xyz.lizhaorong.dao.CardMapper;
import xyz.lizhaorong.dao.GroupMapper;
import xyz.lizhaorong.entity.st.Card;
import xyz.lizhaorong.entity.st.CardInfo;
import xyz.lizhaorong.entity.st.Group;
import xyz.lizhaorong.entity.st.GroupSelect;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StService {

    @Autowired
    CardInfoMapper cardInfoMapper;

    @Autowired
    CardMapper cardMapper;

    @Autowired
    GroupMapper groupMapper;

    private final Map<Integer,Card> cards = new HashMap<>();

    private final Map<Integer,CardInfo>  cardInfoMap = new HashMap<>();

    private final Map<Integer,List<Integer>> groupResult = new HashMap<>();

    // 0 还未开始
    // 1 已经开始
    // 2 已经结束
    private volatile int st_state = 0;

    public void clear(){
        st_state = 0;
    }

    public void init(){
        cards.clear();
        groupResult.clear();
        cardMapper.getAllCards().forEach(e->cards.put(e.getId(),e));
        cardInfoMapper.selectAll().forEach(e->cardInfoMap.put(e.getType(),e));
        st_state = 1;
    }

    public int getState(){
        return st_state;
    }

    public boolean scanCard(Integer id,Integer gid){
        if(st_state != 1) return false;
        Card card = cards.get(id);
        boolean flag = false;
        synchronized (card){
            if(card.getGroup()==0){
                card.setGroup(gid);
                flag = true;
            }
        }
        return flag;
    }


    public GroupSelect getIfMember(Integer uid) {
        log.debug("-------uid : "+uid);
        return groupMapper.getIfMember(uid);
    }

    public List<Card> getGroupCards(Integer gid) {
        List<Integer> list = groupResult.get(gid);
        if(list==null)return new ArrayList<>();
        return list.stream().map(cards::get).collect(Collectors.toList());
    }

    /**
     * 保存一组的列表
     * @param gid
     * @param list
     */
    public boolean saveList(Integer gid, Integer[] list) {
        if (st_state!=1)return false;
        groupResult.put(gid,Arrays.asList(list));
        System.out.println(groupResult);
        return true;
    }

    /**
     *
     * @param gid
     * @return 队伍总分
     */
    public Integer calc(Integer gid){
        List<CardInfo> cards = getGroupCards(gid).stream().map(e->cardInfoMap.get(e.getType())).collect(Collectors.toList());
        //当前印记
        int nowState = 0;
        //总分
        int allPoint = 0;
        //下一次扩大
        double nextBigger = 0;
        for (CardInfo card : cards) {
            //当前卡牌属性
            var prop = card.getProp();
            int point = card.getPoint();

            //备份当前轮次的 前一轮带来的伤害增加
            double preBigger = nextBigger;
            nextBigger=0;

            int nextState = 0;
            //如果当前没有印记
            if(nowState==0){
                log.debug("当前没有印记！");
                nextState=prop<5?prop:0;
            }
            //如果当前有印记
            else {
                switch (prop){
                    case 1:{
                        switch (nowState){
                            case 2:{
                                log.debug("冰+火");
                                point*=1.5;
                                break;
                            }
                            case 3:{
                                log.debug("冰+水");
                                nextBigger = 50;
                                nextState=1;
                                break;
                            }
                            case 4:{
                                log.debug("冰+雷");
                                nextBigger = 1.3;
                                break;
                            }
                            default:{

                            }
                        }
                        break;
                    }
                    case 2:{
                        switch (nowState){
                            case 1 :{
                                log.debug("火+冰");
                                point*=2;
                                break;
                            }
                            case 3 :{
                                log.debug("火+水");
                                point*=1.5;
                                break;
                            }
                            case 4 :{
                                log.debug("火+雷");
                                nextState = 2;
                                point+=50;
                            }
                        }
                        break;
                    }
                    case 3:{
                        switch (nowState){
                            case 1:{
                                log.debug("水+冰");
                                nextState=1;
                                nextBigger=50;
                                break;
                            }
                            case 2:{
                                log.debug("水+火");
                                point*=2;
                                break;
                            }
                            case 4:{
                                log.debug("水+雷");
                                nextState=4;
                                point+=50;
                                break;
                            }
                            default:{

                            }
                        }
                        break;
                    }
                    case 4:{
                        switch (nowState){
                            case 1:{
                                log.debug("雷+冰");
                                nextBigger=1.3;
                                break;
                            }
                            case 2:{
                                log.debug("雷+火");
                                nextState=2;
                                point+=50;
                                break;
                            }
                            case 3:{
                                log.debug("雷+水");
                                nextState=4;
                                point+=50;
                                break;
                            }
                            default:{

                            }
                        }
                        break;
                    }
                    case 5:{
                        log.debug("奥数");
                        point *= 1.6;
                        break;
                    }
                }
            }

            //如果大于1，则代表有放大
            if(preBigger>1){
                if(preBigger<3){
                    //小于3，即为倍数放大
                    log.debug("受上一轮影响，本次伤害翻为 " + preBigger + " 倍！");
                    point*=preBigger;
                }else {
                    //大于3，即为增加数值
                    log.debug("受上一轮影响，本次伤害增加 " + preBigger + " 点！");
                    point+=preBigger;
                }
            }
            log.debug("本次伤害："+point);
            log.debug("");

            allPoint+=point;
            nowState = nextState;
        }
        log.debug("all point : "+ allPoint);
        return allPoint;
    }


    public Set<Integer> getAllGroups() {
        Set<Integer> integers = groupResult.keySet();
        return groupResult.keySet();
    }

    public void stopSt() {
        st_state = 2;
    }
}
