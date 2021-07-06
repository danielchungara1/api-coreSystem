package com.tplate.coresystem.permission.persistence;

import com.tplate.coresystem.shared.persistence.IParametric;
import com.tplate.coresystem.shared.persistence.auditableLinearization.AuditableWithUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "permission")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PermissionModel extends AuditableWithUpdate implements IParametric {

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "name")
    private String name;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "description")
    private String description;

    @PreUpdate
    public void preUpdate() {
        if (this.enabled == null) {
            this.enabled = true;
        }
    }

}
