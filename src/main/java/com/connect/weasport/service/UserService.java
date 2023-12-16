package com.connect.weasport.service;

import com.connect.weasport.domain.User;
import com.connect.weasport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void modify(int id, User requestUser){
        User user = userRepository.findById(id)
          .orElseThrow(()->{
                return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
          });

        user.updateUser(requestUser.getName()
            , requestUser.getEmail()
            , requestUser.getAddress());
    }

    @Transactional(readOnly = true)
    public User userDetail(int id){
        return userRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("수정 실패 : 아이디를 찾을 수 없습니다.");
                });
    }

}
