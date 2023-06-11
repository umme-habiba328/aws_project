import React, { useState } from 'react'
import AddForm from './AddForm';
import './ComponentsCSS/BatchRegister.css';
import axios from 'axios';
function BatchRegister() {

    const [batch,setBatch] = useState('');
    const [trainees,setTrainees] = useState([]);

    const addTrainees = (trainee) =>{
        setTrainees(oldTrainees => ([...oldTrainees,trainee]));
    }

    const updateBatch = (event) => {
        setBatch(event.target.value);
    }

    const handleSubmit = async (e)=>{
        e.preventDefault();
        const response = await axios.post(`/api/batch`,JSON.stringify({name: batch,trainees: trainees}),
        {headers: {
            'Content-Type': 'application/json'
        }}).catch(error => {
          if(error.response.status === 403){
            window.location.href = '/logout'
        }
        else{
          alert(error.response.data.message);
        }})

        if(response.status === 201){
            setBatch('');
            setTrainees([]);
            alert("Batch added successfully");
        }
    }

  return (
    <div className='batch-div'>
        <h2>Create Batch:</h2>
      <form className='batch-form' onSubmit={handleSubmit}>
        <label className='batch-form-label'>Batch Name:</label>
        <input type="text" className="batch-form-input" value={batch} onChange={updateBatch}></input>
        {/* <CourseForm onSubmit={addTrainees}></CourseForm>
        {trainees?.map(trainee => {<><text>{trainee}</text><br/></>})} */}
        <br/>
        <input type="submit" className='batch-form-button' value="Create Batch"></input>
      </form>
    </div>
  )
}

export default BatchRegister
