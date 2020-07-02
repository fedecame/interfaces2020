import React from 'react';
import Carousel from "react-multi-carousel";
import Card from 'react-bootstrap/Card';
import Badge from 'react-bootstrap/Badge';
import apiConsumer from '../ApiConsumer';
import {useHistory} from 'react-router-dom';
import noImage from '../images/no-image-available.jpg';
import '../styles/banner-card.scss';

const CarouselBanners = ({banners}) => {
    const imageBaseURL = "https://image.tmdb.org/t/p/w500/";
    const history = useHistory();

    const responsive = {
        extralarge: {
          breakpoint: { max: 3000, min: 1510 },
          items: 5
        },
        bigdesktop: {
            breakpoint: { max: 1510, min: 1220 },
            items: 4
        },
        notebook: {
            breakpoint: { max: 1220, min: 920},
            items: 3
        },
        tablet: {
          breakpoint: { max: 920, min: 580 },
          items: 2
        },
        mobile: {
          breakpoint: { max: 580, min: 0 },
          items: 1
        }
    };

    const updateImage = (target, resResults) => {
        const posterPath = resResults.find(elem => elem.poster_path !== null).poster_path;
        target.src = imageBaseURL + posterPath;
    }

    const getNewUrlOrFallbackSrc = (event, content) => {
        event.persist();
        if (content.id.startsWith('mov')) {
            apiConsumer.getMovieImage(content.title)
            .then(res => {
                updateImage(event.target, res.data.results);
            })
            .catch(err => {
                console.error("Movie image error: ", err.response);
                event.target.src = noImage;
            });
        } else if (content.id.startsWith('ser')) {
            apiConsumer.getSerieImage(content.title)
            .then(res => {
                updateImage(event.target, res.data.results);
            })
            .catch(err => {
                console.error("Serie image error: ", err.response);
                event.target.src = noImage;
            });
        } else {
            console.log("INVALID Content id");
            event.target.src = noImage;
        }
    };

    const goToContentPage = (contentId) => {
        history.push(`/content/${contentId}`);
    };

    return (
    <Carousel
        responsive={responsive}
        infinite={true}
        autoPlay={true}
        removeArrowOnDeviceType={["mobile"]}
        itemClass="px-2 margin-for-carousel"
    >
        {banners.map(content => (
            <Card border="dark" className="bg-dark text-white mt-4" key={content.id}>
            <Card.Img className="custom-banner-image" variant="top" src={content.poster} onError={event => getNewUrlOrFallbackSrc(event, content)} onClick={() => goToContentPage(content.id)} alt="Card image" />
            <Card.Body>
            <Badge pill variant={content.state ? "success" : "danger"} className="float-right ml-2 mt-1"> {content.state ? "Disponible" : "Indisponible"} </Badge>
                <Card.Title className="font-weight-bold">{content.title}</Card.Title>
                <Card.Text className="banner-card-description">{content.description}</Card.Text>
            </Card.Body>
            </Card>
        ))}
    </Carousel>
    );
}
 
export default CarouselBanners;