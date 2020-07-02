import React, { useState, useEffect } from 'react';
// import Footer from './components/Footer';
import Header from './components/Header';
import ResponsiveEmbed from 'react-bootstrap/ResponsiveEmbed';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image';
import Button from 'react-bootstrap/Button';
// import './styles/productDetails.scss';
// import {useHistory} from 'react-router-dom';
import pochoclos from './images/popcorn.png';
import CardsSeasons from './components/CardsSeasons';
import {useParams, useLocation} from 'react-router-dom';
import apiConsumer from './ApiConsumer';
import corazon from './images/corazon.png';
import corazonLleno from './images/corazonFilled.png';
import Card from 'react-bootstrap/Card';
import './styles/content-page.scss';
import CarouselGeneric from './components/CarouselGeneric';

const ContentPage = () => {
    const {id} = useParams();
    const location = useLocation();
    const [content, setContent] = useState({});

    useEffect(() => {
        console.log(`USE EFFECT DE ${id}`);
        apiConsumer.getContent(id)
        .then(res => {
            console.log("content data: ", res.data);
            setContent(res.data);
        })
        .then(() => {
            apiConsumer.addLastSeen(id)
            .then((res) => {
                console.log("ultimos vistos add: ", res);
            })
            .catch(err => console.error(`Error adding last seen ${id}: `, err));
        })
        .catch(err => console.error(`Error getting content ${id}: `, err.response));
    }, [location, id]);

    const toggleFavorite = (event) => {
        event.persist();
        apiConsumer.addFav(id)
        .then(() => {
            event.target.src = corazonLleno; //despues hacer la logica de que se cambie cuando se lo cliquea de nuevo
        })
        .catch(err => console.error(`Error with fav endpoint for ${id}: `, err.response));
    };

    const isSerie = () => id.startsWith('ser_');

    return (
        <>
        <Header/>
        <Container fluid className="margin-for-fixed-header bg-dark">
            <Row>
                <Col xs={12} sm={4}>
                    <Image style={{maxWidth:"100%", paddingBottom:"2rem"}} src={content.poster} alt={`${content.title} image`}/>
                </Col>
                <Col xs={12} sm={8} className="text-light responsive-font-size-content-page">
                    <Row>
                        <Col>
                        <p className="font-weight-bold">{content.title}</p>
                        <p className="text-justify">{content.description}</p>
                        </Col>
                    </Row>
                    <Row>
                        <Col>
                        {isSerie() ?
                        <CardsSeasons/> 
                        : <Button
                            variant="primary"
                            id="botonForm"
                            className="buttonLoginRegister"
                            onClick={() => console.log("tenga su buen video señor")}
                          >Play</Button>
                        }
                        </Col>
                    </Row>
                </Col>
            </Row>
            <Row>
                <Col>
                {content.relatedContent?.length > 0 && <CarouselGeneric carouselType="Related Content" contentList={content.relatedContent}/>}
                </Col>
            </Row>
        </Container>



        {/* <Header />
        <Container fluid>
            <Col id="pseudoBody">
                <Row id="containerAll">
                    <Row id="containerDetails">
                        <Col xs={6} sm={4} id="containerPoster">
                            <Image src={pochoclos} id="posterImage" thumbnail />
                        </Col>
                        <Col xs={12} sm={8} id="containerDescription">
                            <Row className="align-items-center" id="descriptionRow">
                                <Col>
                                    <h1>{content.title}</h1> <br />
                                    <p>{content.description}</p>
                                </Col>
                                <Col xs={2}>
                                <Image src={corazon} width={40} height={40} onClick={toggleFavorite}></Image>
                                </Col>
                            </Row >
                            <Row id="seasonsRow">
                                <h4>Seasons</h4>
                                <Row id="cardContainer">
                                    <CardsSeasons />
                                </Row>
                            </Row>
                        </Col>
                    </Row>
                    <Row id="containerCarouselContent">
                        <h4>Contenido Relacionado</h4>
                    </Row>

                    <Button 
                        variant="primary"
                        id="botonForm"
                        className="buttonLoginRegister"
                        onClick={() => history.push("/content/:id")}
                    >Play</Button>
                    
                </Row>
                <Row>
                    <Col>
                        <ResponsiveEmbed aspectRatio="16by9">
                            <embed type="video/webm" src="https://www.youtube.com/embed/Kxms-OtUXS0" />
                        </ResponsiveEmbed>
                    </Col>
                </Row>
            </Col>
        </Container> */}
        </>
    );
}

export default ContentPage;