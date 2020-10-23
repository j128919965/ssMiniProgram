package xyz.lizhaorong.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.lizhaorong.entity.st.Card;
import xyz.lizhaorong.entity.st.Group;
import xyz.lizhaorong.entity.st.GroupSelect;
import xyz.lizhaorong.service.StService;
import xyz.lizhaorong.util.support.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/st")
@Slf4j
public class StController {

    @Autowired
    StService stService;

    @GetMapping("/console")
    public String initPage(){
        return "init";
    }

    @PostMapping("/clear")
    @ResponseBody
    public Response<Boolean> clearSt(){
        stService.clear();
        return Response.staticSuccess();
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
        return response.success(stService.scanCard(cid,gid));
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

    @GetMapping("/points")
    @ResponseBody
    public Response<List<Group>> getPoints(){
        Set<Integer> gids = stService.getAllGroups();
        List<Group> groups = new ArrayList<>(gids.size());
        for (Integer gid : gids) {
            Group g = new Group();
            g.setId(gid);
            g.setPoint(stService.calc(gid));
            groups.add(g);
        }
        return new Response<List<Group>>().success(groups);
    }

    @GetMapping("/state")
    @ResponseBody
    public Response<Integer> getStStuatus(){
        return new Response<Integer>().success(stService.getState());
    }


    @PostMapping("/stop")
    @ResponseBody
    public Response<List<Group>> stopSt(){
        stService.stopSt();
        return getPoints();
    }


}
