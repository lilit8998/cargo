package com.example.cargo.service;

import com.example.cargo.dto.BranchSaveDto;
import com.example.cargo.entity.Branch;

public interface BranchService {
    void save(BranchSaveDto branchSaveDto,int city, int country);

}
