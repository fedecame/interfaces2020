import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import CarouselBanners from './components/CarouselBanners';
import { useHistory } from 'react-router-dom';

const CatalogPage = (props) => {
    const history = useHistory();
    return (
        <Container>
            <Row>
                <Col>
                <h1>CatalogPage</h1>
                <CarouselBanners/>
                </Col>
            </Row>
            <Row>
                <Button variant="dark" onClick={() => history.push("/login")}>Login</Button>
            </Row>
        </Container>
    );
}
 
export default CatalogPage;