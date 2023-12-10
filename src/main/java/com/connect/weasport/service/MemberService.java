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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
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

    //멤버의 승인 상태의 따른 리스트
    public List<Member> statusList(Club club, String approvalStatus){
        ApprovalStatus status;
        if (!approvalStatus.equals(ApprovalStatus.CONFIRMED.toString())) {
            status = ApprovalStatus.WAITING;
        } else {
            status = ApprovalStatus.CONFIRMED;
        }
        return memberRepository.findByClubAndApprovalStatus(club, status);
    }


    //유저가 생성한 모임들의 멤버 승인 요청 조회
    public List<Member> getMemberList(int userId, String approvalStatus) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("사용자 아이디를 찾을 수 없습니다.");
                });
        List<Club> clubs = clubRepository.findAllByUser(user);

        List<Member> memberList = new ArrayList<>();
        for(Club club:clubs){
            memberList.addAll(statusList(club, approvalStatus));
        }

        return memberList;
    }

    @Transactional
    public void approveMember(long id){

        Member member = memberRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("멤버 아이디를 찾을 수 없습니다.");
                });

        member.changeStatus(ApprovalStatus.CONFIRMED);
        System.out.println("바꾸기 완료");

    }

    @Transactional
    public void deleteMember(long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("멤버 아이디를 찾을 수 없습니다.");
                });
        memberRepository.delete(member);
    }

}
