import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import CarouselBanners from './components/CarouselBanners';
import { useHistory } from 'react-router-dom';
import apiConsumer from './ApiConsumer';

const CatalogPage = (props) => {
    const history = useHistory();

    const searchTestHandler = (text) => {
        apiConsumer.search(text)
        .then(res => {
            console.log("search: ", res);
            console.log("search data: ", res.data);
        })
        .catch(err => {
            console.error("search error: ", err.response);
        })
    };

    const getBannersHandler = (text) => {
        apiConsumer.getBanners()
        .then(res => {
            console.log("search: ", res);
            console.log("search data: ", res.data);
        })
        .catch(err => {
            console.error("search error: ", err.response);
        })
    };

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
            <Row>
                <Button variant="dark" onClick={() => history.push("/content/:id")}>Content</Button>
            </Row>
           
            <Row>
                <Button variant="secondary" onClick={() => searchTestHandler("the")}>Search</Button>
            </Row>
            <Row>
                <Button variant="primary" onClick={() => getBannersHandler()}>Search</Button>
            </Row>
            <Row>
                <Button variant="primary" onClick={() => history.push("/details")}>Detalle Serie</Button>
            </Row>
        </Container>
    );
}
 
export default CatalogPage;