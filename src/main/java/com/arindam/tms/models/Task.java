package com.arindam.tms.models;


import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseModel{

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime dueDate;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
