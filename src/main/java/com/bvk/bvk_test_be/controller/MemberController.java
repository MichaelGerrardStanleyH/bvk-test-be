package com.bvk.bvk_test_be.controller;

import com.bvk.bvk_test_be.dto.MemberRequestDTO;
import com.bvk.bvk_test_be.dto.MemberResponseDTO;
import com.bvk.bvk_test_be.mapper.TransactionMapper;
import com.bvk.bvk_test_be.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getAllMember(){
        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionMapper.mapEntityListToDtoList(this.memberService.get(), MemberResponseDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMemberById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionMapper.mapEntityToDto(this.memberService.getById(id), MemberResponseDTO.class)
        );
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody MemberRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TransactionMapper.mapEntityToDto(this.memberService.create(dto), MemberResponseDTO.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMember(@PathVariable("id") Long id, @RequestBody MemberRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionMapper.mapEntityToDto(this.memberService.update(id, dto), MemberResponseDTO.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") Long id){
        this.memberService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}


