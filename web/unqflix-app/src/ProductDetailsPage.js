import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import Header from './components/Header';
import ResponsiveEmbed from 'react-bootstrap/ResponsiveEmbed'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import './styles/productDetails.scss';
import {useHistory} from 'react-router-dom';
import apiConsumer from './ApiConsumer';
import pochoclos from './images/popcorn.png';
import { Button, Form, Image } from 'react-bootstrap';

function ProductDetailsPage(){
    let history = useHistory();    
return(
    <Container>
        <Col id="pseudoBody">
          <Header/>
            <Row id="containerAll">
                <Row id="containerDetails">
                    <Col xs={6} sm={4} id="containerPoster">
                         <Image src={pochoclos} id="posterImage" thumbnail />
                    </Col>
                    <Col xs={12} sm={8} id="containerDescription">
                        <Row className ="align-items-center" id="descriptionRow">
                            <Col>
                            <h1>Title</h1> <br/>
                            <p>Descripcion</p>
                            </Col>
                        </Row >
                        <Row id="seasonsRow">
                            <h4>Seasons</h4> 
                        </Row>
                    </Col>
                </Row> 
                <Row id="containerCarouselContent">
                    <h4>Contenido Relacionado</h4>
                </Row>
            </Row>
         <Footer/>
        </Col>
    </Container>   

);


}
export default ProductDetailsPage;