package com.bootcamp.api_library.service;

import com.bootcamp.api_library.exceptions.ResourceNotFoundException;
import com.bootcamp.api_library.model.Member;
import com.bootcamp.api_library.respository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAll() {
        return memberRepository.findAll();
    }

    public Member getMemberById(UUID id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));
    }

    public void addMember(Member newMember) {
        memberRepository.save(newMember);
    }

    public void deleteMember(UUID id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found."));
        memberRepository.delete(member);
    }

    public Member updateMember(UUID id, Member memberDetails) {
        Optional<Member> foundMember = memberRepository.findById(id);

        if(foundMember.isPresent()) {
            Member member = foundMember.get();
            member.setFullName(memberDetails.getFullName());
            member.setEmail(memberDetails.getEmail());
            member.setPassword(memberDetails.getPassword());

            return memberRepository.save(member);
        }

        throw new ResourceNotFoundException("User with id " + memberDetails.getId() + " not found.");
    }

}
