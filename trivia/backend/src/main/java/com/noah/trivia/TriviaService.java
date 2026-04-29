package com.noah.trivia;

import com.noah.trivia.opentdb.OpenTDBQuestion;
import com.noah.trivia.opentdb.OpenTDBResponse;
import com.noah.trivia.response.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TriviaService {
    private RestTemplate restTemplate;
    private Map<String, QuizSession> sessions = new ConcurrentHashMap<>();

    public TriviaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public QuizResponse getQuestions(Integer amount, String difficulty, Integer category) {
        String url = "https://opentdb.com/api.php?amount=" + amount;
        if (category != null) {
            url += "&category=" + category;
        }
        if (difficulty != null && !difficulty.isBlank()) {
            url += "&difficulty=" + difficulty;
        }
        url += "&encode=url3986";

        OpenTDBResponse openTDBResponse = restTemplate.getForObject(url, OpenTDBResponse.class);

        List<Question> questions = new ArrayList<>();
        Map<String, String> correct_answers = new HashMap<>();

        String sessionId = UUID.randomUUID().toString();


        for(OpenTDBQuestion openTDBQuestion : openTDBResponse.getResults()) {
            String questionId = UUID.randomUUID().toString();

            List<String> answers = new ArrayList<>();
            answers.addAll(openTDBQuestion.getIncorrect_answers());
            answers.add(openTDBQuestion.getCorrect_answer());
            Collections.shuffle(answers);

            Question question = new Question();
            question.setId(questionId);
            question.setQuestion(openTDBQuestion.getQuestion());
            question.setCategory(openTDBQuestion.getCategory());
            question.setDifficulty(openTDBQuestion.getDifficulty());
            question.setAnswers(answers);
            questions.add(question);

            correct_answers.put(questionId, openTDBQuestion.getCorrect_answer());
        }

        QuizSession session = new QuizSession();
        session.setSessionId(sessionId);
        session.setCorrectAnswers(correct_answers);
        sessions.put(sessionId, session);


        QuizResponse quizResponse = new QuizResponse();
        quizResponse.setSessionId(sessionId);
        quizResponse.setQuestions(questions);
        return quizResponse;
    }

    public CheckAnswersResponse checkAnswers(QuizSubmission submission) {
        int score = 0;
        QuizSession quizSession = sessions.get(submission.getSessionId());

        List<CheckAnswerResult> results = new ArrayList<>();

        for (Map.Entry<String, String> entry : submission.getAnswers().entrySet()) {
            String questionId = entry.getKey();
            String selectedAnswer = entry.getValue();
            String correctAnswer = quizSession.getCorrectAnswers().get(questionId);

            boolean correct = correctAnswer.equals(selectedAnswer);
            if(correct) {
                score++;
            }

            CheckAnswerResult result = new CheckAnswerResult();
            result.setQuestionId(questionId);
            result.setSelectedAnswer(selectedAnswer);
            result.setCorrectAnswer(correctAnswer);
            result.setCorrect(correct);
            results.add(result);
        }

        CheckAnswersResponse response = new CheckAnswersResponse();
        response.setScore(score);
        response.setTotal(quizSession.getCorrectAnswers().size());
        response.setResults(results);

        sessions.remove(submission.getSessionId());

        return response;
    }
}
