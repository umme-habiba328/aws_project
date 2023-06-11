import React from 'react'
import {useState,useEffect} from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import QuizTable from './QuizTable';
import PerformanceTable from './PerformanceTable';
import {Link} from 'react-router-dom';
import './ComponentsCSS/AllQuiz.css';

function AllQuizForTrainer() {
    const [quizzes,setQuizzes] = useState();

    useEffect(() =>  {
      axios.get(`/api/quiz/allQuizForTrainer`,{headers: {
        'Content-Type': 'application/json',
      }})
      .then((data) => {setQuizzes(data.data);
        console.log(data);
      }).catch(error => {
        if(error.response.status === 403){
          window.location.href = '/logout'
      }
      else{
        alert(error.response.data.message);
      }}) },[])
  
      return(

        <>
        <h2>All Scheduled Quiz</h2>
        <Table className="allQuiz-table" striped bordered hover>
    <thead>
      <tr>
        <th>Course</th>
        <th>Date</th>
        <th>Status</th>
      </tr>
    </thead>
    <tbody>
      {quizzes?.map((quiz)=>{
        return(
          <tr >
            <td>{quiz.courseName}</td>
        <td>{quiz.startTime}</td>
        <td>{quiz.status}</td>
        <td><Link to={`/quizPerformance/${quiz.id}`} className='btn btn-primary'> Performance</Link></td>
        </tr>
          )
        }
      )}
    </tbody>
  </Table>
  </>
      )
}

export default AllQuizForTrainer
