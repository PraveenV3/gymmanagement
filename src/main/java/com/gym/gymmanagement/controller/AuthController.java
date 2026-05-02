package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.model.Member;
import com.gym.gymmanagement.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/register";
    }

    // ── Registration ──────────────────────────────────────────────────────────

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute Member member,
                                  RedirectAttributes redirectAttributes) {
        boolean registered = memberService.register(member);
        if (!registered) {
            redirectAttributes.addFlashAttribute("error", "Username already taken. Please choose a different username.");
            return "redirect:/register";
        }
        redirectAttributes.addFlashAttribute("success", "Registration successful! Please log in.");
        return "redirect:/login";
    }

    // ── Login ─────────────────────────────────────────────────────────────────

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        if (memberService.login(username, password)) {
            return "redirect:/dashboard?username=" + username;
        }
        redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
        return "redirect:/login";
    }

    // ── Dashboard ─────────────────────────────────────────────────────────────

    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam(required = false) String username, Model model) {
        model.addAttribute("username", username);
        return "dashboard";
    }
}
