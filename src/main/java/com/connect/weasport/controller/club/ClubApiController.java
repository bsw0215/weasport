package com.connect.weasport.controller.club;

import com.connect.weasport.config.PrincipalDetails;
import com.connect.weasport.controller.ResponseDto;
import com.connect.weasport.controller.member.MemberCreateDto;
import com.connect.weasport.domain.*;
import com.connect.weasport.repository.ClubRepository;
import com.connect.weasport.repository.MemberRepository;
import com.connect.weasport.repository.UserRepository;
import com.connect.weasport.service.ClubService;
import com.connect.weasport.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubApiController {

    private final ClubService clubService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;




    @GetMapping("/api/tables")
    public List<Club> clubFind(){
        return clubService.findAllClubs();

    }

    @GetMapping("/api/myTable/{id}")
    public List<Club> myClubFind(@PathVariable("id") int id){

        List<Member> members = memberService.findByUser(id);

        List<Club> clubs = new ArrayList<>();

        for(Member m : members){
            clubs.add(m.getClub());
        }

        return clubs;


    }

    @PostMapping("/api/club")
    public ResponseDto<Integer> createClub(@RequestBody ClubCreateDto clubCreateDto, @AuthenticationPrincipal PrincipalDetails principal){
        clubCreateDto.setUserId(principal.getUser().getId());
        Club club = clubService.createClub(clubCreateDto);
        Member member = Member.builder().user(principal.getUser()).club(club).approvalStatus(ApprovalStatus.CONFIRMED).build();
        memberRepository.save(member);

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/club/{id}")
    public ResponseDto<Integer> deleteClub(@PathVariable long id){
        clubService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("api/reply")
    public ResponseDto<Integer> replySave(@RequestBody ClubReplySaveDto clubReplySaveDto) {
        clubService.replyWrite(clubReplySaveDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


}
