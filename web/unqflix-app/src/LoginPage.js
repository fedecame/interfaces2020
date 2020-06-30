import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import {Link, useHistory} from 'react-router-dom';
import './styles/login.scss';
import pochoclos from './images/popcorn.png';
import logo from './images/logo.png';
import apiConsumer from './ApiConsumer';
import Cookies from 'js-cookie';

const LoginPage = () => {
    let history = useHistory();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState('');

    const loginHandler = (event) => {
        event.preventDefault();
        apiConsumer.login({email, password})
        .then(res => {
            Cookies.remove('authToken');
			Cookies.set('authToken', res.headers.authorization);
			apiConsumer.updateAuthToken();
            if (res.status >= 200 && res.status < 300){
                history.push('/');
            }
        })
        .catch(err => console.error("login error response: ", err.response));
    }

    const handlePasswordChange = (event) => setPassword(event.target.value);
    const handleEmailChange = (event) => setEmail(event.target.value);

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
                        <form onSubmit={loginHandler} method="post">
                            <label htmlFor="email">Email:</label>
                            <br/>
                            <input className="inputLoginRegister" 
                            type="email" 
                            name="email"
                            value={email}
                            onChange={handleEmailChange}/>
                            <br/>
                            <label htmlFor="pass">Password: </label>
                            <br/>
                            <input className="inputLoginRegister"
                            type="password" 
                            name="pass"
                            value={password}
                            onChange={handlePasswordChange}/>
                            <br/><br/>
                            <button className="buttonLoginRegister" 
                            id="botonForm"
                            type="submit">Login</button>
                            <br/><br/>
                            <Link to="/register" className="anchorLoginRegister">Register</Link>
                        </form>
                    </div>
                </div>
            </div>
            <Footer/>
        </div>
    );
};

export default LoginPage;