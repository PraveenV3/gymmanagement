package com.gym.gymmanagement.model;

public class Member {
    private String id;
    private String name;
    private String age;
    private String plan;
    private String username;
    private String password;

    public Member() {}

    public Member(String id, String name, String age, String plan) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.plan = plan;
    }

    public Member(String id, String name, String age, String plan, String username, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.plan = plan;
        this.username = username;
        this.password = password;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}