package com.tplate.coresystem.shared.persistence.auditableLinearization;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AuditableWithUpdate extends AuditableWithCreate {

    @Column(name = "updated_last_at")
    protected Date updatedLastAt;

    @Column(name = "updated_last_by")
    protected String updatedLastBy;

    /**
     * Before updating the entity, audit information is added.
     */
    @PreUpdate
    public void preUpdate() {
        this.setUpdatedLastAt(new Date());
        this.setUpdatedLastBy("SYSTEM"); // TODO: Change to real user when spring security is enabled
    }
}
