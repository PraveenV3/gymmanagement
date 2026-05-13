package com.gym.gymmanagement.service;

import com.gym.gymmanagement.model.Member;
import java.io.*;
import java.util.*;

public class MemberService {

    private final String FILE_PATH = "data/members.txt";

    public void saveMember(Member member) throws IOException {
        File file = new File(FILE_PATH);
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        // store: id,name,age,plan,username,password,fullName,dateOfBirth,phoneNumber,emailAddress,gender,nicPassportNumber,address,emergencyContactName,emergencyContactNumber,joinDate,membershipStatus,profilePhotoPath
        bw.write(member.getId()+","+
                member.getName()+","+
                member.getAge()+","+
                member.getPlan()+","+
                member.getUsername()+","+
                member.getPassword()+","+
                nullToEmpty(member.getFullName())+","+
                nullToEmpty(member.getDateOfBirth())+","+
                nullToEmpty(member.getPhoneNumber())+","+
                nullToEmpty(member.getEmailAddress())+","+
                nullToEmpty(member.getGender())+","+
                nullToEmpty(member.getNicPassportNumber())+","+
                nullToEmpty(member.getAddress())+","+
                nullToEmpty(member.getEmergencyContactName())+","+
                nullToEmpty(member.getEmergencyContactNumber())+","+
                nullToEmpty(member.getJoinDate())+","+
                nullToEmpty(member.getMembershipStatus())+","+
                nullToEmpty(member.getProfilePhotoPath()));
        bw.newLine();
        bw.close();
    }

    private String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public List<Member> getAllMembers() throws IOException {
        List<Member> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return list;
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while((line = br.readLine()) != null){
            String[] data = line.split(",");
            // handle various entry formats
            Member member = null;
            if (data.length >= 18) {
                // new format with all fields
                member = new Member(data[0],data[1],data[2],data[3],data[4],data[5]);
                member.setFullName(emptyToNull(data[6]));
                member.setDateOfBirth(emptyToNull(data[7]));
                member.setPhoneNumber(emptyToNull(data[8]));
                member.setEmailAddress(emptyToNull(data[9]));
                member.setGender(emptyToNull(data[10]));
                member.setNicPassportNumber(emptyToNull(data[11]));
                member.setAddress(emptyToNull(data[12]));
                member.setEmergencyContactName(emptyToNull(data[13]));
                member.setEmergencyContactNumber(emptyToNull(data[14]));
                member.setJoinDate(emptyToNull(data[15]));
                member.setMembershipStatus(emptyToNull(data[16]));
                member.setProfilePhotoPath(emptyToNull(data[17]));
            } else if (data.length >= 6) {
                // old format with just basic fields
                member = new Member(data[0],data[1],data[2],data[3],data[4],data[5]);
            } else if (data.length == 4) {
                // oldest format
                member = new Member(data[0],data[1],data[2],data[3]);
            }
            if (member != null) {
                list.add(member);
            }
        }
        br.close();
        return list;
    }

    private String emptyToNull(String str) {
        return str == null || str.trim().isEmpty() ? null : str;
    }

    public void updateMember(Member member) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        List<String> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length > 0 && data[0].equals(member.getId())) {
                lines.add(member.getId()+","+
                        member.getName()+","+
                        member.getAge()+","+
                        member.getPlan()+","+
                        member.getUsername()+","+
                        member.getPassword()+","+
                        nullToEmpty(member.getFullName())+","+
                        nullToEmpty(member.getDateOfBirth())+","+
                        nullToEmpty(member.getPhoneNumber())+","+
                        nullToEmpty(member.getEmailAddress())+","+
                        nullToEmpty(member.getGender())+","+
                        nullToEmpty(member.getNicPassportNumber())+","+
                        nullToEmpty(member.getAddress())+","+
                        nullToEmpty(member.getEmergencyContactName())+","+
                        nullToEmpty(member.getEmergencyContactNumber())+","+
                        nullToEmpty(member.getJoinDate())+","+
                        nullToEmpty(member.getMembershipStatus())+","+
                        nullToEmpty(member.getProfilePhotoPath()));
            } else {
                lines.add(line);
            }
        }
        br.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        for (String l : lines) {
            bw.write(l);
            bw.newLine();
        }
        bw.close();
    }

    public void deleteMember(String id) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        File temp = new File(file.getAbsolutePath() + ".tmp");
        BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length > 0 && data[0].equals(id)) {
                continue; // skip deleted
            }
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
        // replace original file with temp
        if (!file.delete()) {
            // if delete fails, try to overwrite
        }
        temp.renameTo(file);
    }

    public Member getMemberByUsername(String username) throws IOException {
        for (Member m : getAllMembers()) {
            if (m.getUsername() != null && m.getUsername().equals(username)) {
                return m;
            }
        }
        return null;
    }
}