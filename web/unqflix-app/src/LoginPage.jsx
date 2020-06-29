import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import './styles/login.scss';
import pochoclos from './images/popcorn.png';
import logo from './images/logo.png';
import axios from 'axios';
import { login } from './Api';

function Loginpage() {
const [user, setUser] = useState('');
const [password, setPassword] = useState('');
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
                            <input className="inputLoginRegister" 
                            type="text" 
                            name="user"
                            value={user}/>
                            setUser(user)
                            <br/>
                            <label>Password: </label>
                            <br/>
                            <input className="inputLoginRegister"
                            type="password" 
                            name="pass"
                            value={password}
                             />
                            setPassword(password)
                            <br/><br/>
                            <button className="buttonLoginRegister" 
                            id="botonForm"
                            onClick={login}>Login</button>
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