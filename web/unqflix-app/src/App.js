import React from 'react';
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
        <Route path="/register">
          <RegisterPage/>
        </Route>
        <Route path="/login">
          <LoginPage/>
        </Route>
        <PrivateRoute exact path="/">
          <CatalogPage colAmount={6}/>
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
