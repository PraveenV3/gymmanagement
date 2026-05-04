package com.gym.gymmanagement.service;

import com.gym.gymmanagement.model.Trainer;
import java.io.*;
import java.util.*;

public class TrainerService {

    private final String FILE_PATH = "data/trainers.txt";

    // CREATE
    public void saveTrainer(Trainer trainer) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(trainer.getId() + "," +
                trainer.getName() + "," +
                trainer.getSpecialty() + "," +
                trainer.getExperience());
        bw.newLine();
        bw.close();
    }

    // READ
    public List<Trainer> getAllTrainers() throws IOException {
        List<Trainer> list = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            list.add(new Trainer(data[0], data[1], data[2], data[3]));
        }
        br.close();
        return list;
    }

    // DELETE
    public void deleteTrainer(String id) throws IOException {
        List<Trainer> trainers = getAllTrainers();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));

        for (Trainer t : trainers) {
            if (!t.getId().equals(id)) {
                bw.write(t.getId() + "," +
                        t.getName() + "," +
                        t.getSpecialty() + "," +
                        t.getExperience());
                bw.newLine();
            }
        }
        bw.close();
    }

    // UPDATE
    public void updateTrainer(Trainer updatedTrainer) throws IOException {
        List<Trainer> trainers = getAllTrainers();
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));

        for (Trainer t : trainers) {
            if (t.getId().equals(updatedTrainer.getId())) {
                bw.write(updatedTrainer.getId() + "," +
                        updatedTrainer.getName() + "," +
                        updatedTrainer.getSpecialty() + "," +
                        updatedTrainer.getExperience());
            } else {
                bw.write(t.getId() + "," +
                        t.getName() + "," +
                        t.getSpecialty() + "," +
                        t.getExperience());
            }
            bw.newLine();
        }
        bw.close();
    }
}
