import React, {useState, useEffect } from 'react'
import {Card,Button,ListGroup} from 'react-bootstrap';
import './ComponentsCSS/AnswerForm.css';
function AnswerForm(props) {
    const [question,setQuestion] = useState({id: '',marks:'',questionDescription:'',answer:''})
    const [answered,setAnswered] = useState(false);
    const set = (name,value) => {
        setQuestion(oldValues => ({...oldValues, [name]: value }));
    };

    useEffect(() => {
      set('id',props.question.id);
      set('marks',props.question.marks);
      set('questionDescription',props.question.questionDescription);
    },[])

    const addAnswer = (event) =>{
      set('answer',event.target.value);
      setQuestion(oldValues => ({...oldValues, ['answer']: event.target.value }));
    }

    const handleSubmit = (e) => {
      props.onSubmit(question);
      setAnswered(true);
    }

  return (
    <div>
    <Card className="mt-3">
      <Card.Header>{props.index +1}.{props.question.questionDescription} {answered && <text>   (Answered)</text>}</Card.Header>
      <ListGroup variant="flush">
        {props.question.options?.map((option,key) => {
          return(
            <>
            <label>
              <input type="radio" key={key} name={props.question.id} value={option.optionDescription} onChange={addAnswer} className="answer-form-radio-button"/>
              {key+1}.{option.optionDescription}
            </label>
            </>
          )
        })}
      </ListGroup>
      
    </Card>
    <br/>
    <Button className='sm' onClick={handleSubmit}>Submit</Button>
  </div>
  )
}

export default AnswerForm
