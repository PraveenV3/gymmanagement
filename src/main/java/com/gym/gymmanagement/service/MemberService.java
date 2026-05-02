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
        // store: id,name,age,plan,username,password
        bw.write(member.getId()+","+member.getName()+","+member.getAge()+","+member.getPlan()+","+member.getUsername()+","+member.getPassword());
        bw.newLine();
        bw.close();
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
            // handle older entries that may not have username/password
            if (data.length >= 6) {
                list.add(new Member(data[0],data[1],data[2],data[3],data[4],data[5]));
            } else if (data.length == 4) {
                list.add(new Member(data[0],data[1],data[2],data[3]));
            }
        }
        br.close();
        return list;
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
                lines.add(member.getId()+","+member.getName()+","+member.getAge()+","+member.getPlan()+","+member.getUsername()+","+member.getPassword());
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
}