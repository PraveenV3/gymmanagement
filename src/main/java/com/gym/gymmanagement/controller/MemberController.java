package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.model.Member;
import com.gym.gymmanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Random;

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
        // Auto-generate a 4-digit ID (1000-9999)
        String generatedId = generateUniqueId();
        member.setId(generatedId);
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

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) throws Exception {
        // only allow access to logged-in users
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        Member member = service.getMemberByUsername(username);
        if (member == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", member);
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfile(Model model, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        Member member = service.getMemberByUsername(username);
        if (member == null) {
            return "redirect:/login";
        }
        model.addAttribute("member", member);
        return "edit-profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(Member member, HttpSession session) throws Exception {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        service.updateMember(member);
        return "redirect:/profile";
    }

    private String generateUniqueId() throws Exception {
        Random random = new Random();
        String generatedId;
        java.util.List<Member> existingMembers = service.getAllMembers();
        
        // Generate a unique 4-digit ID (1000-9999)
        do {
            int id = 1000 + random.nextInt(9000);
            generatedId = String.valueOf(id);
        } while (idExists(generatedId, existingMembers));
        
        return generatedId;
    }

    private boolean idExists(String id, java.util.List<Member> members) {
        for (Member m : members) {
            if (m.getId() != null && m.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}