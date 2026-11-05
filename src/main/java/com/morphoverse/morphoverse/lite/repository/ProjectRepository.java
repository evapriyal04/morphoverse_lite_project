package com.morphoverse.morphoverse.lite.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.morphoverse.morphoverse.lite.entity.Project;



@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}