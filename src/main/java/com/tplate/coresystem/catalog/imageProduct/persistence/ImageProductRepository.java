package com.tplate.coresystem.catalog.imageProduct.persistence;

import com.tplate.coresystem.shared.repositories.BaseRepository;
import com.tplate.coresystem.shared.repositories.DeletableRepository;
import com.tplate.coresystem.shared.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageProductRepository extends
        DeletableRepository<ImageProductModel>,
        SearchableRepository<ImageProductModel> {

    /**
     * Find all (soft-deleted records are not included)
     * @param id product
     * @return records
     */
    @Query("""
                SELECT e
                FROM #{#entityName} e
                WHERE e.deletedAt IS NULL
                AND e.product.id = :id
                ORDER BY e.id ASC
            """)

    List<ImageProductModel> findAllByIdProduct(@Param("id") Long id);

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
}

