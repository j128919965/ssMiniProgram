package xyz.lizhaorong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lizhaorong.entity.Activity;
import xyz.lizhaorong.service.ActivityService;
import xyz.lizhaorong.service.MessageService;
import xyz.lizhaorong.util.support.Response;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @Autowired
    MessageService messageService;

    @GetMapping("/getActivities")
    public Response<List<Activity>> getNowActivities(){
        Response<List<Activity>> response = new Response<>();
        response.success(activityService.getNowActivities());
        return response;
    }

    @GetMapping("/{id}")
    public Response<Activity> getActivityById(@PathVariable("id") Integer id){
        Response<Activity> response = new Response<>();
        response.success(activityService.getActivity(id));
        return response;
    }

    @GetMapping("/ifSigned")
    public Response<Boolean> getHasSigned(Integer uid,Integer aid){
        Response<Boolean> response = new Response<>();
        response.success(activityService.getHasSigned(uid,aid));
        return response;
    }

    @PostMapping("/signup")
    public Response<Boolean> singup(Integer aid,Integer uid){
        activityService.insertActivityChoose(aid,uid);
        messageService.signUpActivity(uid);
        return Response.staticSuccess();
    }


}
