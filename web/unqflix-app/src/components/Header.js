import React, { useState } from 'react';
import { Navbar, Nav, Form, FormControl, Button, Image } from 'react-bootstrap';
import '../styles/header.scss';
import logo from "../images/logo.png"
import user from "../images/usuario.png"
import { useHistory } from 'react-router-dom';
import Cookies from 'js-cookie';

const Header = () => {
    const history = useHistory();
    const [searchText, setSearchText] = useState("");

    const searchHandler = (event) => {
        event.preventDefault();
        history.push(`/search?text=${searchText}`);
    }

    const handleSearchChange = event => setSearchText(event.target.value);

    const redirectToHome = () => history.push("/");

    const logout = () => {
        Cookies.remove("authToken");
        history.push("/login");
    }

    return (
        <>
        <Navbar id="headerUbicacion" bg="light" variant="light" fixed="top" expand="sm">
            <Navbar.Brand>
                <Image id="logoHeader" src={logo} alt="Logo de Unqflix" onClick={redirectToHome} />
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
            </Nav>
            <Form inline onSubmit={searchHandler}>
                <FormControl type="text" value={searchText} onChange={handleSearchChange} placeholder="Search" className="mr-sm-2" />
                <Button type="submit" variant="outline-info">Search</Button>
                <Image id="avatarUser" src={user} alt="avatar de usuario" onClick={logout} />
            </Form>
            </Navbar.Collapse>
        </Navbar>
        </>
    );
}

export default Header;