package com.tplate.coresystem.security.role.persistence;

import com.tplate.coresystem.security.permission.persistence.PermissionModel;
import com.tplate.coresystem.shared.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@Where(clause = "deleted_at IS NULL")
public class RoleModel extends BaseModel {

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "display_name")
    protected String displayName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "permission_role",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private List<PermissionModel> permissions;

}
