package com.tplate.coresystem.catalog.product.persistence;

import com.tplate.coresystem.shared.repositories.DeletableRepository;
import com.tplate.coresystem.shared.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends
        SearchableRepository<ProductModel>,
        DeletableRepository<ProductModel> {

    /**
     * Check if code exist and the record is not deleted
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
                WHERE e.code = :code
                AND e.deletedAt IS NULl
            """)
    boolean existsByCode(String code);

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
                WHERE e.code = :code
                AND e.id <> :id
                AND e.deletedAt IS NULl
            """)
    boolean existsByCodeExcludingId(@Param("code") String code, @Param("id") Long id);

}

