package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.model.Trainer;
import com.gym.gymmanagement.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrainerController {

    TrainerService service = new TrainerService();

    // View Trainer List
    @GetMapping("/trainers")
    public String viewTrainers(Model model) throws Exception {
        model.addAttribute("trainers", service.getAllTrainers());
        return "trainers";
    }

    // Show Add Form
    @GetMapping("/addTrainer")
    public String addTrainerPage() {
        return "add-trainer";
    }

    // Save Trainer
    @PostMapping("/saveTrainer")
    public String saveTrainer(Trainer trainer) throws Exception {
        service.saveTrainer(trainer);
        return "redirect:/trainers";
    }

    // Delete Trainer
    @GetMapping("/deleteTrainer")
    public String deleteTrainer(@RequestParam String id) throws Exception {
        service.deleteTrainer(id);
        return "redirect:/trainers";
    }

    // Show Update Form
    @GetMapping("/editTrainer")
    public String editTrainer(@RequestParam String id, Model model) throws Exception {
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
    public String updateTrainer(Trainer trainer) throws Exception {
        service.updateTrainer(trainer);
        return "redirect:/trainers";
    }
}
