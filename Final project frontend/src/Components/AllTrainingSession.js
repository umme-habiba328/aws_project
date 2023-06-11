import axios from 'axios';
import React, { useEffect, useState } from 'react';
import './ComponentsCSS/AllSession.css';
import Table from 'react-bootstrap/Table';
import SessionTable from './SessionTable';
function AllTrainingSession() {
    const current = new Date().toISOString().slice(0,10);
    const [date,setDate] = useState(current);
    const [course,setCourse] = useState('');
    const [sessions,setSessions] = useState();
    useEffect(() => {
        axios.get(`/api/trainingSession/table`,{params: {date:date, course: course}},
        {headers:{
            'Content-Type': 'application/json'
        }}).then(data => {console.log(data.data);
            setSessions(data.data)})
        .catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
    },[])

    const handleSubmit = (e)  =>{
        e.preventDefault();
        console.log(date);
        axios.get(`/api/trainingSession/table`,{params: {date: date.length> 0 ? date:'',course: course}},
        {headers:{
            'Content-Type': 'application/json',
            'Authorization': window.localStorage.getItem("token")
        }}).then(data => {console.log(data.data);
            setSessions(data.data);
        })
        .catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
    }

    const removeSession =  (removedSession) => {
        setSessions(sessions => sessions.filter(session => session.id !== removedSession));
        axios.delete(`/api/trainingSession`,{params: {id: removedSession}},
        {headers:{
            'Content-Type': 'application/json',
            'Authorization': window.localStorage.getItem("token")
        }}).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
    }

  return (
    <div>
    <div className='allSession-div'>
        <h2>View Scheduled Training Session: </h2>
        <form className='allSession-form' onSubmit={handleSubmit}>
            <label className='allSession-label'>Date: </label>
            <input type="date" className='allSession-input' value={date}  onChange={e => setDate(e.target.value)}></input>
            <label className='allSession-label'>Course Title: </label>
            <input type="text" className='allSession-input' value={course}  onChange={e => setCourse(e.target.value)}></input>
            <input type="submit" className='allSession-button' value='Search'></input>
        </form>
    </div>
    <Table>
        <thead>
            <tr>
                <th>Course Title</th>
                <th>Trainer Name</th>
                <th>Topic</th>
                <th>Status</th>
                <th>Date</th>
                <th>Start time</th>
                <th>endTime</th>
            </tr>
        </thead>
        <tbody>
            {sessions instanceof Array  && sessions.length > 0 ?  sessions?.map((session)=>{
            return(
                <><SessionTable session={session} onClick = {removeSession}/></>
                )
            }
            ): <tr>No session on this date</tr>}
        </tbody>
    </Table>
    </div>
  )
}

export default AllTrainingSession
