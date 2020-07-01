import React, { useState, useEffect } from 'react';
import Card from 'react-bootstrap/Card';
import Badge from 'react-bootstrap/Badge';
import apiConsumer from '../ApiConsumer';
import noImage from '../images/no-image-available.jpg';
import '../styles/grid-card.scss';

const GridCard = ({content}) => {
    const imageBaseURL = "https://image.tmdb.org/t/p/w500/";

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
                console.error("Movie image error: ", err);
                event.target.src = noImage;
            });
        } else if (content.id.startsWith('ser')) {
            apiConsumer.getSerieImage(content.title)
            .then(res => {
                updateImage(event.target, res.data.results);
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

    return (
    <Card key={content.id} className="bg-dark text-white mt-4">
        <Card.Img src={content.poster} onError={(event) => getNewUrlOrFallbackSrc(event, content)} alt="Card image"/>
        <Card.ImgOverlay>
            <Badge pill variant="success" className="float-right ml-2 mt-1"> Disponible </Badge>
            <Card.Title className="font-weight-bold">{content.title}</Card.Title>
            <Card.Text className="grid-card-description">
                {content.description}
            </Card.Text>
        </Card.ImgOverlay>
    </Card>
    );
}
 
export default GridCard;