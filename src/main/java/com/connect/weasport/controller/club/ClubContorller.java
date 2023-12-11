package com.connect.weasport.controller.club;

import com.connect.weasport.domain.Club;
import com.connect.weasport.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClubContorller {

    @Autowired
    private ClubService clubService;


    @GetMapping("/tables")
    public String clubs() { return "tables"; }

    @GetMapping("/create")
    public String clubCreate(){
        return "create";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable long clubId, Model model){
        Club club = clubService.clubDetail(clubId);
        model.addAttribute("club",club);
        return "clubView";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable long clubId, Model model){
        model.addAttribute("club", clubService.clubDetail(clubId));
        return "update-club";
    }



}
