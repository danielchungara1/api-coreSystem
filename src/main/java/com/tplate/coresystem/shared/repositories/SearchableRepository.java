package com.tplate.coresystem.shared.repositories;

import com.tplate.coresystem.shared.BaseModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SearchableRepository<E extends BaseModel> extends BaseRepository<E> {
}

