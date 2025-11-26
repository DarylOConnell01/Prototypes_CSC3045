package com.example.controller;

import com.example.model.Question;
import com.example.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
// Allow requests from React frontend running on port 3000
@CrossOrigin(origins = "http://localhost:3000") 
public class QuizController {
    
    @Autowired
    private QuizService quizService;

    // Endpoint: GET /api/v1/quiz/questions/Safety
    @GetMapping("/questions/{category}")
    public List<Question> getQuestions(@PathVariable String category) {
        return quizService.getQuestionsByCategory(category);
    }

    // Endpoint: POST /api/v1/quiz/submit?questionId=1&answer=Foam
    @PostMapping("/submit")
    public boolean submitAnswer(@RequestParam Long questionId, @RequestParam String answer) {
        return quizService.checkAnswer(questionId, answer);
    }
    
    // Endpoint: POST /api/v1/quiz/progress?userId=user123&score=10
    @PostMapping("/progress")
    public void saveProgress(@RequestParam String userId, @RequestParam int score) {
        quizService.updateUserProgress(userId, score);
    }
}
