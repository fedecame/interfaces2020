import React from 'react';
import Carousel from "react-multi-carousel";
import '../styles/banner-card.scss';
import BannerCard from './BannerCard';

const CarouselBanners = ({banners}) => {
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

    return (
    <Carousel
        responsive={responsive}
        infinite={true}
        autoPlay={true}
        removeArrowOnDeviceType={["mobile"]}
        itemClass="px-2 margin-for-carousel"
    >
        {banners.map(content => (
            <BannerCard key={content.id} content={content}/>
        ))}
    </Carousel>
    );
}
 
export default CarouselBanners;