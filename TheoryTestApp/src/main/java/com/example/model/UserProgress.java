package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_progress")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userId;
    private String weakCategories; // e.g., "Safety,Procedures"
    private int totalScore;

    public UserProgress() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getWeakCategories() { return weakCategories; }
    public void setWeakCategories(String weakCategories) { this.weakCategories = weakCategories; }

    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
}