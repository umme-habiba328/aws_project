import {useState,useEffect} from 'react'
import AllTraineeTable from './AllTraineeTable';
import {useNavigate} from 'react-router';
import axios from 'axios';
import RefreshToken from './RefreshToken';
import Logout from './Logout';
function AllTrainees() {
    const [trainees,setTrainees] = useState();
    //const refresh = RefreshToken();
    const navigate = useNavigate();
    useEffect(() => {
        axios.get(`/api/trainee/allTrainees`,{headers: {
            'Content-Type': 'application/json',
        }}).then((data) => {console.log(data.data);
            setTrainees(data.data);
        }
        ).catch(error => {
            if(error.response.status === 403){
              navigate("/logout");
          }
          else{
            alert(error.response.data.message);
          }})
    },[])
  return (
    <div>
      <AllTraineeTable trainees={trainees}/>
    </div>
  )
}

export default AllTrainees
