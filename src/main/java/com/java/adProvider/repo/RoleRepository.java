package com.java.adProvider.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.adProvider.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
