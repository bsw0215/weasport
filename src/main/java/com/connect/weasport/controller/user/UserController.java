package com.connect.weasport.controller.user;

import com.connect.weasport.domain.ApprovalStatus;
import com.connect.weasport.domain.Club;
import com.connect.weasport.domain.Member;
import com.connect.weasport.domain.User;
import com.connect.weasport.repository.UserRepository;
import com.connect.weasport.service.ClubService;
import com.connect.weasport.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private ClubService clubService;

    @Autowired MemberService memberService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String home() { return "tables"; }

//대시보드 페이지
    @GetMapping("/index/{userId}")
    public String index(@PathVariable int userId, Model model) {
        List<Club> clubs = clubService.findClubByUserId(userId);
        model.addAttribute("clubs", clubs);

        List<Member> mClubs = memberService.findByUser(userId);
        int progress = 0;
        int scheduled = 0;
        for(Member m : mClubs){
            if(m.getClub().getStartDate().isAfter(LocalDate.now())){
                scheduled++;
            }else{
                progress++;
            }
        }



        List<Member> memberList = memberService.getMemberList(userId, "WAITING");
        List<Club> waitList = new ArrayList<>();
        int waiting = memberList.size();
        for(Member m : memberList){
            waitList.add(m.getClub());
        }

        List<Club> waitClubs = clubService.findClubByStatus(userId, ApprovalStatus.WAITING);

        model.addAttribute("waiting",waiting);
        model.addAttribute("progress",progress);
        model.addAttribute("scheduled",scheduled);
        model.addAttribute("waitList", waitList);
        model.addAttribute("waitClubs", waitClubs);

        return "index";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String join() {
        return "register";
    }

    @PostMapping("/joinProc")
    public String joinProc(User user) {
        System.out.println("회원가입 진행 : " + user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "redirect:/login";
    }


}
