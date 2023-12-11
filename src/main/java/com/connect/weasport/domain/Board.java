package com.connect.weasport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import jakarta.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Board extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량데이터
    private String content;

    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) //Many = Board, User = One  한명의 유저가 여러 게시글 작성
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mapppedBy DB에 컬럼 생성 x
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<BoardReply> boardReplies;

}
