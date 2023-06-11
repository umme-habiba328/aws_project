import React from 'react'
import CloseButton from 'react-bootstrap/esm/CloseButton'
function TraineeTable(props) {
    return(
        <tr>
          <td>{props.trainee.id}</td>
          <td>{props.trainee.name}</td>
          <td>{props.trainee.email}</td>
          <td>{props.trainee.courseId}</td>
          
          <td><CloseButton onClick={() => props.onClick(props.trainee)} data-toggle="tooltip"
           data-placement="top" title="Delete"></CloseButton></td>
           
        </tr>
      )
}

export default TraineeTable
