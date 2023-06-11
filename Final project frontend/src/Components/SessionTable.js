import React from 'react'
import CloseButton from 'react-bootstrap/esm/CloseButton'
import EditSession from './EditSession';
import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';
function SessionTable(props) {

  return (
      <tr >
        <td>{props.session.courseTitle}</td>
        <td>{props.session.trainerName}</td>
        <td>{props.session.topic}</td>
        <td>{props.session.status}</td>
        <td>{props.session.date}</td>
        <td>{props.session.startTime}</td>
        <td>{props.session.endTime}</td>
        <td><CloseButton onClick={() => props.onClick(props.session.id)} data-toggle="tooltip"
           data-placement="top" title="Delete"></CloseButton></td>
        <td><Link to={`/editSession/${props.session.id}`} className='btn btn-primary'>Edit</Link></td>
        </tr>
  )
}

export default SessionTable
