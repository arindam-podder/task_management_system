package com.arindam.tms.repositories;

import com.arindam.tms.models.Role;
import com.arindam.tms.models.User;
import com.arindam.tms.models.UserRoleMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleMapperRepository extends JpaRepository<UserRoleMapper, Long> {

    @Query("select userRole.role from UserRoleMapper userRole where userRole.user =:user")
    List<Role> findRolesByUser(@Param("user") User user);
}
