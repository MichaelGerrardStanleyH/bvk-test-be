package com.bvk.bvk_test_be.controller;

import com.bvk.bvk_test_be.dto.OrganizationRequestDTO;
import com.bvk.bvk_test_be.dto.OrganizationResponseDTO;
import com.bvk.bvk_test_be.mapper.TransactionMapper;
import com.bvk.bvk_test_be.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("organizations")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @GetMapping
    public ResponseEntity<?> getAllOrganization(){
        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionMapper.mapEntityListToDtoList(this.organizationService.get(), OrganizationResponseDTO.class)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionMapper.mapEntityToDto(this.organizationService.getById(id), OrganizationResponseDTO.class)
        );
    }

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                TransactionMapper.mapEntityToDto(this.organizationService.create(dto), OrganizationResponseDTO.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody OrganizationRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(
                TransactionMapper
                        .mapEntityToDto(this.organizationService.update(id, dto), OrganizationResponseDTO.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganization(@PathVariable("id") Long id){
        this.organizationService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}



