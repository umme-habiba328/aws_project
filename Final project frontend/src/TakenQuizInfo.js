import React from 'react'
import {Card,ListGroup} from 'react-bootstrap';
import './Components/ComponentsCSS/AnswerForm.css';
function TakenQuizInfo(props) {
  return (
    <div>
      <h2 className='take-quiz-heading'>Quiz already finished</h2>
      <Card className="mt-3">
      <Card.Header>{props.quiz.id}.{props.quiz.topic}</Card.Header>
      <ListGroup variant="flush">
        <h3 className='taken-quizinfo-heading'>Score: {props.quiz.obtainedMarks} out of {props.quiz.totalMarks}</h3>
        <text className='taken-quizinfo-heading'>Quiz Taken at: {props.quiz.date}</text>
      </ListGroup>
    </Card>
    </div>
  )
}

export default TakenQuizInfo
