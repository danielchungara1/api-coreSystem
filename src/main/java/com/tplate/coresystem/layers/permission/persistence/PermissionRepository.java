package com.tplate.coresystem.layers.permission.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {

    @Query("""
        SELECT e FROM PermissionModel e 
        WHERE e.enabled = true                      
    """)
    List<PermissionModel> findAllByEnabledTrue();

}
