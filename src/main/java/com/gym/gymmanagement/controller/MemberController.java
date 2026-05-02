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
        // after registration redirect user to login page
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) throws Exception {
        for (Member m : service.getAllMembers()) {
            if (m.getUsername() != null && m.getUsername().equals(username)
                    && m.getPassword() != null && m.getPassword().equals(password)) {
                // successful login -> show members
                return "redirect:/members";
            }
        }
        // failed login -> back to login with error flag
        return "redirect:/login?error";
    }

    @GetMapping("/members")
    public String members(Model model) throws Exception {
        model.addAttribute("members", service.getAllMembers());
        return "members";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id, Model model) throws Exception {
        for (Member m : service.getAllMembers()) {
            if (m.getId() != null && m.getId().equals(id)) {
                model.addAttribute("member", m);
                break;
            }
        }
        return "edit";
    }

    @PostMapping("/updateMember")
    public String update(Member member) throws Exception {
        service.updateMember(member);
        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) throws Exception {
        service.deleteMember(id);
        return "redirect:/members";
    }
}