import axios from 'axios';
import React, { useState } from 'react'
import './ComponentsCSS/CreateSession.css';
function ScheduleTrainingSession() {
    const [session,setSession] = useState({trainer:'',course:'',status: '',date:'', startTime:'', endTime:'', topic:''});

    const set = name => {
        return ({ target: { value } }) => {
          setSession(oldValues => ({...oldValues, [name]: value }));
        }
    };

    const handleSubmit = async (e) =>{
        e.preventDefault();
        console.log(session.startTime);
        const response = await axios.post(`/api/trainingSession`, JSON.stringify({trainer: session.trainer,course: session.course,status: session.status,date: session.date,
            startTime: session.startTime, endTime: session.endTime,topic:session.topic}),
            {headers: {
                'Content-Type': 'application/json',
                'Authorization': window.localStorage.getItem("token")
            }}).catch(error => {
              if(error.response.status === 403){
                window.location.href = '/logout'
            }
            else{
              alert(error.response.data.message);
            }})
        
        if(response.status === 201){
            setSession({trainer:'',course:'',status: '',date:'', startTime:'', endTime:'', topic:''});
            alert("Session registered successfully");
        }
    }

  return (
    <div className='session-div'>
        <h2 className='session-heading'>Schedule Training Session</h2>
      <form className='session-form' onSubmit={handleSubmit}>
            <label className='session-label'>Course Title:</label>
            <input className='session-input' value={session.course} onChange={set('course')}></input>
            <label className='session-label'>Trainer name:</label>
            <input className='session-input' value={session.trainer} onChange={set('trainer')}></input>
            <label className='session-label'>Course Status:</label>
            <select value={session.status} onChange={set('status')} className="course-input">
                <option></option>
              <option value="SCHEDULED" className="session-input">SCHEDULED</option>
              <option value="ON-GOING" className="session-input">On-Going</option>
              <option value="ENDED" className="session-input">Ended</option>
            </select>
            <label className='session-label'>Date: </label>
            <input type="date" value={session.date} className="session-input" onChange={set('date')}></input>
            <label className='session-label'>Start Time: </label>
            <input type="time" value={session.startTime} className="session-input" onChange={set('startTime')}></input>
            <label className='session-label'>End time: </label>
            <input type="time" value={session.endTime} className="session-input" onChange={set('endTime')}></input>
            <label className='session-label'>Topic name:</label>
            <input className='session-input' value={session.topic } onChange={set('topic')}></input>
            <input type="submit" value="Create Session" className='session-button'></input>
        </form>
    </div>
  )
}

export default ScheduleTrainingSession
