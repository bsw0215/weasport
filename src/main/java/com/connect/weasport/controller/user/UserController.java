package com.connect.weasport.controller.user;

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
    public String home() { return "home"; }


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
        model.addAttribute("progress",progress);
        model.addAttribute("scheduled",scheduled);

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
        return "redirect:/";
    }
}
