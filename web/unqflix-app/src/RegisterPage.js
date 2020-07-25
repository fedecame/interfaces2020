import React, { useState } from 'react';
import './styles/login.scss';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
// import pochoclos from './images/popcorn.png'
import logo from './images/logo.png'
import apiConsumer from './ApiConsumer';
import { Button, Form, Image } from 'react-bootstrap';
import authSingleton from './Auth';
import Alert from 'react-bootstrap/Alert'

const RegisterPage = () => {
<<<<<<< HEAD
	const [username, setUsername] = useState(" ");
	const [email, setEmail] = useState(" ");
	const [password, setPassword] = useState(" ");
	const [image, setImage] = useState(" ");
	const [creditCard, setCreditCard] = useState(" ");
=======
	const [username, setUsername] = useState("");
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");
	const [image, setImage] = useState("");
	const [creditCard, setCreditCard] = useState("");
>>>>>>> 1888452b29d7f7189aa750adb9749803112fbd6f
	const [show, setShow] = useState(false);
	const [errorReg,setErrorReg] =useState(false);



	const usernameChangeHandler = (event) => {setUsername(event.target.value);}
	const emailChangeHandler = (event) => {setEmail(event.target.value);}
	const passwordChangeHandler = (event) => setPassword(event.target.value);
	const imageChangeHandler = (event) => setImage(event.target.value);
	const creditCardChangeHandler = (event) => {setCreditCard(event.target.value);}
	 
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
			setShow(true);
			authSingleton.logout();
			authSingleton.login(res.headers.authorization);
			
		})
		.catch(err => {
			console.error("register error: ", err);
			console.error("register error response: ", err.response);
			setErrorReg(true)
		})
	}

    return ( <>
		{show && <Alert variant="success" onClose={() => setShow(false)} dismissible>
			<Alert.Heading>Welcome!</Alert.Heading>
			<p> Your account has been Registered</p>
		</Alert>}
		{errorReg && <Alert variant="danger" onClose={() => setErrorReg(false)} dismissible>
			<Alert.Heading>Sorry!</Alert.Heading>
			<p> Your account has not been Registered
			</p>
		</Alert>}
		<div id="pseudoBodyLogReg">
			<div id="containerLoginRegister">
				<div id="columnIzqLoginRegister">
				
				</div>
				<div id="columnLoginRegister">
				<div id="containerFormRegister">
					<div id="containerLogoLoginRegister">
				    	<Image src={logo} id="logoLoginRegister" thumbnail />
					     <br/>	
					</div>
					<Form onSubmit={registerHandler} method="post">
					<Form.Group controlId= "formBasicEmail">
					    <Form.Label type="email">Email:</Form.Label>
						<Form.Control type="email" 
                            className="inputLoginRegister"
                            placeholder="myemail@mail.com"
                            value={email}
                            onChange={emailChangeHandler} required/>
						<Form.Label type="text">Name:</Form.Label>
						<Form.Control type="text" 
                            className="inputLoginRegister"
                            placeholder="Input your Name"
                            value={username}
                            onChange={usernameChangeHandler} required/>
						<Form.Label type="pass">Password:</Form.Label>
						<Form.Control type="password" 
                            className="inputLoginRegister"
                            placeholder="Input your Password"
                            value={password}
                            onChange={passwordChangeHandler} required/>
						<Form.Label type="url">Imagelink:</Form.Label>
						<Form.Control type="url" 
                            className="inputLoginRegister"
                            placeholder="Input your image url"
                            value={image}
                            onChange={imageChangeHandler} required/>	
						<Form.Label type="text">Credit Card:</Form.Label>
						<Form.Control type="text" 
							className="inputLoginRegister"
                            placeholder="1234 1234 1234 1234"
                            value={creditCard}
                            onChange={creditCardChangeHandler} required/>
						<Button variant="primary"
                            id="botonForm" className="buttonLoginRegister shadow-lg bg-black rounded"
                            type="submit">Register</Button> <br/>
						<Link to="/login" className="anchorLoginRegister">Back</Link>
						</Form.Group>
					</Form>
				</div>
				</div>
			</div>
			<Footer/>
        </div> </>
    );
}
 
export default RegisterPage;