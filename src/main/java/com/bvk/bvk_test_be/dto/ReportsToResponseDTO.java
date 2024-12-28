package com.bvk.bvk_test_be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportsToResponseDTO {
    private Long id;

    private String name;

    private String urlPicture;

    private String pictureName;

    private String position;
}
