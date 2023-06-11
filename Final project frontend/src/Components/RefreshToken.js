import React from 'react';
import axios from 'axios';
function RefreshToken() {

    const refresh = async () =>{
        await axios.post(`/refreshToken`,
          {headers: {
            'Content-Type': 'application/json'
          }}).catch(error => {
            console.log(error);
            alert(error.response.data.message);})
      }
  return refresh;
}

export default RefreshToken
