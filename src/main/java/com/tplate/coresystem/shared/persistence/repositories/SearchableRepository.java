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
public interface SearchableRepository<E extends BaseModel> extends JpaRepository<E, Long> {

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


}

