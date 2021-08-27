package com.tplate.coresystem.core.repositories;

import com.tplate.coresystem.core.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface BaseRepository<E extends BaseModel> extends JpaRepository<E, Long> {

    /**
     * Returns whether an entity with the given id exists.
     * And the entity must not have been soft-deleted
     *
     *
     * @param id record
     * @return true if an entity with the given id exists, false otherwise.
     */
    @Query("""
                SELECT
                    CASE
                        WHEN count(e)> 0 THEN true
                        ELSE false
                    END
                FROM #{#entityName} e
                WHERE e.id = :id
                AND e.deletedAt IS NULl
            """)
    boolean existsById(@Param("id") Long id);

}

