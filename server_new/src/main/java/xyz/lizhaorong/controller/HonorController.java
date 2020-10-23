package xyz.lizhaorong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.lizhaorong.entity.Honor;
import xyz.lizhaorong.service.HonorService;
import xyz.lizhaorong.util.support.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/honor")
public class HonorController {

    @Autowired
    HonorService honorService;

    @GetMapping
    public Response<List<Map>> getHonors(Integer uid){
        Response<List<Map>> response = new Response<>();
        response.success(honorService.getHonors(uid));
        return response;
    }

}
