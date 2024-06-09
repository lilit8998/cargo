package com.example.cargo.service;

import com.example.cargo.dto.BranchSaveDto;

public interface BranchService {
    void save(BranchSaveDto branchSaveDto,int city, int country);


    boolean existsByUserId(Long id);
}
