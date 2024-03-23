package com.lalitha.busReservationBackend.repository;


import com.lalitha.busReservationBackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
