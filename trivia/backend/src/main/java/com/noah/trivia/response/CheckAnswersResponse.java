package com.noah.trivia.response;

import java.util.List;

public class CheckAnswersResponse {
    private int score;
    private int total;
    private List<CheckAnswerResult> results;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<CheckAnswerResult> getResults() {
        return results;
    }

    public void setResults(List<CheckAnswerResult> results) {
        this.results = results;
    }
}
