package com.connect.weasport.controller.user;

import com.connect.weasport.controller.ResponseDto;
import com.connect.weasport.domain.Board;
import com.connect.weasport.domain.User;
import com.connect.weasport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/api/user/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody User user){
        userService.modify(id, user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}

