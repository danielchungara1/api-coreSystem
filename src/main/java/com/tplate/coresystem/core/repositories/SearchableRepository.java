package com.tplate.coresystem.core.repositories;

import com.tplate.coresystem.core.BaseModel;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SearchableRepository<E extends BaseModel> extends BaseRepository<E> {
}

