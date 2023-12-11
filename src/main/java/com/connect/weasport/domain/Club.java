package com.connect.weasport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@ToString(exclude = {"replyList", "memberList"})
@Getter
public class Club extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = EAGER)
    private User user;

    @OneToMany(mappedBy = "club", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"club"})
    @OrderBy("id desc")
    private final List<Reply> replyList = new ArrayList<>();

    @OneToMany(mappedBy = "club", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"club"})
    private final List<Member> memberList = new ArrayList<>();

    @Column(length = 2000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private int minPerson;

    @Column(nullable = false)
    private int maxPerson;

    private String sido;

    private String si;

    private String address;

    private String weather;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClubStatus clubStatus; // [ACTIVE, EXPIRE]

    @Builder
    public Club(Long id, User user, String description, String title, String contents,
                LocalDate startDate, LocalDate endDate, int minPerson,
                int maxPerson, String sido, String si, String address, String weather, ClubStatus clubStatus) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minPerson = minPerson;
        this.maxPerson = maxPerson;
        this.sido = sido;
        this.si = si;
        this.address = address;
        this.weather = weather;
        this.clubStatus = clubStatus;
    }

    public void updateClub(String description, String title, String contents,
                LocalDate startDate, LocalDate endDate, int minPerson,
                int maxPerson, String sido, String si, String address, String weather) {
        this.description = description;
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
        this.minPerson = minPerson;
        this.maxPerson = maxPerson;
        this.si = si;
        this.sido = sido;
        this.address = address;
        this.weather = weather;
    }

    public void changeStatus(ClubStatus clubstatus) {
        this.clubStatus = clubstatus;
    }


}
