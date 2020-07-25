import React, { useState } from 'react';
import Footer from './components/Footer';
import {Link} from 'react-router-dom';
import './styles/login.scss';
// import pochoclos from './images/popcorn.png';
import logo from './images/logo.png';
import apiConsumer from './ApiConsumer';
import {useHistory} from 'react-router-dom';
import { Button, Form, Image } from 'react-bootstrap';
import authSingleton from './Auth';
import Alert from 'react-bootstrap/Alert'

function LoginPage() {
    let history = useHistory();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState('');
    const [show, setShow] = useState(false);

    const loginHandler = (event) => {
        event.preventDefault();
        apiConsumer.login({email, password})
        .then(res => {
            authSingleton.logout(); // es necesario?
            authSingleton.login(res.headers.authorization);
            if (res.status >= 200 && res.status < 300){
                history.push('/');
            }
        })
        .catch(err => {console.error("login error response: ", err.response); setShow(true)});
    }

    const handlePasswordChange = (event) => {setPassword(event.target.value); setShow(false)}
    const handleEmailChange = (event) => {setEmail(event.target.value); setShow(false)}

    return (<>
        {show && <Alert  variant="danger" onClose={() => setShow(false)} dismissible>
            <Alert.Heading>Error!</Alert.Heading>
            <p> Password or User incorrect
            </p>
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
                        <Form onSubmit={loginHandler} method="post">
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
                            <br/><br/>
                            <Button variant="primary"
                            id="botonForm"
                                    className="buttonLoginRegister shadow-lg bg-black rounded"
                            type="submit">Login</Button>
                            <br/><br/>
                            <Link to="/register" className="anchorLoginRegister">Register</Link>
                        </Form.Group>
                        </Form>
                    </div>
                </div>
            </div>
            <Footer/>
        </div></>
    );
}

export default LoginPage;