package com.connect.weasport.controller.member;

import com.connect.weasport.domain.ApprovalStatus;
import com.connect.weasport.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private Long clubId;
    private int userId;
    private String name;
    private ApprovalStatus approvalStatus;

    public MemberResponseDto(Member member) {
        BeanUtils.copyProperties(member, this);
        this.clubId = member.getClub().getId();
        this.userId = member.getUser().getId();
        this.name = member.getUser().getName();
    }
}

