package com.bvk.bvk_test_be.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String urlPicture;

    private String pictureName;

    @Lob
    @Column(name = "imagedata",length = 1000)
    private byte[] imageData;

    private String position;

    @ManyToOne
    private Member reportsTo;

    @ManyToOne
    @JoinColumn(name = "organizationId")
    private Organization organization;

}

