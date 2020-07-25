import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import './styles/login.scss';
import logo from './images/logo.png';
import apiConsumer from './ApiConsumer';
import { useHistory, useLocation } from 'react-router-dom';
import { Button, Form, Image } from 'react-bootstrap';
import authSingleton from './Auth';
import Alert from 'react-bootstrap/Alert'

function LoginPage() {
    const history = useHistory();
    const location = useLocation();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [showLoginError, setShowLoginError] = useState(false);
    const [showRegisterError, setShowRegisterError] = useState(false);

    const loginHandler = (event) => {
        event.preventDefault();
        apiConsumer.login({email, password})
            .then(res => {
                authSingleton.logout(); // es necesario?
                authSingleton.login(res.headers.authorization);
                if (res.status >= 200 && res.status < 300){
                    redirectTo("/", {showAlert:"logged in"});
                }
            })
            .catch(err => {console.error("login error response: ", err.response); setShowLoginError(true)});
    }

    useEffect(() => {
        location.state?.showAlert && setShowRegisterError(location.state.showAlert);
    }, []);

    const handlePasswordChange = (event) => {setPassword(event.target.value); setShowLoginError(false); setShowRegisterError(false);}
    const handleEmailChange = (event) => {setEmail(event.target.value); setShowLoginError(false); setShowRegisterError(false);}

    const redirectTo = (path, props = null) => {
        setPassword("");
        setTimeout(() => {history.push(path, props)}, 5);
    }

    return (
        <>
            {showLoginError && <Alert  variant="danger" onClose={() => setShowLoginError(false)} dismissible>
                <Alert.Heading>Error!</Alert.Heading>
                <p>Password or User incorrect</p>
            </Alert>}
            {showRegisterError && <Alert  variant="danger" onClose={() => setShowRegisterError(false)} dismissible>
                <Alert.Heading>Sorry!</Alert.Heading>
                <p>Couldn't Register</p>
            </Alert>}
            <div id="pseudoBodyLogReg">
                <div id="containerLoginRegister">
                    <div id="columnIzqLoginRegister">

                    </div>
                    <div id="columnLoginRegister">
                        <div id="containerFormLogin">
                            <div id="containerLogoLoginRegister">
                                <Image src={logo} id="logoLoginRegister" thumbnail />
                                <br/>
                            </div>
                            <Form method="post">
                                <Form.Group controlId= "formBasicEmail">
                                    <Form.Label type="email">Email:</Form.Label>
                                    <br/>
                                    <Form.Control type="email"
                                                  className="inputLoginRegister"
                                                  placeholder="myemail@mail.com"
                                                  value={email}
                                                  onChange={handleEmailChange}/>
                                    <br/>
                                    <Form.Label>Password: </Form.Label>
                                    <br/>
                                    <Form.Control
                                        type="password"
                                        className="inputLoginRegister"
                                        value={password}
                                        onChange={handlePasswordChange}/>
                                    <Button variant="danger"
                                            id="botonForm"
                                            className="buttonLoginRegister shadow-lg bg-black rounded mt-3"
                                            onClick={loginHandler}
                                    >Login</Button>
                                    <Button variant="danger"
                                            id="vosSosUnBoton2"
                                            className="buttonLoginRegister shadow-lg bg-black rounded ml-3 mt-3"
                                            onClick={() => redirectTo("/register")}>Register</Button>
                                </Form.Group>
                            </Form>
                        </div>
                    </div>
                </div>
                <Footer/>
            </div>
        </>
    );
}

export default LoginPage;