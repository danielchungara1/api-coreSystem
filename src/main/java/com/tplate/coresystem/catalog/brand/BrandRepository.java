package com.tplate.coresystem.catalog.brand;

import com.tplate.coresystem.core.repositories.DeletableRepository;
import com.tplate.coresystem.core.repositories.NamableUniqueRepository;
import com.tplate.coresystem.core.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends
        NamableUniqueRepository<BrandModel>,
        SearchableRepository<BrandModel>,
        DeletableRepository<BrandModel> {
}

