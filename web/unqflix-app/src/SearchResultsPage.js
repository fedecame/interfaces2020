import React, { useState, useEffect } from 'react';
// import CarouselBanners from './components/CarouselBanners';
import Header from './components/Header';
import GridGenerator from './components/GridGenerator';
import GridCard from './components/GridCard';
import apiConsumer from "./ApiConsumer";
import { useLocation } from 'react-router-dom';

const SearchResultsPage = () => {
    const location = useLocation();
    const [searchResult, setSearchResult] = useState([]);

    useEffect(() => {
        const queryParams = new URLSearchParams(location.search);
        fetchSearchResult(queryParams.get("text"));
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