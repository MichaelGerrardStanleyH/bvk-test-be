package com.bvk.bvk_test_be.service;

import com.bvk.bvk_test_be.dto.MemberRequestDTO;
import com.bvk.bvk_test_be.entity.Member;
import com.bvk.bvk_test_be.entity.Organization;
import com.bvk.bvk_test_be.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    OrganizationService organizationService;

    @Test
    public void get(){

        Member member = new Member();
        member.setId(1L);
        member.setName("michael");

        List<Member> memberList = List.of(member);

        when(this.memberRepository.findAll()).thenReturn(memberList);

        List<Member> result = this.memberService.get();

        assertNotNull(result);
        assertEquals(memberList, result);
    }

    @Test
    public void getById(){

        Member member = new Member();
        member.setId(1L);
        member.setName("michael");;

        when(this.memberRepository.findById(any())).thenReturn(Optional.of(member));

        Member result = this.memberService.getById(any());

        assertNotNull(result);
        assertEquals(member, result);
    }

    @Test
    public void getAllByNameContains(){

        Member member = new Member();
        member.setId(1L);
        member.setName("michael");

        List<Member> memberList = List.of(member);

        when(this.memberRepository.findByNameContains(any())).thenReturn(memberList);

        List<Member> result = this.memberService.getAllByNameContains(any());

        assertNotNull(result);
        assertEquals(memberList, result);
    }

    @Test
    public void create() throws IOException {

        Member member = new Member();
        member.setId(1L);
        member.setName("michael");

        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("BVK");

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Hello, World!".getBytes()
        );

        MemberRequestDTO dto = new MemberRequestDTO();
        dto.setName("michael");
        dto.setOrganizationId(1L);
        dto.setImage(file);

        when(this.organizationService.getById(any())).thenReturn(organization);

        when(this.memberRepository.save(any())).thenReturn(member);

        Member result = this.memberService.create(dto);

        assertNotNull(result);
        assertEquals(member, result);
    }

    @Test
    public void update() throws IOException {

        Member member = new Member();
        member.setId(1L);
        member.setName("michael");

        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("BVK");

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "Hello, World!".getBytes()
        );

        MemberRequestDTO dto = new MemberRequestDTO();
        dto.setName("michael");
        dto.setOrganizationId(1L);
        dto.setImage(file);

        when(this.memberRepository.findById(any())).thenReturn(Optional.of(member));

        when(this.organizationService.getById(any())).thenReturn(organization);

        when(this.memberRepository.save(any())).thenReturn(member);

        Member result = this.memberService.update(any(), dto);

        assertNotNull(result);
        assertEquals(member, result);
    }

    @Test
    public void delete(){

        Member member = new Member();
        member.setId(1L);
        member.setName("michael");;

        when(this.memberRepository.findById(any())).thenReturn(Optional.of(member));

        this.memberService.delete(any());

        verify(this.memberRepository, times(1)).delete(any());
    }

}