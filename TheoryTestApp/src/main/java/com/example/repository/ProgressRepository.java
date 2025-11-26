package com.example.repository;

import com.example.model.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<UserProgress, Long> {
    Optional<UserProgress> findByUserId(String userId);
}
