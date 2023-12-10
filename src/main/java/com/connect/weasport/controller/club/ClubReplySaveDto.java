package com.connect.weasport.controller.club;

import com.connect.weasport.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubReplySaveDto {
    private int userId;
    private long clubId;
    private String contents;

    public Reply toEntity(){
        return Reply.builder()
                .contents(contents)
                .build();
    }
}
