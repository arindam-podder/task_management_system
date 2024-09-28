package com.arindam.tms;

import com.arindam.tms.enums.UserRole;
import com.arindam.tms.models.Role;
import com.arindam.tms.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagementSystemApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagementSystemApplication.class, args);
    }


    //this is to add User role at the time of App start with the id=1.
    @Override
    public void run(String... args) throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setRole(UserRole.USER);
        roleRepository.save(role);
    }
}
