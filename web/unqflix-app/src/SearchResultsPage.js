import React, { useState, useEffect } from 'react';
// import CarouselBanners from './components/CarouselBanners';
import Header from './components/Header';
import GridGenerator from './components/GridGenerator';
import GridCard from './components/GridCard';
import apiConsumer from "./ApiConsumer";
import { useLocation } from 'react-router-dom';
import './styles/catalog.scss';
import Container from 'react-bootstrap/Container';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';

const SearchResultsPage = () => {
    const location = useLocation();
    const [searchResult, setSearchResult] = useState([]);
    const [searchedText, setSearchedText] = useState("");

    useEffect(() => {
        const queryParams = new URLSearchParams(location.search);
        setSearchedText(queryParams.get("text"));
        fetchSearchResult(searchedText);
    }, [location]);

    const fetchSearchResult = searchText => {
        console.log("search text: ", searchText);
        if (searchText) {
            apiConsumer.search(searchText)
            .then(res => {
                console.log("resultado de busqueda: ", res.data);
                setSearchResult(res.data);
            })
            .catch(err => console.error("search error response: ", err.response));
        }
    };

    return (
        <>
            <Header />
            <Container fluid className="bg-dark">
                <Row>
                    <Col>
                    {searchResult.length > 0 && <div><h3 className="px-2 font-weight-bold text-light margin-for-fixed-header bg-dark">{`Searched text: ${searchedText}`}</h3></div>}
                    </Col>
                </Row>
            </Container>
            {
            searchResult.length > 0 &&
                <GridGenerator colAmount={6}>
                    {searchResult.map(content => <GridCard key={content.id} content={content} />)}
                </GridGenerator>
            }
        </>
    );
}
 
export default SearchResultsPage;