import { Container,Row,Col } from 'react-bootstrap';
import { BrowserRouter as Router,Route, Switch,Routes } from 'react-router-dom';
import Login from "./Login";
import Logout from './Components/Logout';
import Options from './Components/Options';
import NavbarCustom from './Components/NavbarCustom';
import Home from './Home';
import EmployeeProfile from './Components/EmployeeProfile';
import Profile from './Components/Profile';
import AllTrainer from './Components/AllTrainer';
import EmployeeRegister from './Components/EmployeeRegister';
import CourseRegister from './Components/CourseRegister';
import BatchRegister from './Components/BatchRegister';
import AddTraineesToBatch from './Components/AddTraineesToBatch';
import EditCourse from './Components/EditCourse';
import ScheduleTrainingSession from './Components/ScheduleTrainingSession';
import AllTrainingSession from './Components/AllTrainingSession';
import CreateQuiz from './Components/CreateQuiz';
import ProtectedRoutes from './Components/ProtectedRoutes';
import EditSession from './Components/EditSession';
import AllScheduledQuiz from './Components/AllScheduledQuiz';
import TakeQuiz from './Components/TakeQuiz';
import AllTraineesForCourse from './Components/AllTraineesForCourse';
import AllTakenQuiz from './Components/AllTakenQuiz';
import AllQuizForTrainer from './Components/AllQuizForTrainer';
function App() {
  return (
    
    <div>
      <Router>
      <Container>
      <NavbarCustom/>
        <Row>
          <Col md={3}><Options/></Col>
          <Col md={9}>
            <Routes>
            <Route  element = {<ProtectedRoutes forRole={"ROLE_ADMIN"}/>}> 
            <Route path="/adminProfile" element={<Profile/>}></Route>
            <Route path="/employeeProfile" element={<EmployeeProfile/>}></Route>
            <Route path="/courseRegister" element={<CourseRegister/>}></Route>
            <Route path="/editCourse" element={<EditCourse/>}></Route>
            <Route path="/scheduleTrainingSession" element={<ScheduleTrainingSession/>}></Route>
            <Route path="/allTrainingSession" element={<AllTrainingSession/>}></Route>
            <Route path="/batchRegister" element={<BatchRegister/>}></Route>
            <Route path="/addTraineesToBatch" element={<AddTraineesToBatch/>}></Route>
            <Route path="/employeeRegister" element={<EmployeeRegister/>}></Route>
            <Route path='/editSession/:id' element={<EditSession/>}></Route>
            <Route path="/adminHome" element={<Home/>}></Route>
            </Route>
            <Route element={<ProtectedRoutes forRole={"ROLE_TRAINER"}/>}>
            <Route path="/trainerHome" element={<Home/>}></Route>
            <Route path="/trainerProfile" element={<Profile/>}></Route>
            <Route path="/createQuiz" element={<CreateQuiz/>}></Route>
            <Route path="/quizPerformance/:id" element={<AllTakenQuiz/>}></Route>
            <Route path="/allQuizzes" element={<AllQuizForTrainer/>}></Route>
            </Route>
            <Route element={<ProtectedRoutes forRole={"ROLE_TRAINEE"}/>}>
            <Route path="/traineeHome" element={<Home/>}></Route>
            <Route path="/traineeProfile" element={<Profile/>}></Route>
            <Route path="/takeQuiz/:id" element={<TakeQuiz/>}></Route>
            <Route path="/allTrainees" element={<AllTraineesForCourse/>}></Route>
            <Route path="/allScheduledQuiz" element={<AllScheduledQuiz/>}></Route>
            </Route>
            <Route path="/login" element={<Login/>}></Route>
            <Route path="/logout" element={<Logout/>}></Route>
            <Route path="/" element={<Home/>}></Route>
            </Routes>
          </Col>
        </Row>
      </Container>
      </Router>
      </div>
  );
}

export default App;
