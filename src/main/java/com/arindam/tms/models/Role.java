package com.arindam.tms.models;

import com.arindam.tms.enums.UserRole;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseModel{

    private UserRole role;

}
