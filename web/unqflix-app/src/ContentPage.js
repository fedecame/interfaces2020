import React, { useState, useEffect } from 'react';
import Footer from './components/Footer';
import Header from './components/Header';
import ResponsiveEmbed from 'react-bootstrap/ResponsiveEmbed'
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';

const ContentPage = (props) => {
    return ( 
        <div id="pseudoBody">
            <Header/>
            <Container fluid>
                <Row>
                    <Col>
                        <ResponsiveEmbed aspectRatio="16by9">
                            <embed type="video/webm" src="https://www.youtube.com/embed/Kxms-OtUXS0" />
                        </ResponsiveEmbed>
                    </Col>
                </Row>
            </Container>
             <Footer/>
        </div>
    );
}
 
export default ContentPage;