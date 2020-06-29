import React, { useState, useEffect } from 'react';
import './styles/login.scss';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import pochoclos from './images/popcorn.png'
import logo from './images/logo.png'
import apiConsumer from './ApiConsumer';
import Cookies from 'js-cookie';

const RegisterPage = (props) => {
	const [username, setUsername] = useState("asd");
	const [email, setEmail] = useState("asd@asd.com");
	const [password, setPassword] = useState("asd");
	const [image, setImage] = useState("http://asd.jpg");
	const [creditCard, setCreditCard] = useState("123");

	const userRegister = {
		"name": "Edward Elric",
		"email": "edwardElric@gmail.com",
		"password": "philosopherStone",
		"image": "https://a.wattpad.com/cover/83879595-352-k192548.jpg",
		"creditCard": "4444 3333 2222 1111"
	}

	const usernameChangeHandler = (event) => setUsername(event.target.value);
	const emailChangeHandler = (event) => setEmail(event.target.value);
	const passwordChangeHandler = (event) => setPassword(event.target.value);
	const imageChangeHandler = (event) => setImage(event.target.value);
	const creditCardChangeHandler = (event) => setCreditCard(event.target.value);

	const registerHandler = (event) => {
		event.preventDefault();
		apiConsumer.register({
			name: username,
			email,
			password,
			image,
			creditCard
		})
		.then(res => {
			Cookies.set('authToken', res.headers.authorization);
			apiConsumer.updateAuthToken();
		})
		.catch(err => {
			console.error("register error: ", err);
			console.error("register error response: ", err.response);
		})
	}

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
					<form onSubmit={registerHandler} method="post">
						<label htmlFor="email">Email:</label><br/>
						<input className="inputLoginRegister" type="email" name="email" value={email} onChange={emailChangeHandler} required/>
						<br/>
						<label htmlFor="user">Name:</label><br/>
						<input className="inputLoginRegister" type="text" name="user" value={username} onChange={usernameChangeHandler} required/>
						<br/>
						<label htmlFor="pass">Password: </label><br/>
						<input className="inputLoginRegister" type="password" name="pass" value={password} onChange={passwordChangeHandler} required/>
						<br/>
						<label htmlFor="image">Imagelink:</label><br/>
						<input className="inputLoginRegister" type="url" name="image" value={image} onChange={imageChangeHandler} required/>
						<br/>
						<label htmlFor="creditCard">Credit Card:</label><br/>
						<input className="inputLoginRegister" type="text" name="creditCard" value={creditCard} onChange={creditCardChangeHandler} required/>
						<br/><br/>
						<button className="buttonLoginRegister" id="botonForm" type="submit">Register</button><br/><br/>
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