import { useState } from "react";
import Container from "react-bootstrap/Container";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import {Navigate} from 'react-router-dom';
import {Link} from "react-router-dom";
import { Button } from "react-bootstrap";
import Logout from './Logout';
import axios from 'axios';
export default function NavbarCustom(){
  

  // const logout = async () => {
  //       const username = window.localStorage.getItem("username");
  //           await axios.get(`/userLogout`,{params: {username:username}},
  //         {headers: {
  //           'Content-Type': 'application/json'
  //         }}).catch(error => {
  //           console.log(error);
  //           alert(error);})
  //           window.localStorage.clear();
  //           window.location.href = "/";
  //     }
      

    return (
      <Navbar bg="dark" variant="dark" expand="md" className="justify-content-center">
      <Container fluid>
        <Navbar.Brand href="/">Training Management System</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbarScroll" />
        <Navbar.Collapse id="navbarScroll">
          <Nav
            className="me-auto my-2 my-lg-0"
            style={{ maxHeight: '100px' }}
            navbarScroll
          >
            </Nav>
            {/* {window.localStorage.getItem("isLogged") === "true" && <Form className="d-flex">
            <FormControl
              type="search"
              placeholder="Search"
              className="me-2"
              aria-label="Search"
            onChange={e => setSearch({val: e.target.value})}/>
            <Button variant="outline-success" onClick={handleSearch}>Search</Button>
          </Form>} */}
          {window.localStorage.getItem("role") === "ROLE_ADMIN" && <text style={{color:"white",marginRight: "10px"}}>User Role: Admin</text>}
          {window.localStorage.getItem("role") === "ROLE_TRAINER" && <text style={{color:"white",marginRight: "10px"}}>User Role: Trainer</text>}
          {window.localStorage.getItem("role") === "ROLE_TRAINEE" && <text style={{color:"white",marginRight: "10px"}}>User Role: Trainee</text>}
          {window.localStorage.getItem("isLogged") === "true" && <Link className="list-group-item" to="/logout">Logout</Link>}
          {window.localStorage.getItem("isLogged") !== "true" && <Link className="list-group-item" to="/login">Login</Link>}
        </Navbar.Collapse>
      </Container>
    </Navbar>
    );
}