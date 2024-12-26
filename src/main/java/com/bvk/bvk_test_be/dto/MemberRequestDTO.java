package com.bvk.bvk_test_be.dto;

import lombok.*;

@Data
public class MemberRequestDTO {

    private String name;

    private String urlPicture;

    private String pictureName;

    private String position;

    private String reportsTo;

    private Long organizationId;

}


