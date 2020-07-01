import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import Header from './components/Header';
import ResponsiveEmbed from 'react-bootstrap/ResponsiveEmbed';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import { Button, Form, Image } from 'react-bootstrap';
import './styles/productDetails.scss';
import {useHistory} from 'react-router-dom';
import pochoclos from './images/popcorn.png';
import CardsSeasons from './components/CardsSeasons';

const ContentPage = (props) => {
    return (
        // <div id="pseudoBody">
        <>
        <Header />
        <Container fluid>
            <Col id="pseudoBody">
                <Row id="containerAll">
                    <Row id="containerDetails">
                        <Col xs={6} sm={4} id="containerPoster">
                            <Image src={pochoclos} id="posterImage" thumbnail />
                        </Col>
                        <Col xs={12} sm={8} id="containerDescription">
                            <Row className="align-items-center" id="descriptionRow">
                                <Col>
                                    <h1>Title</h1> <br />
                                    <p>Descripcion</p>
                                </Col>
                            </Row >
                            <Row id="seasonsRow">
                                <h4>Seasons</h4>
                                <Row id="cardContainer">
                                    <CardsSeasons />
                                </Row>
                            </Row>
                        </Col>
                    </Row>
                    <Row id="containerCarouselContent">
                        <h4>Contenido Relacionado</h4>
                    </Row>

                    {/* <Button 
                        variant="primary"
                        id="botonForm"
                        className="buttonLoginRegister"
                        onClick={() => history.push("/content/:id")}
                    >Play</Button> */}
                    
                </Row>
                <Row>
                    <Col>
                        <ResponsiveEmbed aspectRatio="16by9">
                            <embed type="video/webm" src="https://www.youtube.com/embed/Kxms-OtUXS0" />
                        </ResponsiveEmbed>
                    </Col>
                </Row>
            </Col>
        </Container>
        {/* <Footer /> */}
        </>
        // </div>
    );
}

export default ContentPage;