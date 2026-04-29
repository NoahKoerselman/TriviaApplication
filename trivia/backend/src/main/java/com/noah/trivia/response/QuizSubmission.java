package com.noah.trivia.response;

import java.util.Map;

public class QuizSubmission {
    private String sessionId;
    private Map<String, String> answers;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Map<String, String> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<String, String> answers) {
        this.answers = answers;
    }
}
