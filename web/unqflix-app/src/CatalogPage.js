import React, { useState, useEffect } from 'react';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Card from 'react-bootstrap/Card';
import Badge from 'react-bootstrap/Badge'
import CarouselBanners from './components/CarouselBanners';
import { useHistory } from 'react-router-dom';
import apiConsumer from './ApiConsumer';
import './styles/catalog.scss';
import GridGenerator from './components/GridGenerator';
import reactLogo from './images/logo192.png';
import noImage from './images/no-image-available.jpg';

const CatalogPage = (props) => {
    const history = useHistory();
    const [catalog, setCatalog] = useState([]);
    let colAmount = 6;
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
        .catch(err => console.error("ERROR GET CONTENT: ", err));
    }
    let counter = 0;
    const getNewUrlOrFallbackSrc = (event, content) => {
        event.persist();
        if (content.id.startsWith('mov')) {
            apiConsumer.getMovieImage(content.title)
            .then(res => {
                const posterPath = res.data.results.find(elem => elem.poster_path !== null).poster_path;
                event.target.src = `https://image.tmdb.org/t/p/w500/${posterPath}`;
            })
            .catch(err => {
                event.target.src = noImage;
            });
        } else if (content.id.startsWith('ser')) {
            apiConsumer.getSerieImage(content.title)
            .then(res => {
                const posterPath = res.data.results.find(elem => elem.poster_path !== null).poster_path;
                event.target.src = `https://image.tmdb.org/t/p/w500/${posterPath}`;
            })
            .catch(err => {
                console.error("Serie image error: ", err);
                event.target.src = noImage;
            });
        } else {
            console.log("INVALID Content id");
            event.target.src = noImage;
        }
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
        // <Container fluid>
        //     <Row className="justify-content-sm-between">
        //         <Col className="coltest" xs={12} sm={6} lg={4}>
        //             <p>CatalogPage 1</p>
        //         </Col>
        //         <Col className="coltest" xs={12} sm={6} lg={4}>
        //             <p>CatalogPage 2</p>
        //         </Col>
        //         <Col className="coltest" xs={12} sm={6} lg={4}>
        //             <p>CatalogPage 3</p>
        //         </Col>
        //         <Col className="coltest" xs={12} sm={6} lg={4}>
        //             <p>CatalogPage 4</p>
        //         </Col>
        //         <Col className="coltest" xs={12} sm={6} lg={4}>
        //             <p>CatalogPage 5</p>
        //         </Col>
        //     </Row>
        //     <Row>
        //         <Col>
        //             <CarouselBanners/>
        //         </Col>
        //     </Row>
        //     <Row>
        //         <Button variant="dark" onClick={() => history.push("/login")}>Login</Button>
        //     </Row>
        //     <Row>
        //         <Button variant="secondary" onClick={() => searchTestHandler("the")}>Search</Button>
        //     </Row>
        //     <Row>
        //         <Button variant="primary" onClick={() => getBannersHandler()}>Search</Button>
        //     </Row>
        // </Container>

        <>
            {/* <Container fluid>
                <Row>
                    <Col>
                        <CarouselBanners/>
                    </Col>
                </Row>
                <Row className="mt-4 mb-4">
                    <Col>
                        <Button variant="dark" onClick={() => history.push("/login")}>Login</Button>
                    </Col>
                    <Col>
                        <Button variant="secondary" onClick={() => searchTestHandler("the")}>Search</Button>
                    </Col>
                    <Col>
                        <Button variant="primary" onClick={() => getBannersHandler()}>Search</Button>
                    </Col>
                </Row>
                <Row>
                    <Col>
                        {catalog.map((content, index) => (
                            <div key={content.id}>
                            <Card key={content.id} className="bg-dark text-white">
                                <Card.Img src={reactLogo} alt="Card image" />
                                <Card.ImgOverlay>
                                    <Card.Title>Card title</Card.Title>
                                    <Card.Text>
                                    This is a wider card with supporting text below as a natural lead-in to
                                    additional content. This content is a little bit longer.
                                    </Card.Text>
                                    <Card.Text>Last updated 3 mins ago</Card.Text>
                                </Card.ImgOverlay>
                            </Card>
                            </div>
                        ))}
                    </Col>
                </Row>
            </Container> */}
            {catalog.length &&
            <GridGenerator colAmount={6}>
                {catalog.map(content => (
                    <Card key={content.id} className="bg-dark text-white mt-4">
                        <Card.Img src={content.poster} onError={(event) => getNewUrlOrFallbackSrc(event, content)} alt="Card image"/>
                        <Card.ImgOverlay>
                            <Badge pill variant="success" className="float-right ml-2 mt-1"> Disponible </Badge>
                            <Card.Title className="font-weight-bold">{content.title}</Card.Title>
                            <Card.Text className="catalog-description">
                                {content.description}
                            </Card.Text>
                            
                            
                        </Card.ImgOverlay>
                    </Card>
                ))}
            </GridGenerator>
            }

        </>
    );
}
 
export default CatalogPage;