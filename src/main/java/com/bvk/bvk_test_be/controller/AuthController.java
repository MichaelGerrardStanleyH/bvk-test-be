package com.bvk.bvk_test_be.controller;

import com.bvk.bvk_test_be.dto.MemberRequestDTO;
import com.bvk.bvk_test_be.dto.SignInDTO;
import com.bvk.bvk_test_be.dto.SignUpDTO;
import com.bvk.bvk_test_be.dto.SignUpResponseDTO;
import com.bvk.bvk_test_be.entity.Member;
import com.bvk.bvk_test_be.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> singup(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("position") String position,
            @RequestParam(value = "reportsToId", required = false) Long reportsToId,
            @RequestParam("image") MultipartFile image
    ) throws IOException {

        SignUpDTO dto = SignUpDTO.builder()
                .name(name)
                .email(email)
                .password(password)
                .position(position)
                .reportsTo(reportsToId)
                .image(image)
                .organizationId(1L)
                .build();

        this.authService.signUp(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Success Create User");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SignInDTO dto){
        Long userId = this.authService.signIn(dto);

        return ResponseEntity.status(HttpStatus.OK).body(new SignUpResponseDTO(HttpStatus.OK.value(), userId));
    }

}


