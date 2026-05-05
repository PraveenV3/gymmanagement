package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.model.Member;
import com.gym.gymmanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

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
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session) throws Exception {
        for (Member m : service.getAllMembers()) {
            if (m.getUsername() != null && m.getUsername().equals(username)
                    && m.getPassword() != null && m.getPassword().equals(password)) {
                // successful login -> store username in session and show members
                session.setAttribute("username", username);
                return "redirect:/members";
            }
        }
        // failed login -> back to login with error flag
        return "redirect:/login?error";
    }

    @GetMapping("/members")
    public String members(Model model, HttpSession session) throws Exception {
        // only allow access to logged-in users
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("members", service.getAllMembers());
        return "members";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable String id, Model model, HttpSession session) throws Exception {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        for (Member m : service.getAllMembers()) {
            if (m.getId() != null && m.getId().equals(id)) {
                model.addAttribute("member", m);
                break;
            }
        }
        return "edit";
    }

    @PostMapping("/updateMember")
    public String update(Member member, HttpSession session) throws Exception {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        service.updateMember(member);
        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id, HttpSession session) throws Exception {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        service.deleteMember(id);
        return "redirect:/members";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}