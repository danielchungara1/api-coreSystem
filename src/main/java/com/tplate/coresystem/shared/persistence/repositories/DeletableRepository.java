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
public interface DeletableRepository<E extends BaseModel> extends BaseRepository<E> {


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

