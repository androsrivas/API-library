package com.bootcamp.api_library.controller;

import com.bootcamp.api_library.model.Member;
import com.bootcamp.api_library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity create(@RequestBody Member newMember) {
        memberService.addMember(newMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMember);
    }

    @GetMapping("/members")
    public List<Member> getAll() {
        return memberService.getAll();
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getById(@PathVariable UUID id) {
        Member member = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PutMapping("/members/{id}")
    public ResponseEntity<Member> update(@PathVariable UUID id, @RequestBody Member memberDetails) {
        Member member = memberService.updateMember(id, memberDetails);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
