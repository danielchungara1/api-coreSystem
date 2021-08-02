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
public interface BaseRepository<E extends BaseModel> extends JpaRepository<E, Long> {

    /**
     * Find all (soft-deleted records are not included)
     *
     * @return records
     */
    @Query("""
                SELECT e
                FROM #{#entityName} e
                WHERE e.deletedAt IS NULL
                ORDER BY e.id ASC
            """)
    List<E> findAll();

    /**
     * Find record by id (soft-deleted records are not included)
     *
     * @param id record
     * @return record
     */
    @Query("""
                SELECT e
                FROM #{#entityName} e
                WHERE e.id = :id
                AND e.deletedAt IS NULl 
            """)
    Optional<E> findById(@Param("id") Long id);

    /**
     * Get record by id (soft-deleted records are not included)
     *
     * @param id record
     * @return record
     */
    @Query("""
                SELECT e
                FROM #{#entityName} e
                WHERE e.id = :id
                AND e.deletedAt IS NULl 
            """)
    E getById(@Param("id") Long id);

    /**
     * Check: id must exist (soft-deleted records are not included)
     *
     * @param id record
     * @return true if exist otherwise false
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


}

