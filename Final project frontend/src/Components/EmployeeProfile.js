import {useState,useEffect} from 'react';
import axios from 'axios';
import './ComponentsCSS/Profile.css';
import AddForm from './AddForm';
import CloseButton from 'react-bootstrap/esm/CloseButton';
import { Table } from 'react-bootstrap';
export default function EmployeeProfile(props){
  const [employee, setEmployee] = useState({id:'',name:'',password:'',role:'',email:'',contactNumber:'+880',batch:'',course:''});
  const [courses,setCourses] = useState([])
  const [name,setName] = useState('');

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

      const updateName = (event) =>{
        setName(event.target.value);
      }

      const handleSubmit=((e) =>  {
        e.preventDefault();
        axios.get(`/api/employee/update`,{params: {name}},{headers: {
          'Content-Type': 'application/json',
          'Authorization': window.localStorage.getItem("token")
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
      })

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

        const handleDelete = async (e) =>{
          const id = employee.id;
          const response = await axios.delete(`/api/employee`,{params:{id}},{headers: {
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
            setEmployee({id:'',name:'',password:'',role:'',email:'',contactNumber:'+880',batch:'',course:''});
            setName('');
            alert("Delete successful");
          }
        }

    return(
        <div className="update-div">
           <form action="" className="search-form" onSubmit={handleSubmit}>
            <h3>Search Employee by name: </h3>
            <label className="search-label">Name: </label>
            <input className="search-input" type="text" value={name} name="name" required pattern="[a-zA-Z0-9 ]+" onChange={(e) => setName(e.target.value)}/>
            <input type="submit" className="search-button" value="Search"></input>
            </form>
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
            <form onSubmit={handleDelete}>
              <input type="submit" className='update-button' value="Delete"></input>
            </form>
        </div>
    )
}