import React from 'react';
import Carousel from "react-multi-carousel";
import GridCard from './GridCard';
import '../styles/carouselGeneric.scss';

const CarouselGeneric = ({carouselType, contentList}) => {

    const responsive = {
        ultrawides: {
          breakpoint: { max: 3000, min: 1510 },
          items: 5
        },
        desktop: {
          breakpoint: { max: 1510, min: 1220 },
          items: 4
        },
        notebook: {
          breakpoint: { max: 1220, min: 920 },
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

    return ( 
        <>
        <h3 className="px-2 font-weight-bold mt-3 text-light">{carouselType}</h3>
        <Carousel
            responsive={responsive}
            infinite={true}
            autoPlay={true}
            removeArrowOnDeviceType={["mobile"]}
            itemClass="px-2 margin-for-carousel"
        >
            {contentList.map(content => (
                <GridCard key={content.id} content={content}/>
            ))}
        </Carousel>
        </>
    );
}
 
export default CarouselGeneric;