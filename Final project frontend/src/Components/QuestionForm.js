import React, { useState } from 'react'
import OptionForm from './OptionForm';
import './ComponentsCSS/QuestionForm.css';
import { CloseButton } from 'react-bootstrap';
function QuestionForm(props) {
    const [options,setOptions] = useState([]);
    const [question,setQuestion] = useState({marks:'',questionDescription:'',options:[],answer:''})
    
    const set = name => {
      return ({ target: { value } }) => {
        setQuestion(oldValues => ({...oldValues, [name]: value }));
      }
    };

    const addAnswer = (option) =>{
      console.log(option);
      setQuestion(oldValues => ({...oldValues, ['answer']: option }));
    }

    const removeOption = (removedOption) => {
      setOptions(options => options.filter(option => option !== removedOption));
    }

    const addOptions = (option) => {
      setOptions(options => ([...options,option]))
    }

    const handleSubmit = (e) => {
      e.preventDefault();
      question.options = options;
      props.onSubmit(question);
      setQuestion({marks:'',questionDescription:'',options:[],answer:''})
      setOptions([]);
    }

  return (
    <div className='question-form-div'>
      <h3>Input question for quiz</h3>
        <label className='question-form-label'>Question Description</label>
        <textarea className='question-form-input' value={question.questionDescription} onChange={set('questionDescription')}></textarea>
        <label className='question-form-label'>Marks for question: </label>
        <input className='question-form-input' type="number" value={question.marks} onChange={set('marks')}></input>
        
        <OptionForm onSubmit={addOptions}/>
        
        <br/>
        {options?.map((option) => {
          return(
            <><label>
            <input
              type="radio"
              name="react-tips"
              value={option}
              onChange={() => addAnswer(option)}
              className="form-check-input"
            />
            {option}
          </label>
            <CloseButton className='question-closebutton' onClick={() => removeOption(option)} data-toggle="tooltip"
             data-placement="top" title="Delete"></CloseButton>
             <br/>
            </>
          )
        })}

        {question.answer && <text>Answer for this question: {question.answer}</text>}
        <button onClick={handleSubmit} className="question-form-button">Add question</button>
    </div>
  )
}

export default QuestionForm
