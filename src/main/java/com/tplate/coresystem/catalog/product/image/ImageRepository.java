package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.core.repositories.DeletableRepository;
import com.tplate.coresystem.core.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                AND i.deleted_at IS NULL                
                AND p.id = :productId                
                AND i.main = TRUE
            """, nativeQuery = true)
    boolean existMainImageForProductId(@Param("productId") Long productId);

    @Query(value = """
                SELECT i.*
                FROM product_image i
                INNER JOIN product p 
                ON i.product_id = p.id 
                WHERE p.deleted_at IS NULL
                AND i.deleted_at IS NULL
                AND p.id = :productId                
                AND i.main = TRUE
            """, nativeQuery = true)
    Optional<ImageModel> findMainImageByProductId(Long productId);

    @Query(value = """
                SELECT i.*
                FROM product_image i
                INNER JOIN product p 
                ON i.product_id = p.id 
                WHERE p.deleted_at IS NULL
                AND i.deleted_at IS NULL
                AND p.id = :productId                
                AND i.main = FALSE
            """, nativeQuery = true)
    List<ImageModel> findAlternativesImagesByProductId(Long productId);

    @Query(value = """
                SELECT i.*
                FROM product_image i
                INNER JOIN product p 
                ON i.product_id = p.id 
                WHERE p.deleted_at IS NULL
                AND i.deleted_at IS NULL
                AND p.id = :productId
                AND i.id = :imageId    
            """, nativeQuery = true)
    Optional<ImageModel> findByImageIdAndProductId(Long imageId, Long productId);

    @Query(value = """
                SELECT i.*
                FROM product_image i
                INNER JOIN product p 
                ON i.product_id = p.id 
                WHERE p.deleted_at IS NULL
                AND i.deleted_at IS NULL
                AND p.id = :productId
            """, nativeQuery = true)
    List<ImageModel> findAllImagesByProductId(Long productId);

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
                AND i.deleted_at IS NULL                
                AND p.id = :productId
                AND i.id = :imageId                
            """, nativeQuery = true)
    boolean existByProductIdAndImageId(Long productId, Long imageId);

}

