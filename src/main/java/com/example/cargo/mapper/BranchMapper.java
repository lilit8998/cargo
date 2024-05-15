package com.example.cargo.mapper;

import com.example.cargo.dto.BranchSaveDto;
import com.example.cargo.entity.Branch;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BranchMapper {

    Branch branchSaveDtoToBranch(BranchSaveDto branchSaveDto);
}
