package com.noah.trivia.response;

import java.util.List;

public class QuizResponse {
    private String sessionId;
    private List<Question> questions;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}