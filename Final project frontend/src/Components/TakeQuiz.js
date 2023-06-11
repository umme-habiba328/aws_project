import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom';
import axios from 'axios';
import {Button} from 'react-bootstrap';
import AnswerForm from './AnswerForm';
import './ComponentsCSS/AnswerForm.css';
import TakenQuizInfo from '../TakenQuizInfo';
function TakeQuiz() {

    const current = new Date().toISOString();
    const {id} = useParams();
    const [quiz,setQuiz] = useState({});
    const [questions,setQuestions] = useState([]);
    const [question,setQuestion] = useState({id: '',marks:'',questionDescription:'',answer:''})

    const set = (name,value) => {
        setQuiz(oldValues => ({...oldValues, [name]: value }));
    };

    const addQuestions = (addedQuestion) =>{
        setQuestions(questions => questions.filter(question => question.id !== addedQuestion.id));
        setQuestions(questions => ([...questions,addedQuestion]));
    }

    useEffect(()=>{
        axios.get(`/api/takeQuiz`,{params: {id:id}},{headers: {
            'Content-Type': 'application/json',
          }})
          .then((data) => {setQuiz(data.data);
            console.log(data);
            console.log(current);
          }).catch(error => {
            if(error.response.status === 403){
              window.location.href = '/logout'
          }
          else{
            alert(error.response.data.message);
          }})
    },[])

    const handleSubmit = async (e)=>{
        e.preventDefault();
        console.log(questions);
    const response = await axios.post(`/api/takeQuiz`, JSON.stringify({topic: quiz.topic,id: quiz.id, startTime: current, course:quiz.courseName, questions: questions}),
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
      setQuiz();
      alert("Quiz created successFully");
      window.location.href = '/home'
    }
    }

  return (
    <div className='answerform-div'>
      <h2>Quiz on: {quiz.topic}</h2>
      {quiz.questions instanceof Array && quiz.questions.length > 0 ? quiz.questions.map((question,i) => {
            return(
            <>
            <AnswerForm index={i} question = {question} onSubmit={addQuestions}/>
            <text>{question.answer}</text>
            
            </>)
        }): <TakenQuizInfo quiz={quiz}/>}
        <br/>
        { quiz.questions instanceof Array && <Button className='outline-primary' onClick={handleSubmit}>Finish Quiz</Button>}
    </div>
  )
}

export default TakeQuiz
