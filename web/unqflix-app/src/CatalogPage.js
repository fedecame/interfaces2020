import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import apiConsumer from './ApiConsumer';
import './styles/catalog.scss';
import GridGenerator from './components/GridGenerator';
import GridCard from './components/GridCard';

const CatalogPage = (props) => {
    const history = useHistory();
    const [catalog, setCatalog] = useState([]);
    let colAmount = 6;
    if (catalog.length == 0) {
        apiConsumer.getAvailableContent()
        .then(res => {
            console.log("catalog data: ", res.data);
            setCatalog(res.data);
        })
        .then(() => {
            if (catalog.length < colAmount) {
                colAmount = catalog.length;
            }
        })
        .catch(err => console.error("ERROR GET CONTENT: ", err));
    }

    const searchTestHandler = (text) => {
        apiConsumer.search(text)
        .then(res => {
            console.log("search: ", res);
            console.log("search data: ", res.data);
        })
        .catch(err => {
            console.error("search error: ", err.response);
        })
    };

    const getBannersHandler = (text) => {
        apiConsumer.getBanners()
        .then(res => {
            console.log("search: ", res);
            console.log("search data: ", res.data);
        })
        .catch(err => {
            console.error("search error: ", err.response);
        })
    };

    return (
        <>
        {catalog.length > 0 &&
            <GridGenerator colAmount={6}>
                {catalog.map(content => <GridCard key={content.id} content={content}/> )}
            </GridGenerator>
        }
        </>
    );
}
 
export default CatalogPage;