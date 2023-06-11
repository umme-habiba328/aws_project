import React from 'react'
import CloseButton from 'react-bootstrap/CloseButton';
function TopicTable(props) {
  return (
    <tr>
          <td>{props.topic}</td>
          <td><CloseButton onClick={() => props.onClick(props.topic)} data-toggle="tooltip"
           data-placement="top" title="Delete"></CloseButton></td>
           
        </tr>
  )
}

export default TopicTable
