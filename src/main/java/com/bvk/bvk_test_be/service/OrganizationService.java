package com.bvk.bvk_test_be.service;

import com.bvk.bvk_test_be.dto.OrganizationRequestDTO;
import com.bvk.bvk_test_be.entity.Organization;
import com.bvk.bvk_test_be.repository.OrganizationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    OrganizationRepository organizationRepository;

    public List<Organization> get(){
        return this.organizationRepository.findAll();
    }

    public Organization create(OrganizationRequestDTO dto){
        Organization organization = Organization.builder()
                .name(dto.getName())
                .build();

        return this.organizationRepository.save(organization);
    }

    public Organization getById(Long id){
        Organization existOrganization = this.organizationRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("no organization with id " + id)
        );

        return existOrganization;
    }

    public Organization update(Long id, OrganizationRequestDTO dto){

        Organization existOrganization = this.getById(id);

        existOrganization.setName(dto.getName());

        return this.organizationRepository.save(existOrganization);
    }

    public void delete(Long id){
        Organization existOrganization = this.getById(id);

        this.organizationRepository.delete(existOrganization);
    }
}


