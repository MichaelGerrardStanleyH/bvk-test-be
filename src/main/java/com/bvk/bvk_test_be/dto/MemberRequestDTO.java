package com.bvk.bvk_test_be.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestDTO {

    private String name;

    private String position;

    private Long reportsToId;

    private Long organizationId;

    private MultipartFile image;

}


