import React, { useState, useEffect } from 'react';
import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";
import GridCard from './GridCard';
import '../styles/carouselGeneric.scss';

const CarouselGeneric = ({carouselType, contentList}) => {

    const contents = [{
        "id": "mov_2",
        "description": "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
        "title": "The Shawshank Redemption",
        "state": false,
        "poster": "https://image.tmdb.org/t/p/w500/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg"
    },
    {
        "id": "mov_3",
        "description": "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge.",
        "title": "The Godfather",
        "state": true,
        "poster": "https://image.tmdb.org/t/p/w500/d4KNaTrltq6bpkFS01pYtyXa09m.jpg"
    },
    {
        "id": "mov_5",
        "description": "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York. In the 1950s, Michael Corleone attempts to expand the family business into Las Vegas, Hollywood and Cuba.",
        "title": "The Godfather: Part II",
        "state": true,
        "poster": "https://image.tmdb.org/t/p/w500/bVq65huQ8vHDd1a4Z37QtuyEvpA.jpg"
    },
    {
        "id": "mov_9",
        "description": "A supernatural tale set on death row in a Southern prison, where gentle giant John Coffey possesses the mysterious power to heal people's ailments. When the cell block's head guard, Paul Edgecomb, recognizes Coffey's miraculous gift, he tries desperately to help stave off the condemned man's execution.",
        "title": "The Green Mile",
        "state": true,
        "poster": "https://image.tmdb.org/t/p/w500/sOHqdY1RnSn6kcfAHKu28jvTebE.jpg"
    },
    {
        "id": "mov_14",
        "description": "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent, Batman sets out to dismantle the remaining criminal organizations that plague the streets. The partnership proves to be effective, but they soon find themselves prey to a reign of chaos unleashed by a rising criminal mastermind known to the terrified citizens of Gotham as the Joker.",
        "title": "The Dark Knight",
        "state": true,
        "poster": "https://image.tmdb.org/t/p/w500/qJ2tW6WMUDux911r6m7haRef0WH.jpg"
    },
    {
        "id": "mov_15",
        "description": "Aragorn is revealed as the heir to the ancient kings as he, Gandalf and the other members of the broken fellowship struggle to save Gondor from Sauron's forces. Meanwhile, Frodo and Sam take the ring closer to the heart of Mordor, the dark lord's realm.",
        "title": "The Lord of the Rings: The Return of the King",
        "state": true,
        "poster": "https://image.tmdb.org/t/p/w500/rCzpDGLbOoPwLjy3OAm5NUPOTrC.jpg"
    },
    {
        "id": "mov_21",
        "description": "While the Civil War rages between the Union and the Confederacy, three men – a quiet loner, a ruthless hit man and a Mexican bandit – comb the American Southwest in search of a strongbox containing $200,000 in stolen gold.",
        "title": "The Good, the Bad and the Ugly",
        "state": false,
        "poster": "https://image.tmdb.org/t/p/w500/wfPHdfofBD5PN96dV96a51B3Ja2.jpg"
    }];

    const responsive = {
        ultrawides: {
          breakpoint: { max: 3000, min: 1750 },
          items: 5
        },
        desktop: {
          breakpoint: { max: 1750, min: 1310 },
          items: 4
        },
        notebook: {
          breakpoint: { max: 1310, min: 880 },
          items: 3
        },
        tablet: {
          breakpoint: { max: 880, min: 464 },
          items: 2
        },
        mobile: {
          breakpoint: { max: 464, min: 0 },
          items: 1
        }
      };

    return ( 
        <>
        <h3 className="px-2">{carouselType}</h3>
        <Carousel
            responsive={responsive}
            infinite={true}
            autoPlay={true}
            removeArrowOnDeviceType={["mobile"]}
            itemClass="px-2 margin-for-carousel"
        >
            {contents.map(content => (
                <GridCard key={content.id} content={content}/>
            ))}
        </Carousel>
        </>
    );
}
 
export default CarouselGeneric;