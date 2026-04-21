package com.gym.gymmanagement.service;

import com.gym.gymmanagement.model.Member;
import java.io.*;
import java.util.*;

public class MemberService {

    private final String FILE_PATH = "data/members.txt";

    public void saveMember(Member member) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true));
        bw.write(member.getId()+","+member.getName()+","+member.getAge()+","+member.getPlan());
        bw.newLine();
        bw.close();
    }

    public List<Member> getAllMembers() throws IOException {
        List<Member> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));

        String line;
        while((line = br.readLine()) != null){
            String[] data = line.split(",");
            list.add(new Member(data[0],data[1],data[2],data[3]));
        }
        br.close();
        return list;
    }
}