import React, { useState,useEffect } from 'react'
import { useParams } from 'react-router-dom'
import PerformanceTable from './PerformanceTable';
import Table from 'react-bootstrap/esm/Table';
import axios from 'axios';
function AllTakenQuiz() {

    const {id} = useParams();

    const [takenQuizzes,setTakenQuizzes] = useState();

    useEffect(() =>  {
      axios.get(`/api/takeQuiz/allTakenQuizPerformance`,{params: {quizId:id}},{headers: {
        'Content-Type': 'application/json',
      }})
      .then((data) => {setTakenQuizzes(data.data);
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
        <h2>Perfomance of all Trainees</h2>
        <Table className="allQuiz-table" striped bordered hover>
    <thead>
      <tr>
        <th>Topic</th>
        <th>Course</th>
        <th>Date</th>
        <th>Trainee</th>
        <th>Obtained Marks</th>
      </tr>
    </thead>
    <tbody>
      {takenQuizzes?.map((quiz)=>{
        return(
          <><PerformanceTable quiz={quiz}/></>
          )
        }
      )}
    </tbody>
  </Table>
  </>
      )
}

export default AllTakenQuiz
