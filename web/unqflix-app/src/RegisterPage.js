import React, { useState, useEffect } from 'react';
import './styles/login.scss';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import pochoclos from './images/popcorn.png'
import logo from './images/logo.png'

const RegisterPage = (props) => {
    return ( 
		<div id="pseudoBody">
			<div id="containerLoginRegister">
				<div id="columnIzqLoginRegister">
					<img id="popcornImage" src={pochoclos} alt="Pochoclos"/>
				</div>
				<div id="columnLoginRegister">
				<div id="containerFormRegister">
					<div id="containerLogoLoginRegister">
						<img id="logoLoginRegister"	src={logo} alt="Logo de Unqflix"/><br/>
					</div>
					<form action="">
						<label>Email:</label><br/>
						<input className="inputLoginRegister" type="email" name="email" required/>
						<br/>
						<label>Name:</label><br/>
						<input className="inputLoginRegister" type="text" name="user" required/>
						<br/>
						<label>Password: </label><br/>
						<input className="inputLoginRegister" type="password" name="pass" required/>
						<br/>
						<label >Imagelink:</label><br/>
						<input className="inputLoginRegister" type="url" name="image"/>
						<br/>
						<label >Credit Card:</label><br/>
						<input className="inputLoginRegister" type="text" name="creditCard"/>
						<br/><br/>
						<button className="buttonLoginRegister" id="botonForm">Register</button><br/><br/>
						<Link to="/login" className="anchorLoginRegister">Back</Link>
					</form>
				</div>
				</div>
			</div>
			<Footer/>
        </div>
    );
}
 
export default RegisterPage;