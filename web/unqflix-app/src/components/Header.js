import React, { useState, useEffect } from 'react';
import helado from '../images/helado.png';
import logo from '../images/logo.png';
import '../styles/login.scss';
import { Button, Form, Image } from 'react-bootstrap';

const Header = (props) => {
    return ( 
      <div id="headerSearch">
        <Image src={logo} id="logoHeader" thumbnail />
        <Form.Control type="text" 
                            id="inputSearch"
                            placeholder="Search" />
         <img id="imagenHelado"	src={helado} alt="Helado Napolitano"/>
      </div>
    );
}
 
export default Header;