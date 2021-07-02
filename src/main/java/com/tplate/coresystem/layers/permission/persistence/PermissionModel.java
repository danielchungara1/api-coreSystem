package com.tplate.coresystem.layers.permission.persistence;

import com.tplate.coresystem.layers.shared.persistence.IParametric;
import com.tplate.coresystem.layers.shared.persistence.auditableLinearization.AuditableWithUpdate;
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

}
