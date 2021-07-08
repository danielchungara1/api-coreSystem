package com.tplate.coresystem.role.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

}

