import React from 'react'
import {useState,useEffect} from 'react';
import axios from 'axios';
import Table from 'react-bootstrap/Table';
import QuizTable from './QuizTable';
import './ComponentsCSS/AllQuiz.css';

function AllScheduledQuiz() {
    const [quizzes,setQuizzes] = useState();

    useEffect(() =>  {
      axios.get(`/api/quiz/allScheduledQuizForCourse`,{headers: {
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
        <h2>All Scheduled Quiz for your course</h2>
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
          <><QuizTable quiz={quiz}/></>
          )
        }
      )}
    </tbody>
  </Table>
  </>
      )
}

export default AllScheduledQuiz
