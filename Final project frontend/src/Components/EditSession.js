import React, { useEffect, useState } from 'react';
import { useParams,Navigate } from 'react-router-dom';
import './ComponentsCSS/CreateSession.css';
import axios from 'axios';
function EditSession() {
  const [session,setSession] = useState();
  const {id} = useParams();

  const set = name => {
    return ({ target: { value } }) => {
      setSession(oldValues => ({...oldValues, [name]: value }));
    }
  };

  useEffect(() =>{
    axios.get(`/api/trainingSession`,{params: {id:id}},
        {headers:{
            'Content-Type': 'application/json',
        }}).then(data => {console.log(data.data);
            setSession(data.data);
          })
        .catch(error => {
          if(error.response.status === 403){
            window.location.href = '/logout'
        }
        else{
          alert(error.response.data.message);
        }})
  },[])

  const handleUpdate = async (e) =>{
    e.preventDefault();
    const response = await axios.put(`/api/trainingSession`,JSON.stringify({id: session instanceof Object && session.id,course: session instanceof Object && session.courseTitle,
      trainer: session instanceof Object && session.trainerName,date: session instanceof Object && session.date,
      startTime: session instanceof Object && session.startTime,endTime: session instanceof Object && session.endTime,
      status: session instanceof Object && session.status,topic: session instanceof Object && session.topic}),
      {headers: {
       'Content-Type': 'application/json',
      }}).catch(error => { if(error.response.status === 403){
        window.location.href = '/logout'
      }
      else{
        alert(error.response.data.message);
      }})
        
   if(response.status === 202){
       alert("Session update Successful");
   }
  }

  return (
    <div className='session-div'>
      <h2>Edit Scheduled Training Session</h2>
      <form className='session-form' onSubmit={handleUpdate}>
            <label className='session-label'>Course Title:</label>
            <input className='session-input' value={session instanceof Object ? session.courseTitle : ""} onChange={set('courseTitle')}></input>
            <label className='session-label'>Trainer name:</label>
            <input className='session-input' value={session instanceof Object  ? session.trainerName : ""} onChange={set('trainerName')}></input>
            <label className='session-label'>Session Status:</label>
            <select value={session instanceof Object && session.status} onChange={set('status')} className="course-input">
              <option value="SCHEDULED" className="session-input">Scheduled</option>
              <option value="ON-GOING" className="session-input">On-Going</option>
              <option value="ENDED" className="session-input">Ended</option>
            </select>
            <label className='session-label'>Date: </label>
            <input type="date" value={session instanceof Object && session.date} className="session-input" onChange={set('date')}></input>
            <label className='session-label'>Start Time: </label>
            <input type="time" value={session instanceof Object && session.startTime} className="session-input" onChange={set('startTime')}></input>
            <label className='session-label'>End time: </label>
            <input type="time" value={session instanceof Object && session.endTime} className="session-input" onChange={set('endTime')}></input>
            <label className='session-label'>Topic name:</label>
            <input className='session-input' value={session instanceof Object && session.topic} onChange={set('topic')}></input>
            <input type="submit" value="Update Session" className='session-button'></input>
        </form>
    </div>
  )
}

export default EditSession
