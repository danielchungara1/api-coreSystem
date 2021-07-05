package com.tplate.coresystem.shared.persistence.auditableLinearization;

import com.tplate.coresystem.shared.persistence.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AuditableWithCreate extends BaseModel {

    @Column(name = "created_at")
    protected Date createdAt;

    @Column(name = "created_by")
    protected String createdBy;

}
