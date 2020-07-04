import React, { useState, useEffect } from 'react';
// import Footer from './components/Footer';
import Header from './components/Header';
import ResponsiveEmbed from 'react-bootstrap/ResponsiveEmbed';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Container from 'react-bootstrap/Container';
import Image from 'react-bootstrap/Image';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
// import './styles/productDetails.scss';
// import {useHistory} from 'react-router-dom';
// import pochoclos from './images/popcorn.png';
import CardsSeasons from './components/CardsSeasons';
import {useParams, useLocation} from 'react-router-dom';
import apiConsumer from './ApiConsumer';
import corazon from './images/corazon.png';
import corazonLleno from './images/corazonFilled.png';
// import Card from 'react-bootstrap/Card';
import './styles/content-page.scss';
import CarouselGeneric from './components/CarouselGeneric';

const ContentPage = () => {
    const {id} = useParams();
    const location = useLocation();
    const [content, setContent] = useState({});
    const [showVideo, setShowVideo] = useState(false);
    const [imageSrc, setImageSrc] = useState("");
    const [favsDetail, setFavsDetail] = useState([]);
    const [isFav, setIsFav] = useState(false);

    useEffect(() => {
        location.state?.imageSrc && setImageSrc(location.state.imageSrc);
        fetchUserFavs();
        fetchSpecificContent();
    }, [location, id]);

    useEffect(() => {
        if (favsDetail.length) {
            setIsFav(favsDetail.some((fav) => fav.id === id));
        }
    }, [favsDetail, id]);

    const fetchSpecificContent = () => {
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
    };

    const fetchUserFavs = () => {
        apiConsumer.getUserContent()
        .then(res => {
            console.log("user content data: ", res.data);
            if (res.data.favorites.length !== favsDetail.length) {
                setFavsDetail(res.data.favorites);
            }
        })
        .catch(err => console.error("ERROR GET USER CONTENT: ", err.response));
    };

    const toggleFavorite = (event) => {
        event.persist();
        apiConsumer.addFav(id)
        .then(() => {
            setIsFav(!isFav);
        })
        .catch(err => console.error(`Error with fav endpoint for ${id}: `, err.response));
    };

    const isSerie = () => id.startsWith('ser_');

    const embedYoutubeUrl = (videoUrl) => {return (videoUrl.replace('watch?v=', 'embed/') + '?autoplay=1')};

    return (
        <>
        <Header/>
        <Modal
            show={showVideo}
            onHide={() => setShowVideo(false)}
            dialogClassName="modal-video-content-page"
            animation={false}
            aria-labelledby="content-video-modal"
            centered
        >
            {!!content.video && <ResponsiveEmbed aspectRatio="16by9">
                <embed title="content-video-modal" type="video/webm" src={embedYoutubeUrl(content.video)}/>
            </ResponsiveEmbed>}
        </Modal>
        <Container fluid className="margin-for-fixed-header bg-dark pt-3">
            <Row>
                <Col xs={12} md={4}>
                    <Image className="image-content-page" src={imageSrc || content.poster} alt={`${content.title} image`}/>
                </Col>
                <Col xs={12} md={8} className="text-light responsive-font-size-content-page">
                    <Row>
                        <Col>
                        <Image src={isFav ? corazonLleno : corazon} width={36} height={36} onClick={toggleFavorite} className="float-right mr-2 heart-content-page"/>
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
                            onClick={() => setShowVideo(true)}
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