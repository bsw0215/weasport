package com.connect.weasport.repository;

import com.connect.weasport.domain.ApprovalStatus;
import com.connect.weasport.domain.Club;
import com.connect.weasport.domain.Member;
import com.connect.weasport.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserAndClub(User user, Club club);

    Page<Member> findByClubAndApprovalStatus(Club club, ApprovalStatus approvalStatus, Pageable pageable);

    Page<Member> findAllByUser(User user, Pageable pageable);

    List<Member> findAllByUser(User user);

    void deleteAllByClub(Club club);
}
