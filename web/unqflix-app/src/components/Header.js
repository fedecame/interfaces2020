import React, { useState, useEffect } from 'react';
import {Navbar, Nav, Form, FormControl, Button,} from 'react-bootstrap'
import apiConsumer from "../ApiConsumer"
import logo from "../images/logo.png"
import user from "../images/usuario.png"
import { useHistory } from 'react-router-dom';

const Header = (props) => {

    const history = useHistory();
    const [searchText, setSearchText] = useState("");


    const searchHandler = (event) => {
        event.preventDefault();
        apiConsumer.search(searchText)
            .then(res => {
                console.log("resultado de busqueda: ", res.data) //sacar cuando este lo de las imagenes
            })
            .catch(err => console.error("search error response: ", err.response));
    }

    const handleSearchChange = event => setSearchText(event.target.value);
    const redirectToHome = () => history.push("/")

    return (

        <div>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>
                    <img id="logoHeader" onClick={redirectToHome} src={logo} alt="Logo de Unqflix" />
                </Navbar.Brand>
                <Nav className="mr-auto">
                </Nav>
                <Form inline onSubmit={searchHandler}>
                    <FormControl type="text" value={searchText} onChange={handleSearchChange} placeholder="Search" className="mr-sm-2" />
                    <Button type="submit" variant="outline-info">Search</Button>
                </Form>
                
                <img id="avatarUser" src={user} alt="avatar de usuario"/>
                
            </Navbar>
        </div>

    );
}
 
export default Header;