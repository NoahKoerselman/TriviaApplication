import { useState } from 'react';
import he from "he";
import './App.css';
import StartScreen from './components/StartScreen';

function App() {
  const [questions, setQuestions] = useState(null);
  const [currentIndex, setCurrentIndex] = useState(0);
  const [score, setScore] = useState(0);
  const [userAnswers, setUserAnswers] = useState({});

  const [loading, setLoading] = useState(false);
  const [quizInProgress, setQuizInProgress] = useState(false);

  const [showWarning, setShowWarning] = useState(false);

  const [results, setResults] = useState(null);

  const [homeScreen, setHomeScreen] = useState(true);

  async function fetchQuestions(options) {
    setLoading(true);
    const { amount, category, difficulty } = options;
    let url = `/questions?amount=${amount}`;

    if (category) {
      url += `&category=${category}`;
    }

    if (difficulty) {
      url += `&difficulty=${difficulty}`;
    }

    const response = await fetch(url);
    const data = await response.json();
    setQuestions(data);
    setCurrentIndex(0);
    setScore(0);
    setUserAnswers({});
    setLoading(false);
    setQuizInProgress(true);
    setResults(null);
    setHomeScreen(false);
  }

  function selectAnswer(questionId, answer) {
    setUserAnswers(prev => ({
      ...prev,
      [questionId]: answer
    }))
  }

  function handleSubmit() {
    if (!answeredAllQuestions) {
      setShowWarning(true);
      return;
    }

    submitAnswers();
  }

  async function submitAnswers() {
    setLoading(true);
    console.log('submit');
    console.log(questions);
    const response = await fetch('/checkanswers', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        sessionId: questions.sessionId,
        answers: userAnswers
      })
    })
    const data = await response.json();
    setResults(data);
    setLoading(false);
  }

  function goToHomeScreen() {
    setQuestions(null);
    setHomeScreen(true);
  }

  const isLastQuestion = questions && currentIndex === questions.questions.length - 1;
  const answeredAllQuestions = questions && Object.keys(userAnswers).length === questions.questions.length;

  return (
    <div className='app-container'>
      <h1 className='title'>TRIVIA QUIZ</h1>

      {/* Start Screen */}
      {!questions && !loading && homeScreen && (
        <div className='card'>
          <StartScreen onStart={fetchQuestions}/>
        </div>
      )}

      {/* Loading */}
      {loading && (
        <div className='card'>
          <p className='loading'>Loading...</p>
        </div>
      )}

      {/* Quiz scherm */}
      {quizInProgress && !results && (
        <div className='card'>
          <p className='progress'>Question {currentIndex + 1} of {questions.questions.length}</p>
          <span className='badge'>{he.decode(questions.questions[currentIndex].category)}</span>
          <span className={`badge ${questions.questions[currentIndex].difficulty}`}>{questions.questions[currentIndex].difficulty}</span>

          <h2 className='question'>{he.decode(questions.questions[currentIndex].question)}</h2>

          <div className='answers'>
            {questions.questions[currentIndex].answers.map((answer) => (
              <button
                key={answer}
                className={`answer-btn ${userAnswers[questions.questions[currentIndex].id] === answer ? 'selected' : ''}`}
                onClick={() => selectAnswer(questions.questions[currentIndex].id, answer)}
              >
                {he.decode(answer)}
              </button>
            ))}
          </div>

          {/* Navigatie */}
          <div className='navigation'>
            <button
              className='previous-btn'
              onClick={() => setCurrentIndex(i => i - 1)}
              disabled={currentIndex === 0}
            >
              ← Previous
            </button>

            {isLastQuestion ? (
              <button className='submit-btn' onClick={handleSubmit}>
                Submit ✓
              </button>
              ) : (
              <button
                className='next-btn'
                onClick={() => setCurrentIndex(i => i + 1)}
                disabled={isLastQuestion}
              >
                Next →
              </button>
            )}
          </div>
        </div>
      )}


      {/* POP UP */}
      {showWarning && (
        <div className='warning'>
          <p>You have not filled in all questions. Submit anyway?</p>

          <button className='backtoquiz-btn' onClick={() => setShowWarning(false)}>
            Back to quiz
          </button>

          <button
            className='submitanyway-btn'
            onClick={() => {
              setShowWarning(false);
              submitAnswers();
            }}
          >
            Submit anyway
          </button>
        </div>
      )}

      {results && !loading && !homeScreen && (
        <div className='results'>
          <div className='score'>
            <h2>{results.score} / {results.total}</h2>
            <p>
              {results.score === results.total && '🎉 Perfect!'}
              {results.score >= results.total / 2 && results.score < results.total && '👍 Well Done!'}
              {results.score < results.total / 2 && '💪 Better luck next time!'}
            </p>
          </div>

          {results.results.map((result) => (
            <div
              key={result.questionId}
              className={`result-item ${result.correct ? 'correct' : 'incorrect'}`}
            >
              <strong>{result.correct ? '✅ Correct!' : '❌ Incorrect!'}</strong>
              <p>Your answer: {result.selectedAnswer}</p>
              <p>Correct answer: {result.correctAnswer}</p>
            </div>
          ))}

          <div className='gotohomescreen'>
            <button className="gotohomescreen-btn" onClick={goToHomeScreen}>
              Go to home screen
            </button>
          </div>
        </div>
      )}

      
    </div>
  )
}

export default App;
