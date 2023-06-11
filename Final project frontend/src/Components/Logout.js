import React, { useEffect } from 'react';
import {Navigate} from 'react-router-dom';
import axios from 'axios';
function Logout() {

  useEffect(() => { 
      
        const username = window.localStorage.getItem("username");
          axios.get(`/userLogout`,{params: {username:username}},
          {headers: {
            'Content-Type': 'application/json'
          }}).catch(error => {
            console.log(error);
            alert(error);})
            window.localStorage.clear();
            window.location.href = "/";
      
        // logout();
        // window.localStorage.clear();
        // window.location.href = "/";
      },[]) 

   return (
    <><text>logging out</text></>
   )
}

export default Logout
