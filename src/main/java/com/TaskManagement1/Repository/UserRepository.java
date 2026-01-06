package com.TaskManagement1.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	Optional<User>findByUserEmail(String userEmail);

}
