import React, { useState,useEffect } from 'react'
import axios from 'axios';
import './ComponentsCSS/EditCourse.css';
import AddForm from './AddForm';
import TopicTable from './TopicTable';
import Table  from 'react-bootstrap/Table';
function EditCourse() {
    const [title,setTitle] = useState('');
    const [course,setCourse] = useState();
    const [topics,setTopics] = useState([]);
    const [getTopics,setGetTopics] = useState();
    const set = name => {
        return ({ target: { value } }) => {
          setCourse(oldValues => ({...oldValues, [name]: value }));
        }
    };

    useEffect(() => {
        { course != null && getTopics != null &&
            axios.get(`/api/course/allTopics`,{params: {id : course.id}},
                {headers: {
                'Content-Type': 'application/json',
                'Authorization': window.localStorage.getItem("token")
            }}).then((data) => {console.log(data.data);
                setTopics(data.data);
            }
            ).catch(error => {
                if(error.response.status === 403){
                  window.location.href = '/logout'
              }
              else{
                alert(error.response.data.message);
              }})}
    },[getTopics])

    const removeTopic = (removedTopic) => {
        setTopics(topics => topics.filter(topic => topic !== removedTopic));
        axios.get(`/api/course/deleteTopicsFromCourse`,{params: {name:removedTopic, course_id: course.id}},{headers: {
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

    const addTopics = (topic) =>{
        setTopics(topics => ([...topics,topic]));
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        await axios.get(`/api/course`,{params: {title}},
        {headers: {
            'Content-Type': 'application/json',
            'Authorization': window.localStorage.getItem("token")
          }})
          .then((data) => {console.log(data.data);
            setCourse(data.data);
            setGetTopics(title);
            }
          ).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
    }

    const handleUpdate = async (e) => {
        e.preventDefault();
        const response = await axios.put(`/api/course`,JSON.stringify({id: course instanceof Object && course.id,title: course instanceof Object && course.title,
           batch: course instanceof Object && course.batch instanceof Object ? course.batch.name: course.batch,
           startDate: course instanceof Object && course.startDate,endDate: course instanceof Object && course.endDate,
           status: course instanceof Object && course.status,topics: topics ? topics:[]}),
           {headers: {
            'Content-Type': 'application/json',
           }}).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }});
        if(response.status === 202){
            alert("Course update Successful");
        }
    }

    const handleDelete = async (e) => {
        e.preventDefault();
        const response = await axios.delete(`/api/course`,{params:{id: course.id}},{headers: {
            'Content-Type': 'application/json',
            'Authorization': window.localStorage.getItem("token")
          }}).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
          if(response.status === 202){
            console.log(response);
            setCourse();
            setTopics([]);
            alert("Delete successful");
          }
    }

  return (
    <div className='course-update-div'>
        <form action="" className="search-form" onSubmit={handleSubmit}>
            <h3>Search Course by title: </h3>
            <label className="search-label">Title: </label>
            <input className="search-input" type="text" value={title} required onChange={(e) => setTitle(e.target.value)}/>
            <input type="submit" className="search-button" value="Search"></input>
        </form>
        <form className='course-update-form' onSubmit={handleUpdate}>
            <label className='course-update-label'>Course Title:</label>
            <input className='course-update-input' value={course instanceof Object ? course.title : ""} onChange={set('title')}></input>
            <label className='course-update-label'>Course Batch:</label>
            <input className='course-update-input' value={course instanceof Object  ? course.batch instanceof Object ? course.batch.name : course.batch : ""} onChange={set('batch')}></input>
            <label className='course-update-label'>Course Status:</label>
            <select value={course instanceof Object && course.status} onChange={set('status')} className="course-input">
              <option value="UP-COMING" className="course-input">Up-Coming</option>
              <option value="ON-GOING" className="course-input">On-Going</option>
              <option value="ENDED" className="course-input">Ended</option>
            </select>
            <label className='course-update-label'>Start Date: </label>
            <input type="date" value={course instanceof Object && course.startDate} className="course-update-input" onChange={set('startDate')}></input>
            <label className='course-update-label'>End Date: </label>
            <input type="date" value={course instanceof Object && course.endDate} className="course-update-input" onChange={set('endDate')}></input>
            <label className='course-update-label'>Topic name:</label>
            <AddForm onSubmit={addTopics}></AddForm>
            <input type="submit" value="Update Course" className='course-update-button'></input>
        </form>
        <Table className="allTopic-table" striped hover>
                <thead>
                    <tr>
                    <th>Topics Name</th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                    {topics.length > 0 ? topics.map((topic)=>{
                    return(
                        <><TopicTable topic={topic} onClick={removeTopic}/>
                        </>
                        )
                    }
                    ): <tr>No topics in this course</tr>}
                    
                </tbody>
            </Table>
    </div>
  )
}

export default EditCourse
