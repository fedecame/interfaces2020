import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import './styles/login.scss';
import pochoclos from './images/popcorn.png'
import logo from './images/logo.png'

const LoginPage = (props) => {
    return ( 
        <div id="pseudoBody">
            <div id="containerLoginRegister">
                <div id="columnIzqLoginRegister">	
                    <img id="popcornImage" src={pochoclos} alt="Pochoclos"/>
                </div>
                <div id="columnLoginRegister">
                    <div id="containerFormLogin">
                        <div id="containerLogoLoginRegister">
                            <img id="logoLoginRegister"	src={logo} alt="Logo de Unqflix"/>
                            <br/>
                        </div>
                        <form action="">
                            <label >User:</label>
                            <br/>
                            <input className="inputLoginRegister" type="text" name="user"/>
                            <br/>
                            <label>Password: </label>
                            <br/>
                            <input className="inputLoginRegister" type="password" name="pass"/>
                            <br/><br/>
                            <button className="buttonLoginRegister" id="botonForm">Login</button>
                            <br/><br/>
                            <Link to="/register" className="anchorLoginRegister">Register</Link>
                        </form>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
}
 
export default LoginPage;