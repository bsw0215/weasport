package com.connect.weasport.controller.club;

import com.connect.weasport.domain.Club;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClubCreateDto {

    private int userId;
    private String title;
    private String contents;
    private int minPerson;
    private int maxPerson;
    private String startDate;
    private String endDate;
    private String description;
    private String sido;
    private String si;
    private String address;
    private String weather;

    public Club toEntity() {
        return Club.builder()
                .title(title)
                .contents(contents)
                .minPerson(minPerson)
                .maxPerson(maxPerson)
                .description(description)
                .sido(sido)
                .si(si)
                .address(address)
                .weather(weather)
                .build();
    }
}
