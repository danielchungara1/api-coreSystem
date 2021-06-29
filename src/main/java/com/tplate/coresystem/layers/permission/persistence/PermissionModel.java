package com.tplate.coresystem.layers.permission.persistence;

import com.tplate.coresystem.layers.shared.persistence.ParametricModel;
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
public class PermissionModel extends ParametricModel {

    private Boolean enabled;

}
