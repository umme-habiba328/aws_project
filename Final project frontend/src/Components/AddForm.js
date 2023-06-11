import React, { useState } from 'react';
import './ComponentsCSS/CourseForm.css';
const AddForm = (props) => {
    const [input,setInput] = useState();
    const handleChange= (e) => {
        setInput(e.target.value)
    };
    const handleSubmit = (e) =>{
        e.preventDefault();
        props.onSubmit(input);
        setInput('');
    };
  return (
    <div onSubmit={handleSubmit}>
        <input type="text" value={input} onChange={handleChange} className="course-form-input"></input>
        <br/>
        <button onClick={handleSubmit} className="course-form-button">Add</button>
    </div>
  )
}

export default AddForm
