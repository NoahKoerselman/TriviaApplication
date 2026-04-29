package com.noah.trivia.opentdb;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.noah.trivia.response.Question;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenTDBResponse {
    private int response_code;
    private List<OpenTDBQuestion> results;

    public int getResponse_code() {
        return response_code;
    }

    public void setResponse_code(int response_code) {
        this.response_code = response_code;
    }

    public List<OpenTDBQuestion> getResults() {
        return results;
    }

    public void setResults(List<OpenTDBQuestion> results) {
        this.results = results;
    }
}
