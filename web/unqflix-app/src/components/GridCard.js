import React from 'react';
import Card from 'react-bootstrap/Card';
import Badge from 'react-bootstrap/Badge';
import apiConsumer from '../ApiConsumer';
import noImage from '../images/no-image-available.jpg';
import '../styles/grid-card.scss';
import {useHistory} from 'react-router-dom';

const GridCard = ({content}) => {
    const imageBaseURL = "https://image.tmdb.org/t/p/w500/";
    const history = useHistory();
    let imageValidSrc = content.poster; // usado para pasarla por history.push

    const updateImage = (target, resResults) => {
        const posterPath = resResults.find(elem => elem.poster_path !== null).poster_path;
        imageValidSrc = imageBaseURL + posterPath; // usado para pasarla por history.push
        target.src = imageValidSrc;
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
        history.push(`/content/${contentId}`, {imageSrc: imageValidSrc});
    };

    return (
    <Card key={content.id} className="bg-dark text-white mt-4 custom-grid-card" onClick={() => goToContentPage(content.id)}>
        <Card.Img src={content.poster} onError={event => getNewUrlOrFallbackSrc(event, content)} alt="Card image"/>
        <Card.ImgOverlay>
            <Badge pill variant={content.state ? "success" : "danger"} className="float-right ml-2 mt-1"> {content.state ? "Disponible" : "Indisponible"} </Badge>
            <Card.Title className="font-weight-bold">{content.title}</Card.Title>
            <Card.Text className="grid-card-description">
                {content.description}
            </Card.Text>
        </Card.ImgOverlay>
    </Card>
    );
}
 
export default GridCard;