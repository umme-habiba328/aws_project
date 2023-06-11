import {ListGroupItem,ListGroup} from "react-bootstrap"
import {Link} from "react-router-dom";
import "./ComponentsCSS/Options.css"
export default function Options(){
    return(
        <div>
            {window.localStorage.getItem("role") === "ROLE_ADMIN" && <ListGroup className="options-list">
                <Link className="list-group-item list-group-item-action options-list-items" to="/">Home</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/adminProfile">Profile</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/employeeRegister">Register Employee</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/employeeProfile">Employee Profile</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/batchRegister">Create Batch</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/addTraineesToBatch">Add Trainees To Batch</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/courseRegister">Create Course</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/editCourse">Edit Course</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/scheduleTrainingSession">Schedule Training Session</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/allTrainingSession">View Scheduled Training Session</Link>
            </ListGroup>}
            {window.localStorage.getItem("role") === "ROLE_TRAINER" && <ListGroup className="options-list">
                <Link className="list-group-item list-group-item-action options-list-items" to="/">Home</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/trainerProfile">Profile</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/createQuiz">Create Quiz</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/allQuizzes">View all quiz</Link>
            </ListGroup>}
            {window.localStorage.getItem("role") ? "" : <ListGroup className="options-list">
                <Link className="list-group-item list-group-item-action options-list-items" to="/">Home</Link>
                
            </ListGroup>}
            {window.localStorage.getItem("role") === "ROLE_TRAINEE" && <ListGroup className="options-list">
                <Link className="list-group-item list-group-item-action options-list-items" to="/">Home</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/traineeProfile">Profile</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/allScheduledQuiz">Scheduled Quiz</Link>
                <Link className="list-group-item list-group-item-action options-list-items" to="/allTrainees">View All Trainees</Link>
            </ListGroup>}
        </div>
    )
}