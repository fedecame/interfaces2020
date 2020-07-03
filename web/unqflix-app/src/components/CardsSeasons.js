import '../styles/productDetails.scss';
import React from 'react';
import {Card, Nav ,ListGroup, ListGroupItem} from 'react-bootstrap'


const CardSeasons = () =>{
return(
<Card bg={'Dark'} text={'white'} id="cardSeasons">
  <Card.Header>
    <Nav variant="tabs" defaultActiveKey="#first">
      <Nav.Item>
        <Nav.Link href="#first">Season 1</Nav.Link>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link href="#link">Season 2</Nav.Link>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link href="#link">Season 3</Nav.Link>
      </Nav.Item>
    </Nav>
  </Card.Header>
  <Card.Body>
  <ListGroup className="list-group-flush">
    <ListGroupItem>Capitulo 1</ListGroupItem>
    <ListGroupItem>Capitulo 2</ListGroupItem>
    <ListGroupItem>Capitulo 3</ListGroupItem>
  </ListGroup>
  </Card.Body>
</Card>
);
}

export default CardSeasons; 