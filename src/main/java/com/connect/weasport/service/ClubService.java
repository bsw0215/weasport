package com.connect.weasport.service;


import com.connect.weasport.controller.club.ClubCreateDto;
import com.connect.weasport.domain.Club;
import com.connect.weasport.domain.ClubStatus;
import com.connect.weasport.domain.Member;
import com.connect.weasport.domain.User;
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
        System.out.println("Service시작날짜:" + startDateString);
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
                .clubStatus(ClubStatus.ACTIVE)
                .build();
        return clubRepository.save(newClub);
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







}
