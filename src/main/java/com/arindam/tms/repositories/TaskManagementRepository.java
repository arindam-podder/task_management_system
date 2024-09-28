package com.arindam.tms.repositories;

import com.arindam.tms.enums.Priority;
import com.arindam.tms.enums.TaskStatus;
import com.arindam.tms.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskManagementRepository extends JpaRepository<Task, Long> {
    // Implementation of TaskManagementRepository

    List<Task> findAllByUserId(Long userId);

    @Query("SELECT t FROM Task t WHERE " +
            "(:status IS NULL OR t.status = :status) AND " +
            "(:priority IS NULL OR t.priority = :priority) AND " +
            "(:dueDate IS NULL OR t.dueDate = :dueDate)")
    List<Task> findByFilters(@Param("status") TaskStatus status,
                             @Param("priority") Priority priority,
                             @Param("dueDate") LocalDateTime dueDate);

    @Query("SELECT t FROM Task t WHERE " +
            "(t.title LIKE %:keyword% OR t.description LIKE %:keyword%)")
    List<Task> searchByKeyword(@Param("keyword") String keyword);

}
