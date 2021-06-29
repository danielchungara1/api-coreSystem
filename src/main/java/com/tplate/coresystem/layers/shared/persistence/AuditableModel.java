package com.tplate.coresystem.layers.shared.persistence;

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
public abstract class AuditableModel extends BaseModel{

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "updated_last_at")
    private Date updatedLastAt;

    @Column(name = "updated_last_by")
    private String updatedLastBy;

    @Column(name = "fetched_last_at")
    private Date fetchedLastAt;

    @Column(name = "fetched_last_by")
    private String fetchedLastBy;

}
