package com.tplate.coresystem.shared.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ParametricRepository<E extends ParametricModel> extends AuditableRepository<E> {

    /**
     * Check if exists some record with the same name
     * @param name checked
     * @return true if the name already exists otherwise false
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM #{#entityName} e
                WHERE e.name = ?1
            """)
    Boolean existsByName(String name);

    /**
     * Check if exists someone with the same name excluding the one with id passed
     * @param name entity
     * @param id entity
     * @return true if the name already exists otherwise false
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM #{#entityName} e
                WHERE e.name = ?1 AND e.id <> ?2
            """)
    Boolean existsByNameExcludingId(String name, Long id);
}

