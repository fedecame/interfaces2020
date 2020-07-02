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

const CatalogPage = ({colAmount}) => {
    const history = useHistory();
    const location = useLocation();
    const [catalog, setCatalog] = useState([]);
    const [banners, setBanners] = useState([]);
    const [favs, setFavs] = useState([]);
    const [lastSeen, setLastSeen] = useState([]);
    let favsDesdeCat = 0;

    useEffect(() => {
        fetchAvailableContent();
        fetchUserContent();
        fetchBanners();
    }, [location, favsDesdeCat]);

    const fetchAvailableContent = () => {
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
        .catch(err => console.error("ERROR GET AVAILABLE CONTENT: ", err.response));
    };

    const fetchUserContent = () => {
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
        .catch(err => console.error("ERROR GET USER CONTENT: ", err.response));
    };

    const fetchBanners = () => {
        apiConsumer.getBanners()
        .then(res => {
            console.log("banners data: ", res.data);
            setBanners(res.data);
        })
        .catch(err => {
            console.error("Banners request error: ", err.response);
        });
    };

    return (
        <>
        <Header />
        <Container fluid className="margin-for-fixed-header bg-dark">
            <Row className="pt-3">
                <Col>
                {/* <h1>CatalogPage</h1> */}
                {banners.length > 0 && <CarouselBanners banners={banners}/>}
                </Col>
            </Row>
            <Row>
                <Col>
                {favs.length > 0 && <CarouselGeneric carouselType="Favorites" contentList={favs}/>}
                </Col>
            </Row>
            <Row>
                <Col>
                {lastSeen.length > 0 && <CarouselGeneric carouselType="Last Seen" contentList={lastSeen}/>}
                </Col>
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