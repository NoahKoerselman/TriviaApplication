package com.noah.trivia;

import com.noah.trivia.opentdb.OpenTDBQuestion;
import com.noah.trivia.opentdb.OpenTDBResponse;
import com.noah.trivia.response.CheckAnswersResponse;
import com.noah.trivia.response.QuizResponse;
import com.noah.trivia.response.QuizSubmission;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for handling all trivia quiz related HTTP requests.
 *
 * <p>This controller acts as the entry point between the frontend (e.g. React application)
 * and the backend of the trivia system. It exposes endpoints that allow clients to
 * retrieve quiz questions and potentially submit answers or fetch scores.</p>
 *
 * <p>The controller itself does not contain business logic. Instead, it delegates
 * data processing and retrieval to a service layer (e.g. TriviaService), which
 * handles the core application logic such as fetching questions from a database
 * or external API.</p>
 *
 * <p>Typical responsibilities include:</p>
 * <ul>
 *   <li>Providing an endpoint to retrieve trivia questions</li>
 *   <li>Handling incoming HTTP requests and returning JSON responses</li>
 *   <li>Coordinating with the service layer to process quiz-related operations</li>
 * </ul>
 *
 * <p>This separation of concerns keeps the controller lightweight and focused
 * on request/response handling.</p>
 */
@RestController
public class Controller {
    private TriviaService service;

    public Controller(TriviaService service) {
        this.service = service;
    }

    @GetMapping("/questions")
    public QuizResponse getQuestions( @RequestParam int amount,
                                      @RequestParam(required = false) String difficulty,
                                      @RequestParam(required = false) Integer category) {
        return service.getQuestions(amount, difficulty, category);
    }

    @PostMapping("/checkanswers")
    public CheckAnswersResponse checkAnswers(@RequestBody QuizSubmission submission) {
        return service.checkAnswers(submission);
    }

}
