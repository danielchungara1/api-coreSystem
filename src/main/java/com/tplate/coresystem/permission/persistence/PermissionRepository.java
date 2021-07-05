package com.tplate.coresystem.permission.persistence;

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

    /**
     * Checks if exist some permission with the name, excluding from the search the permission with id provided.
     * @param name of the permission
     * @param id of the permission
     * @return true is exist otherwise false
     */
    @Query(value = """
            SELECT
                CASE
                    WHEN count(e)> 0 THEN true
                    ELSE false
                END
            FROM PermissionModel e
            WHERE e.id <> ?2
            AND e.name = ?1
            """)
    boolean existsByNameExcludingById(String name, Long id);
}

