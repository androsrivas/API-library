package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.model.Member;
import com.bootcamp.api_library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAll();
    }

    @GetMapping("/members/{id}")
    public Optional<Member> getMemberById(@PathVariable UUID id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("/members")
    public void createMember(@RequestBody Member newMember) {
        memberService.addMember(newMember);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable UUID id, @RequestBody Member memberDetails) {
        try {
            Member member = memberService.updateMember(id, memberDetails);
            return new ResponseEntity<>(member, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(id);
    }
}
