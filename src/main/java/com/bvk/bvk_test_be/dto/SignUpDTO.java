package com.bvk.bvk_test_be.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {

    private String name;

    private String email;

    private String password;

    private MultipartFile image;

    private String position;

    private Long organizationId;

    private Long reportsTo;

}
