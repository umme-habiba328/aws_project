import { useState, useEffect } from "react";
import "./ComponentsCSS/RegisterForm.css";
import axios from "axios";
import AddForm from "./AddForm";
const EmployeeRegister = () => {
    const [employee, setEmployee] = useState({name:'',password:'',role:'',email:'',contactNumber:'+880'});
    const [courses,setCourses] = useState([])
    const set = name => {
        return ({ target: { value } }) => {
          setEmployee(oldValues => ({...oldValues, [name]: value }));
        }
      };

    const addCourses = (course) =>{
        const newCourse = [course,...courses];
        setCourses(newCourse);
    }
    const handleSubmit= async (e) =>{
        e.preventDefault();
        const response = await axios.post(`/api/employee`,
        JSON.stringify({name:employee.name,password:employee.password,role:employee.role,email:employee.email,contactNumber:employee.contactNumber,courses: courses ? courses:''}),{
            headers: {
              'Content-Type': 'application/json',
              'Authorization': window.localStorage.getItem("token")
            }
          }).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }});
          if(response.status === 201){
            setCourses([]);
            setEmployee({name:'',password:'',email:'',role:'',contactNumber:'+880'});
            alert("User registered successfully");
          }
          
    }

    return (
        <div className="register-div">
        <h2 className="register-heading">Register Employee</h2>
        <form action="" className="register-form" onSubmit={handleSubmit}>
            <label className="register-label">Name: </label>
            <input className="register-input" value={employee.name} type="text" name="name" required pattern="[a-zA-Z0-9 ]+" onChange={set('name')}/>
            <br />
            <label className="register-label">Password </label>
            <input className="register-input" value={employee.password} type="text" name="password" min="8" required pattern="[a-zA-Z0-9@#._]+" onChange={set('password')}/>
            <br />
            <label className="register-label">Role: </label>
            <select name="role" value={employee.role} onChange={set('role')} className="register-input">
              <option></option>
              <option value="ROLE_ADMIN" className="register-input">ADMIN</option>
              <option value="ROLE_TRAINER" className="register-input">TRAINER</option>
              <option value="ROLE_TRAINEE" className="register-input">TRAINEE</option>
            </select>
            <br />
            <label className="register-label">Email: </label>
            <input className="register-input" value={employee.email} type="email" name="email" required min={1} onChange={set('email')}></input>
            <br />
            <label className="register-label">Contact Number: </label>
            <input className="register-input" value={employee.contactNumber} onChange={set('contactNumber')}/>
            <br/>
            {employee.role === "ROLE_TRAINER" && 

              <>
              <lable className="register-label">Courses:</lable>
              <AddForm onSubmit={addCourses}/>
              {courses?.map(course=><><text>{course}</text><br/></>)}</>
            }
              
            <input type="submit" className="register-button"></input>
        </form>
        
        </div>
    )
}

export default EmployeeRegister