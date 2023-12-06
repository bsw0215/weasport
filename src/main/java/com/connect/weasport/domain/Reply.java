package com.connect.weasport.domain;

import lombok.*;

import jakarta.persistence.*;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Reply extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "club_id")
    @ManyToOne(fetch = EAGER)
    private Club club;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = EAGER)
    private User user;

    @Column(length = 500, nullable = false)
    private String contents;

    public void changeReply(String newReply) {
        this.contents = newReply;
    }

    @Builder
    public Reply(Club club, User user, String contents) {
        this.club = club;
        this.user = user;
        this.contents = contents;
    }

}
