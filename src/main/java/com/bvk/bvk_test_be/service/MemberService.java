package com.bvk.bvk_test_be.service;

import com.bvk.bvk_test_be.dto.MemberRequestDTO;
import com.bvk.bvk_test_be.entity.Member;
import com.bvk.bvk_test_be.entity.Organization;
import com.bvk.bvk_test_be.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    OrganizationService organizationService;

    public List<Member> get(){
        return this.memberRepository.findAll();
    }

    public Member create(MemberRequestDTO dto){
        Organization existOrganization = this.organizationService.getById(dto   .getOrganizationId());

        Member member = Member.builder()
                .name(dto.getName())
                .urlPicture("urlPicture")
                .pictureName("picture.jpg")
                .position(dto.getPosition())
                .reportsTo(dto.getReportsTo())
                .organization(existOrganization)
                .build();

        return this.memberRepository.save(member);
    }

    public Member getById(Long id){
        Member existMember = this.memberRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("no member with id " + id)
        );

        return existMember;
    }

    public Member update(Long id, MemberRequestDTO dto){
        Member existMember = this.getById(id);

        Organization existOrganization = this.organizationService.getById(dto.getOrganizationId());

        existMember.setName(dto.getName());
        existMember.setPosition(dto.getPosition());
        existMember.setUrlPicture(dto.getUrlPicture());
        existMember.setPictureName(dto.getPictureName());
        existMember.setReportsTo(dto.getReportsTo());
        existMember.setOrganization(existOrganization);

        return this.memberRepository.save(existMember);
    }

    public void delete(Long id){
        Member existMember = this.getById(id);

        this.memberRepository.delete(existMember);
    }

}





