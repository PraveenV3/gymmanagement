package com.gym.gymmanagement.model;

public class Trainer {

    private String id;
    private String name;
    private String specialty;
    private String experience;

    public Trainer() {}

    public Trainer(String id, String name, String specialty, String experience) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.experience = experience;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getExperience() { return experience; }
    public void setExperience(String experience) { this.experience = experience; }
}
