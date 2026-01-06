package com.TaskManagement1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog,Long> {

}
