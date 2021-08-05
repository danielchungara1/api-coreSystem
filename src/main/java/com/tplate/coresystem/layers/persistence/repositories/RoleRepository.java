package com.tplate.coresystem.layers.persistence.repositories;

import com.tplate.coresystem.layers.persistence.models.RoleModel;
import com.tplate.coresystem.shared.persistence.repositories.BaseRepository;
import com.tplate.coresystem.shared.persistence.repositories.DeletableRepository;
import com.tplate.coresystem.shared.persistence.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends
        SearchableRepository<RoleModel>,
        DeletableRepository<RoleModel>,
        BaseRepository<RoleModel>{

    /**
     * Check if name exist and the record is not deleted
     *
     * @param name record
     * @return true if exist otherwise false
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM #{#entityName} e
                WHERE e.name = :name
                AND e.deletedAt IS NULl
            """)
    boolean existsByName(String name);

    /**
     * Check if name exists (soft-deleted records are not included)
     * Excluding record with id
     *
     * @param id   record to exclude
     * @param name recored
     * @return true if exist otherwise false
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM #{#entityName} e
                WHERE e.name = :name
                AND e.id <> :id
                AND e.deletedAt IS NULl
            """)
    boolean existsByNameExcludingId(@Param("name") String name, @Param("id") Long id);

}

