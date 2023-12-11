package com.connect.weasport.controller.user;

import com.connect.weasport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    @PostMapping("/api/user/usernameRegister")
    public String check(@RequestBody Map<String, String> requestData){
        String username = requestData.get("username");
        if(userService.usernameOverlap(username).get("result")){
            return "1";
        }else{
            return "0";
        }

    }

}

