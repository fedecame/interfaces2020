import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const CatalogPage = (props) => {
    return (
        <Container>
            <Row>
                <Col>
                <h1>CatalogPage</h1>
                <Button variant="dark">Dark</Button>
                </Col>
            </Row>
        </Container>
    );
}
 
export default CatalogPage;