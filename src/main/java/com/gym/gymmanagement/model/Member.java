package com.gym.gymmanagement.model;

public class Member {
    private String id;
    private String name;
    private String age;
    private String plan;
    private String username;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String emailAddress;
    private String gender;
    private String nicPassportNumber;
    private String address;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String joinDate;
    private String membershipStatus;
    private String profilePhotoPath;

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

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNicPassportNumber() { return nicPassportNumber; }
    public void setNicPassportNumber(String nicPassportNumber) { this.nicPassportNumber = nicPassportNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactNumber() { return emergencyContactNumber; }
    public void setEmergencyContactNumber(String emergencyContactNumber) { this.emergencyContactNumber = emergencyContactNumber; }

    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    public String getMembershipStatus() { return membershipStatus; }
    public void setMembershipStatus(String membershipStatus) { this.membershipStatus = membershipStatus; }

    public String getProfilePhotoPath() { return profilePhotoPath; }
    public void setProfilePhotoPath(String profilePhotoPath) { this.profilePhotoPath = profilePhotoPath; }
}