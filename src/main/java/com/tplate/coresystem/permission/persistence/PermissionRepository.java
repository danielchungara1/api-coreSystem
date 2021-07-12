package com.tplate.coresystem.permission.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {

    /**
     * Fetch all permissions
     * @return permissions
     */
    @Query("""
                SELECT e FROM PermissionModel e               
            """)
    List<PermissionModel> findAllByEnabledTrue();

}

