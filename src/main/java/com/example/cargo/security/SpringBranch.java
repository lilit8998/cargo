package com.example.cargo.security;

import com.example.cargo.entity.Branch;
import com.example.cargo.entity.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class SpringBranch extends org.springframework.security.core.userdetails.Branch {

    private Branch branch;

    public SpringBranch(Branch branch) {
        super(branch.getEmail(), branch.getPassword());
        this.branch = branch;
    }

    public Branch getBranch() {
        return branch;
    }
}
