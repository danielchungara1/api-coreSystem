package com.tplate.coresystem.shared.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AuditableRepository<E extends AuditableModel> extends JpaRepository<E, Long> {

    /**
     * Fetch all not deleted
     *
     * @return not deleted
     */
    @Query("""
                SELECT e
                FROM #{#entityName} e
                WHERE e.deletedAt IS NULL
            """)
    List<E> findAllNotDeleted();

}

