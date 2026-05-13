package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.model.Trainer;
import com.gym.gymmanagement.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class TrainerController {

    TrainerService service = new TrainerService();

    // View Trainer List
    @GetMapping("/trainers")
    public String viewTrainers(Model model, HttpSession session) throws Exception {
        // only allow access to logged-in users
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        model.addAttribute("trainers", service.getAllTrainers());
        return "trainers";
    }

    // Show Add Form
    @GetMapping("/addTrainer")
    public String addTrainerPage(HttpSession session) {
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "add-trainer";
    }

    // Save Trainer
    @PostMapping("/saveTrainer")
    public String saveTrainer(Trainer trainer, HttpSession session) throws Exception {
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        service.saveTrainer(trainer);
        return "redirect:/trainers";
    }

    // Delete Trainer
    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam String id, HttpSession session) throws Exception {
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        service.deleteTrainer(id);
        return "redirect:/trainers";
    }

    // Show Update Form
    @GetMapping("/editTrainer")
    public String editTrainer(@RequestParam String id, Model model, HttpSession session) throws Exception {
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        for (Trainer t : service.getAllTrainers()) {
            if (t.getId().equals(id)) {
                model.addAttribute("trainer", t);
                break;
            }
        }
        return "edit-trainer";
    }

    // Update Trainer
    @PostMapping("/updateTrainer")
    public String updateTrainer(Trainer trainer, HttpSession session) throws Exception {
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        service.updateTrainer(trainer);
        return "redirect:/trainers";
    }
}
