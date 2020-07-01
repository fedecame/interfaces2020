import React, { useState, useEffect } from 'react';
// import './App.css';
import CatalogPage from './CatalogPage';
import RegisterPage from './RegisterPage';
import LoginPage from './LoginPage';
import ContentPage from './ContentPage';
import SearchResultsPage from './SearchResultsPage';
import ProductSerieDetailsPage from './ProductSerieDetailsPage'
import ProductMovieDetailsPage from './ProductMovieDetailsPage'
import NotFoundPage from './NotFoundPage';
import authSingleton from './Auth';

import {
  BrowserRouter,
  Switch,
  Route,
  Redirect
} from 'react-router-dom';

function App() {
  const [favs, setFavs] = useState([]);
  const [lastSeen, setLastSeen] = useState([]);

  function PrivateRoute({ children, ...rest }) {
    return (
      <Route
        {...rest}
        render={({ location }) =>
          authSingleton.isAuthenticated() ? (
            children
          ) : (
            <Redirect
              to={{
                pathname: "/login",
                state: { from: location }
              }}
            />
          )
        }
      />
    );
  }

  return (
    <BrowserRouter>
      <Switch>
        <Route path="/register" component={RegisterPage}/>
        <Route path="/login" component={LoginPage}/>
        <PrivateRoute exact path="/">
          <CatalogPage colAmount={6} favsState={[favs, setFavs]} lastSeenState={[lastSeen, setLastSeen]}/>
        </PrivateRoute>
        <PrivateRoute path="/content/:id">
          <ContentPage />
        </PrivateRoute>
        <PrivateRoute path="/detailsSerie">
          <ProductSerieDetailsPage />
        </PrivateRoute>
        <PrivateRoute path="/detailsMovie">
          <ProductMovieDetailsPage />
        </PrivateRoute>
        <PrivateRoute path="/search">
          <SearchResultsPage />
        </PrivateRoute>
        <Route path="*" component={NotFoundPage}/>
      </Switch>
    </BrowserRouter>
  )
}

export default App;
