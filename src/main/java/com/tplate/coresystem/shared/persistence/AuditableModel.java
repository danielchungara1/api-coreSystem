package com.tplate.coresystem.shared.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AuditableModel extends BaseModel {

    @Column(name = "created_at")
    protected Date createdAt;

    @Column(name = "created_by")
    protected String createdBy;

    @Column(name = "deleted_at")
    protected Date deletedAt;

    @Column(name = "deleted_by")
    protected String deletedBy;

    @Column(name = "updated_last_at")
    protected Date updatedLastAt;

    @Column(name = "updated_last_by")
    protected String updatedLastBy;

    /**
     * Before creating the entity, audit information is added.
     */
    @PrePersist
    public void prePersist() {
        this.setCreatedAt(new Date());
        this.setCreatedBy("SYSTEM"); // TODO: Change to real user when spring security is enabled
    }

    /**
     * Before updating the entity, audit information is added.
     */
    @PreUpdate
    public void preUpdate() {
        this.setUpdatedLastAt(new Date());
        this.setUpdatedLastBy("SYSTEM"); // TODO: Change to real user when spring security is enabled
    }

}
