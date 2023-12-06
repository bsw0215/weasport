package com.connect.weasport.service;

import com.connect.weasport.controller.member.MemberCreateDto;
import com.connect.weasport.domain.ApprovalStatus;
import com.connect.weasport.domain.Club;
import com.connect.weasport.domain.Member;
import com.connect.weasport.domain.User;
import com.connect.weasport.repository.ClubRepository;
import com.connect.weasport.repository.MemberRepository;
import com.connect.weasport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserRepository userRepository;
    private final ClubRepository clubRepository;

    @Transactional
    public Member join(MemberCreateDto request){

        User user = userRepository.findById(request.getUserId()).orElseThrow(()->{
            return new IllegalArgumentException("모임 신청 실패 : 사용자 아이디를 찾을 수 없습니다.");
        });
        Club club = clubRepository.findById(request.getClubId()).orElseThrow(()->{
            return new IllegalArgumentException("모임 신청 실패 : 모임 아이디를 찾을 수 없습니다.");
        });

        if (memberRepository.findByUserAndClub(user, club).isPresent()) {
            return null;
        }

        Member member = Member.builder().user(user).club(club).approvalStatus(ApprovalStatus.WAITING).build();
        return memberRepository.save(member);
    }

    @Transactional
    public List<Member> findByUser(int userId){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new IllegalArgumentException("사용자 아이디를 찾을 수 없습니다.");
        });

        List<Member> members = memberRepository.findAllByUser(user);
        return members;
    }

}
