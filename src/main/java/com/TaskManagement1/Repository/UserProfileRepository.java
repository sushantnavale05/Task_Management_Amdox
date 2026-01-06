package com.TaskManagement1.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagemente1.Entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
	
	Optional<UserProfile>findByUserOrganizationEmail(String userOrganizationEmail);

}

