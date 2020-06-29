import React, { useState, useEffect } from 'react';
import Carousel from 'react-bootstrap/Carousel';
import banner1 from '../images/imagenes banner test/foto lago Cropped.jpg';
import banner2 from '../images/imagenes banner test/foto ruta Cropped.jpg';
import banner3 from '../images/imagenes banner test/foto ruta2 Cropped.jpg';
import banner4 from '../images/imagenes banner test/foto vias Cropped.jpg';

const CarouselBanner = (props) => {
    const [index, setIndex] = useState(0);

    const handleSelect = (selectedIndex, e) => {
      setIndex(selectedIndex);
    };

    return (
        <Carousel activeIndex={index} onSelect={handleSelect}>
            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src={banner4}
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3>First slide label</h3>
                    <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src={banner1}
                    alt="Second slide"
                />
        
                <Carousel.Caption>
                    <h3>Second slide label</h3>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src={banner2}
                    alt="Third slide"
                />
        
                <Carousel.Caption>
                    <h3>Third slide label</h3>
                    <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                </Carousel.Caption>
            </Carousel.Item>
      </Carousel>
    );
}
 
export default CarouselBanner;