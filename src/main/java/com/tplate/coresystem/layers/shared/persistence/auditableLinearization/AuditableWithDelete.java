package com.tplate.coresystem.layers.shared.persistence.auditableLinearization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AuditableWithDelete extends AuditableWithUpdate {

    @Column(name = "deleted_at")
    protected Date deletedAt;

    @Column(name = "deleted_by")
    protected String deletedBy;


}
