import React, { useState, useEffect } from 'react';
import { Navbar, Nav, Form, FormControl, Button, Image } from 'react-bootstrap'
import apiConsumer from "../ApiConsumer"
import '../styles/header.scss';
import logo from "../images/logo.png"
import user from "../images/usuario.png"
import { useHistory } from 'react-router-dom';
import Cookies from 'js-cookie';

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

    const logout = () => {
        Cookies.remove("authToken");
    }

    return (

        <div>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>
                    <Image id="logoHeader" src={logo} alt="Logo de Unqflix" onClick={redirectToHome} />
                </Navbar.Brand>
                <Nav className="mr-auto">
                </Nav>
                <Form inline onSubmit={searchHandler}>
                    <FormControl type="text" value={searchText} onChange={handleSearchChange} placeholder="Search" className="mr-sm-2" />
                    <Button type="submit" variant="outline-info">Search</Button>
                </Form>
                <Image id="avatarUser" src={user} alt="avatar de usuario" onClick={logout} />
            </Navbar>
        </div>

    );
}

export default Header;