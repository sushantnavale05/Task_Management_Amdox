package com.TaskManagement1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagement1.Enum.SprintState;
import com.TaskManagemente1.Entity.Sprint;

@Repository
public interface SprintRepository extends JpaRepository<Sprint,Long>{

	List<Sprint>findByProjectId(Long projectId);
	List<Sprint>findBySprintState(SprintState sprintState);
}
