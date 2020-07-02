import React from 'react';
import { Button, Image, Row, Col, Container } from 'react-bootstrap';
import notFound from './images/notFound.png';
import './styles/notFoundPage.scss';

const NotFoundPage = () => {
    return ( 
        <Container id="notFoundContainer">
        <Row>
          <Col id="notFound"> 
          <Image src={notFound} id="notFound" thumbnail />
          </Col>
        </Row>
      </Container>
    );
}
 
export default NotFoundPage;