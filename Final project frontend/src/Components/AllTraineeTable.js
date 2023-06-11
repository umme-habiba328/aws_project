import React from 'react'
import {Table} from 'react-bootstrap';
function AllTraineeTable(props) {
  return (
    
    <div>
        
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
                    {props.trainees ? props.trainees.map((trainee)=>{
                    return(
                        <tr>
                        <td>{trainee.id}</td>
                        <td>{trainee.name}</td>
                        <td>{trainee.email}</td>
                        <td>{trainee.courseId}</td>
                      </tr>
                        )
                    }
                    ): <tr>No trainees in this course</tr>}
                    
                </tbody>
            </Table>
    </div>
  )
}

export default AllTraineeTable
