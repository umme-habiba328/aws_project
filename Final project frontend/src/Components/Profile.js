import React, { useState,useEffect } from 'react'
import axios from 'axios';
import './ComponentsCSS/Profile.css';
import AddForm from './AddForm';
import CloseButton from 'react-bootstrap/esm/CloseButton';
import { Table } from 'react-bootstrap';
function Profile() {
    const [employee, setEmployee] = useState({id:'',name:'',password:'',role:'',email:'',contactNumber:'+880',batch:'',course:''});
    const [courses,setCourses] = useState([])
  
        const set = name => {
          return ({ target: { value } }) => {
            setEmployee(oldValues => ({...oldValues, [name]: value }));
          }
        };
  
        const courseToTitle = (trainerCourses) =>{
            trainerCourses.forEach(element => {
              addCourses(element.title);
            });
        } 
  
        const addCourses = (course) =>{
          setCourses(oldCourses => ([...oldCourses,course]));
        };
  
        const removeCourse = (removedCourse) => {
          setCourses(courses => courses.filter(course => course !== removedCourse))
          console.log(courses);
        }
  
        useEffect(() =>  {
          axios.get(`/api/trainee`,{headers: {
            'Content-Type': 'application/json'
          }})
          .then((data) => {console.log(data.data);
            setEmployee({id:'',name:'',password:'',role:'',email:'',contactNumber:'+880',batch:'',course:''})
            setEmployee(data.data);
            setCourses([])
            courseToTitle(data.data.courses);}
          ).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
        },[])
  
          const handleUpdate =async (e)=>{
            e.preventDefault();
              const response = await axios.put(`/api/employee`,
            JSON.stringify({id:employee.id, name:employee.name, password:employee.password, role:employee.role, email:employee.email,
            contactNumber:employee.contactNumber,batch: employee.batch instanceof Object ? employee.batch.name:employee.batch , 
            course:employee.course instanceof Object ? employee.course.title : employee.course, courses: courses ? courses:['']}),{
              headers: {
                'Content-Type': 'application/json'
              }
            }).catch(error => {
              if(error.response.status === 403){
                window.location.href = '/logout'
            }
            else{
              alert(error.response.data.message);
            }});
              
              if(response.status === 200){
                alert("Update successful");
              }
          }
  
      return(
          <div className="update-div">
          <h2 className="update-heading">{employee.role === "ROLE_ADMIN" ? 
                                          "Admin": employee.role === "ROLE_TRAINER" ? 
                                          "Trainer": employee.role === "ROLE_TRAINEE" ?
                                        "Trainee" : "Employee"} Profile</h2>
          <form action="" className="update-form" onSubmit={handleUpdate}>
              <label className="update-label">ID: </label>
              <text>{employee.id}</text>
              <label className="update-label">Name: </label>
              <input className="update-input" value={employee.name} type="text" name="name" required pattern="[a-zA-Z0-9 ]+" onChange={set('name')}/>
              <label className="update-label">Password </label>
              <input className="update-input" value={employee.password} type="text" name="password" min="8" required pattern="[a-zA-Z0-9@#._]+" onChange={set('password')}/>
              <label className="update-label">Email: </label>
              <input className="update-input" value={employee.email} type="text" name="email" required onChange={set('email')}></input>
              <label className="update-label">Contact Number: </label>
              <input className="update-input" value={employee.contactNumber} name="contactNumber" onChange={set('contactNumber')}/>
              {employee.role === "ROLE_TRAINEE" && 
              <>
              <label className="update-label">Batch: </label>
              <input className="update-input" value={employee.batch instanceof Object ? employee.batch.name:employee.batch} onChange={set('batch')}/>
              <label className="update-label">Course: </label>
              <input className="update-input" value={employee.course instanceof Object ? employee.course.title:employee.course} onChange={set('course')}/>
              </>}
              {employee.role === "ROLE_TRAINER" && 
              
                <>
                <label className='update-label'>Courses: </label>
                <AddForm onSubmit={addCourses}/>
                
                <Table><thead>
                        <tr>
                          <th>Course Name</th>
                        </tr>
                        </thead>
                        {courses?.map(course=>
                        <tr>
                        <td>{course}</td>
                        <td><CloseButton data-toggle="tooltip"
                              data-placement="top" 
                              title="Delete" 
                              className='update-closebutton' 
                              onClick={()=>removeCourse(course)}/>
                        </td>
                          </tr>)}</Table></>
              }
              <input type="submit" className="update-button" value="Update"></input>
          </form>
          </div>
      )
}

export default Profile
