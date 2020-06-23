import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import './styles/login.scss';
import pochoclos from './images/popcorn.png'
import logo from './images/logo.png'

const LoginPage = (props) => {
    return ( 
        <>
            <div id="container">
                <div id="column">	
                    <img id="popcorn" src={pochoclos} alt="Pochoclos"/>
                </div>
                <div id="columnLog">
                    <div id="containerForm">
                        <div id="containerLogo">
                            <img id="logo"	src={logo} alt="Logo de Unqflix"/>
                            <br/>
                        </div>
                        <form action="">
                            <label >User:</label>
                            <br/>
                            <input type="text" name="user"/>
                            <br/>
                            <label>Password: </label>
                            <br/>
                            <input type="password" name="pass"/>
                            <br/><br/>
                            <button id="botonForm">Login</button>
                            <br/><br/>
                            <Link to="/register">Register</Link>
                        </form>
                    </div>
                </div>
            </div>
            <Footer/>
        </>
    );
}
 
export default LoginPage;