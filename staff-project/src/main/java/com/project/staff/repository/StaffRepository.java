package com.project.staff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.staff.model.StaffDetails;

public interface StaffRepository extends JpaRepository<StaffDetails, Long>
{

    List<StaffDetails> findByNameContaining(String name);
}
