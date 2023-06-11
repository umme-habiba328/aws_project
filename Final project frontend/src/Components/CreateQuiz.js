import React, { useState } from 'react'
import QuestionForm from './QuestionForm';
import Card from 'react-bootstrap/Card';
import './ComponentsCSS/CreateQuiz.css';
import axios from 'axios';
import { CloseButton,ListGroup,ListGroupItem } from 'react-bootstrap';
function CreateQuiz() {
    const [quiz,setQuiz] = useState({course:'',topic:'',startTime:''});
    const [questions,setQuestions] = useState([])
    const [totalMarks,setTotalMarks] = useState(0);
    const set = name => {
      return ({ target: { value } }) => {
        setQuiz(oldValues => ({...oldValues, [name]: value }));
      }
  };

  const removeQuestion = (removedQuestion) => {
    console.log(removedQuestion.questionDescription);
    setQuestions(questions => questions.filter(question => question.questionDescription !== removedQuestion.questionDescription));
  }

  const addQuestion = question =>{
    setQuestions(questions => ([...questions,question]));
    const marks = totalMarks + parseInt(question.marks);
    setTotalMarks(marks);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await axios.post(`/api/quiz`, JSON.stringify({startTime: quiz.startTime,course:quiz.course,totalMarks: totalMarks,topic:quiz.topic,questions: questions}),
    {headers: {
      'Content-Type': 'application/json'
    }}).catch(error => {
      if(error.response.status === 403){
        window.location.href = '/logout'
    }
    else{
      alert(error.response.data.message);
    }})

    if(response.status === 201){
      setQuiz({course:'',topic:'',startTime:''});
      setQuestions([]);
      alert("Quiz created successFully");
    }
  }

  return (
    <div>
    <div className='create-quiz-div'>
      <h2 className='create-quiz-heading'>Create Quiz</h2>
      <form className='create-quiz-form' onSubmit={handleSubmit}>
        <label className='create-quiz-label'>Course Name:</label>
        <input type="text" className='create-quiz-input' value={ quiz.course } onChange={set('course')}></input>
        <label className='create-quiz-label'>Topic:</label>
        <input type="text" className='create-quiz-input' value={ quiz.topic } onChange={set('topic')}></input>
        <label className='create-quiz-label'>Start Time:</label>
        <input type="datetime-local" className='create-quiz-input' value={ quiz.startTime } onChange={set('startTime')}></input>
        <hr size="5"/>
        <QuestionForm onSubmit={addQuestion}/>
        <input className='create-quiz-button' type="submit" value="Create Quiz"></input>
        </form>
      </div>
        {questions.length > 0 ? questions.map(question => {

          return(
          <>
          <Card className="mt-3">
            <Card.Header>{question.questionDescription}<CloseButton className='card-header-closebutton' onClick={() => removeQuestion(question)}></CloseButton></Card.Header>
            <ListGroup variant="flush">
                {question.options?.map(option => {
                  return(
                    <>
                    <ListGroup.Item className='quiz-listgroup'>{option}</ListGroup.Item>
                    </>
                    )
                })}
                <ListGroup.Item className='quiz-listgroup'>Answer: {question.answer}</ListGroup.Item>
              </ListGroup>
            </Card>
          </>)
        }): ""}
    </div>
  )
}

export default CreateQuiz
