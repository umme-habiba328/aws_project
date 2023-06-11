import React from 'react'
import { CloseButton } from 'react-bootstrap';
import { Link } from 'react-router-dom';
function QuizTable(props) {
  return (
    <tr >
        <td>{props.quiz.courseName}</td>
        <td>{props.quiz.startTime}</td>
        <td>{props.quiz.status}</td>
        <td><Link to={`/takeQuiz/${props.quiz.id}`} className='btn btn-primary'> Attend quiz</Link></td>
        </tr>
  )
}

export default QuizTable
