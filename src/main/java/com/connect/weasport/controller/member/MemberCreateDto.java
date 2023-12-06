package com.connect.weasport.controller.member;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberCreateDto {
    private int userId;
    private Long clubId;
}
