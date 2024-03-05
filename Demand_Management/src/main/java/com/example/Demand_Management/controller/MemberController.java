package com.example.Demand_Management.controller;

import com.example.Demand_Management.Models.Member;
import com.example.Demand_Management.repo.Member_repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MemberController {
    @Autowired
    Member_repo MemberRepository;

    @GetMapping("/getAllMembers")
    public ResponseEntity<List<Member>> getAllBooks() {
        try {
            List<Member> MemberList = new ArrayList<>();
            MemberRepository.findAll().forEach(MemberList::add);

            if (MemberList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(MemberList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMemberById/{id}")
    public ResponseEntity<Member> getBookById(@PathVariable Long id) {
        Optional<Member> memberObj = MemberRepository.findById(id);
        if (memberObj.isPresent()) {
            return new ResponseEntity<>(memberObj.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addMember")
    public ResponseEntity<Member> addBook(@RequestBody Member member) {
        try {
            Member memberObj = MemberRepository.save(member);
            return new ResponseEntity<>(memberObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/updateMembers/{id}")
    public ResponseEntity<Member> updateDemand(@PathVariable Long id, @RequestBody Member member) {
        try {
            Optional<Member> memberData = MemberRepository.findById(id);
            if (memberData.isPresent()) {
                Member updatedMemberData = memberData.get();
                updatedMemberData.setFirst_name(member.getFirst_name());
                updatedMemberData.setLast_name(member.getLast_name());

                Member memberObj = MemberRepository.save(updatedMemberData);
                return new ResponseEntity<>(memberObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMemberById/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Long id) {
        try {
            MemberRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllMembers")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            MemberRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
