package xyz.lizhaorong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lizhaorong.entity.Notice;
import xyz.lizhaorong.entity.Swiper;
import xyz.lizhaorong.service.NoticeService;
import xyz.lizhaorong.util.support.Response;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @GetMapping
    public Response<List<Notice>> getNotices(){
        Response<List<Notice>> response = new Response<>();
        response.success(noticeService.getNoticesAviliable());
        return response;
    }

    @GetMapping("/swiper")
    public Response<List<String>> getSwiper(){
        Response<List<String>> response = new Response<>();
        response.success(noticeService.getSwiper());
        return response;
    }

}
