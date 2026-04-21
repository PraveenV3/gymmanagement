package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.model.Member;
import com.gym.gymmanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    MemberService service = new MemberService();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/saveMember")
    public String save(Member member) throws Exception {
        service.saveMember(member);
        return "redirect:/members";
    }

    @GetMapping("/members")
    public String members(Model model) throws Exception {
        model.addAttribute("members", service.getAllMembers());
        return "members";
    }
}