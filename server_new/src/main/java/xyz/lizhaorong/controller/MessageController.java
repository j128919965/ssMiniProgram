package xyz.lizhaorong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.lizhaorong.entity.Message;
import xyz.lizhaorong.service.MessageService;
import xyz.lizhaorong.util.support.Response;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public Response<List<Message>> getMessages(Integer uid){
        Response<List<Message>> response = new Response<>();
        response.success(messageService.getAllMessageByUid(uid));
        return response;
    }

    @GetMapping("/notReadCount")
    public Response<Integer> getNotReadCount(Integer uid) {
        Response<Integer> response = new Response<>();
        response.success(messageService.getNotReadCount(uid));
        return response;
    }
    @PostMapping("/readMsg/{id}")
    public Response<Boolean> readMsg(@PathVariable("id") Integer id){
        messageService.readMsg(id);
        return Response.staticSuccess();
    }

}
