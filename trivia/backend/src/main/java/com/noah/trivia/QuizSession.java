package com.noah.trivia;

import java.util.Map;

public class QuizSession {
    private String sessionId;
    private Map<String, String> correct_answers;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, String> getCorrectAnswers() {
        return correct_answers;
    }

    public void setCorrectAnswers(Map<String, String> correct_answers) {
        this.correct_answers = correct_answers;
    }
}
