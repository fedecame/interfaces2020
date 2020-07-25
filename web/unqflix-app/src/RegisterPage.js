import React, { useState } from 'react';
import './styles/login.scss';
import Footer from './components/Footer';
import { useHistory } from 'react-router-dom';
import logo from './images/logo.png';
import apiConsumer from './ApiConsumer';
import { Button, Form, Image } from 'react-bootstrap';
import authSingleton from './Auth';

const RegisterPage = () => {
	const [email, setEmail] = useState("");
	const [username, setUsername] = useState("");
	const [password, setPassword] = useState("");
	const [image, setImage] = useState("");
	const [creditCard, setCreditCard] = useState("");

	const usernameChangeHandler = (event) => { setUsername(event.target.value); }
	const emailChangeHandler = (event) => { setEmail(event.target.value); }
	const passwordChangeHandler = (event) => setPassword(event.target.value);
	const imageChangeHandler = (event) => setImage(event.target.value);
	const creditCardChangeHandler = (event) => { setCreditCard(event.target.value); }
	const history = useHistory();

	const registerHandler = () => {
		apiConsumer.register({
			name: username,
			email,
			password,
			image,
			creditCard
		})
			.then(res => {
				authSingleton.logout();
				authSingleton.login(res.headers.authorization);
				redirectTo("/", {showAlert:"registered"});
			})
			.catch(err => {
				console.error("register error: ", err);
				console.error("register error response: ", err.response);
				redirectTo("/login", {showAlert:true});
			})
	}

	const redirectTo = (path, props = null) => {
		setPassword("");
		setTimeout(() => {history.push(path, props)}, 5);
	}

	return (<>
			<div id="pseudoBodyLogReg">
				<div id="containerLoginRegister">
					<div id="columnIzqLoginRegister">
					</div>
					<div id="columnLoginRegister">
						<div id="containerFormRegister">
							<div id="containerLogoLoginRegister">
								<Image src={logo} id="logoLoginRegister" thumbnail />
								<br />
							</div>
							<Form method="post">
								<Form.Group controlId="formBasicEmail">
									<Form.Label type="email">Email:</Form.Label>
									<Form.Control type="email"
												  className="inputLoginRegister"
												  placeholder="myemail@mail.com"
												  value={email}
												  onChange={emailChangeHandler} required />
									<Form.Label type="text">Name:</Form.Label>
									<Form.Control type="text"
												  className="inputLoginRegister"
												  placeholder="Input your Name"
												  value={username}
												  onChange={usernameChangeHandler} required />
									<Form.Label type="pass">Password:</Form.Label>
									<Form.Control type="password"
												  className="inputLoginRegister"
												  placeholder="Input your Password"
												  value={password}
												  onChange={passwordChangeHandler} required />
									<Form.Label type="url">Imagelink:</Form.Label>
									<Form.Control type="url"
												  className="inputLoginRegister"
												  placeholder="Input your image url"
												  value={image}
												  onChange={imageChangeHandler} required />
									<Form.Label type="text">Credit Card:</Form.Label>
									<Form.Control type="text"
												  className="inputLoginRegister"
												  placeholder="1234 1234 1234 1234"
												  value={creditCard}
												  onChange={creditCardChangeHandler} required />
									<Button variant="danger"
											id="botonForm"
											className="buttonLoginRegister shadow-lg bg-black rounded mt-3"
											onClick={registerHandler}
									>Register</Button>
									<Button variant="danger"
											id="vosSosUnBoton"
											className="buttonLoginRegister shadow-lg bg-black rounded ml-3 mt-3"
											onClick={() => redirectTo("/login")}
									>Back</Button>
								</Form.Group>
							</Form>
						</div>
					</div>
				</div>
				<Footer />
			</div>
		</>
	);
}

export default RegisterPage;