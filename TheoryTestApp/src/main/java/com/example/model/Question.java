package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category; // e.g., "Safety", "Standards"
    
    @Column(columnDefinition = "TEXT")
    private String questionText;
    
    private String correctAnswer;
    
    private String options; // Stored as "OptionA,OptionB,OptionC" for simplicity

    // Constructors
    public Question() {}

    public Question(String category, String questionText, String correctAnswer, String options) {
        this.category = category;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getOptions() { return options; }
    public void setOptions(String options) { this.options = options; }
}
