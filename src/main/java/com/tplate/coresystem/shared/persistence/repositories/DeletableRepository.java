package com.tplate.coresystem.shared.persistence.repositories;

import com.tplate.coresystem.shared.persistence.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface DeletableRepository<E extends BaseModel> extends JpaRepository<E, Long> {


    /**
     * Delete record (soft-delete strategy)
     *
     * @param id record
     */
    @Query("""
                UPDATE #{#entityName} e
                SET e.deletedAt = :date,
                    e.deletedBy = :user
                WHERE e.id = :id
            """)
    @Modifying
    void deleteById(@Param("id") Long id, @Param("date") Date date, @Param("user") String user);

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

