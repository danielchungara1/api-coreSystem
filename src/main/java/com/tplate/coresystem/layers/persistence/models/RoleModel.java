package com.tplate.coresystem.layers.persistence.models;

import com.tplate.coresystem.shared.persistence.models.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RoleModel extends BaseModel {

    @Column(name = "name")
    protected String name;

    @Column(name = "description")
    protected String description;

    @Column(name = "display_name")
    protected String displayName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="permission_role",
            joinColumns={@JoinColumn(name="role_id")},
            inverseJoinColumns={@JoinColumn(name="permission_id")})
    private List<PermissionModel> permissions;

}
