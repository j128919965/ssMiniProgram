package xyz.lizhaorong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.lizhaorong.dao.NoticeMapper;
import xyz.lizhaorong.dao.SwiperMapper;
import xyz.lizhaorong.entity.Notice;
import xyz.lizhaorong.entity.Swiper;
import xyz.lizhaorong.util.support.Response;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    SwiperMapper swiperMapper;

    public List<String> getSwiper(){
        List<Swiper> swipers = swiperMapper.selectAll();
        return swipers.stream().map(Swiper::getUrl).collect(Collectors.toList());
    }


    public List<Notice> getNoticesAviliable() {
        Notice notice = new Notice();
        notice.setVisiable(true);
        return noticeMapper.select(notice);
    }
}
