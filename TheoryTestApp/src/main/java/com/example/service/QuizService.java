package com.example.service;

import com.example.model.Question;
import com.example.model.UserProgress;
import com.example.repository.QuestionRepository;
import com.example.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepo;
    
    @Autowired
    private ProgressRepository progressRepo;

    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public boolean checkAnswer(Long questionId, String userAnswer) {
        Question q = questionRepo.findById(questionId)
            .orElseThrow(() -> new RuntimeException("Question not found"));
            
        return q.getCorrectAnswer().equalsIgnoreCase(userAnswer);
    }
    
    public void updateUserProgress(String userId, int scoreToAdd) {
        UserProgress progress = progressRepo.findByUserId(userId)
            .orElse(new UserProgress());
            
        if (progress.getUserId() == null) {
            progress.setUserId(userId);
            progress.setTotalScore(0);
        }
        
        progress.setTotalScore(progress.getTotalScore() + scoreToAdd);
        progressRepo.save(progress);
    }
}