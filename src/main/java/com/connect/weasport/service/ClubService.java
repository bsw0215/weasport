package com.connect.weasport.service;


import com.connect.weasport.controller.club.ClubCreateDto;
import com.connect.weasport.controller.club.ClubReplySaveDto;
import com.connect.weasport.domain.*;
import com.connect.weasport.repository.ClubRepository;
import com.connect.weasport.repository.MemberRepository;
import com.connect.weasport.repository.ReplyRepository;
import com.connect.weasport.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Club createClub(ClubCreateDto requestDto){
        Club club = requestDto.toEntity();
        String startDateString = requestDto.getStartDate();
        String endDateString = requestDto.getEndDate();
        LocalDate startDate = LocalDate.parse(startDateString, DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(endDateString, DateTimeFormatter.ISO_DATE);
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(()->{
                    return new IllegalArgumentException("클럽 생성 실패 : 사용자 아이디를 찾을 수 없습니다.");
                });
        Club newClub = Club.builder()
                .user(user)
                .title(club.getTitle())
                .contents(club.getContents())
                .minPerson(club.getMinPerson())
                .maxPerson(club.getMaxPerson())
                .startDate(startDate)
                .endDate(endDate)
                .description(club.getDescription())
                .sido(club.getSido())
                .si(club.getSi())
                .address(club.getAddress())
                .weather((club.getWeather()))
                .clubStatus(ClubStatus.ACTIVE)
                .build();
        return clubRepository.save(newClub);
    }

    public Club clubDetail(long clubId){
        return clubRepository.findById(clubId).orElseThrow(()->{
            return new IllegalArgumentException("클럽을 찾을 수 없습니다.");
        });
    }

    private void changeClubStatus(Club club) {
        if (LocalDate.now().isAfter(club.getEndDate())) {
            club.changeStatus(ClubStatus.EXPIRED);
        } else club.changeStatus(ClubStatus.ACTIVE);
    }

    private void changeAllClubStatus() {
        List<Club> clubList = clubRepository.findAll();
        for (Club club : clubList) {
            changeClubStatus(club);
        }
    }

    public List<Club> findClubByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("모임 찾기 : 사용자 아이디를 찾을 수 없습니다.");
                });
        List<Club> clubs = clubRepository.findAllByUser(user);

        return clubs;
    }


    public List<Club> findAllClubs(){
        changeAllClubStatus();

        List<Club> clubs = clubRepository.findAll();

        if (!clubs.isEmpty()) {
            clubs.removeIf(club -> club.getClubStatus().equals(ClubStatus.EXPIRED));
        }

        return clubs;
    }

    public List<Club> findClubByStatus(int userId, ApprovalStatus approvalStatus){
        User user = userRepository.findById(userId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("모임 찾기 : 사용자 아이디를 찾을 수 없습니다.");
                });

        List<Member> memberList = memberRepository.findByUserAndApprovalStatus(user, approvalStatus);

        List<Club> clubList = new ArrayList<>();

        for(Member member:memberList){
            clubList.add(member.getClub());
        }
        return clubList;
    }

    @Transactional
    public void replyWrite(ClubReplySaveDto clubReplySaveDto) {
        Reply reply = clubReplySaveDto.toEntity();
        int userId = clubReplySaveDto.getUserId();
        Long clubId = clubReplySaveDto.getClubId();

        final Reply newReply = convertToReply(reply, userId, clubId);
        replyRepository.save(newReply);
    }

    private Reply convertToReply(final Reply comment, final int userId, final Long clubId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 작성 : 사용자 아이디를 찾을 수 없습니다.");
                });
        final Club club = clubRepository.findById(clubId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 작성 : 모임을 찾을 수 없습니다.");
                });

        return Reply.builder()
                .contents(comment.getContents())
                .club(club)
                .user(user)
                .build();
    }

    @Transactional
    public void replyDelete(long replyId){
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 삭제 실패: 아이디를 찾을 수 없습니다.");
                });
        replyRepository.delete(reply);
    }

    @Transactional
    public void delete(long id){
        clubRepository.deleteById(id);
    }

    @Transactional
    public void modify(long id, Club requestClub) {
        Club club = clubRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        changeClubStatus(club);
        club.updateClub(requestClub.getDescription()
                ,requestClub.getTitle()
                ,requestClub.getContents()
                ,requestClub.getStartDate()
                ,requestClub.getEndDate()
                ,requestClub.getMinPerson()
                ,requestClub.getMaxPerson()
                ,requestClub.getSido()
                ,requestClub.getSi()
                ,requestClub.getAddress()
                ,requestClub.getWeather()
        );

    }
}
