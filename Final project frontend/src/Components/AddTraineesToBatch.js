import React, { useEffect, useState } from 'react'
import axios from 'axios';
import Table from 'react-bootstrap/esm/Table';
import TraineeTable from './TraineeTable';
function AddTraineesToBatch() {
    const [batch,setBatch] = useState();
    const [batchName,setBatchName] = useState('');
    const [traineeName,setTraineeName] = useState('');
    const [trainees,setTrainees] = useState([]);

    useEffect(() => {
      { batch != null &&
          axios.get(`/api/batch/allTrainees`,{params: {id : batch.id}},{headers: {
          'Content-Type': 'application/json'
      }}).then((data) => {console.log(data.data);
          setTrainees(data.data);
      }
      ).catch(error => {
          if(error.response.status === 403){
            window.location.href = '/logout'
        }
        else{
          alert(error.response.data.message);
        }}) }
  }, [batch])

    const removeTrainee = (removedTrainee) => {
        setTrainees(trainees => trainees.filter(trainee => trainee.id !== removedTrainee.id));
        const name = removedTrainee.name;
        axios.get(`/api/trainee/removeTraineeBatch`,{params: {name}},{headers: {
            'Content-Type': 'application/json'
        }}).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
    }

    const addTrainee = (trainee) => {
        setTrainees(trainees => ([...trainees,trainee]));
    }

    const handleSearch = async (e) =>{
        e.preventDefault();
        await axios.get(`/api/batch`,{params: {name:batchName}},{headers: {
            'Content-Type': 'application/json'
          }})
          .then((data) => {console.log(data.data);
            setBatch(data.data);
            }
          ).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }});
    }

    const handleTraineeSearch = async (e) => {
        e.preventDefault();
        await axios.get(`/api/trainee/traineeForBatch`,{params: {name:traineeName,batch:batchName}},{headers: {
            'Content-Type': 'application/json'
          }})
          .then((data) => {console.log(data.data);
            addTrainee(data.data);
            setTraineeName('');
            }
          ).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }});
    }

  return (
    <div>
       <form action="" className="search-form" onSubmit={handleSearch}>
            <h3>Search batch by name: </h3>
            <label className="search-label">Name: </label>
            <input className="search-input" type="text" value={batchName} name="name" required onChange={(e) => {setBatchName(e.target.value)}}/>
            <input type="submit" className="search-button" value="Search batch"></input>
            </form>
            <form action="" className="search-form" onSubmit={handleTraineeSearch}>
            <h3>Add trainee by name: </h3>
            <label className="search-label">Name: </label>
            <input className="search-input" type="text" value={traineeName} name="name" required onChange={(e) => {setTraineeName(e.target.value)}}/>
            <input type="submit" className="search-button" value="Add trainee"></input>
            </form>
            <Table className="allTrainee-table" striped hover>
                <thead>
                    <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Course</th>
                    <th></th>
                    </tr>
                </thead>
                <tbody>
                    {trainees ? trainees.map((trainee)=>{
                    return(
                        <><TraineeTable trainee={trainee} onClick={removeTrainee}/>
                        </>
                        )
                    }
                    ): <tr>No trainees in this batch</tr>}
                    
                </tbody>
            </Table>
            
    </div>
  )
}

export default AddTraineesToBatch
