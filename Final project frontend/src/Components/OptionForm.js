import React from 'react'
import {useState} from 'react';
import './ComponentsCSS/OptionForm.css';
function OptionForm(props) {
  const [option,setOption] = useState();
    const handleChange= (e) => {
      setOption(e.target.value)
    };
    const handleSubmit = (e) =>{
        e.preventDefault();
        props.onSubmit(option);
        setOption('');
    };
  return (
    <div>
        <label>Options for this question: </label>
        <br/>
        <textarea type="textarea" value={option} onChange={handleChange} className="option-form-input"></textarea>
        <br/>
        <button onClick={handleSubmit} className="option-form-button">Add Option</button>
    </div>
  )
}

export default OptionForm
