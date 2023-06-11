import { useState, useEffect } from "react";
import "./Components/ComponentsCSS/RegisterForm.css";
import axios from "axios";

export default function Login(){
    const [user, setUser] = useState({name:'',password:''});
    const set = name => {
        return ({ target: { value } }) => {
            setUser(oldValues => ({...oldValues, [name]: value }));
        }
      };

    const handleSubmit= async (e) =>{
        e.preventDefault();
        const res = await axios.post(`/authenticate`,
        JSON.stringify({username:user.name,password:user.password}),{
            headers: {
              'Content-Type': 'application/json'
            }
          }).catch(error => {alert(error.response.data.message)});
        if(res.status === 200){
          
          window.localStorage.setItem("role",res.data.role);
          window.localStorage.setItem("username",res.data.userName);
          window.localStorage.setItem("isLogged","true");
          window.location.href = "/";
        }
    }

    return (
        <div className="register-div">
        <h2 className="register-heading">Login</h2>
        <form action="" className="register-form" onSubmit={handleSubmit}>
            <label className="register-label">Name: </label>
            <input className="register-input" type="text" name="name" required pattern="[a-zA-Z0-9 ]+" onChange={set('name')}/>
            <br />
            <label className="register-label">Password: </label>
            <input className="register-input" type="password" name="password" required pattern="[a-zA-Z0-9@#._]+" onChange={set('password')}/>
            <br/>
            <input type="submit" className="register-button"></input>
        </form>
        </div>
    )
}