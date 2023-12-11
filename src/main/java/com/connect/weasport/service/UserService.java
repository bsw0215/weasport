package com.connect.weasport.service;

import com.connect.weasport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public HashMap<String, Boolean> usernameOverlap(String username) {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("result", userRepository.existsByUsername(username));
        return map;
    }

}
