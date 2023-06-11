import React from 'react'
import { Link } from 'react-router-dom'
function PerformanceTable(props) {
    return (
        <tr >
            <td>{props.quiz.topic}</td>
            <td>{props.quiz.courseName}</td>
        <td>{props.quiz.date}</td>
        <td>{props.quiz.traineeName}</td>
        <td>{props.quiz.marks}</td>
        </tr>
      )
}

export default PerformanceTable
