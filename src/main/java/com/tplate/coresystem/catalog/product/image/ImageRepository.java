package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.shared.repositories.DeletableRepository;
import com.tplate.coresystem.shared.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends
        DeletableRepository<ImageModel>,
        SearchableRepository<ImageModel> {

    @Query(value = """
                SELECT
                    CASE
                        WHEN count(i)> 0 THEN true
                        ELSE false
                    END
                FROM product_image i
                INNER JOIN product p 
                ON i.product_id = p.id 
                WHERE p.deleted_at IS NULL
                AND p.id = :productId
                AND i.deleted_at IS NULL
                AND i.main = TRUE
            """, nativeQuery = true)
    boolean existMainImageForProductId(@Param("productId") Long productId);

//    /**
//     * Find all (soft-deleted records are not included)
//     * @param id product
//     * @return records
//     */
//    @Query("""
//                SELECT e
//                FROM #{#entityName} e
//                WHERE e.deletedAt IS NULL
//                AND e.product.id = :id
//                ORDER BY e.id ASC
//            """)
//
//    List<ProductImageModel> findAllByIdProduct(@Param("id") Long id);
//
//    /**
//     * Check if name exist and the record is not deleted
//     *
//     * @param name record
//     * @return true if exist otherwise false
//     */
//    @Query("""
//                SELECT
//                    CASE
//                        WHEN count(e)> 0 THEN true
//                        ELSE false
//                    END
//                FROM #{#entityName} e
//                WHERE e.name = :name
//                AND e.deletedAt IS NULl
//            """)
//    boolean existsByName(String name);
//
//    /**
//     * Delete record (soft-delete strategy)
//     *
//     * @param id record
//     */
//    @Query("""
//                UPDATE #{#entityName} e
//                SET e.deletedAt = :date,
//                    e.deletedBy = :user
//                WHERE e.product.id = :id
//            """)
//    @Modifying
//    void deleteAllByProductId(@Param("id") Long id, @Param("date") Date date, @Param("user") String user);

}

