package com.tplate.coresystem.role.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {

    /**
     * Fetch all not deleted roles
     * @return not deleted roles
     */
    @Query("""
                SELECT e FROM RoleModel e
                WHERE e.deletedAt IS NULL
            """)
    List<RoleModel> findAllNotDeleted();

    /**
     * Check if exists some role with the same name.
     * @return true if the name already exists otherwise false
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM RoleModel e
                WHERE e.name = ?1
            """)
    Boolean existsByName(String name);
}

