import React, { useState, useEffect } from 'react';
import {Navbar, Nav, Form, FormControl, Button,} from 'react-bootstrap'
import apiConsumer from "../ApiConsumer"
import logo from "../images/logo.png"

const Header = (props) => {

    // let history = useHistory();
    const [searchText, setSearchText] = useState("");
     

    const searchHandler = (event) => {
        event.preventDefault();
        apiConsumer.search(searchText)
        // .then(res => (res.status >= 200 && res.status < 300) ? history.push('/') : null)
        .then(res => {
            console.log("resultado de busqueda: ", res.data) //sacar cuando este lo de las imagenes
        })
        .catch(err => console.error("search error response: ", err.response));
    }

    const handleSearchChange = event => setSearchText(event.target.value);



    return ( 
       
       <div>
            <Navbar bg="dark" variant="dark">
                <Navbar.Brand>
                <img id="logoHeader" src={logo} alt="Logo de Unqflix"/>
                </Navbar.Brand>
                <Nav className="mr-auto">
                </Nav>
                <Form inline onSubmit={searchHandler}>
                    <FormControl type="text" value={searchText} onChange={handleSearchChange} placeholder="Search" className="mr-sm-2" />
                    <Button type="submit" variant="outline-info">Search</Button>
                </Form>
            </Navbar>
        </div>
        
    );
}
 
export default Header;