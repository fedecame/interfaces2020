import React, { useState, useEffect } from 'react';
import './styles/login.scss';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import pochoclos from './images/popcorn.png'
import logo from './images/logo.png'

const RegisterPage = (props) => {
    return ( 
        <>
        <div id="container">
		    <div id="column">
                <img id="popcorn" src={pochoclos} alt="Pochoclos"/>
            </div>
		    <div id="columnLog">
			<div id="containerFormRegister">
				<div id="containerLogo">
                    <img id="logo"	src={logo} alt="Logo de Unqflix"/><br/>
                </div>
				<form action="">
                    <label>Email:</label><br/>
                    <input type="email" name="email" required/>
                    <br/>
					<label>Name:</label><br/>
					<input type="text" name="user" required/>
					<br/>
					<label>Password: </label><br/>
					<input type="password" name="pass" required/>
                    <br/>
                    <label >Imagelink:</label><br/>
					<input type="url" name="image"/>
                    <br/>
                    <label >Credit Card:</label><br/>
					<input type="text" name="creditCard"/>
					<br/><br/>
					<button id="botonForm">Register</button><br/><br/>
					<Link to="/login">Back</Link>
				</form>
			</div>
		    </div>
	    </div>
        <Footer/>
        </>
    );
}
 
export default RegisterPage;