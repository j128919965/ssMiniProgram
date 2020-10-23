package xyz.lizhaorong.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.lizhaorong.entity.st.Card;
import xyz.lizhaorong.entity.st.GroupSelect;
import xyz.lizhaorong.service.StService;
import xyz.lizhaorong.util.support.Response;

import java.util.List;

@Controller
@RequestMapping("/st")
@Slf4j
public class StController {

    @Autowired
    StService stService;

    @GetMapping("/init")
    public String initPage(){
        return "init";
    }

    @PostMapping("/init")
    @ResponseBody
    public Response<Boolean> initSt(){
        stService.init();
        return Response.staticSuccess();
    }

    @PostMapping("/scan")
    @ResponseBody
    public Response<Boolean> scanCard(Integer cid,Integer gid){
        Response<Boolean> response = new Response<>();
        response.success(stService.scanCard(cid, gid));
        return response;
    }

    @PostMapping("/save")
    @ResponseBody
    public Response<Boolean> saveList(Integer gid,Integer[] list){
        log.debug("gid : "+gid);
        stService.saveList(gid,list);
        return Response.staticSuccess();
    }

    @GetMapping("/group")
    @ResponseBody
    public Response<GroupSelect> getIfMember(Integer uid){
        Response<GroupSelect> response = new Response<>();
        response.success(stService.getIfMember(uid));
        return response;
    }

    @GetMapping("/cards")
    @ResponseBody
    public Response<List<Card>> getGroupCards(Integer gid){
        Response<List<Card>> response = new Response<>();
        response.success(stService.getGroupCards(gid));
        return response;
    }



}
