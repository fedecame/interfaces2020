import React from 'react';
// import './App.css';
import CatalogPage from './CatalogPage';
import RegisterPage from './RegisterPage';
import LoginPage from './LoginPage';
import ContentPage from './ContentPage';
import SearchResultsPage from './SearchResultsPage';
import ProductDetailsPage from './ProductDetailsPage'
import NotFoundPage from './NotFoundPage';

import {
  BrowserRouter,
  Switch,
  Route,
  Link,
  Redirect
} from 'react-router-dom';

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={CatalogPage}/>
        <Route path="/register" component={RegisterPage}/>
        <Route path="/login" component={LoginPage}/>
        <Route path="/content/:id" component={ContentPage}/>
        <Route path="/details" component={ProductDetailsPage}/>
        <Route path="/search" component={SearchResultsPage}/>
       
        <Route path="*" component={NotFoundPage}/>
      </Switch>
    </BrowserRouter>
  )
}

export default App;
