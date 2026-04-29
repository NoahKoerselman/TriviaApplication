import { useState } from 'react';
import './startScreen.css';

function StartScreen({onStart}) {
  {/* START OF QUIZ */}
  const [amount, setAmount] = useState(10);
  const [category, setCategory] = useState("");
  const [difficulty, setDifficulty] = useState("");
  const [type, setType] = useState("");

  function handleStart() {
    onStart({
      amount,
      category,
      difficulty,
    });
  }

  return (
    <div className="start-screen">
      <h2>Customize Your Quiz</h2>
      {/* Number of questions */}
      <div className='option'>
        <label>Amount of Questions:</label>
        <input
          type="number"
          min="1"
          max="50"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
      </div>

      {/* Category */}
      <div className='option'>
        <label>Category:</label>
        <select
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        >
          <option value="">Any category</option>
          <option value="9">General Knowledge</option>
          <option value="21">Sports</option>
          <option value="23">History</option>
          <option value="17">Science & Nature</option>
          <option value="28">Vehicles</option>
        </select>
      </div>

      {/* Difficulty */}
      <div className='option'>
        <label>Difficulty:</label>
        <select
          value={difficulty}
          onChange={(e) => setDifficulty(e.target.value)}
        >
          <option value="">Any difficulty</option>
          <option value="easy">Easy</option>
          <option value="medium">Medium</option>
          <option value="hard">Hard</option>
        </select>
      </div>

      <button className='start-quiz' onClick={handleStart}>
        Start Quiz 🚀
      </button>
    </div>
  )

}

export default StartScreen;