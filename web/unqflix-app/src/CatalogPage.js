import React, { useState, useEffect } from 'react';
import { useHistory, useLocation } from 'react-router-dom';
import apiConsumer from './ApiConsumer';
import './styles/catalog.scss';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import CarouselBanners from './components/CarouselBanners';
import GridGenerator from './components/GridGenerator';
import GridCard from './components/GridCard';
import Header from './components/Header';
import CarouselGeneric from './components/CarouselGeneric';

const CatalogPage = ({colAmount, favsState, lastSeenState}) => {
    const history = useHistory();
    const [catalog, setCatalog] = useState([]);
    const [favs, setFavs] = favsState;
    const [lastSeen, setLastSeen] = lastSeenState;

    if (catalog.length == 0) {
        apiConsumer.getAvailableContent()
        .then(res => {
            console.log("catalog data: ", res.data);
            setCatalog(res.data);
        })
        .then(() => {
            if (catalog.length < colAmount) {
                colAmount = catalog.length;
            }
        })
        .catch(err => console.error("ERROR GET AVAILABLE CONTENT: ", err));
    }

    if (favs.length == 0 || lastSeen.length == 0) {
        apiConsumer.getUserContent()
        .then(res => {
            console.log("user content data: ", res.data);
            if (res.data.favorites.length !== favs.length) {
                setFavs(res.data.favorites);
            }
            if (res.data.lastSeen.length !== lastSeen.length) {
                setLastSeen(res.data.lastSeen);
            }
        })
        .catch(err => console.error("ERROR GET USER CONTENT: ", err));
    }

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
        <>
        <Header />
        <Container fluid>
            <Row>
                <Col>
                <h1>CatalogPage</h1>
                {/* <CarouselBanners/> */}
                {favs.length > 0 && <CarouselGeneric carouselType="Favorites" contentList={favs}/>}
                {lastSeen.length > 0 && <CarouselGeneric carouselType="Last Seen" contentList={lastSeen}/>}
                </Col>
            </Row>
            <Row>
                <Button variant="dark" onClick={() => history.push("/login")}>Login</Button>
            </Row>
        
            <Row>
                <Button variant="secondary" onClick={() => searchTestHandler("the")}>Search</Button>
            </Row>
            <Row>
                <Button variant="primary" onClick={() => getBannersHandler()}>Search</Button>
            </Row>
            <Row>
                <Button variant="primary" onClick={() => history.push("/detailsSerie")}>Detalle Serie</Button>
            </Row>
            <Row>
                <Button variant="primary" onClick={() => history.push("/detailsMovie")}>Detalle Movie</Button>
            </Row>
        </Container>
        {catalog.length > 0 &&
            <GridGenerator colAmount={colAmount}>
                {catalog.map(content => <GridCard key={content.id} content={content}/> )}
            </GridGenerator>
        }
        </>
    );
}
 
export default CatalogPage;