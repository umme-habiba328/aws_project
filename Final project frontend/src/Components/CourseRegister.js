import axios from 'axios';
import React, { useState } from 'react';
import './ComponentsCSS/CourseRegister.css';
function CourseRegister() {

    const [course,setCourse] = useState({batch:'',title:'',status:'',startDate:'', endDate:''})
    const set = name => {
        return ({ target: { value } }) => {
          setCourse(oldValues => ({...oldValues, [name]: value }));
        }
      };

      const handleSubmit = async (e) => {
        e.preventDefault()
        console.log(course);
            const response = await axios.post(`/api/course`,JSON.stringify({batch: course.batch,title : course.title,status:course.status,startDate: course.startDate, endDate: course.endDate}),
            {headers:{
                'Content-Type': 'application/json',
                'Authorization': window.localStorage.getItem("token")}
            }).catch(error => {
              if(error.response.status === 403){
                window.location.href = '/logout'
            }
            else{
              alert(error.response.data.message);
            }})
            console.log(response);
            if(response.status === 201){
                setCourse({title:'',status:'',startDate:'',endDate:''})
                alert("Course added successfully")
            }
      }

  return (
    <div className='course-div'>
        <h2>Create Course</h2>
      <form className='course-form' onSubmit={handleSubmit}>
      <label className='course-label'>Batch Name: </label>
        <input type="text" value={course.batch} className="course-input" required onChange={set('batch')}></input>
        <label className='course-label'>Course title: </label>
        <input type="text" value={course.title} className="course-input" required onChange={set('title')}></input>
        <label className='course-label'>Course status: </label>
        <select value={course.status} onChange={set('status')} className="course-input">
          <option></option>
          <option value="UP-COMING" className="course-input">Up-Coming</option>
          <option value="ON-GOING" className="course-input">On-Going</option>
          <option value="ENDED" className="course-input">Ended</option>
        </select>
        <label className='course-label'>Start Date: </label>
        <input type="date" value={course.startDate} className="course-input" onChange={set('startDate')}></input>
        <label className='course-label'>End Date: </label>
        <input type="date" value={course.endDate} className="course-input" onChange={set('endDate')}></input>
        <input type="submit" className="course-button" value="Add Course"></input>
      </form>
    </div>
  )
}

export default CourseRegister
