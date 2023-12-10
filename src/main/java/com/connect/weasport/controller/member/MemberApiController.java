package com.connect.weasport.controller.member;

import com.connect.weasport.controller.ResponseDto;
import com.connect.weasport.domain.Member;
import com.connect.weasport.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;


    @PostMapping("/api/member")
    public ResponseDto<Integer> memberJoin(@RequestBody MemberCreateDto memberCreateDto){

        Member member = memberService.join(memberCreateDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }

    @PutMapping("api/club/{id}")
    public ResponseDto<Integer> approve(@PathVariable long id){
        System.out.println("id=" + id);
        memberService.approveMember(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("api/club/{id}")
    public ResponseDto<Integer> memberDelete(@PathVariable long id){
        memberService.deleteMember(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
