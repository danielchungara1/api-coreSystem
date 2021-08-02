package com.tplate.coresystem.shared.persistence.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
@NoArgsConstructor
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    protected Long id;

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
     * Before creating, the audit information must be saved.
     */
    @PrePersist
    public void prePersist() {
        this.setCreatedAt(new Date());
        this.setCreatedBy("SYSTEM"); // TODO: Change to real user when spring security is enabled

    }

    /**
     * Before updating, the audit information must be saved.
     */
    @PreUpdate
    public void preUpdate() {
        this.setUpdatedLastAt(new Date());
        this.setUpdatedLastBy("SYSTEM"); // TODO: Change to real user when spring security is enabled

    }

}
