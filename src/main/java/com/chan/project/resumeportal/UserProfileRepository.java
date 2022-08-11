package com.chan.project.resumeportal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chan.project.resumeportal.models.User;
import com.chan.project.resumeportal.models.UserProfile;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName);
}
