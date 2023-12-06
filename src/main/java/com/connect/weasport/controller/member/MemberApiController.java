package com.connect.weasport.controller.member;

import com.connect.weasport.controller.ResponseDto;
import com.connect.weasport.domain.Member;
import com.connect.weasport.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping
    public ResponseDto<Integer> memberJoin(@RequestBody MemberCreateDto memberCreateDto){

        Member member = memberService.join(memberCreateDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);


    }


}
