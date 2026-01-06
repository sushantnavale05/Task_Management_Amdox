package com.TaskManagement1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

}

