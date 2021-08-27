package com.tplate.coresystem.security.role;

import com.tplate.coresystem.core.repositories.BaseRepository;
import com.tplate.coresystem.core.repositories.DeletableRepository;
import com.tplate.coresystem.core.repositories.NamableUniqueRepository;
import com.tplate.coresystem.core.repositories.SearchableRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends
        SearchableRepository<RoleModel>,
        DeletableRepository<RoleModel>,
        NamableUniqueRepository<RoleModel> {

}

