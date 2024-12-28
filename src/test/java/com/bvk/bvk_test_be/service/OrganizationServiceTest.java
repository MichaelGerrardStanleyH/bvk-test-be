package com.bvk.bvk_test_be.service;

import com.bvk.bvk_test_be.dto.OrganizationRequestDTO;
import com.bvk.bvk_test_be.entity.Organization;
import com.bvk.bvk_test_be.repository.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {

    @InjectMocks
    OrganizationService organizationService;

    @Mock
    OrganizationRepository organizationRepository;


    @Test
    public void get(){
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("BVK");

        List<Organization> organizationList = List.of(
                organization
        );

        when(this.organizationRepository.findAll()).thenReturn(organizationList);

        List<Organization> result = this.organizationService.get();

        assertNotNull(result);
        assertEquals(organizationList, result);
    }

    @Test
    public void getById(){
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("BVK");

        when(this.organizationRepository.findById(any())).thenReturn(Optional.of(organization));

        Organization result = this.organizationService.getById(any());

        assertNotNull(result);
        assertEquals(organization, result);
    }

    @Test
    public void create(){
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("BVK");

        OrganizationRequestDTO dto = new OrganizationRequestDTO();
        dto.setName("BVK");

        when(this.organizationRepository.save(any())).thenReturn(organization);

        Organization result = this.organizationService.create(dto);

        assertNotNull(result);
        assertEquals(organization, result);
    }

    @Test
    public void update(){
        Organization organization = new Organization();
        organization.setId(1L);
        organization.setName("BVK");

        OrganizationRequestDTO dto = new OrganizationRequestDTO();
        dto.setName("BVK");


        when(this.organizationRepository.findById(any())).thenReturn(Optional.of(organization));

        when(this.organizationRepository.save(any())).thenReturn(organization);

        Organization result = this.organizationService.update(any(), dto);

        assertNotNull(result);
        assertEquals(organization, result);
    }



@Test
public void delete(){
    Organization organization = new Organization();
    organization.setId(1L);
    organization.setName("BVK");

    when(this.organizationRepository.findById(any())).thenReturn(Optional.of(organization));

    this.organizationService.delete(any());

    verify(this.organizationRepository, times(1)).delete(organization);
}

}