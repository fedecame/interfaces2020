import React, { useState, useEffect } from 'react';
import { Navbar, Nav, Form, FormControl, Button, Image } from 'react-bootstrap';
import apiConsumer from "../ApiConsumer";
import '../styles/header.scss';
import logo from "../images/logo.png"
import user from "../images/usuario.png"
import { useHistory } from 'react-router-dom';
import Cookies from 'js-cookie';
import GridGenerator from './GridGenerator';
import GridCard from './GridCard';

const Header = () => {

    const history = useHistory();
    const [searchText, setSearchText] = useState("");
    // const [searchResult, setSearchResult] = useState([]);

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
        <Navbar id="headerUbicacion" bg="light" variant="light" fixed="top">
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
        {/* {
        searchResult.length > 0 &&
            <GridGenerator colAmount={6}>
                {searchResult.map(content => <GridCard key={content.id} content={content} />)}
            </GridGenerator>
        } */}
        </>
    );
}

export default Header;