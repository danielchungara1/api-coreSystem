package com.tplate.coresystem.shared.persistence.repositories;

import com.tplate.coresystem.shared.persistence.models.ParametricModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface ParametricRepository<E extends ParametricModel> extends BaseRepository<E> {

    /**
     * Check: record existence with the name provided. (soft-deleted records are included)
     * @param name record
     * @return true if exists otherwise false
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM #{#entityName} e
                WHERE e.name = :name
            """)
    boolean existsByName(@Param("name") String name);

    /**
     * Check: record existence with the name and excluding record with the id provided. (soft-deleted records are included)
     * @param name record
     * @param id record
     * @return true if exists otherwise false
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
            """)
    boolean existsByNameExcludingId(@Param("name") String name, @Param("id") Long id);
}

