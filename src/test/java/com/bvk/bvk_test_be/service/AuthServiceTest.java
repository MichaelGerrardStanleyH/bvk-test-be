package com.bvk.bvk_test_be.service;

import com.bvk.bvk_test_be.dto.MemberRequestDTO;
import com.bvk.bvk_test_be.dto.SignInDTO;
import com.bvk.bvk_test_be.dto.SignUpDTO;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private OrganizationService organizationService;

    @Mock
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;


    @Test
    void signUp() throws IOException {
        Member member = new Member();
        member.setId(1L);
        member.setName("michael");

        Member reportsTo = new Member();
        member.setId(2L);
        member.setName("gerry");

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

        SignUpDTO dto = new SignUpDTO();
        dto.setName("michael");
        dto.setOrganizationId(1L);
        dto.setReportsTo(2L);
        dto.setImage(file);

        when(this.memberRepository.findByEmail(any())).thenReturn(Optional.empty());

        when(this.organizationService.getById(any())).thenReturn(organization);

        when(this.memberService.getById(any())).thenReturn(reportsTo);

        when(this.memberRepository.save(any())).thenReturn(member);

        this.authService.signUp(dto);

        verify(this.memberRepository, times(1)).save(any());
    }

    @Test
    public void signIn(){
        Member member = new Member();
        member.setId(1L);
        member.setName("michael");
        member.setEmail("michael@gmail.com");
        member.setPassword("123");

        SignInDTO dto = new SignInDTO();
        dto.setEmail(member.getEmail());
        dto.setPassword(member.getPassword());

        when(this.memberRepository.findByEmail(any())).thenReturn(Optional.of(member));

        Long userId = this.authService.signIn(dto);

        assertEquals(member.getId(), userId);
    }

}