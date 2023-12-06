package com.connect.weasport.domain;

import lombok.Builder;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = EAGER)
    private User user;

    @JoinColumn(name = "club_id")
    @ManyToOne(fetch = EAGER)
    private Club club;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus; //승인상태 [WAITING, CONFIRMED]

    @Builder
    public Member(User user, Club club, ApprovalStatus approvalStatus) {
        this.user = user;
        this.club = club;
        this.approvalStatus = approvalStatus;
    }

    public void changeStatus(ApprovalStatus approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
