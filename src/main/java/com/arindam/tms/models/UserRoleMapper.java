package com.arindam.tms.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_role_mapper")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleMapper extends BaseModel{

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

}
