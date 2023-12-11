package com.connect.weasport.controller.board;

import com.connect.weasport.domain.Board;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSaveRequestDto {
    private String title;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();



    }


}
