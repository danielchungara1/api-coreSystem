package com.tplate.coresystem.layers.persistence.models;

import com.tplate.coresystem.shared.persistence.models.ParametricModel;
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
public class RoleModel extends ParametricModel {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="permission_role",
            joinColumns={@JoinColumn(name="role_id")},
            inverseJoinColumns={@JoinColumn(name="permission_id")})
    private List<PermissionModel> permissions;

}