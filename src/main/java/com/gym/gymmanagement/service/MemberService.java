package com.gym.gymmanagement.service;

import com.gym.gymmanagement.model.Member;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MemberService {

    private final Map<String, Member> members = new HashMap<>();

    public boolean register(Member member) {
        if (members.containsKey(member.getUsername())) {
            return false;
        }
        members.put(member.getUsername(), member);
        return true;
    }

    public boolean login(String username, String password) {
        Member member = members.get(username);
        return member != null && member.getPassword().equals(password);
    }
}
