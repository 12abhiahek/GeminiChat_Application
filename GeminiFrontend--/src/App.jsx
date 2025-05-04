import { useState } from 'react'
import './App.css'
import axios from 'axios';
import ReactMarkdown from 'react-markdown';



// function App() {
//   const [question, setQuestion] = useState('');
//   const [answer, setAnswer] = useState('');
//   const [loading, setLoading] = useState(false);

//   const handleAsk = async () => {
//     if (!question.trim()) return;
//     setLoading(true);
//     try {
//       const response = await axios.post('http://localhost:8080/ai/qna/ask', {
//         question: question
//       });
//       setAnswer(response.data);
//     } catch (error) {
//       console.error('Error asking the question:', error);
//       setAnswer('Something went wrong.');
//     } finally {
//       setLoading(false);
//     }
//   };

//   return (
//     <div className="app-container">
//       <h1>Gemini Q&A Chat</h1>

//       <div className="chat-window">
//         <div className="answer-box">
//           <h3>Answer:</h3>
//           <p>{answer}</p>
//         </div>
//       </div>

//       <div className="input-area">
//         <textarea
//           rows="3"
//           placeholder="Ask a question..."
//           value={question}
//           onChange={(e) => setQuestion(e.target.value)}
//         />
//         <button onClick={handleAsk} disabled={loading}>
//           {loading ? 'Asking...' : 'Ask Gemini'}
//         </button>
//       </div>
//     </div>
//   );
// }

function App() {
  const [question, setQuestion] = useState('');
  const [answer, setAnswer] = useState('');
  const [loading, setLoading] = useState(false);

  const handleAsk = async () => {
    if (!question.trim()) return;
    setLoading(true);
    try {
      const response = await axios.post('http://localhost:8080/ai/qna/ask', {
        question: question
      });
      setAnswer(response.data);
    } catch (error) {
      console.error('Error asking the question:', error);
      setAnswer('Something went wrong.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="app-container">
      <h1>Gemini Q&A Chat</h1>

      <div className="chat-window">
        <div className="answer-box">
          <h3>Answer:</h3>
          <ReactMarkdown>{answer}</ReactMarkdown> {/* Changed here */}
        </div>
      </div>

      <div className="input-area">
        <textarea
          rows="3"
          placeholder="Ask a question..."
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
        />
        <button onClick={handleAsk} disabled={loading}>
          {loading ? 'Asking...' : 'Ask Gemini'}
        </button>
      </div>
    </div>
  );
}




export default App
